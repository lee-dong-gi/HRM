package user.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.UpdateParams;

import user.domain.EmpDto;
import user.service.EmpService;

@Controller
public class EmpRegisterController {

	@Autowired
	private EmpService service;

	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String registerForm() throws Exception {
		return "emp/join";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String register(EmpDto dto) throws Exception {
		System.out.println(dto);
		String hashedPw = BCrypt.hashpw(dto.getPasswd(), BCrypt.gensalt());
		dto.setPasswd(hashedPw);
		service.register(dto);
		return "user/login";
	}

	@RequestMapping("chk11.do")
	@ResponseBody
	public String idCheck(String id) throws Exception {
		String s = "no";
		System.out.println(service.idCheck(id));
		if (service.idCheck(id) != null) {
			s = "yes";
		}
		System.out.println(s);
		Gson gson = new Gson();
		System.out.println(gson.toJson(s));
		return gson.toJson(s);
	}

	@RequestMapping("emp/findIdForm")
	public String findIdForm() throws Exception {
		return "emp/findIdForm";
	}

	@RequestMapping("emp/findId")
	@ResponseBody
	public String findId(String name, String email) throws Exception {
		String s = "yes";
		if (service.findId(name, email) != null) {
			s = "no";
		}
		Gson gson = new Gson();
		return gson.toJson(s);
	}

	@RequestMapping("emp/showId")
	public String showId(Model model, String name, String email) throws Exception {
		model.addAttribute("id", service.findId(name, email));
		return "emp/showId";
	}

	@RequestMapping("emp/findPwForm")
	public String findPwForm() throws Exception {
		return "emp/findPwForm";
	}

	@RequestMapping("emp/findPw")
	@ResponseBody
	public String findPw(String id, String email) throws Exception {
		String s = "yes";
		if (service.findPw(id, email) != null) {
			s = "no";
		}
		Gson gson = new Gson();
		return gson.toJson(s);
	}

	@RequestMapping("emp/resetPwForm")
	public String resetPwForm(Model model, String id, String email) throws Exception {
		model.addAttribute("pw", service.findPw(id, email));
		model.addAttribute("id", id);
		return "emp/resetPwForm";
	}

	@RequestMapping("emp/resetPw")
	@ResponseBody
	public String resetPw(String passwd, String id) throws Exception {
		System.out.println(id + " " + passwd);
		String s = "yes";
		String hashedPw = BCrypt.hashpw(passwd, BCrypt.gensalt());
		int i = service.up(hashedPw, id);
		System.out.println(i);
		if (i != 0) {
			s = "no";
		}
		Gson gson = new Gson();
		return gson.toJson(s);
	}
}
