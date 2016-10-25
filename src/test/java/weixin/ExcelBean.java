package weixin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExcelBean {
	private String name;
	private HashMap<Date, Integer> data;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<Date, Integer> getData() {
		return data;
	}
	public void setData(HashMap<Date, Integer> data) {
		this.data = data;
	}
	
	
}
