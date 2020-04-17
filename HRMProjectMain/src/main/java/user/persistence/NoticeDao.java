package user.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import user.domain.NoticeDto;

public class NoticeDao extends SqlSessionDaoSupport {

	// 공지사항 목록
	public List<NoticeDto> getAll(int offset) throws Exception {
		return getSqlSession().selectList("notice.all",offset);
	}
	
	// 공지사항 내용
	public NoticeDto getBoard(int num) throws Exception {
		return getSqlSession().selectOne("notice.get", num);
	}
	
	// 공지사항 등록
	public int insertBoard(NoticeDto dto) throws Exception {
		return getSqlSession().insert("notice.ins", dto);
	}
	
	
	// 공지사항 수정
	public int updateBoard(NoticeDto dto) throws Exception {
		return getSqlSession().update("notice.upd", dto);
	}
	
	// 공지사항 삭제
	public int deleteBoard(int num) throws Exception {
		return getSqlSession().delete("notice.del", num);
	}
	
	// 공지사항 카운트
	public int count() throws Exception {
		return getSqlSession().selectOne("notice.count");
	}
	
	// 검색한 공지사항 목록
	public List<NoticeDto> search(String s) throws Exception {
		return getSqlSession().selectList("notice.search", s);
	}
	
	// 검색한 공지사항 카운트
	public int searchCount(String s) throws Exception {
		return getSqlSession().selectOne("notice.searchCount", s);
	}
}
