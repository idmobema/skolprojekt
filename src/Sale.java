import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class Sale implements Iterable<SaleLineItem> {
	private SortedMap<Item, SaleLineItem> saleLines = new TreeMap<Item, SaleLineItem>();
	private Date date = new Date();
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private int lineNo = 0;
	//temporary
	private Cashier cashier = new Cashier("Icander", "Coop", new Address("Fågelvägen", "4", "126 34", "Hägersten"), new PhoneNumber("08", "123456"));
	
	public Iterator<SaleLineItem> iterator() {
		ArrayList<SaleLineItem> sli = new ArrayList<SaleLineItem>(saleLines.values());
		Collections.sort(sli);
		return sli.iterator();
	}
	public void add(Item item) {
		if(saleLines.containsKey(item)) {
			saleLines.get(item).increaseQuantity();
		}
		else {
		saleLines.put(item, new SaleLineItem(item, ++lineNo));
		
		}
	}
	public String getDate() {
		return dateFormat.format(date);
		
	}
	public String getTime() {
		return timeFormat.format(date);
		
	}
	
	public Money getTotal() {
		Money currentTotal = new Money(0, "SEK");
		for(SaleLineItem sli: this) {
			currentTotal = currentTotal.plus(sli.getSubTotal());
			currentTotal = currentTotal.minus(sli.getDiscountAmount());
		}
		return currentTotal;
	}
	
	public String getCashier() {
		return cashier.getName();
	}

}
