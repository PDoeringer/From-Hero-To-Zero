import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;


//CO2 emissions (kt),EN.ATM.CO2E.KT,Sweden,SWE,53346.2,53277.5,40388.1,38993,39120.1,38691.2,38168.2,35915.9,34964.6,33576.1,..,..

@Named
@RequestScoped
public class List_Country_Science{
	
	
	private String category;
    private String code;
    private String name;
    private String name_short;
    private String [ ] data;
    private String data_string;
	
	private List<Country> countrys = new ArrayList<Country>();
	private String searched_emission = "Gib oben dein Land oder die Abkürzung ein. Der Wert jeweils ganz rechts in der Zeile ist der aktuellste:";
	
	private String path = "C:\\Users\\Admin\\git\\shortcasts-dev\\Artikelverwaltung\\src\\main\\java\\data_science.txt";
	

    public List_Country_Science() throws IOException
    {
    	//System.out.println("first Activated");
    	File doc = new File(path);

        BufferedReader obj = new BufferedReader(new FileReader(doc));

        String strng;
        while ((strng = obj.readLine()) != null) {
        	//System.out.println(strng);
        	
        	String[] parts = strng.split(",");
        	Country cun = new Country(parts[0], parts[1], parts[2], parts[3], Arrays.copyOfRange(parts, 4, parts.length));
        	
        	countrys.add(cun);
        }

    }

    
    
    
    public String get_emission_by_country() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String name = request.getParameter("countryform:inputcountry");
        
        
    	//System.out.println("emission activated");
    	
    	for (Country country : countrys)
    	{
    		if (country.getName().equals(name) || country.getName_short().equals(name)) {
    			setSearched_emission(country.getData_string());
    			return null;
    		}
    	}
    	setSearched_emission("Land wurde nicht gefunden");
		return null;
    }
    
    
    
    public void add_data() {
    	
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String name = request.getParameter("countrysaveform:inputsavecountry");
        String data = request.getParameter("countrysaveform:inputsavedata");
    	
    	for (Country country : countrys)
    	{
    		//System.out.println(String.join(" | ", country.getData()));
    		if (country.getName().equals(name) || country.getName_short().equals(name)) {
    			
    			int n = country.getData().length;
    			String[] newArray = Arrays.copyOf(country.getData(), n + 1);
    			newArray[n] = data;
    			
    			
    			
    			country.setData(newArray);
    			
    			//System.out.println(String.join(" | ", country.getData()));
    			try {
					saveList();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
    			setSearched_emission("Die neuen Daten wurden gespeichert");
    			break;
    		}
    		setSearched_emission("Land wurde nicht gefunden");
    		//System.out.println();
    	}
    	
    	
    }
    
    
    
    
    
    public List<String> saveList() throws FileNotFoundException {
    	
    	List<String> ListForSave = new ArrayList<String>();
    	
    	for (Country country : countrys)
    	{
    		String saveString = "";
    		saveString = saveString + country.getCategory();
    		saveString = saveString + "," + country.getCode();
    		saveString = saveString + "," + country.getName();
    		saveString = saveString + "," + country.getName_short();
    		
    		for (int i = 0; i < country.getData().length; i++) {
    			saveString = saveString + "," + country.getData()[i];
    			}
    		ListForSave.add(saveString);
    	}
    	
    	
    	PrintWriter out = new PrintWriter(path);
        for (String forSave : ListForSave)
    	{
        	out.println(forSave);
    	}
        out.close();
    	
    	
		return ListForSave;
	}
    
    
    public void copyFile() throws IOException {
    	File source = new File("C:\\Users\\Admin\\git\\shortcasts-dev\\Artikelverwaltung\\src\\main\\java\\data_science.txt");
    	File dest = new File("C:\\Users\\Admin\\git\\shortcasts-dev\\Artikelverwaltung\\src\\main\\java\\data.txt");
    	
    	InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
    
    
    
    
    
    
    
    
	public List<Country> getCountrys() {
		return countrys;
	}

	public void setCountrys(List<Country> countrys) {
		this.countrys = countrys;
	}
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName_short() {
		return name_short;
	}
	public void setName_short(String name_short) {
		this.name_short = name_short;
	}

	public String [ ] getData() {
		return data;
	}
	public void setData(String [ ] data) {
		this.data = data;
	}

	public String getData_string() {
		return data_string;
	}

	public void setData_string(String data_string) {
		this.data_string = data_string;
	}

	public String getSearched_emission() {
		return searched_emission;
	}


	public void setSearched_emission(String searched_emission) {
		this.searched_emission = searched_emission;
	}
    

}
