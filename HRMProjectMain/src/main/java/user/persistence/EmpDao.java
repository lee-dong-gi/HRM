package user.persistence;

import user.domain.EmpDto;

public interface EmpDao {
	
	void register(EmpDto dto) throws Exception;

	String idCheck(String id) throws Exception;

	EmpDto info(int empno) throws Exception;

	String findId(String name, String email) throws Exception;

	String findPw(String id, String email) throws Exception;
	
	int up(String passwd, String id) throws Exception;

	int signup();

	int signupCount();

}
