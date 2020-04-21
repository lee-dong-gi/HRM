package contact.persistence;

import java.util.List;
import java.util.Map;

import contact.domain.ContactDto;

public interface ContactDao {
	//연락처 목록
	public List<ContactDto> list() throws Exception;
	
	//연락처 등록 
	public void insert(ContactDto dto) throws Exception;
	
	//연락처 수정 
	public void update(ContactDto dto) throws Exception;
	
	//연락처 삭제 
	public void delete(int num) throws Exception;
	
	//연락처 뷰 페이지 
	public ContactDto read(int num) throws Exception;

	
}
