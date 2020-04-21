package contact.persistence;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import contact.domain.ContactDto;

@Repository
public class ContactDaoImpl extends SqlSessionDaoSupport  implements ContactDao {
	
	//연락처 목록
	@Override
	public List<ContactDto> list() throws Exception {
		return getSqlSession().selectList("contact.list");
	}

	
	//연락처 등록 
	@Override
	public void insert(ContactDto dto) throws Exception {
		getSqlSession().insert("contact.insert", dto);
	}

	//연락처 수정 
	@Override
	public void update(ContactDto dto) throws Exception {
		getSqlSession().update("contact.update", dto);
	}

	//연락처 삭제 
	@Override
	public void delete(int num) throws Exception {
		getSqlSession().delete("contact.delete", num);
	}


	@Override
	public ContactDto read(int num) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("contact.read", num);
	}


}
