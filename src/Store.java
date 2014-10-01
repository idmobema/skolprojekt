
public class Store {

	private String name;
	private String adress;
	private String phoneNbr;
	
	public Store(String name, String adress, String phoneNbr){
		if(name == "")
			throw new IllegalArgumentException();
		this.name = name;
		this.adress = adress;
		this.phoneNbr = phoneNbr;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAdress(){
		return adress;
	}
	
	public String getPhoneNbr(){
		return phoneNbr;
	}
	
	public String toString(){
		return name + ", " + adress + ", tel. " + phoneNbr;
	}
	
	

}
