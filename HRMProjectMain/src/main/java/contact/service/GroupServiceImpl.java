package contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contact.domain.GroupDto;
import contact.persistence.GroupDao;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDao gdao;
	
	@Override
	public List<GroupDto> glist() throws Exception {
		// TODO Auto-generated method stub
		return gdao.glist();
	}

	@Override
	public void ginsert(GroupDto gdto) throws Exception {
		// TODO Auto-generated method stub
		gdao.ginsert(gdto);
	}

	@Override
	public void gupdate(GroupDto gdto) throws Exception {
		// TODO Auto-generated method stub
		gdao.gupdate(gdto);
	}

	@Override
	public void gdelete(int gnum) throws Exception {
		// TODO Auto-generated method stub
		gdao.gdelete(gnum);
	}

	@Override
	public GroupDto gread(int gnum) throws Exception {
		// TODO Auto-generated method stub
	    return gdao.gread(gnum);	
	}

}
