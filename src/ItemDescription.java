public class ItemDescription {
	private double price;
	private String name;
	private String description;
	private String itemId;
	
	public ItemDescription(String name, String description, double price, String itemId) {
		this.price = price;
		this.name = name;
		this.description = description;
		this.itemId = itemId;
		
		
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
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
