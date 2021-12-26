package entity.payment;

public class DebitCard {
	private String issuingBank;
	private String cardNumber;
	private String validFrom;
	private String cardHolder;

	public DebitCard(String issuingBank, String cardNumber, String validFrom, String cardHolder) {
		super();
		this.issuingBank = issuingBank;
		this.cardNumber = cardNumber;
		this.validFrom = validFrom;
		this.cardHolder = cardHolder;
	}
}


