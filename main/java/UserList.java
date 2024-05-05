import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;



@Named
@RequestScoped
public class UserList
{
	
    private String username;
    private String password;
    
    private String fehlanmeldung = "";
    
    private List<User> users = new ArrayList<User>();
    private List<User> admins = new ArrayList<User>();

    public UserList() throws IOException
    {
    	users = getObject("C:\\Users\\Admin\\git\\shortcasts-dev\\Artikelverwaltung\\src\\main\\java\\userList.txt");
    	admins = getObject("C:\\Users\\Admin\\git\\shortcasts-dev\\Artikelverwaltung\\src\\main\\java\\userListAdmin.txt");
    }
    
    
    
    public String login() {
    	
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	String newUsername = request.getParameter("loginform:inputusername");
    	String newPassword = request.getParameter("loginform:inputpasswort");
    	

    	
    	for (User user : users)
    	{

    		if (user.compareUser(newUsername, newPassword)) {
    			return "Data_Set.xhtml";
    		}
    	}
    	
    	for (User user : admins)
    	{

    		if (user.compareUser(newUsername, newPassword)) {
    			return "Data_Set_herrausgeber.xhtml";
    		}
    	}
    	
    	fehlanmeldung = "Benutzername oder Passwort war falsch.";
    	
		return "Login.xhtml";
    	
    }
    
    private List<User> getObject(String path) throws IOException {
    	List<User> users = new ArrayList<User>();
    	
    	File doc = new File(path);

        BufferedReader obj = new BufferedReader(new FileReader(doc));

        String strng;
        while ((strng = obj.readLine()) != null) {
        	String[] parts = strng.split(",");
        	User user = new User(parts[0], parts[1]);
        	users.add(user);
        }
		return users;
    }
    
    
    
    
    
    public String getUsername() {
		return username;
	}
    
    public String getPassword() {
		return password;
	}



	public String getFehlanmeldung() {
		return fehlanmeldung;
	}



	public void setFehlanmeldung(String fehlanmeldung) {
		this.fehlanmeldung = fehlanmeldung;
	}

}
