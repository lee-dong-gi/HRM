package mail.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import mail.domain.EmailVO;
import mail.service.EmailService;

@Controller
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	private Map<String,Integer> authMap = new HashMap<String, Integer>();

	@RequestMapping("/send")
	@ResponseBody
	public String sendMail(String inputReceiver, Model m) throws Exception {
		Random r = new Random();
		int authNum =  r.nextInt(10000)+1;
		if(authNum < 1000) {
			authNum=authNum+1000;
		}
		System.out.println(authNum);
		
		authMap.put(Integer.toString(authNum), authNum);
		EmailVO email = new EmailVO();
		boolean result = false;

		String receiver = inputReceiver; // Receiver.

		String subject = "인증코드 발송 메일입니다!";

		String content = "인증코드 : " + authNum;

		email.setReceiver(receiver);

		email.setSubject(subject);

		email.setContent(content);
		
		Gson json = new Gson();
		try {
			result = emailService.sendMail(email);			
			System.out.println("sendmail :: "+result);
		}catch (Exception e) {
			return json.toJson("메일 발송이 실패하였습니다. 메일확인 후 다시시도하여주세요!");
		}
		
		if(result==true) {
			return json.toJson("메일이 발송되었습니다 확인해주세요!");
		}else {			
			return json.toJson("메일 발송이 실패하였습니다. 메일확인 후 다시시도하여주세요!");
		}

	}
	
	@RequestMapping("/checkmail")
	@ResponseBody
	public String checkmail(int checkNum) throws Exception {

		String result;
		System.out.println(checkNum);
		String auth=Integer.toString(checkNum);
		if(authMap.get(auth)==checkNum) {
			result = "인증되었습니다!";
			authMap.remove(auth);
		}else {
			result = "인증번호가 다릅니다!";
		}
		System.out.println(result);
		Gson json = new Gson();
		return json.toJson(result);
		
	}

}