

public class PhoneNumber {
	
	private String areaCode;
	private String number;
	
	public PhoneNumber(String areaCode, String nbr){
		if(areaCode.length() < 2 || areaCode.length() > 4)
			throw new IllegalArgumentException("Riktnumret måste vara mellan 2 och 4 siffror");
		Integer.parseInt(areaCode);
		if(!areaCode.startsWith("0"))
			throw new IllegalArgumentException("Riktnumret måste börja med 0");
		this.areaCode = areaCode;
		
		if(nbr.length() < 5 || nbr.length() > 8)
			throw new IllegalArgumentException();
		Integer.parseInt(nbr);
		number = nbr;
		
	}
	
	public String getAreaCode(){
		return areaCode;
	}
	
	public String getNbr(){
		return number;
	}
	
	public String toString(){
		return areaCode + "-" + number;
	}

}
