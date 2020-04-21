package user.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import user.domain.EmpDto;

public class ManageDao extends SqlSessionDaoSupport {

	public List<EmpDto> sel() throws Exception {
		return getSqlSession().selectList("man.sel");
	}
	
	public EmpDto emp(String id) throws Exception {
		return getSqlSession().selectOne("man.emp", id);
	}
	
	public int upd(String id, String level, int salary, int deptno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("level", level);
		map.put("salary", salary);
		map.put("deptno", deptno);
		return getSqlSession().update("man.upd", map);
	}
	
	public int del(String id) {
		return getSqlSession().delete("man.del", id);
	}
}
