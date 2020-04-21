package contact.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelDto {
	String name;
	String level;
	String compname;
	String email;
	String phonenum;
	List<Map<String, String>>excelContent;

	public List<Map<String, String>> getExcelContent() {
		return excelContent;
	}
	public void setExcelContent(List<Map<String, String>> excelContent) {
		this.excelContent = excelContent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	@Override
	public String toString() {
		return "ExcelDto [name=" + name + ", level=" + level + ", compname=" + compname + ", email=" + email
				+ ", phonenum=" + phonenum + ", excelContent=" + excelContent + "]";
	}
	
}
