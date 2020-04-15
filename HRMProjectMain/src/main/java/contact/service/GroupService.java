package contact.service;

import java.util.List;

import contact.domain.GroupDto;

public interface GroupService {
	//연락처 목록 
		public List<GroupDto> glist() throws Exception;
		
		//연락처 등록 
		public void ginsert(GroupDto gdto) throws Exception;
		
		//연락처 수정 
		public void gupdate(GroupDto gdto) throws Exception;
		
		//연락처 삭제 
		public void gdelete(int gnum) throws Exception;
		
		
		//연락처 보기 
		public GroupDto gread(int gnum) throws Exception;
}
