package user.persistence;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import user.domain.DeptDTO;

public class DeptDao extends SqlSessionDaoSupport{
	
	public List<DeptDTO> seldeptall() throws Exception {
		return getSqlSession().selectList("user.UserMapper.seldeptall");
	}
	public List<DeptDTO> seldeptno(Map map) throws Exception {
		return getSqlSession().selectList("user.UserMapper.seldeptno",map);
	}
	public List<DeptDTO> seldeptname(Map map) throws Exception {
		return getSqlSession().selectList("user.UserMapper.seldeptname",map);
	}
	public List<DeptDTO> seldeptloc(Map map) throws Exception {
		return getSqlSession().selectList("user.UserMapper.seldeptloc",map);
	}
	public DeptDTO seldeptone(int deptno) throws Exception {
		return getSqlSession().selectOne("user.UserMapper.seldeptone",deptno);
	}
	
	public int seldeptnoco(Map map) throws Exception {
		return getSqlSession().selectOne("user.UserMapper.seldeptnoco",map);
	}
	public int seldeptnameco(Map map) throws Exception {
		return getSqlSession().selectOne("user.UserMapper.seldeptnameco",map);
	}
	public int seldeptlocco(Map map) throws Exception {
		return getSqlSession().selectOne("user.UserMapper.seldeptlocco",map);
	}
	
	public int seldeptallco() throws Exception {
		return getSqlSession().selectOne("user.UserMapper.seldeptallco");
	}
	public int insertdept(DeptDTO dd) throws Exception {
		return getSqlSession().insert("user.UserMapper.insertdept",dd);
	}
	public int deptupdate(DeptDTO dd) throws Exception {
		return getSqlSession().update("user.UserMapper.deptupdate",dd);
	}
	public int deletedept(int deptno) throws Exception {
		return getSqlSession().insert("user.UserMapper.deletedept",deptno);
	}
} 
