package chat.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import app.domain.AppDTO;
import chat.domain.ChatDTO;
import chat.service.ChatService;
import user.domain.UserVO;
import web.socket.BroadSocket;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatservice;
	
//	public static HttpSession SESSION;
	private static HttpSession SESSION;
	
	
	public static HttpSession getSESSION() {
		return SESSION;
	}

	public static void setSESSION(HttpSession sESSION) {
		SESSION = sESSION;
	}

	@RequestMapping(value = "/chatbroadcast", method = RequestMethod.GET)
	public String chatbroadcast(HttpSession hs, String chatname,Model m) throws Exception{
		UserVO userVO = (UserVO)hs.getAttribute("login");
		hs.setAttribute("chatname", chatname);
		setSESSION(hs);
		System.out.println(SESSION);
		m.addAttribute("chatname", chatname);
		if(userVO.getApproval()==2) {
			return "chat/chatbroadcast";
		}else {
			return "chat/chatroom";
		}
	}
	
	@RequestMapping(value = "/chatroom", method = RequestMethod.GET)
	public String chatroom(HttpSession hs) throws Exception{
		UserVO userVO = (UserVO)hs.getAttribute("login");
		if(userVO.getApproval()==2) {
			return "chat/chatroomadmin";
		}else {
			return "chat/chatroom";
		}
	}
	
	@RequestMapping(value = "/chatroomadmin", method = RequestMethod.GET)
	public String chatroomadmin(HttpSession hs) throws Exception{
		UserVO userVO = (UserVO)hs.getAttribute("login");
		if(userVO.getApproval()==2) {
			return "chat/chatroomadmin";
		}else {
			return "chat/chatroom";
		}
	}
	
	@RequestMapping(value = "/addchat", method = RequestMethod.GET)
	public String addchat(HttpSession hs,Model m) throws Exception{
		UserVO userVO = (UserVO)hs.getAttribute("login");
		m.addAttribute("creater", userVO.getId());
		return "chat/addchat";
	}
	
	@RequestMapping(value = "/chatbroad", method = RequestMethod.GET)
	public String chatbroad(HttpSession hs, String chatname, Model m){
		hs.setAttribute("chatname", chatname);
		setSESSION(hs);
		System.out.println(SESSION);
		m.addAttribute("chatname", chatname);
		return "chat/chatpage";
	}
	
	//ajax chatroom
	@RequestMapping(value = "/chatlist", method = RequestMethod.POST)
	@ResponseBody
	public String chatlist(HttpSession hs) throws Exception {
		List<ChatDTO> list = chatservice.selroomAll();
		Calendar cal = Calendar.getInstance();
		Date date;
		for(int i = 0; i < list.size(); i++) {
			date=list.get(i).getCreationdate();
			cal.setTime(date);
			cal.add(Calendar.HOUR_OF_DAY , -9);
			list.get(i).setFormatdate(getTime(cal)); 
		}
		Gson json = new Gson(); 		
		return json.toJson(list);
	}
	
	//채팅방 삭제
	@RequestMapping(value = "/chatdel", method = RequestMethod.POST)
	public String chatdel(HttpSession hs,@RequestParam(value="checkchat") List<String> checkchat) throws Exception {
		List<String> list = checkchat;
		List<Integer> numlist = new ArrayList<Integer>();
		for(String rs : list) {
			numlist.add(Integer.parseInt(rs));
		}
		chatservice.deleteroom(numlist);
		return "chat/chatroomadmin";
	}
	
	//채팅방 생성
	@RequestMapping(value = "/createroom", method = RequestMethod.POST)
	public String createroom(ChatDTO chatdto) throws Exception{
		chatservice.insertroom(chatdto);
		return "chat/chatroomadmin";
	}
	
	public String getTime(Calendar cal) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date d = new Date(cal.getTimeInMillis());
	    String s = sdf.format(d);
	    return s;
	   }
}
