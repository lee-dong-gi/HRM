package chat.service;

import java.util.List;

import chat.domain.ChatDTO;

public interface ChatService {
	
	List<ChatDTO> selroomAll() throws Exception;
	
	void insertroom(ChatDTO chatdto) throws Exception;
	
	void deleteroom(List<Integer> chatroomnum) throws Exception;
	
}
