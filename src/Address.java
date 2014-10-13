
public class Address {
	
	public Address(String street, String streetNumber, String postalCode, String PostTown){
		if(street == "")
			throw new IllegalArgumentException();
	}
	
	public String getStreet(){
		return "Vägen";
	}
	
	public String getStreetNumber(){
		return "2";
	}

}
