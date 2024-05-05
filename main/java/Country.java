import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;





public class Country
{
	
    private String category;
    private String code;
    private String name;
    private String name_short;
    private String [ ] data;
    private String data_string;

    public Country(String category, String code, String name, String name_short, String [ ] data)
    {	
    	this.category = category;
    	this.code = code;
        this.name = name;
        this.name_short = name_short;
        this.data = data;
        this.setData_string(String.join(" | ", data));
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

}
