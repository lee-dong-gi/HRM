package user.persistence;

import java.util.List;

import user.domain.CommentDto;
import user.domain.FreeBoardDto;

public interface FreeDao {
	
	List<FreeBoardDto> free() throws Exception;
	
	FreeBoardDto count(int num) throws Exception;
	
	FreeBoardDto ins(FreeBoardDto dto) throws Exception;
	
	int up(FreeBoardDto dto) throws Exception;
	
	int del(int num) throws Exception;
	
	int commentCount(int num) throws Exception;

	List<CommentDto> comment(int num) throws Exception;

	int commentIns(CommentDto dto) throws Exception;

	int commentDel(int num) throws Exception;

	int count() throws Exception;

	int commentDel2(int num) throws Exception;
	
	List<FreeBoardDto> search(String s) throws Exception;
	
	int searchCount(String s) throws Exception;
	
	List<FreeBoardDto> all(int offset) throws Exception;
	
	FreeBoardDto commentCount2(int num) throws Exception;
}
