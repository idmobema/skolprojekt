
public interface Item {
	
	
	public double getUnitPrice();
	public Discount getDiscount();
	public String getID();
	public void setUnitPrice(double newUnitPrice);
	public void setSpecialOfferDiscount(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay);
	public void setPriceDiscount(double percentage, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay);
}
