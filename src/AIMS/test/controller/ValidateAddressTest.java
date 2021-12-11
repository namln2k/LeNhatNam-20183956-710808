package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Test class for function validateAddress()
 * 
 * @author NAM.LN183956
 *
 */
class ValidateAddressTest {

	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}

	@ParameterizedTest
	@CsvSource({ "hanoi, true", "so 15 Hai Ba Trung Hanoi, true", "$#Hanoi, false", ", false", "Hai Ba Trung, true" })
	void testValidateAddress(String address, boolean expected) {
		boolean isValid = placeOrderController.validateAddress(address);
		assertEquals(expected, isValid);
	}

}
