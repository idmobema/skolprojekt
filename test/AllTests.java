import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ AddressTester.class, DiscountTester.class, ItemTester.class, ItemDescriptionTests.class,
		MoneyTests.class, MoreMoneyTests.class, PersonTests.class, PhoneNumberTest.class,
		PriceDiscountTester.class, PricedPerPieceTester.class,
		PricedPerWeightTester.class, ReceiptTests.class,
		SaleLineItemTests.class, SaleTest.class, SpecialOfferTester.class,
		StoreTest.class, TimeValidityTester.class, DiscountResult.class })
public class AllTests {

}
