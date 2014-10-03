

public class PhoneNumber {
	
	private String areaCode;
	
	public PhoneNumber(String areaCode, String nbr){
		if(areaCode.length() < 2 || areaCode.length() > 4)
			throw new IllegalArgumentException();
		Integer.parseInt(areaCode);
		if(!areaCode.startsWith("0"))
			throw new IllegalArgumentException();
		this.areaCode = areaCode;
		
	}
	
	public String getAreaCode(){
		return areaCode;
	}

}
