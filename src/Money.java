
public class Money {
	private int amount;
	
	public Money(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Money plus(Money addend) {
		return new Money(amount + addend.getAmount());
	}
	
	public Money minus(Money subtrahend) {
		if (subtrahend.getAmount() > amount)
			throw new IllegalArgumentException("Subtrahend must not be greater than minuend.");
		return new Money(amount - subtrahend.getAmount());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Money) {
			Money otherMoney = (Money) other;
			return otherMoney.amount == amount;
		}
		return false;	
	}

	@Override
	public int hashCode() {
		return 1337 * amount;
	}
}
