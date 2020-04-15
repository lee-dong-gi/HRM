package contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contact.domain.ContactDto;
import contact.persistence.ContactDao;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactDao dao;
	
	//연락처 목록 
	@Override
	public List<ContactDto> list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}

	//연락처 등록 
	@Override
	public void insert(ContactDto dto) throws Exception {
		dao.insert(dto);
	}

	
	//연락처 수정 insert
	@Override
	public void update(ContactDto dto) throws Exception {
		dao.update(dto);
	}
	
	//연락처 삭제 
	@Override
	public void delete(int num) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(num);
	}

	@Override
	public ContactDto read(int num) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(num);
	}
	
	

}
