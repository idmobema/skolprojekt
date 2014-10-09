
public class Money {
	private int amount;
	private String currency;
	
	public Money(int amount, String currency) {
		if (amount < 0)
			throw new IllegalArgumentException("Invalid amount: " + amount + ". Value must not be negative.");
		if (currency.length() != 3)
			throw new IllegalArgumentException("Invalid currency acronym.");
		this.amount = amount;
		this.currency = currency;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public Money plus(Money addend) {
		return new Money(amount + addend.getAmount(), currency);
	}
	
	public Money minus(Money subtrahend) {
		if (subtrahend.getAmount() > amount)
			throw new IllegalArgumentException("Invalid subtrahend: " + subtrahend + ". Value must not be greater than minuend.");
		return new Money(amount - subtrahend.getAmount(), currency);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Money) {
			Money otherMoney = (Money) other;
			return otherMoney.amount == amount && otherMoney.currency.equals(currency);
		}
		return false;	
	}

	@Override
	public int hashCode() {
		return amount * 1337 + 1337;
	}
	
	@Override
	public String toString() {
		return "" + amount + " " + currency;
	}
}
