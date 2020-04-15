package app.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

import app.service.AppService;
import app.domain.AppDTO;
import user.domain.UserVO;
import user.domain.UserVOD;

@Controller
@RequestMapping("/approve")
public class ApproveController implements ApplicationContextAware{
	
	@Autowired
	private AppService appService; 
	private WebApplicationContext context = null;
	
	@RequestMapping(value = "/appboard", method = RequestMethod.GET)
	public String appboardGET(HttpSession hs,Model m, int pageNum,@Nullable int selectapp){
		UserVO userVO = (UserVO)hs.getAttribute("login");
		int approval = userVO.getApproval(); 
		int count;
		try {
			m.addAttribute("pageNum",pageNum);
			m.addAttribute("approval",approval);
			if(selectapp==0) {
				m.addAttribute("selectapp", 0);
				count = appService.appCo(userVO.getId());
			}else{
				m.addAttribute("selectapp", 1);
				count = appService.appcoall(userVO.getId());
			}
			m.addAttribute("count",count);
			System.out.println(count);
			if(pageNum==0) {pageNum=pageNum+1;}
			if(count==0) {count=1;}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "approve/appboard";
	}
	
		
	//결재수정 페이지
	@RequestMapping(value = "/appupdate", method = RequestMethod.GET)
	public String appupdateGET(HttpSession hs,Model m,int appnum){
		UserVO userVO = (UserVO)hs.getAttribute("login");
		m.addAttribute("writer", userVO.getName());
		m.addAttribute("appnum", appnum);
		try {
			AppDTO appdto = appService.appcontentm(appnum);
			m.addAttribute("appdto", appdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "approve/appupdate";
	}
	
	//결재수정 처리
	@RequestMapping(value = "/appupdate", method = RequestMethod.POST)
	public String appupdatePOST(AppDTO appdto, HttpSession hs,@Nullable MultipartFile appfiles, Model m, int appnum){
		UserVO userVO = (UserVO)hs.getAttribute("login");
		try {
			appdto.setUserid(userVO.getId());
			appdto.setAppnum(appnum);
		if(appfiles.getSize()!=0) {
			String fileName = appfiles.getOriginalFilename();
			String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			UUID uuid = UUID.randomUUID();
			String newFileName = uuid.toString() + extension;
			File file = new File("D://uploads/"+newFileName);
			appfiles.transferTo(file);
			appdto.setPathname("D://uploads/"+newFileName);
			appdto.setRealfilename(appfiles.getOriginalFilename());
		}else {
			appdto.setRealfilename("");
			appdto.setPathname("");
		}
		appService.updateapp(appdto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String view = "redirect:appcontent?appnum="+appnum+"&selectapp=0";
		return view;
	}
	
	//결재삭제 뷰
	@RequestMapping(value = "/appdel", method = RequestMethod.GET)
	public String appdel(Model m,int appnum){		
		m.addAttribute("appnum", appnum);
		String view = "approve/appdel";
		return view;
	}
	
	//결재삭제 처리
	@RequestMapping(value = "/appdel", method = RequestMethod.POST)
	@ResponseBody
	public String appdelPost(HttpSession hs,int appnum,String passwd, Model m){
		UserVO userVO = (UserVO)hs.getAttribute("login");
		Gson json = new Gson(); 	
		String result;
		if(userVO == null||!BCrypt.checkpw(passwd, userVO.getPasswd())) {//DB에서 받아온 회원정보가 없거나 페스워드 비교값이 일치하지않으면
			result = "비밀번호가 다릅니다!";
			System.out.println(json.toJson(result));
			return json.toJson(result);
		}else {
			try {
				System.out.println("passwd :: " + passwd + "appnum :: " + appnum);
				appService.deleteapp(appnum);
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
	//결재쓰기 페이지
	@RequestMapping(value = "/appwrite", method = RequestMethod.GET)
	public String appwriteGET(HttpSession hs,Model m){
		UserVO userVO = (UserVO)hs.getAttribute("login");
		m.addAttribute("writer", userVO.getName());
		return "approve/appwrite";
	}
	//결재쓰기 처리
	@RequestMapping(value = "/appwrite", method = RequestMethod.POST)
	public String appwrite(AppDTO appdto, HttpSession hs,@Nullable MultipartFile appfiles, Model m) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		appdto.setUserid(userVO.getId());

		if(appfiles.getSize()!=0) {
			String fileName = appfiles.getOriginalFilename();
			String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			UUID uuid = UUID.randomUUID();
			String newFileName = uuid.toString() + extension;
			File file = new File("D://uploads/"+newFileName);
			appfiles.transferTo(file);
			appdto.setPathname("D://uploads/"+newFileName);
			appdto.setRealfilename(appfiles.getOriginalFilename());
		}else {
			appdto.setRealfilename("");
			appdto.setPathname("");
		}
		appService.insertapp(appdto);
		m.addAttribute("pageNum", 1);
		return"redirect:appboard?pageNum=1&selectapp=0";
	}
	
	//date 포멧
	public String getTime(Calendar cal) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date d = new Date(cal.getTimeInMillis());
	    String s = sdf.format(d);
	    return s;
	   }
	
	//ajax ready mainpage
	@RequestMapping(value = "/mainapp", method = RequestMethod.POST)
	@ResponseBody
	public String mainapp(HttpSession hs,Model m,int approval) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		int count;
		List<Integer> list= new ArrayList<Integer>();
		if(approval==0) {
			count = appService.progressapp(userVO.getId());
			list.add(count);
		}else {
			count = appService.progressapp(userVO.getId());			
			int othercount = appService.progressappcoall(userVO.getId());		
			list.add(count);
			list.add(othercount);
		}
		Gson json = new Gson();
		return json.toJson(list);
	}
	
	//ajax ready 내 결재
	@RequestMapping(value = "/applist", method = RequestMethod.POST)
	@ResponseBody
	public String appboardPOST(HttpSession hs,Model m) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		List<AppDTO> list = appService.selapp(userVO.getId());
		Calendar cal = Calendar.getInstance();
		Date date;
		for(int i = 0; i < list.size(); i++) {
			date=list.get(i).getWritedate();
			cal.setTime(date);
			cal.add(Calendar.HOUR_OF_DAY , -9);
			list.get(i).setFormatdate(getTime(cal)); 
		}
		Gson json = new Gson();
		return json.toJson(list);
	}
	
	//ajax ready 타 결재
	@RequestMapping(value = "/selectother", method = RequestMethod.POST)
	@ResponseBody
	public String selectother(HttpSession hs) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		List<AppDTO> list;
		if((int)userVO.getApproval()!=0) {
			list = appService.appboardall(userVO.getId());
		}else {
			list = appService.selapp(userVO.getId());
		}
		Calendar cal = Calendar.getInstance();
		Date date;
		for(int i = 0; i < list.size(); i++) {
			date=list.get(i).getWritedate();
			cal.setTime(date);
			cal.add(Calendar.HOUR_OF_DAY , -9);
			list.get(i).setFormatdate(getTime(cal)); 
		}
		Gson json = new Gson(); 		
		return json.toJson(list);
	}
	
	//ajax search ready 
	@RequestMapping(value = "/applistsearch", method = RequestMethod.POST)
	@ResponseBody
	public String appboardsearchPOST(HttpSession hs, int selector,String seltext, int pageNum,int selectapp, Model m) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		List<AppDTO> list;
		UserVO userVO = (UserVO)hs.getAttribute("login");
		map.put("userid",userVO.getId());
		System.out.println("selectapp :: "+selectapp);
		System.out.println("approval :: "+userVO.getApproval());
		if((userVO.getApproval()==1|userVO.getApproval()==2)&selectapp==1) {
			if(selector==0) {
				map.put("writer", "%"+seltext+"%");
				list = appService.appsearchwadmin(map);
			}else if(selector==1) {
				map.put("subject", "%"+seltext+"%");
				list = appService.appsearchsadmin(map);
			}else {
				map.put("realfilename", "%"+seltext+"%");
				list = appService.appsearchfadmin(map);
			}		
		}else {
			if(selector==0) {
				map.put("writer", "%"+seltext+"%");
				list = appService.appsearchw(map);
			}else if(selector==1) {
				map.put("subject", "%"+seltext+"%");
				list = appService.appsearchs(map);
			}else {
				map.put("realfilename", "%"+seltext+"%");
				list = appService.appsearchf(map);
			}		
		}
		Calendar cal = Calendar.getInstance();
		Date date;
		for(int i = 0; i < list.size(); i++) {
			date=list.get(i).getWritedate();
			cal.setTime(date);
			cal.add(Calendar.HOUR_OF_DAY , -9);
			list.get(i).setFormatdate(getTime(cal)); 
		}
		m.addAttribute("selectapp", selectapp);
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("selector",selector);
		m.addAttribute("seltext",seltext);
		Gson json = new Gson(); 		
		return json.toJson(list);
	}
	
	//search
	@RequestMapping(value = "/selectArticle")
	public String selectArticle(HttpSession hs,int pageNum, int selector,String seltext, Model m, int selectapp) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		int count;
		UserVO userVO = (UserVO)hs.getAttribute("login");
		map.put("userid",userVO.getId());
		if((userVO.getApproval()==1|userVO.getApproval()==2)&selectapp==1) {
			if(selector==0) {
				map.put("writer", "%"+seltext+"%");
				count = appService.appSearchwcoadmin(map);
			}else if(selector==1) {
				map.put("subject", "%"+seltext+"%");
				count = appService.appSearchscoadmin(map);
			}else {
				map.put("realfilename", "%"+seltext+"%");
				count = appService.appSearchfcoadmin(map);
			}
		}else {
			if(selector==0) {
				map.put("writer", "%"+seltext+"%");
				count = appService.appSearchwco(map);
			}else if(selector==1) {
				map.put("subject", "%"+seltext+"%");
				count = appService.appSearchsco(map);
			}else {
				map.put("realfilename", "%"+seltext+"%");
				count = appService.appSearchfco(map);
			}
		}
		try {
			System.out.println(count);
			if(pageNum==0) {
				pageNum=pageNum+1;
			}
			m.addAttribute("count",count);
			m.addAttribute("pageNum",pageNum);
			m.addAttribute("selector",selector);
			m.addAttribute("seltext",seltext);
			m.addAttribute("selectapp", selectapp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "approve/appboardsearch";
	}

	
	//글 내용
	@RequestMapping(value = "/appcontent",method = RequestMethod.GET)
	public String appcontent(HttpSession hs,int appnum,int selectapp, Model m) {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		Map<String,String> map = new HashMap<String, String>();
		AppDTO appdto;
		try {
			if(selectapp==0) {
				map.put("userid", userVO.getId());
				map.put("appnum", Integer.toString(appnum));
				appdto = appService.appcontent(map);
			}else{
				map.put("appnum", Integer.toString(appnum));
				appdto = appService.appcontentm(appnum);
			}
			Calendar cal = Calendar.getInstance();
			Date date;
	
			date=appdto.getWritedate();
			cal.setTime(date);
			cal.add(Calendar.HOUR_OF_DAY , -9);
			appdto.setFormatdate(getTime(cal)); 
			
			m.addAttribute("appdto",appdto);
			m.addAttribute("appresult",appdto.getAppresult());
			m.addAttribute("approval", userVO.getApproval());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "approve/appcontent";
	}
	
	@RequestMapping("/file")//download
	public ModelAndView download(HttpSession hs,int appnum, Model m) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		String userid = userVO.getId();
		File downloadFile = getFile(userid,appnum,m);
		return new ModelAndView("download", "downloadFile", downloadFile);//빈네임 뷰리졸버 찾아감(빈네임,KEY,VALUE)
	}

	private File getFile(String userid, int appnum, Model m) {	
		AppDTO appdto;
		try {
			appdto = appService.appcontentm(appnum);
			m.addAttribute("realfilename", appdto.getRealfilename());
			return new File(appdto.getPathname());
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	//승인
	@RequestMapping(value ="/okapp", method = RequestMethod.POST)
	public String okapp(HttpSession hs, int appnum) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		int approval = (int)userVO.getApproval();
		if(approval==1|approval==2) {//2차검증
			appService.okApp(appnum);
		}
		return "redirect:/jsp/approve/appcontent?appnum="+appnum+"&selectapp=1";
	}
	
	//반려
	@RequestMapping(value ="/noapp", method = RequestMethod.POST)
	public String noapp(HttpSession hs, int appnum) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		int approval = (int)userVO.getApproval();
		if(approval==1|approval==2) {//2차검증
			appService.noApp(appnum);
		}
		return "redirect:/jsp/approve/appcontent?appnum="+appnum+"&selectapp=1";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}//ApplicationContextAware 이걸 구현하고있다면 setter 자동호출
	
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ관리자ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	//관리자 페이지
	@RequestMapping(value = "/appadmin", method = RequestMethod.GET)
	public String appadmin(HttpSession hs, Model m, int pageNum){
		UserVO userVO = (UserVO)hs.getAttribute("login");
		if(userVO.getApproval()==2) {//관리자만 접근 가능
			try {
				String userid = userVO.getId();
				int count = appService.seluserAllco(userid);
				m.addAttribute("count", count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			m.addAttribute("pageNum", pageNum);
			return "approve/appadmin";			
		}else {
			return "redirect:/jsp/user/main";						
		}
	}
	
	@RequestMapping(value = "/appadmin", method = RequestMethod.POST)
	@ResponseBody
	public String appadmin(HttpSession hs, int pageNum,Model m) throws Exception {
		UserVO userVO = (UserVO)hs.getAttribute("login");
		List<UserVOD> list;
		if(userVO.getApproval()==2) {
			m.addAttribute("pageNum", pageNum);
			list = appService.seluserAll(userVO.getId());
		}else {
			list = null;
		}
		Gson json = new Gson(); 		
		return json.toJson(list);
	}
	
	@RequestMapping(value = "/selArticleadmin")
	public String selectArticleadmin(HttpSession hs,int pageNum, int selector,String seltext, Model m) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		int count;
		UserVO userVO = (UserVO)hs.getAttribute("login");
		String userid;
		userid = userVO.getId();
		System.out.println(userVO.getId());
		System.out.println(userid);
		map.put("userid",userid);
		
			if(selector==0) {
				map.put("username", "%"+seltext+"%");
				count = appService.selnameDownco(map);
			}else if(selector==1) {
				map.put("dname", "%"+seltext+"%");
				count = appService.seldeptDownco(map);
			}else {
				map.put("empno", "%"+seltext+"%");
				count = appService.selempnoDownco(map);
			}

			System.out.println(count);
			if(pageNum==0) {
				pageNum=pageNum+1;
			}
			m.addAttribute("count",count);
			m.addAttribute("pageNum",pageNum);
			m.addAttribute("selector",selector);
			m.addAttribute("seltext",seltext);

		return "approve/appadminsearch";
	}
	
	//ajax search ready 
	@RequestMapping(value = "/applistsearchadmin", method = RequestMethod.POST)
	@ResponseBody
	public String applistsearchadminPOST(HttpSession hs, int selector,String seltext, int pageNum, Model m) throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		List<UserVOD> list;
		UserVO userVO = (UserVO)hs.getAttribute("login");
		int approval = userVO.getApproval();
		map.put("userid",userVO.getId());
		System.out.println("approval :: "+userVO.getApproval());
		if(approval==2) {
			if(selector==0) {
				map.put("username", "%"+seltext+"%");
				list = appService.selnameDown(map);
			}else if(selector==1) {
				map.put("dname", "%"+seltext+"%");
				list = appService.seldeptDown(map);
			}else {
				map.put("empno", "%"+seltext+"%");
				list = appService.selempnoDown(map);
			}
		}else {
			list=null;
		}
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("selector",selector);
		m.addAttribute("seltext",seltext);
		Gson json = new Gson(); 		
		return json.toJson(list);
	}
	
	
    //AJAX 호출 (결제자 해제)
    @RequestMapping(value="/rebokeuser", method = RequestMethod.POST)
    @ResponseBody
    public Object rebokeuser(@RequestParam("checklist[]")List<String> checklist) {

		List<Integer> numlist = new ArrayList<Integer>();
		for(String rs : checklist) {
			numlist.add(Integer.parseInt(rs));
		}
    	
    	try {
			appService.Revoke(numlist);
		} catch (Exception e) {	
			e.printStackTrace();
		}
        
        //리턴값
        Map<String, Object> rsVal = new HashMap<String, Object>();
        
        //성공했다고 처리
        rsVal.put("code", "OK");
        rsVal.put("message", "비결재자로 지정되었습니다.");
        
        return rsVal;

    }
    
    //AJAX 호출 (결제자 지정)
    @RequestMapping(value="/appointuser", method = RequestMethod.POST)
    @ResponseBody
    public Object appointuser(@RequestParam("checklist[]")List<String> checklist) {
    	System.out.println(checklist);
		List<Integer> numlist = new ArrayList<Integer>();
		for(String rs : checklist) {
			numlist.add(Integer.parseInt(rs));
		}
    	
    	try {
			appService.Appoint(numlist);
		} catch (Exception e) {	
			e.printStackTrace();
		}
    	
    	//리턴값
    	Map<String, Object> rsVal = new HashMap<String, Object>();
    	
    	//성공했다고 처리
    	rsVal.put("code", "OK");
    	rsVal.put("message", "결재자로 지정되었습니다.");
    	
    	return rsVal;
    	
    }
	
	
	
}
