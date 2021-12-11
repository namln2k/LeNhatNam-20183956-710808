package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test class for function validatePhoneNumber()
 * 
 * @author NAM.LN183956
 *
 */
class ValidatePhoneNumberTest {

	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({ "0123456789, true", "01234, false", "abc123, false", "1234567890, false" })
	void testValidatePhoneNumber(String phone, boolean expected) {
		boolean isValid = placeOrderController.validatePhoneNumber(phone);

		assertEquals(expected, isValid);
	}
}
