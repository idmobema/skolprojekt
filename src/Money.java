
public class Money {
	private int amount;
	
	public Money(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	// Should return new Money object, objects used in addition should be unchanged
	public Money add(Money addend) {
		return new Money(amount + addend.getAmount());
	}
}
