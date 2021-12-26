package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.DebitCard;
import entity.payment.PaymentTransaction;
import utils.API;
import utils.MyMap;

/**
 * Call API của NationalBank và trả về kết quả của giao dịch
 * @author NAM.LN183956
 *
 */
public class NationalBankSubsystem implements InterbankInterface {
	
	/**
	 * @see InterbankInterface#payOrder(entity.payment.DebitCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction payOrder(DebitCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException {
		String responseText = null;
		try {
			responseText = API.post("https://61c45a94f1af4a0017d994ce.mockapi.io/api/v1/pay-order", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		MyMap response = null;
		try {
			response = MyMap.toMyMap(responseText, 0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnrecognizedException();
		}

		PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
				(String) response.get("transactionId"), (String) response.get("transactionContent"),
				Integer.parseInt((String) response.get("amount")), (String) response.get("createdAt"));
		return trans;
	}

	/**
	 * @see InterbankInterface#refund(entity.payment.DebitCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction refund(DebitCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException {
		return null;
	}

	/**
	 * @see InterbankInterface#payOrder(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException {
		return null;
	}

	/**
	 * @see InterbankInterface#refund(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction refund(CreditCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException {
		return null;
	}

}
