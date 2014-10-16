import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class Sale implements Iterable<SaleLineItem> {
	private SortedMap<Item, SaleLineItem> saleLines = new TreeMap<Item, SaleLineItem>();
	private Date date = new Date();
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	//temporary
	private Person cashier = new Person("Icander", "Coop", "Hemma", "123456");
	
	public Iterator<SaleLineItem> iterator() {
		return saleLines.values().iterator();
	}
	public void add(Item item) {
		if(saleLines.containsKey(item)) {
			saleLines.get(item).increaseQuantity();
		}
		else {
		saleLines.put(item, new SaleLineItem(item));
		
		}
	}
	public String getDate() {
		return dateFormat.format(date);
		
	}
	public String getTime() {
		return timeFormat.format(date);
		
	}
	//double for now
	public double getTotal() {
		double currentTotal = 0;
		for(SaleLineItem sli: this) {
			currentTotal += sli.getSubTotal();
		}
		return currentTotal;
	}
	
	public String getCashier() {
		return cashier.toString();
	}

}
