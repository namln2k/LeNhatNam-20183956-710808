package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import entity.order.Order;
import utils.Configs;

/**
 * Class controller cho use case place rush order
 * 
 * @author NAM.LN183956
 *
 */
public class PlaceRushOrderController extends BaseController {

	/**
	 * Hiển thị form điền thông tin cho rush order
	 * 
	 */
	public void displayRushInfoForm() {
	}

	/**
	 * Kiểm tra thông tin giao hàng nhanh có hợp lệ hay không
	 * 
	 * @param info: Các thông tin cho đơn giao hàng nhanh
	 * @throws InterruptedException
	 * @author NAM.LN183956
	 */
	public void validateRushInfo(HashMap<String, String> info) throws InterruptedException {
		if (!validateDeliveryDate(info.get("date"))) {
			throw new InterruptedException("Ngày giao hàng không hợp lệ");
		}
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
	 * Kiểm tra ngày giao hàng có hợp lệ không
	 * 
	 * @param deliveryDate: ngày giao hàng mong muốn (Kiểu LocalDate)
	 * @return true nếu ngày giao hàng là sau ngày hôm nay
	 * @author NAM.LN183956
	 */
	public boolean validateDeliveryDate(LocalDate deliveryDate) {
		return deliveryDate.isAfter(LocalDate.now());
	}

	/**
	 * Kiểm tra xem tỉnh thành mà khách yêu cầu có support rush order hay không
	 * 
	 * @param province
	 * @return true nếu tên tỉnh thành hợp lệ và có support rush order. Nếu không
	 *         return false
	 * @author NAM.LN183956
	 */
	public boolean validateProvince(String province) {
		// Kiểm tra tên tỉnh thành có hợp lệ hay không
		if (Arrays.asList(Configs.PROVINCES).contains(province)) {

			// Nếu có, kiểm tra tỉnh thành đó có support rush order hay không
//			checkValidateSupport();
			return true;
		}
		return false;
	}

	/**
	 * Tính toán phí gia tăng khi đặt đơn hàng rush order
	 */
	public int calculateAdditionalFees(Order order) {
		// Chưa cài đặt, mặc định return 0
		return 0;
	}

}
