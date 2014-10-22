
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
		return item.getItemDesc().getName();
	}
	
	public Money getSubTotal() {
		Money subtotal = new Money(0,"SEK");
		for(int i = 0 ; i<quantity; ++i) {
			subtotal = subtotal.plus(item.getUnitPrice());
		}
		return subtotal;
	}
	
	public String toString() {
		// Lägger till rad med styckvara
		 
		if (quantity > Receipt.MAX_QUANTITY)
			throw new IllegalArgumentException("Invalid quantity: " + quantity + ". Value between 1 and " + Receipt.MAX_QUANTITY + " required.");
		String itemName = Receipt.checkItemName(item.getItemDesc().getName());
		Receipt.checkPrices(item.getUnitPrice().getAmount().doubleValue(), getSubTotal().getAmount().doubleValue());
		String str = Receipt.formatLine(itemName, getSubTotal().toString());
		if (quantity > 1) // Om större kvantitet än 1, lägg till extrarad med prisuträkning
			str += "  " + quantity + "st x " + item.getUnitPrice() + "\n";
		return str;		
			
	}
	
	
	
	

}
