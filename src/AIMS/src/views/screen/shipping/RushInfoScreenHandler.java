package views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.PlaceRushOrderController;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.RushInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;

public class RushInfoScreenHandler extends BaseScreenHandler implements Initializable {

	private static Logger LOGGER = Utils.getLogger(InvoiceScreenHandler.class.getName());

	private RushInfo rushInfo;

	@FXML
	private ComboBox<String> supplier;

	@FXML
	private DatePicker deliveryDate;

	public RushInfoScreenHandler(Stage stage, String screenPath, RushInfo rushInfo) throws IOException {
		super(stage, screenPath);
		this.rushInfo = rushInfo;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.supplier.getItems().addAll(Configs.SUPPLIERS);

	}

	@FXML
	void submitRushInfo(MouseEvent event) throws IOException, InterruptedException {

		// add info to messages
		HashMap messages = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		String formattedDate = deliveryDate.getValue().format(formatter);
		messages.put("deliveryDate", formattedDate);
		messages.put("supplier", supplier.getValue());
		
		Order order = rushInfo.getOrder();

		// calculate additional shipping fees
		int additionalFees = getBController().calculateAdditionalFees(order);
		order.setAdditionalFees(additionalFees);
		order.setRushInfo(messages);

		// create invoice screen
		Invoice invoice = getBController().createInvoice(order);
		BaseScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH,
				invoice);
		InvoiceScreenHandler.setPreviousScreen(this);
		InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
		InvoiceScreenHandler.setScreenTitle("Invoice Screen");
		InvoiceScreenHandler.setBController(getBController());
		InvoiceScreenHandler.show();
	}

	public PlaceRushOrderController getBController() {
		return (PlaceRushOrderController) super.getBController();
	}

}
