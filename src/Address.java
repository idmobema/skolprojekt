

public class Address {
	
	private String street;
	private String streetNumber;
	private String postalCode;
	private String postTown;
	
	public Address(String street, String streetNumber, String postalCode, String postTown){
		
		this.street = okStreet(street);
		this.streetNumber = okStreetNumber(streetNumber);
		this.postalCode = okPostalCode(postalCode);
		this.postTown = okPostTown(postTown);
	
	}
	
	//kontroll av street
	private String okStreet(String street){
		if(street == "" || street == null) 
			throw new IllegalArgumentException();
		return street;
	}
	
	//kontroll av StreetNumber
	private String okStreetNumber(String streetNumber){
		
		if(streetNumber == null)
			throw new IllegalArgumentException();
		int nbrAsInt;
		try{
			//Ett gatunummer utan bokstav ska kunna tolkas som int.
			nbrAsInt = Integer.parseInt(streetNumber);
		}catch(NumberFormatException nfe){
			char lastChar = streetNumber.charAt(streetNumber.length() - 1);
			if(!Character.isLetter(lastChar))
				throw new IllegalArgumentException();
			
			//Ett gatunamn med bokstav ska kunna tolkas som int om man tar bort bokstaven och ev space.
			nbrAsInt = Integer.parseInt(streetNumber.substring(0, streetNumber.length() - 1).trim());
		}
		//gatunumret får inte vara 0 eller negativt
		if(nbrAsInt < 1)
				throw new IllegalArgumentException();
		return streetNumber;
	}
	
	//kontroll av postalCode
	private String okPostalCode( String postalCode){
		
		if(postalCode == null)
			throw new IllegalArgumentException();
		int codeLength = postalCode.length();
		//om längden ska vara 5 eller 6 beror på om den är skriven med mellanslag eller ej
		if(postalCode.startsWith("0") || codeLength < 5 || codeLength > 6 )
			throw new IllegalArgumentException();
		if(codeLength == 6)
			if(postalCode.charAt(3) != ' ')
				throw new IllegalArgumentException();
		String firstPartCode = postalCode.substring(0,3);
		String secondPartCode = postalCode.substring(codeLength - 2 , codeLength);
		//kollar att postnumret kan tolkas som int oavsett mellanslag eller ej
		Integer.parseInt(firstPartCode + secondPartCode);
		//sparas alltid med mellanslag
		return firstPartCode + " " + secondPartCode;
	}
	
	private String okPostTown(String postTown){
		if(postTown == "" || postTown == null)
			throw new IllegalArgumentException();
		return postTown;
	}
	
	public String getStreet(){
		return street;
	}
	
	public String getStreetNumber(){
		return streetNumber;
	}
	
	public String getPostalCode(){
		return postalCode;
	}
	
	public String getPostTown(){
		return postTown;
	}
	
	public String toString(){
		return street + " " + streetNumber + "\n" + postalCode + " " + postTown;
	}

}
