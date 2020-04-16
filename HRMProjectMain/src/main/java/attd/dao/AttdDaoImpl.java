package attd.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import attd.model.AttdDto;

public class AttdDaoImpl extends SqlSessionDaoSupport implements AttdDao {

	private static String mapperName = "attd.attdMappers";

	// 근태 출근
	@Override
	public void insertAttd(AttdDto attdDto) throws Exception {
		getSqlSession().insert(mapperName + ".insertAttd", attdDto);
	}

	// 지각률
	@Override
	public int countLate(String name) throws Exception {
		return getSqlSession().selectOne(mapperName + ".countLate", name);
	}
	
	// 근태 조회
	@Override
	public List<AttdDto> selAttd(String name) throws Exception {
		return getSqlSession().selectList(mapperName + ".selAttd", name);
	}

	// 부서명
	@Override
	public String getDname(int deptno) throws Exception {
		return getSqlSession().selectOne(mapperName + ".getDname", deptno);
	}

	// 근태 퇴근
	@Override
	public int uptOff(int attd_no) throws Exception {
		return getSqlSession().update(mapperName + ".uptOff", attd_no);
	}
}