public class ItemDescription {
	private String name;
	private String description;
	private String itemId;
	private Money price;
	
	public ItemDescription(String name, String description, Money price, String itemId) {
		this.price = price;
		this.name = name;
		this.description = description;
		this.itemId = itemId;
	
	}
	
	public String getName() {
		return name;
	}
	
	public Money getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getItemId() {
		return itemId;
	}
	
	public String toString() {
		return name;
	}

}
