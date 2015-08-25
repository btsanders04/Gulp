
public class User {

	
	private String name,email,zipcode;
	private int user_id;
	private boolean loggedIn=false;
	public User(int id){
		user_id=id;
	}
	
	
	public void setDetails(String n, String e, String zip){
		n=name;
		email=e;
		zipcode=zip;
	}


	public boolean isLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
}
