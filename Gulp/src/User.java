
public class User {

	
	private String name,email,zipcode;
	private Integer user_id;
	//private boolean loggedIn=false;
	public User(int id){
		user_id=id;
	}
	
	
	public Integer getUser_id() {
		return user_id;
	}


	public void setDetails( String n, String e, String zip){
		n=name;
		email=e;
		zipcode=zip;
	}


	
	
}
