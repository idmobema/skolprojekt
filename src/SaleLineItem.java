
public class SaleLineItem implements Comparable<SaleLineItem> {
	private Item item;
	private int quantity = 1;
	private int lineNo = 0;
	
	public SaleLineItem(Item item, int lineNo) {
		this.item = item;
		this.lineNo = lineNo;
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
	
	public Money getDiscountAmount() {
		return item.getDiscountResult(quantity).getDiscountAmount();
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
		DiscountResult dr = item.getDiscountResult(quantity);
		if (dr.getDiscountDescription() != null) {
			str += Receipt.formatLine(dr.getDiscountDescription(), "-" + dr.getDiscountAmount().toString());
		}
		return str;		
			
	}

	@Override
	public int compareTo(SaleLineItem o) {

		return lineNo - o.lineNo;
	}
	
	
	
	

}
