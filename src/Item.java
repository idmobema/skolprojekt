
public abstract class Item implements Comparable<Item>{
	double price;
	String name;
	String itemId;
	
	public Item(String name, String itemId, double price) {
		this.price = price;
		this.name = name;
		this.itemId = itemId;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getItemId() {
		return itemId;
	}
	
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(Item other) {
		return other.itemId.compareTo(itemId);
	}
	
	abstract public double getUnitPrice();
	abstract public Discount getDiscount();
	abstract public String getID();
	abstract public void setUnitPrice(double newUnitPrice);
	abstract public void setSpecialOfferDiscount(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay);
	abstract public void setPriceDiscount(double percentage, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay);
}
