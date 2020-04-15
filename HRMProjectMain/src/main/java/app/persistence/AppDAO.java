package app.persistence;

import java.util.List;
import java.util.Map;

import app.domain.AppDTO;
import user.domain.UserVO;
import user.domain.UserVOD;

public interface AppDAO {
		//결재현황게시판 처리
		void insertApp(AppDTO appdto) throws Exception;

		void updateApp(AppDTO appdto) throws Exception;
		
		void deleteApp(int appnum) throws Exception;
		
		void okapp(int appnum) throws Exception;
		
		void noapp(int appnum) throws Exception;
		
		//결재 게시판 내용 불러오기
		List<AppDTO> selApp(String userid) throws Exception;
		
		int progressApp(String userid) throws Exception;
		
		int progressappcoAll(String userid) throws Exception;
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		List<AppDTO> appSearchw(Map<String,String> map) throws Exception;
		
		List<AppDTO> appSearchs(Map<String,String> map) throws Exception;
		
		List<AppDTO> appSearchf(Map<String,String> map) throws Exception;
		
		int appSearchwco(Map<String,String> map) throws Exception;
		
		int appSearchsco(Map<String,String> map) throws Exception;
		
		int appSearchfco(Map<String,String> map) throws Exception;
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		List<AppDTO> appSearchwadmin(Map<String,String> map) throws Exception;
		
		List<AppDTO> appSearchsadmin(Map<String,String> map) throws Exception;
		
		List<AppDTO> appSearchfadmin(Map<String,String> map) throws Exception;
		
		int appSearchwcoadmin(Map<String,String> map) throws Exception;
		
		int appSearchscoadmin(Map<String,String> map) throws Exception;
		
		int appSearchfcoadmin(Map<String,String> map) throws Exception;
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		int appCount(String userid) throws Exception;
		
		int appcountAll(String userid) throws Exception;
		
		AppDTO appContent(Map<String,String> map) throws Exception;
		
		AppDTO appContentm(int appnum) throws Exception;
		
		List<AppDTO> appBoardAll(String userid) throws Exception;
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		List<UserVOD> seluserall(String userid) throws Exception;
		
		int seluserallco(String userid) throws Exception;
		
		List<UserVOD> seldeptup(Map<String,String> map) throws Exception;
		
		int seldeptupco(Map<String,String> map) throws Exception;
		
		List<UserVOD> seldeptdown(Map<String,String> map) throws Exception;
		
		int seldeptdownco(Map<String,String> map) throws Exception;
		
		List<UserVOD> selnamedown(Map<String,String> map) throws Exception;
		
		int selnamedownco(Map<String,String> map) throws Exception;
		
		List<UserVOD> selempnodown(Map<String,String> map) throws Exception;
		
		int selempnodownco(Map<String,String> map) throws Exception;
		
		void appoint(List<Integer> list) throws Exception;
		
		void revoke(List<Integer> list) throws Exception;
		
}
