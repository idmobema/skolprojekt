import java.util.ArrayList;

public class Store {

	private String name;
	private Address address;
	private ArrayList<PhoneNumber> phoneNbrs = new ArrayList<>();

	public Store(String name, Address addr, PhoneNumber nbr) {

		if(name == null || name == "" || addr == null || nbr == null)
			throw new IllegalArgumentException();
		this.name = name;
		address = addr;
		phoneNbrs.add(nbr);

	}

	public String getName() {
		return name;
	}
	
	public void setAddress(Address addr){
		address = addr;
	}

	public Address getAddress() {
		return address;
	}

	public ArrayList<PhoneNumber> getPhoneNumbers() {
		return phoneNbrs;
	}
	
	public void addPhoneNumber(PhoneNumber nbr){
		if(!phoneNbrs.contains(nbr))
			phoneNbrs.add(nbr);
	}
	
	public PhoneNumber removePhoneNumber(int index){
		return phoneNbrs.remove(index);
	}

	public String toString() {
		String all = name + "\n\nAdress:\n" + address + "\n\nTelefon:";
		for(PhoneNumber nbr : phoneNbrs)
			all += "\n" + nbr;
		return  all;
	}

}
