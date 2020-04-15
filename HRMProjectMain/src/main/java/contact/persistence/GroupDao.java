package contact.persistence;

import java.util.List;

import contact.domain.ContactDto;
import contact.domain.GroupDto;

public interface GroupDao {
	//그룹 목록
	public List<GroupDto> glist() throws Exception;
	
	//그룹 뷰 페이지
	public GroupDto gread(int gnum) throws Exception;
	
	//그룹 추가 
	public void ginsert(GroupDto gdto) throws Exception;
	
	//그룹 수정 
	public void gupdate(GroupDto gdto) throws Exception;
	
	//그룹 삭제 
	public void gdelete(int gnum) throws Exception;
	
	
}
