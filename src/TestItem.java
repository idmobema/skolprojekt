
public class TestItem extends Item {

	TestItem(ItemDescription itemDesc) {
		super(itemDesc);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public DiscountResult getDiscountResult(int quantity) {
		return new DiscountResult("Rabatt: 2 för 25:-", new Money(290, "SEK")); 	
	}

}
