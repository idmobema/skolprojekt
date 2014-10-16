/** PricedPerPiece represents a concrete item. This can have either a SpecialOffer or a PriceDiscount discount or no discount.
 * 
 * Item with SpecialOffer type of discount:
 * The (final) unitPrice is calculated depending of the type of discount this item has/hasn't. By setting up a SpecialOffer discount 
 * for this object the next step is to call this discount's setBuyQuantity() and setGetFreeQuantity() in order to have a relevance
 * for the unitPrice's calculation. The unitPrice is then unitPrice / (buyQuantity + getFreeQuantity). The task for checking the number
 * of items of the same type bought in the same sale should be delegated to SaleLineItem or Receipt. However, if these discount fields
 * are not set the getUnitPrice() will return the unitPrice that was originally set in the PricedPerPiece() constructor.
 * 
 * Item with PriceDiscount type of discount:
 * If the discount for this item is not set then the getUnitPrice() will return the initial unitPrice set with the constructor or the
 * setUnitPrice(). If the discount is set then unitPrice will be unitPrice - unitPrice / (100 / percentage).
 * 
 * The methods setSpecialOfferDiscount() and setPriceDiscount() will change the discount field accordingly, meaning that an instance of
 * this class can have only one type of discount at a time or none.
 * 
 * Helper methods to enforce the following rules:
 * - unitPrice NOT negative or zero
 * - unitID 11 alpha-numerical chars long.
 * Other methods that are called by discount are enforcing rules applicable to TimeValidity.
 * 
 * Comments on the field unitID:
 * The first three chars would represent the product category. E.g. "Mej12ARL051". Mej would designate the Mejeri category.
 * The next two chars would represent the quantity left on the shelf or deposit. Here 12 left. Might be a warning to start to refill.
 * The next three chars would be a manufacturer code. Here ARL Arla.
 * The last three chars would be this products unique identifier among other products from the same manufacturer. E.g. 051 would repre-
 * sent the milk with 3.5 % fat. 035 would be a yoghurt with vanilla from the same manufacturer like this: Mej12ARL035
 * 
 * unitID is REDUNDANT if this sold unit's data is not send to a database.
 * 
 * #BC141013# */

public class PricedPerPiece extends Item {
	
	public PricedPerPiece(String name, String unitID, double price){
		super(name, unitID, price);

		
	}
	
	public void setSpecialOfferDiscount(String discountType, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay) {
		Discount discount = new SpecialOffer(discountType, startYear, startMonth, startDay, endYear, endMonth, endDay);
		setDiscount(discount);

	}	
}
