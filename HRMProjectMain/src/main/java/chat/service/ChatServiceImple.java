package chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.domain.ChatDTO;
import chat.persistence.ChatDAO;

@Service
public class ChatServiceImple implements ChatService {
	
	@Autowired
	private ChatDAO chatdao;
	
	@Override
	public List<ChatDTO> selroomAll() throws Exception {
		return chatdao.SelRoomAll();
	}

	@Override
	public void insertroom(ChatDTO chatdto) throws Exception {
		chatdao.InsertRoom(chatdto);
	}

	@Override
	public void deleteroom(List<Integer> chatroomnum) throws Exception {
		chatdao.DeleteRoom(chatroomnum);
	}

}
