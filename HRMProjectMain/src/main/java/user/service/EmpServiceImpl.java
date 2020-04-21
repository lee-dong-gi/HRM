package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.domain.EmpDto;
import user.persistence.EmpDao;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpDao dao;

	@Override
	public void register(EmpDto dto) throws Exception {
		dao.register(dto);
	}
	
	@Override
	   public String idCheck(String id) throws Exception {
	      return dao.idCheck(id);
	}
	
	@Override
	public EmpDto info(int empno) throws Exception{
		return dao.info(empno);
	}
	
	@Override
	public String findId(String name, String email) throws Exception {
		return dao.findId(name, email);
	}
	
	@Override
	public String findPw(String id, String email) throws Exception{
		return dao.findPw(id, email);
	}
	
	@Override
	public int up(String passwd, String id) throws Exception{
		return dao.up(passwd, id);
	}
	
	@Override
	public int signup() {
		return dao.signup();
	}
	
	@Override
	public int signupCount() {
		return dao.signupCount();
	}
	
	@Override
	public EmpDto myInfo(String id) throws Exception{
		return dao.myInfo(id);
	}
	
	@Override public int modify(EmpDto dto) throws Exception { 
		return dao.modify(dto);
	}
}
