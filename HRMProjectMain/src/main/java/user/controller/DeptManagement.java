package user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import user.domain.DeptDTO;
import user.domain.UserVO;
import user.service.DeptService;

@Controller
@RequestMapping(value = "/dept")
public class DeptManagement {
	@Autowired
	private DeptService service;
	
	@RequestMapping(value = "/deptlist", method = RequestMethod.GET)
	public String deptlist(HttpSession hs,int pageNum,Model m) {
		UserVO uv = (UserVO)hs.getAttribute("login");
		System.out.println(uv);
			try {
				m.addAttribute("count", service.seldeptAllco());
				m.addAttribute("pageNum", pageNum);
				return "dept/deptlist";			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/HRMProjectMain/jsp/user/mainadmin";

	}
	
	@RequestMapping(value = "/deptupdate", method = RequestMethod.GET)
	public String deptupdate(HttpSession hs,Model m,int deptno) {
		UserVO uv = (UserVO)hs.getAttribute("login");
		System.out.println(uv);
		try {
			DeptDTO dd = service.seldeptone(deptno);
			m.addAttribute("deptno", deptno);
			m.addAttribute("dname", dd.getDname());
			m.addAttribute("loc", dd.getLoc());
			return "dept/deptupdate";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/HRMProjectMain/jsp/user/mainadmin";
	}
	
	//search
		@RequestMapping(value = "/deptlistsearch", method = RequestMethod.GET)
		public String selectArticle(HttpSession hs,int pageNum, int selector,String seltext, Model m) throws Exception {
			Map<String,String> map = new HashMap<String, String>();
			int count = 0;
			UserVO userVO = (UserVO)hs.getAttribute("login");
			if((userVO.getApproval()==2)) {
				if(selector==0) {
					map.put("deptno", "%"+seltext+"%");
					count = service.seldeptnoco(map);
				}else if(selector==1) {
					map.put("dname", "%"+seltext+"%");
					count = service.seldeptnameco(map);
				}else {
					map.put("loc", "%"+seltext+"%");
					count = service.seldeptlocco(map); 
				}
				if(pageNum==0) {
					pageNum=pageNum+1;
				}
				m.addAttribute("count",count);
				m.addAttribute("pageNum",pageNum);
				m.addAttribute("selector",selector);
				m.addAttribute("seltext",seltext);
				return "dept/deptlistsearch";
			}
			return "dept/deptlistsearch";

		}
	
	@RequestMapping(value = "/adddept", method = RequestMethod.GET)
	public String adddept(HttpSession hs,Model m) {
		UserVO uv = (UserVO)hs.getAttribute("login");
		if(uv.getApproval()==2) {
			try {
				return "dept/adddept";			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/HRMProjectMain/jsp/user/mainadmin";
		}else {
			return "/HRMProjectMain/jsp/user/mainadmin";
		}
	}
	@RequestMapping(value = "/deldept", method = RequestMethod.GET)
	public String deldept(HttpSession hs,Model m,int deptno) {
		UserVO uv = (UserVO)hs.getAttribute("login");
		if(uv.getApproval()==2) {
			try {
				m.addAttribute("deptno", deptno);
				return "dept/deldept";	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "/HRMProjectMain/jsp/user/mainadmin";
		}else {
			return "/HRMProjectMain/jsp/user/mainadmin";
		}
	}
	
	@RequestMapping(value = "/deptlist", method = RequestMethod.POST)
	@ResponseBody
	public String deptlistPOST(HttpSession hs) {
		UserVO uv = (UserVO)hs.getAttribute("login");
		try {
			if(uv.getApproval()==2) {
			List<DeptDTO> list = service.seldeptAll();
			Gson json = new Gson(); 
			return json.toJson(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//ajax search ready 
		@RequestMapping(value = "/deptlistsearch", method = RequestMethod.POST)
		@ResponseBody
		public String appboardsearchPOST(HttpSession hs, int selector,String seltext, int pageNum, Model m) throws Exception {
			Map<String,String> map = new HashMap<String, String>();
			List<DeptDTO> list = null;
			UserVO userVO = (UserVO)hs.getAttribute("login");
			map.put("userid",userVO.getId());
			if(userVO.getApproval()==2){
				if(selector==0) {
					map.put("deptno", "%"+seltext+"%");
					list = service.seldeptno(map);
				}else if(selector==1) {
					map.put("dname", "%"+seltext+"%");
					list = service.seldeptname(map);
				}else {
					map.put("loc", "%"+seltext+"%");
					list = service.seldeptloc(map);
				}		
			}
			m.addAttribute("pageNum", pageNum);
			m.addAttribute("selector",selector);
			m.addAttribute("seltext",seltext);
			Gson json = new Gson(); 		
			return json.toJson(list);
		}
	
	
	@RequestMapping(value = "/adddept", method = RequestMethod.POST)
	public String adddeptPOST(HttpSession hs,Model m,DeptDTO dd) {
		UserVO uv = (UserVO)hs.getAttribute("login");
		if(uv.getApproval()==2) {
			try {
				service.insertdept(dd);
				return "redirect:/jsp/dept/deptlist?pageNum=1";	
			} catch (Exception e) {
			}
		}
		return "redirect:/jsp/dept/deptlist?pageNum=1";	
	}
	
	@RequestMapping(value = "/deptupdate", method = RequestMethod.POST)
	public String deptupdatePOST(HttpSession hs,Model m,DeptDTO dd) {
		UserVO uv = (UserVO)hs.getAttribute("login");
		if(uv.getApproval()==2) {
			try {
				service.deptupdate(dd);
				return "redirect:/jsp/dept/deptlist?pageNum=1";	
			} catch (Exception e) {
			}
		}
		return "redirect:/jsp/dept/deptlist?pageNum=1";	
	}
	
	//부서삭제 처리
		@RequestMapping(value = "/deldept", method = RequestMethod.POST)
		@ResponseBody
		public String deldeptPost(HttpSession hs,int deptno,String passwd, Model m){
			UserVO userVO = (UserVO)hs.getAttribute("login");
			Gson json = new Gson(); 	
			String result;
			if(userVO == null||!BCrypt.checkpw(passwd, userVO.getPasswd())) {//DB에서 받아온 회원정보가 없거나 페스워드 비교값이 일치하지않으면
				result = "비밀번호가 다릅니다!";
				System.out.println(json.toJson(result));
				return json.toJson(result);
			}else {
				try {
					System.out.println("passwd :: " + passwd + "deptno :: " + deptno);
					service.deletedept(deptno);
					result = "삭제처리 되었습니다!";
					System.out.println(json.toJson(result));
					return json.toJson(result);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			result = "삭제처리 되었습니다!";
			System.out.println(json.toJson(result));
			return json.toJson(result);
		}
}
