package contact.persistence;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import contact.domain.ExcelDto;

public class ExcelDaoImpl extends SqlSessionDaoSupport implements ExcelDao{

	@Override
	public void insertExcel(ExcelDto ed) {
		// TODO Auto-generated method stub
		getSqlSession().insert("contact.insertExcel", ed);
	}


}
