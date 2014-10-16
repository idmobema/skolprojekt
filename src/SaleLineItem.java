
public class SaleLineItem {
	private Item item;
	private int quantity = 1;
	
	public SaleLineItem(Item item) {
		this.item = item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void increaseQuantity() {
		++quantity;
	}
	
	public String getItemName() {
		return item.getName();
	}
	
	public double getSubTotal() {
		return item.getPrice()*quantity;
	}
	
	public String toString() {
		// Lägger till rad med styckvara
		 
		if (quantity <= 0 || quantity > Receipt.MAX_QUANTITY)
			throw new IllegalArgumentException("Invalid quantity: " + quantity + ". Value between 1 and " + Receipt.MAX_QUANTITY + " required.");
		String itemName = Receipt.checkItemName(item.getName());
		Receipt.checkPrices(item.getPrice(), getSubTotal());
		String str = Receipt.formatLine(itemName, Receipt.formatPrice(getSubTotal()));
		if (quantity > 1) // Om större kvantitet än 1, lägg till extrarad med prisuträkning
			str += "  " + quantity + "st x " + Receipt.formatPrice(item.getPrice()) + "\n";
		return str;		
			
	}
	
	
	
	

}
