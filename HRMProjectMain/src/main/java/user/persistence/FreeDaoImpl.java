package user.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import user.domain.CommentDto;
import user.domain.FreeBoardDto;

public class FreeDaoImpl extends SqlSessionDaoSupport implements FreeDao {

	@Override
	public List<FreeBoardDto> free() throws Exception {
		return getSqlSession().selectList("free.list");
	}

	@Override
	public FreeBoardDto count(int num) throws Exception {
		return getSqlSession().selectOne("free.num", num);

	}

	@Override
	public FreeBoardDto ins(FreeBoardDto dto) throws Exception {
		return getSqlSession().selectOne("free.ins", dto);

	}

	@Override
	public int up(FreeBoardDto dto) throws Exception {
		return getSqlSession().update("free.up", dto);

	}

	@Override
	public int del(int num) throws Exception {
		return getSqlSession().delete("free.del", num);
	}

	@Override
	public int commentCount(int num) throws Exception {
		return getSqlSession().selectOne("co.count", num);
	}

	@Override
	public List<CommentDto> comment(int num) throws Exception {
		return getSqlSession().selectList("co.list", num);
	}

	@Override
	public int commentIns(CommentDto dto) throws Exception {
		return getSqlSession().insert("co.ins", dto);
	}

	@Override
	public int commentDel(int cno) throws Exception {
		return getSqlSession().delete("co.del", cno);
	}

	@Override
	public int commentDel2(int num) throws Exception {
		return getSqlSession().delete("co.del2", num);
	}

	@Override
	public int count() {
		return getSqlSession().selectOne("free.count");
	}

	@Override
	public List<FreeBoardDto> search(String s, int offset) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("s", s);
		map.put("offset", offset);
		return getSqlSession().selectList("free.search", map);
	}

	@Override
	public int searchCount(String s) throws Exception {
		return getSqlSession().selectOne("free.searchCount", s);
	}
	
	@Override
	public List<FreeBoardDto> search2(String s, int offset) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("s", s);
		map.put("offset", offset);
		return getSqlSession().selectList("free.search2", map);
	}

	@Override
	public int searchCount2(String s) throws Exception {
		return getSqlSession().selectOne("free.searchCount2", s);
	}

	@Override
	public List<FreeBoardDto> all(int offset) throws Exception {
		return getSqlSession().selectList("free.all", offset);
	}

	@Override
	public FreeBoardDto commentCount2(int num) throws Exception {
		return getSqlSession().selectOne("free.commentCount", num);
	}
}
