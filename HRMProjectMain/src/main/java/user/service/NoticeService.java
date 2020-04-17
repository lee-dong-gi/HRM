package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.domain.NoticeDto;
import user.persistence.NoticeDao;

@Service
public class NoticeService {

	@Autowired
	NoticeDao dao;

	// 공지사항 목록
	public List<NoticeDto> getAll(int offset) throws Exception {
		return dao.getAll(offset);
	}

	// 공지사항 내용
	public NoticeDto getBoard(int num) throws Exception {
		return dao.getBoard(num);
	}

	// 공지사항 등록
	public int insertBoard(NoticeDto dto) throws Exception {
		return dao.insertBoard(dto);
	}

	// 공지사항 수정
	public int updateBoard(NoticeDto dto) throws Exception {
		return dao.updateBoard(dto);
	}

	// 공지사항 삭제
	public int deleteBoard(int num) throws Exception {
		return dao.deleteBoard(num);
	}

	// 공지사항 카운트
	public int count() throws Exception {
		return dao.count();
	}

	// 검색한 공지사항 목록
	public List<NoticeDto> search(String s) throws Exception {
		return dao.search(s);
	}

	// 검색한 공지사항 카운트
	public int searchCount(String s) throws Exception {
		return dao.searchCount(s);
	}
}
