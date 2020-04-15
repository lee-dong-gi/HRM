package contact.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import contact.domain.GroupDto;

public class GroupDaoImpl extends SqlSessionDaoSupport implements GroupDao{

	//그룹 목록
	@Override
	public List<GroupDto> glist() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("contact.glist");
	}

	//그룹 목록 뷰 
	@Override
	public GroupDto gread(int gnum) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("contact.gread", gnum);
	}

	//그룹 추가 
	@Override
	public void ginsert(GroupDto gdto) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().insert("contact.ginsert", gdto);
	}

	//그룹 수정 
	@Override
	public void gupdate(GroupDto gdto) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().update("contact.gupdate", gdto);
	}

	//그룹 삭제 
	@Override
	public void gdelete(int gnum) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().delete("contact.gdelete", gnum);
	}

}
