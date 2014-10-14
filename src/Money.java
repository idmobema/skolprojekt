import java.math.BigDecimal;
import java.util.Currency;

public class Money {
	// Konstant som anger förhållandet mellan den mindre och den större valutaenheten, t ex antalet ören per krona
	public static int FRACTIONS = 100;
	
	// Summan lagras som long, i den mindre valutaenheten (t ex ören), för bättre precision
	private long amount;
	private Currency currency;
	
	// Konstruktor som tar summan i ören (t ex) och ett Currency-objekt
	public Money(long amount, Currency currency) {
		if (amount < 0)
			throw new IllegalArgumentException("Invalid amount: " + amount + ". Value must not be negative.");
		this.amount = amount;
		this.currency = currency;
	}
	
	// Konstruktor som tar summan i ören (t ex) och en valutakod (t ex "SEK", "USD" etc)
	public Money(long amount, String currencyCode) {
		this.amount = amount;
		try {
			this.currency = Currency.getInstance(currencyCode);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid currency code: " + currencyCode);
		}
	}
	
	// Två konstruktorer som tar summan i den större valutaenheten, t ex kronor, och räknar om den till den mindre enheten
	public Money(double amount, Currency currency) {
		this(Math.round(FRACTIONS * amount), currency);
	}

	public Money(double amount, String currencyCode) {
		this(Math.round(FRACTIONS * amount), currencyCode);
	}	
	
	// Summans get-metod returnerar en BigDecimal, för ökad precision
	public BigDecimal getAmount() {
		return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	// Hjälpmetod för enkel tillgång till valutakoden
	private String getCurrencyCode() {
		return currency.getCurrencyCode();
	}
	
	// Obs att plus inte förändrar det befintliga objektet, utan returnerar ett nytt objekt
	public Money plus(Money addend) {
		return new Money(amount + addend.amount, getCurrencyCode());
	}
	
	// Obs att minus inte förändrar det befintliga objektet, utan returnerar ett nytt objekt
	public Money minus(Money subtrahend) {
		if (subtrahend.amount > amount)
			throw new IllegalArgumentException("Invalid subtrahend: " + subtrahend + ". Value must not be greater than minuend.");
		return new Money(amount - subtrahend.amount, getCurrencyCode());
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
		return (int) amount + 1337;
	}
	
	@Override
	public String toString() {
		return "" + getAmount() + " " + getCurrencyCode();
	}
}
