package web.socket;


import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.springframework.stereotype.Component;

import chat.controller.ChatController;

@Component
public class HttpSessionWebSocket extends ServerEndpointConfig.Configurator{
	public static final String Session = "Session";

	// EndPointConfig에 HttpSession과 HttpContext를 넣는다. Request와 Response는 웹 요청, 응답시에만 필요한 데이터이기 때문에 필요없다.
	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
	HttpSession hs =(HttpSession) request.getHttpSession();
	HttpSession SESSION = ChatController.getSESSION();
	// HttpRequest로부터 Session을 받는다.
	// HttpSession으로 부터 Context도 받는다.
	config.getUserProperties().put(HttpSessionWebSocket.Session, hs);
	config.getUserProperties().put(SESSION.getId(), SESSION);
	System.out.println(SESSION.getAttribute("login"));
	}
}



