package chat.persistence;

import java.util.List;

import chat.domain.ChatDTO;

public interface ChatDAO {

	List<ChatDTO> SelRoomAll() throws Exception;
	
	void InsertRoom(ChatDTO chatdto) throws Exception;
	
	void DeleteRoom(List<Integer> chatroomnum) throws Exception;
	
}
