package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import user.service.DeptService;
import user.service.ManageService;

@Controller
public class ManageController {

	@Autowired
	private ManageService service;
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("manage")
	public String list(Model model) throws Exception {
		model.addAttribute("list", service.sel());
		return "manage/list";
	}
	
	@RequestMapping("manage/emp")
	public String emp(String id, Model model) throws Exception {
		model.addAttribute("dept", deptService.seldeptAll());
		model.addAttribute("emp", service.emp(id));
		return "manage/emp";
	}
	
	@RequestMapping(value = "manage/update", method = RequestMethod.GET)
	@ResponseBody
	public String upd(String id, int deptno, String level, int salary) {
		String s = "no";
		int i = service.upd(id, level, salary, deptno);
		if (i != 0) {
			s = "yes";
		}
		Gson gson = new Gson();
		return gson.toJson(s);
	}
}
