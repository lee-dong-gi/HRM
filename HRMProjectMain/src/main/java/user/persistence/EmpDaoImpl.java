package user.persistence;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import user.domain.EmpDto;

public class EmpDaoImpl extends SqlSessionDaoSupport implements EmpDao {
	private static final String namespace = "emp";
	@Override
	public void register(EmpDto dto) throws Exception {
		getSqlSession().insert(namespace + ".emp", dto);
	}
	
	@Override
	   public String idCheck(String id) throws Exception {
	      return getSqlSession().selectOne(namespace + ".idCheck", id);
	   }
	
	@Override
	public EmpDto info(int empno) throws Exception{
		return getSqlSession().selectOne(namespace + ".info", empno);
	}
	
	@Override
	public String findId(String name, String email) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		return getSqlSession().selectOne(namespace + ".findId", map);
	}
	
	@Override
	public String findPw(String id, String email) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("email", email);
		return getSqlSession().selectOne(namespace + ".findPw", map);
	}
	
	@Override
	public int up(String passwd, String id) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("passwd", passwd);
		map.put("id", id);
		return getSqlSession().update(namespace + ".up", map);
	}
	
	@Override
	public int signup() {
		return getSqlSession().insert(namespace + ".signup");
	}
	
	@Override
	public int signupCount() {
		return getSqlSession().selectOne(namespace + ".signupCount");
	}
	
}
