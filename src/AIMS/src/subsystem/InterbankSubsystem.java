package subsystem;

import common.exception.InternalServerErrorException;
import common.exception.InvalidCardException;
import common.exception.NotEnoughBalanceException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.DebitCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author hieud
 *
 */
public class InterbankSubsystem implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController ctrl;
	
	/**
	 * @see InterbankInterface#payOrder(entity.payment.DebitCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction payOrder(DebitCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException {
		PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}
	
	/**
	 * @see InterbankInterface#refund(entity.payment.DebitCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction refund(DebitCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException {
		PaymentTransaction transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		String str = new String();
		this.ctrl = new InterbankSubsystemController();
	}

	/**
	 * @see InterbankInterface#payOrder(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}
}
