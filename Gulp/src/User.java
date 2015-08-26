
public class User {

	
	private String name,email,zipcode, password;
	private Integer user_id;
	//private boolean loggedIn=false;
	public User(int id){
		user_id=id;
	}
	
	
	public Integer getUser_id() {
		return user_id;
	}


	public void setDetails( String n, String e, String zip, String pass){
		name=n;
		email=e;
		zipcode=zip;
		password=pass;
	}


	public String getPassword() {
		return password;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getZipcode() {
		return zipcode;
	}


	
	
}
