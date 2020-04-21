package mail.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MailDao extends SqlSessionDaoSupport{

	public void updatemailcheck(int empno) throws Exception {
		getSqlSession().insert("user.UserMapper.updatemailcheck",empno);
		
	}
}
