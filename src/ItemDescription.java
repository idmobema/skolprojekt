public class ItemDescription {
	private double price;
	private String name;
	
	public ItemDescription(String name, String description, double price, String itemId) {
		this.price = price;
		this.name = name;
		
		
		
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	

}
