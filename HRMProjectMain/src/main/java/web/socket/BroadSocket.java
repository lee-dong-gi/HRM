package web.socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.WebSocketSession;

import chat.controller.ChatController;
import user.domain.UserVO;

// WebSocket 호스트 설정
@ServerEndpoint(value = "/broadsocket", configurator=HttpSessionWebSocket.class)
public class BroadSocket{
// 접속 된 클라이언트 WebSocket session 관리 리스트
private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
private static List<HttpSession> HttpsessionUsers = Collections.synchronizedList(new ArrayList<>());
private static List<String> ChatRoom = Collections.synchronizedList(new ArrayList<>());
// 메시지에서 유저 명을 취득하기 위한 정규식 표현
private static Pattern pattern = Pattern.compile("^\\{\\{.*?\\}\\}");
private HttpSession hs;
private String UserChatRoom;

public BroadSocket() {
    // 클라이언트가 접속할 때마다 서버측에서는 Thread 가 새로 생성되는 것을 확인할 수 있다
    String threadName = "Thread-Name:"+Thread.currentThread().getName();
    System.out.println(threadName);
}

public void start(Session userSession, EndpointConfig config) {
	StringTokenizer st;
	String [] array;
	HttpSession SESSION = ChatController.getSESSION();
	sessionUsers.add(userSession);
	hs = (HttpSession)config.getUserProperties().get(SESSION.getId());
	HttpsessionUsers.add(hs);
	UserVO userVO = (UserVO)hs.getAttribute("login");
	UserChatRoom=(String)hs.getAttribute("chatname");
	ChatRoom.add(UserChatRoom+"/"+userSession.getId());
	
	for(int i = 0; i < HttpsessionUsers.size(); i++) {
		System.out.println(HttpsessionUsers.size());
		st=new StringTokenizer(ChatRoom.get(i), "/");
		array= new String[st.countTokens()];
		int j = 0;
		while(st.hasMoreElements()){
			array[j++] = st.nextToken();
		}
		try {
		if(UserChatRoom.equals(array[0])) {
			System.out.println("UserChatRoom :: " + UserChatRoom + "|||ChatRoom :: " + array[0]);
				sessionUsers.get(i).getBasicRemote().sendText("server : "+userVO.getName()+"님이 접속하였습니다!");
		}
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	// 콘솔에 접속 로그를 출력한다.
	System.out.println("클라이언트 접속됨 "+userVO.getName());
}

private synchronized void endUser(Session userSession) {
	StringTokenizer st;
	String [] array;
//	sessionUsers.remove(userSession);
//	HttpsessionUsers.remove(hs);
//	ChatRoom.remove(UserChatRoom+"/"+userSession.getId());
	String chatname = (String)hs.getAttribute("chatname");
	UserVO userVO = (UserVO)hs.getAttribute("login");
	for(int i = 0; i < sessionUsers.size(); i++) {
		System.out.println(sessionUsers.size());
		st=new StringTokenizer(ChatRoom.get(i), "/");
		array= new String[st.countTokens()];
		int j = 0;
		while(st.hasMoreElements()){
			array[j++] = st.nextToken();
		}
		if(chatname.equals(array[0])) {
			System.out.println("UserChatRoom :: " + chatname + "|||ChatRoom :: " + array[0]);
			try {
				sessionUsers.get(i).getBasicRemote().sendText("server : "+userVO.getName()+"님이 퇴장하셨습니다!");
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
}

// WebSocket으로 브라우저가 접속하면 요청되는 함수
@OnOpen
public void handleOpen(Session userSession, EndpointConfig config) {
	start(userSession,config);
	System.out.println(UserChatRoom);
	//채팅방 접속 사용자 정보 출력
	System.out.println(hs.getAttribute("login"));
}





// WebSocket으로 메시지가 오면 요청되는 함수
@OnMessage
public void handleMessage(String message, Session userSession) throws IOException {
	StringTokenizer st;
	String [] array;
// 메시지 내용을 콘솔에 출력한다.
	System.out.println("message :: "+message);
//　초기 유저 명
	String name = "anonymous";
// 메시지로 유저 명을 추출한다.
	Matcher matcher = pattern.matcher(message);//매치가되면 저장되고 아니면 안되는건가?
// 메시지 예: {{유저명}}메시지
	if (matcher.find()) {//주어진 문자열에서 특정 패턴을 찾아낸다. 
			name = matcher.group();//매칭된 부분을 반환한다.
	}
// 클로져를 위해 변수의 상수화
	String msg = message.replaceAll(pattern.pattern(), "");// 패턴과 일치되는 부분을 replacement("")로 대체한다. 
	final String username = name.replaceFirst("^\\{\\{", "").replaceFirst("\\}\\}$", "");//패턴과 일치되는 첫 번째 문자열을 replacement로 대체한다
	st=new StringTokenizer(msg, "****");
	array= new String[st.countTokens()];
	 int j = 0;
	  while(st.hasMoreElements()){
	   array[j++] = st.nextToken();
	  }
	System.out.println(array[1]);
	for(int i = 0; i < sessionUsers.size(); i++ ) {
		Session session = sessionUsers.get(i);
		String chatroom = (String)HttpsessionUsers.get(i).getAttribute("chatname");
		System.out.println(chatroom);
		if(array[1].equals("admin")) {
				session.getBasicRemote().sendText("server : " + array[0]);
		}else {
			if (session == userSession|!(array[1]).equals(chatroom)) {
			}else {
				try {		
					 session.getBasicRemote().sendText(username + " : " + array[0]);
				} catch (IOException e) {
					// 에러가 발생하면 콘솔에 표시한다.
					e.printStackTrace();
				}
			}
		}
	}
}
// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
@OnClose
public void handleClose(Session userSession) {
	System.out.println("endUser실행");
	sessionUsers.remove(userSession);
	HttpsessionUsers.remove(hs);
	ChatRoom.remove(UserChatRoom+"/"+userSession.getId());
	System.out.println(hs.getAttribute("login"));
	try {endUser(userSession);}catch (Exception e) {e.printStackTrace();}
// session 리스트로 접속 끊은 세션을 제거한다.
//	endUser(userSession);
// 콘솔에 접속 끊김 로그를 출력한다.
	
	System.out.println("client is now disconnected...");
	}
}

