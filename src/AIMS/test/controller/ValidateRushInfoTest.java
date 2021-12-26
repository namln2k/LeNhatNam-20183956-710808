package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 
 * Test class for function validatePhoneNumber()
 * 
 * @author NAM.LN183956
 *
 */
class ValidateRushInfoTest {

	PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({ "2021-10-20, false", "2021-12-30, true", "2021-12, false", ", false" })
	void testValidateDeliveryDate(String deliveryDate, boolean expected) {
		boolean isValid = placeRushOrderController.validateDeliveryDate(deliveryDate);

		assertEquals(expected, isValid);
	}

	@ParameterizedTest
	@CsvSource({ "Shopee Ship, true", "Loship, false", "Gojek Ship, true", ", false" })
	void testValidateSupplier(String supplier, boolean expected) {
		boolean isValid = placeRushOrderController.validateSupplier(supplier);
		
		assertEquals(expected, isValid);
	}

}
