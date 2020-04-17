package user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.domain.CommentDto;
import user.domain.FreeBoardDto;
import user.persistence.FreeDao;

@Service
public class FreeServiceImpl implements FreeService {
	@Autowired
	FreeDao dao;

	@Override
	public List<FreeBoardDto> free() throws Exception {
		return dao.free();
	}

	@Override
	public FreeBoardDto count(int num) throws Exception {
		return dao.count(num);
	}

	@Override
	public FreeBoardDto ins(FreeBoardDto dto) throws Exception {
		return dao.ins(dto);
	}

	@Override
	public int up(FreeBoardDto dto) throws Exception {
		return dao.up(dto);
	}

	@Override
	public int del(int num) throws Exception {
		return dao.del(num);
	}

	@Override
	public int commentCount(int num) throws Exception {
		return dao.commentCount(num);
	}

	@Override
	public List<CommentDto> comment(int num) throws Exception {
		return dao.comment(num);
	}

	@Override
	public int commentIns(CommentDto dto) throws Exception {
		return dao.commentIns(dto);
	}

	@Override
	public int commentDel(int cno) throws Exception {
		return dao.commentDel(cno);
	}
	
	@Override
	public int commentDel2(int num) throws Exception {
		return dao.commentDel2(num);
	}

	@Override
	public int count() throws Exception {
		return dao.count();
	}
	
	@Override
	public List<FreeBoardDto> search(String s) throws Exception {
		return dao.search(s);
	}
	
	@Override
	public int searchCount(String s) throws Exception {
		return dao.searchCount(s);
	}
	
	@Override
	public List<FreeBoardDto> all(int offset) throws Exception {
		return dao.all(offset);
	}
	
	@Override
	public FreeBoardDto commentCount2(int num) throws Exception {
		return dao.commentCount2(num);
	}
}
