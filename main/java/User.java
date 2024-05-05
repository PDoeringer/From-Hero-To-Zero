import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;


public class User
{
	
    private String username;
    private String password;

    public User(String username, String password)
    {
    	this.username = username;
    	this.password = password;
    }
    
    
    
    public boolean compareUser(String newUsername, String newPassword) {
    	if (username.equals(newUsername) && password.equals(String.valueOf(newPassword.hashCode()))) {
    		return true;
    	}
    	return false;
    }
    
    
    
    public String getUsername() {
		return username;
	}
    
    public String getPassword() {
		return password;
	}

}
