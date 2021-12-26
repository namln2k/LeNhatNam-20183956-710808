package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import entity.order.Order;
import entity.shipping.RushInfo;
import utils.Configs;

public class PlaceRushOrderController extends PlaceOrderController {

	/**
	 * Just for logging purpose
	 */
	private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

	/**
	 * This method takes responsibility for processing the shipping info from user
	 * 
	 * @param info
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void processRushInfo(HashMap info) throws InterruptedException, IOException {
		LOGGER.info("Process Delivery Info");
		LOGGER.info(info.toString());
		validateRushInfo(info);
	}

	/**
	 * Kiểm tra thông tin giao hàng nhanh có hợp lệ hay không
	 * 
	 * @param info: Các thông tin cho đơn giao hàng nhanh
	 * @throws InterruptedException
	 * @author NAM.LN183956
	 */
	public void validateRushInfo(HashMap<String, String> info) throws InterruptedException {
		if (!validateDeliveryDate(info.get("deliveryDate"))) {
			throw new InterruptedException("Ngày giao hàng không hợp lệ");
		}
	}

	/**
	 * Kiểm tra ngày giao hàng có hợp lệ không
	 * 
	 * @param deliveryDate: ngày giao hàng mong muốn (Kiểu Date)
	 * @return true nếu ngày giao hàng là sau ngày hôm nay
	 * @author NAM.LN183956
	 */
	public boolean validateDeliveryDate(Date deliveryDate) {
		return deliveryDate.after(new Date());
	}

	/**
	 * Kiểm tra thông tin ngày giao hàng có hợp lệ không
	 * 
	 * @param deliveryDate: ngày giao hàng mà khách mong muốn
	 * @return true nếu ngày giao hàng có định dạng hợp lệ, và phải sau ngày hôm
	 *         nay. Ngược lại return false
	 * @author NAM.LN183956
	 */
	public boolean validateDeliveryDate(String deliveryDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		boolean isValid;
		try {
			// So sánh ngày giao hàng mong muốn với ngày hôm nay
			Date date = dateFormat.parse(deliveryDate.trim());
			isValid = validateDeliveryDate(date);
		} catch (ParseException ex) {
			return false;
		}
		return isValid;
	}

	/**
	 * Kiểm tra xem nhà cung cấp dịch vụ rush order có hợp lệ hay không
	 * 
	 * @param supplier
	 * @return true nếu tên nhà cung cấp dịch vụ là hợp lệ. Nếu không return false
	 * @author NAM.LN183956
	 */
	public boolean validateSupplier(String supplier) {
		// Kiểm tra tên tỉnh thành có hợp lệ hay không
		if (Arrays.asList(Configs.SUPPLIERS).contains(supplier)) {
			return true;
		}
		return false;
	}
	
	private AdditionalFeesInterface additionalFeesCalculator = new AdditionalFeesController();

	/**
	 * Tính toán phí gia tăng khi đặt đơn hàng rush order
	 */
	public int calculateAdditionalFees(Order order) {
		return additionalFeesCalculator.calculateAdditionalFees(order);
	}
	
}

