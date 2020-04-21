package app.persistence;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import app.domain.AppDTO;
import app.domain.KategorieDTO;
import user.domain.UserVO;
import user.domain.UserVOD;

public class AppDAOImpl extends SqlSessionDaoSupport implements AppDAO {
	
	private static final String NAMESPACEAPP = "app.AppMappers";
	
		//결재현황 게시판 처리
		@Override
		public void insertApp(AppDTO appdto) throws Exception {
			getSqlSession().insert(NAMESPACEAPP+".insertApp",appdto);
			
		}
		
		//결재 업데이트
		@Override
		public void updateApp(AppDTO appdto) throws Exception {
			getSqlSession().update(NAMESPACEAPP+".appupdate",appdto);
		}
		
		//결재 삭제
		@Override
		public void deleteApp(int appnum) throws Exception {
			getSqlSession().delete(NAMESPACEAPP+".appdelete",appnum);
			
		}
		
		//결재게시판 내용 불러오기
		@Override
		public List<AppDTO> selApp(String userid) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appBoard", userid);
			
		}
		
		//결제 게시글 가져오기
		@Override
		public int appCount(String userid) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appcount",userid);
		}

		@Override
		public List<AppDTO> appSearchw(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchw", map);
		}

		@Override
		public List<AppDTO> appSearchs(Map<String, String> map) throws Exception {	
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchs", map);
		}

		@Override
		public List<AppDTO> appSearchf(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchf", map);
		}

		@Override
		public AppDTO appContent(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appcontent", map);
		}

		@Override
		public int appSearchwco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchwco", map);
		}

		@Override
		public int appSearchsco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchsco", map);
		}

		@Override
		public int appSearchfco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchfco", map);
		}

		@Override
		public List<AppDTO> appBoardAll(Map map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appBoardAll", map);
		}

		@Override
		public int appcountAll(Map map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appcountAll", map);
		}

		@Override
		public AppDTO appContentm(int appnum) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appcontentm", appnum);
		}

		@Override
		public void okapp(int appnum) throws Exception {
			getSqlSession().selectOne(NAMESPACEAPP+".okapp", appnum);		
		}

		@Override
		public void noapp(int appnum) throws Exception {
			getSqlSession().selectOne(NAMESPACEAPP+".noapp", appnum);
		}
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		@Override
		public List<AppDTO> appSearchwadmin(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchwadmin", map);
		}

		@Override
		public List<AppDTO> appSearchsadmin(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchsadmin", map);
		}

		@Override
		public List<AppDTO> appSearchfadmin(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchfadmin", map);
		}

		@Override
		public int appSearchwcoadmin(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchwcoadmin", map);
		}

		@Override
		public int appSearchscoadmin(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchscoadmin", map);
		}

		@Override
		public int appSearchfcoadmin(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchfcoadmin", map);
		}
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

		@Override
		public List<UserVOD> seluserall(String userid) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".seluserall", userid);
		}

		@Override
		public List<UserVOD> seldeptup(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".seldeptup", map);
		}

		@Override
		public List<UserVOD> seldeptdown(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".seldeptdown", map);
		}

		@Override
		public List<UserVOD> selnamedown(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".selnamedown", map);
		}

		@Override
		public List<UserVOD> selempnodown(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".selempnodown", map);
		}

		@Override
		public void appoint(List<Integer> list) throws Exception {
			getSqlSession().update(NAMESPACEAPP+".appoint", list);	
		}

		@Override
		public void revoke(List<Integer> list) throws Exception {
			getSqlSession().update(NAMESPACEAPP+".revoke", list);
		}

		@Override
		public int seluserallco(String userid) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".seluserallco", userid);
		}

		@Override
		public int seldeptupco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".seldeptupco", map);
		}

		@Override
		public int seldeptdownco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".seldeptdownco", map);
		}

		@Override
		public int selnamedownco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".selnamedownco", map);
		}

		@Override
		public int selempnodownco(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".selempnodownco", map);
		}

		@Override
		public int progressApp(String userid) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".progressapp", userid);
		}
		
		@Override
		public int progressappcoAll(Map map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".progressappcoAll", map);
		}

		@Override
		public List<KategorieDTO> getkategorie() throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".getkategorie");
		}

		@Override
		public List<UserVO> getuserinfo(int deptno) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".getuserinfo",deptno);
		}

		@Override
		public UserVO seluserone(int empno) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".seluserone",empno);
		}

		@Override
		public List<AppDTO> appSearchwsub(Map<String, Object> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchwsub",map);
		}

		@Override
		public List<AppDTO> appSearchssub(Map<String, Object> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchssub",map);
		}

		@Override
		public List<AppDTO> appSearchfsub(Map<String, Object> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appSearchfsub",map);
		}

		@Override
		public int appSearchwcosub(Map<String, Object> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchwcosub",map);
		}

		@Override
		public int appSearchscosub(Map<String, Object> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchscosub",map);
		}

		@Override
		public int appSearchfcosub(Map<String, Object> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appSearchfcosub",map);
		}

		@Override
		public List<AppDTO> appBoardAlladmin(Map<String, String> map) throws Exception {
			return getSqlSession().selectList(NAMESPACEAPP+".appBoardAlladmin",map);
		}

		@Override
		public int appcountAlladmin(Map<String, String> map) throws Exception {
			return getSqlSession().selectOne(NAMESPACEAPP+".appcountAlladmin",map);
		}	

}
