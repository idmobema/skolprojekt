

public class PhoneNumber {
	
	private String areaCode;
	private String number;
	
	public PhoneNumber(String areaCode, String nbr){
		
		if(areaCode == null)
			throw new IllegalArgumentException();
		if(areaCode.length() < 2 || areaCode.length() > 4)
			throw new IllegalArgumentException("Riktnumret m�ste vara mellan 2 och 4 siffror");
		if(!areaCode.startsWith("0"))
			throw new IllegalArgumentException("Riktnumret m�ste b�rja med 0");
		if(areaCode.startsWith("00"))
			throw new IllegalArgumentException("Riktnumret kan inte b�rja med tv� 0:or");
		Integer.parseInt(areaCode); //Ingen text om vad felet �r n�r det inte funkar
		this.areaCode = areaCode;
		
		
		if(nbr == null)
			throw new IllegalArgumentException();
		if(nbr.length() < 5 || nbr.length() > 8)
			throw new IllegalArgumentException("Numret m�ste vara mellan 5 och 8 siffror");
		int nbrAsInt = Integer.parseInt(nbr); //Ingen text om vad felet �r n�r det inte funkar
		if(nbrAsInt < 0)
			throw new NumberFormatException("Numret kan inte vara negativt");
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
