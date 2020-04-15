package chat.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import chat.domain.ChatDTO;

public class ChatDAOImpl extends SqlSessionDaoSupport implements ChatDAO {
	
	private static final String NAMESPACECHAT = "chat.ChatMappers";
	
	@Override
	public List<ChatDTO> SelRoomAll() throws Exception {
		return getSqlSession().selectList(NAMESPACECHAT+".selChatRoomAll");
	}

	@Override
	public void InsertRoom(ChatDTO chatdto) throws Exception {
		getSqlSession().insert(NAMESPACECHAT+".insertChatRoom",chatdto);
	}

	@Override
	public void DeleteRoom(List<Integer> chatroomnum) throws Exception {
		getSqlSession().delete(NAMESPACECHAT+".deleteChatRoom",chatroomnum);
		
	}

}
