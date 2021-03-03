


//Criminal class
public class Criminal {
	// Object data field
	private String name = "";
	private int id;
	private int age;
	private String crime= "";
	private String status = "";
	
	
	// Constructor for a criminal object
	public Criminal() {
		
	}
	// Constructor for a criminal object with given data
	public Criminal(String name, int id, int age, String crime,String status) {
		this.name = name;
		this.id = id;
		this.age = age;
		this.crime = crime;
		this.status = status;
		
	}
	
	//Setter method for name
	public void setName(String name) {
		this.name = name;
	}
	//Setter method for id
	public void setId(int id) {
		this.id = id;
	}
	//Setter method for age
	public void setAge(int age) {
		this.age = age;
	}
	//Setter method for crime
	public void setCrime(String crime) {
		this.crime = crime;
	}
	//Setter method for status
	public void setStatus(String status) {
		this.status = status;
	}
	//Getter method for name
	public String getName() {
		return name;
	}
	//Getter method for id
	public int getId() {
		return id;
	}
	//Getter method for age
	public int getAge() {
		return age;
	}
	//Getter method for crime
	public String getCrime() {
		return crime;
	}
	//Getter method for status
	public String getStatus() {
		return status;
	}
	
}
