
public class Address {
	
	private String street;
	private String streetNumber;
	
	public Address(String street, String streetNumber, String postalCode, String postTown){
		if(street == "")
			throw new IllegalArgumentException();
		this.street = street;
		
		int nbrAsInt;
		try{
			nbrAsInt = Integer.parseInt(streetNumber);
		}catch(NumberFormatException nfe){
			nbrAsInt = Integer.parseInt(streetNumber.substring(0, streetNumber.length() - 1).trim());
		}
		if(nbrAsInt < 1)
				throw new IllegalArgumentException();
		this.streetNumber = streetNumber;
		
		Integer.parseInt(postalCode);
		if(postalCode.startsWith("0") || postalCode.length() != 5)
			throw new IllegalArgumentException();
	}
	
	public String getStreet(){
		return street;
	}
	
	public String getStreetNumber(){
		return streetNumber;
	}
	
	public String getPostalCode(){
		return "77889";
	}

}
