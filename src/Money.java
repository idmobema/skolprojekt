import java.math.BigDecimal;
import java.util.Currency;

public class Money implements Comparable<Money> {
	// Konstant som anger förhållandet mellan den mindre och den större valutaenheten, t ex antalet ören per krona
	public static int FRACTIONS = 100;
	
	// Summan lagras som long, i den mindre valutaenheten (t ex ören), för bättre precision
	private long amount;
	private Currency currency;
	
	// Konstruktor som tar summan i ören (t ex) och en valutakod (t ex "SEK", "USD" etc)
	public Money(long amount, String currencyCode) {
		if (amount < 0)
			throw new IllegalArgumentException("Invalid amount: " + amount + ". Value must not be negative.");
		this.amount = amount;
		try {
			this.currency = Currency.getInstance(currencyCode);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid currency code: " + currencyCode);
		}
	}

	// Konstruktor som tar summan i ören (t ex) och ett Currency-objekt
	public Money(long amount, Currency currency) {
		this(amount, currency.getCurrencyCode());
	}
	
	// Två motsvarande konstruktorer som tar summan i den större valutaenheten, t ex kronor, och räknar om den till den mindre enheten
	public Money(double amount, String currencyCode) {
		this(Math.round(FRACTIONS * amount), currencyCode);
	}	
	
	public Money(double amount, Currency currency) {
		this(Math.round(FRACTIONS * amount), currency.getCurrencyCode());
	}
	
	// "Hjälpkonstruktor" för att enkelt kunna skapa kronor
	public static Money getCrowns(double amount) {
		return new Money(amount, Currency.getInstance("SEK"));
	}

	// Summans get-metod returnerar en BigDecimal, för ökad precision och snygg formatering
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
		checkCurrency(addend);
		return new Money(amount + addend.amount, getCurrencyCode());
	}
	
	// Obs att minus inte förändrar det befintliga objektet, utan returnerar ett nytt objekt
	public Money minus(Money subtrahend) {
		checkCurrency(subtrahend);
		if (subtrahend.amount > amount)
			throw new IllegalArgumentException("Invalid subtrahend: " + subtrahend + ". Value must not be greater than minuend.");
		return new Money(amount - subtrahend.amount, getCurrencyCode());
	}
	
	// Hjälpmetod för kontroll av valuta vid aritmetiska operationer
	private void checkCurrency(Money other) {
		if (!other.currency.equals(currency))
			throw new IllegalArgumentException("Invalid argument. Money in different currencies can not be added, subtracted or compared.");
	}
	
	// Obs att times inte förändrar det befintliga objektet, utan returnerar ett nytt objekt
	public Money times(int multiplier) {
		checkMultiplier(multiplier);
		return new Money(amount * multiplier, "SEK");
	}
	
	// Överlagrad times-metod för multiplikation med double, t ex en vikt
	public Money times(double multiplier) {
		checkMultiplier(multiplier);
		return new Money(Math.round(amount * multiplier), "SEK");
	}
	
	// Hjälpmetod för kontroll av multiplikand
	private void checkMultiplier(double multiplier) {
		if (multiplier < 0)
			throw new IllegalArgumentException("Invalid multiplier. Value must be zero or positive.");		
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Money) {
			Money otherMoney = (Money) other;
			return otherMoney.amount == amount && otherMoney.currency.equals(currency);
		}
		return false;	
	}

	// Ful-implementation som bör ändras vid tillfälle
	@Override
	public int hashCode() {
		return (int) amount + 1337;
	}
	
	public int compareTo(Money other) {
		checkCurrency(other);
		return (int) (amount - other.amount);
	}
	
	@Override
	public String toString() {
		return "" + getAmount() + " " + getCurrencyCode();
	}
}
