package user.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import user.domain.CalDto;
import user.domain.CalendarDto;
import user.service.CalendarService;

@Controller
public class CanlendarController {

	@Autowired
	private CalendarService service;
	
	@RequestMapping("/cal/calendar")
	public String calendar(Model model, HttpServletRequest request, CalDto dto) throws Exception {
		Calendar cal = Calendar.getInstance();
		CalDto calData;
		String fullDate;
		String mm = request.getParameter("month");
		String yy = request.getParameter("year");
		
		if (mm == null && yy == null) {
			dto = new CalDto(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)), String.valueOf(cal.get(Calendar.DATE)), null, null);
		}else {
			int mmm = Integer.parseInt(mm) - 1;
			int yyy = Integer.parseInt(yy);
			dto = new CalDto(String.valueOf(yyy), String.valueOf(mmm), String.valueOf(cal.get(Calendar.DATE)), null, null);
		}		
		System.out.println("달" + request.getParameter("month"));
		Map<String, Integer> today = dto.calMap(dto);
		List<CalDto> list = new ArrayList<CalDto>();
		
		for (int i = 1; i < today.get("start"); i++) {
			calData = new CalDto(null, null, null, null, null);
			list.add(calData);
		}
		
		for (int i = today.get("startDay"); i <= today.get("endDay"); i++) {
			int iMonth = Integer.parseInt(dto.getMonth()) + 1;
			String month = String.valueOf(iMonth);
			if(iMonth < 10) {
				month = "0" + month;
			}
			String date = String.valueOf(i);
			if(i < 10) {
				date = "0" + date;
			}
			fullDate = dto.getYear() + month + date;
			calData = new CalDto(String.valueOf(dto.getYear()), month, String.valueOf(i), fullDate, null);
			calData.setStrings(service.check(fullDate));
			list.add(calData);
		}
		
		int index = 7 - list.size() % 7;
		if(list.size() % 7 != 0) {
			for(int i = 0; i < index; i++) {
				calData = new CalDto(null, null, null, null, null);
				list.add(calData);
			}
		}
		
		model.addAttribute("list", list);	
		model.addAttribute("today", today);
		return "calendar/calendar";
	}
	
	@RequestMapping(value = "/cal/register", method = RequestMethod.GET)
	public String registerForm() throws Exception {
		return "calendar/registerForm";
	}
	
	@RequestMapping(value = "/cal/register", method = RequestMethod.POST)
	public String register(CalendarDto dto) throws Exception {
		service.register(dto);
		return "redirect:calendar";
	}
	@RequestMapping(value = "/cal/check")
	public String check(Model model, int num) throws Exception {
		CalendarDto dto = service.show(num);
		model.addAttribute("cal", dto);
		return "calendar/check";
	}
	
	@RequestMapping(value = "/cal/update", method = RequestMethod.GET)
	public String updateForm(Model model, int num) throws Exception {
		model.addAttribute("cal", service.show(num));
		return "calendar/updateForm";
	}
	
	@RequestMapping(value = "/cal/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(CalendarDto dto) throws Exception {
		String s = "no";
		int update = service.update(dto);
		if (update != 0) {
			s = "yes";
		}
		Gson gson = new Gson();
		return gson.toJson(s);
	}
	
	@RequestMapping(value = "/cal/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(int num) throws Exception {
		String s = "no";
		int update = service.delete(num);
		if (update != 0) {
			s = "yes";
		}
		Gson gson = new Gson();
		return gson.toJson(s);
	}
}
