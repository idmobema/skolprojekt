

public class Address {
	private String street;
	private String streetNumber;
	private String postalCode;
	private String postTown;

	public Address(String street, String streetNumber, String postalCode,
			String postTown) {

		this.street = okStreet(street);
		this.streetNumber = okStreetNumber(streetNumber);
		this.postalCode = okPostalCode(postalCode);
		this.postTown = okPostTown(postTown);

	}

	// kontroll av street
	private String okStreet(String street) {

		if (street == null)
			throw new NullPointerException("Invalid street: null");
		if (street.equals(""))
			throw new IllegalArgumentException("Invalid street: empty");

		return street;
	}

	// kontroll av StreetNumber
	private String okStreetNumber(String streetNumber) {

		if (streetNumber == null)
			throw new NullPointerException("Invalid street number: null");
		int nbrAsInt;
		try {
			// Ett gatunummer utan bokstav ska kunna tolkas som int.
			nbrAsInt = Integer.parseInt(streetNumber);
		} catch (NumberFormatException nfe) {

			try {
				// Ett gatunamn med bokstav ska kunna tolkas som int om man tar
				// bort bokstaven och ev space.
				nbrAsInt = Integer.parseInt(streetNumber.substring(0,
						streetNumber.length() - 1).trim());
			} catch (NumberFormatException numForExc) {
				throw new NumberFormatException(
						"Invalid street number: "
								+ streetNumber
								+ ". Street number must be numerical with possible additional letter.");
			}

			char lastChar = streetNumber.charAt(streetNumber.length() - 1);
			if (!Character.isLetter(lastChar))
				throw new IllegalArgumentException(
						"Invalid street number: "
								+ streetNumber
								+ ". Street number must be numerical with possible additional letter.");

		}
		// gatunumret får inte vara 0 eller negativt
		if (nbrAsInt < 1)
			throw new IllegalArgumentException("Invalid street number: "
					+ streetNumber + ". Street number must be positive.");
		return streetNumber;
	}

	// kontroll av postalCode
	private String okPostalCode(String postalCode) {

		if (postalCode == null)
			throw new NullPointerException("Invalid postal code: null");
		int codeLength = postalCode.length();
		// om längden ska vara 5 eller 6 beror på om den är skriven med
		// mellanslag eller ej
		if (postalCode.startsWith("0") || codeLength < 5 || codeLength > 6)
			throw new IllegalArgumentException(
					"Invalid postal code: "
							+ postalCode
							+ ". Postal code must be 5 digits written in one of the two formats: xxx xx or xxxxx. " 
							+ "First digit cannot be 0.");
		if (codeLength == 6)
			if (postalCode.charAt(3) != ' ')
				throw new IllegalArgumentException("Invalid postal code: "
						+ postalCode + ". Postal code must be 5 digits written in one of the two formats: xxx xx or xxxxx.");
		String firstPartCode = postalCode.substring(0, 3);
		String secondPartCode = postalCode
				.substring(codeLength - 2, codeLength);
		try{
		// kollar att postnumret kan tolkas som int oavsett mellanslag eller ej
		Integer.parseInt(firstPartCode + secondPartCode);
		}catch(NumberFormatException nfe){
			throw new NumberFormatException("Invalid postal code: " + postalCode + ". Postal code must be numerical.");
		}
		
		// sparas alltid med mellanslag
		return firstPartCode + " " + secondPartCode;
	}

	private String okPostTown(String postTown) {
		
		if (postTown == null)
			throw new NullPointerException("Invalid post town: null");
		
		if (postTown.equals("") )
			throw new IllegalArgumentException("Invalid postTown: empty");
		return postTown;
	}

	public String getStreet() {
		return street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getPostTown() {
		return postTown;
	}

	public String toString() {
		return street + " " + streetNumber + "\n" + postalCode + " " + postTown;
	}

}
