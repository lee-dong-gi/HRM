package user.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import user.domain.CalendarDto;

public class CalendarDao extends SqlSessionDaoSupport {

	public void register(CalendarDto dto) throws Exception {
		getSqlSession().insert("cal.register", dto);
	}
	
	public List<CalendarDto> check(String now) throws Exception {
		return getSqlSession().selectList("cal.check", now);
	}
	
	public CalendarDto show(int num) throws Exception {
		return getSqlSession().selectOne("cal.show", num);
	}
	
	public int update(CalendarDto dto) throws Exception {
		return getSqlSession().update("cal.upd", dto);
	}
	
	public int delete(int num) throws Exception {
		return getSqlSession().delete("cal.del", num);
	}
}
