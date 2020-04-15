package user.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalDto {

	private String year;
	private String month;
	private String date;
	private String fullDate;
	private List<CalendarDto> strings;
	
	public String getFullDate() {
		return fullDate;
	}
	public void setFullDate(String fullDate) {
		this.fullDate = fullDate;
	}
	public List<CalendarDto> getStrings() {
		return strings;
	}
	public void setStrings(List<CalendarDto> strings) {
		this.strings = strings;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Map<String, Integer> calMap(CalDto dto) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dto.getYear()), Integer.parseInt(dto.getMonth()), 1);
		
		int startDay = cal.getMinimum(Calendar.DATE);
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int start = cal.get(Calendar.DAY_OF_WEEK);
		int tYear = Integer.parseInt(dto.getYear());
		int tMonth = Integer.parseInt(dto.getMonth()) + 1;
		map.put("startDay", startDay);
		map.put("endDay", endDay);
		map.put("start", start);
		map.put("tYear", tYear);
		map.put("tMonth", tMonth);
		map.put("bYear", baCalMap(tYear, tMonth).get("bYear"));
		map.put("bMonth", baCalMap(tYear, tMonth).get("bMonth"));
		map.put("aYear", baCalMap(tYear, tMonth).get("aYear"));
		map.put("aMonth", baCalMap(tYear, tMonth).get("aMonth"));
		
		
		return map;
	}
	
	private Map<String, Integer> baCalMap(int tYear, int tMonth){
		Map<String, Integer> baMap = new HashMap<String, Integer>();
		int bYear = tYear;
		int bMonth = tMonth - 1;
		int aYear = tYear;
		int aMonth = tMonth + 1;
		
		if (bMonth < 1) {
			bMonth = 12;
			bYear = tYear - 1;
		}
		
		if (aMonth > 12) {
			aMonth = 1;
			aYear = tYear + 1;
		}
		
		baMap.put("bYear", bYear);
		baMap.put("bMonth", bMonth);
		baMap.put("aYear", aYear);
		baMap.put("aMonth", aMonth);
		
		return baMap;
	}
	
	public CalDto(String year, String month, String date, String fullDate, List<CalendarDto> strings) {
		super();
		this.year = year;
		this.month = month;
		this.date = date;
		this.fullDate = fullDate;
		this.strings = strings;
	}
	
}
