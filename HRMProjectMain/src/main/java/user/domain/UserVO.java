package user.domain;

import java.sql.Timestamp;

public class UserVO {

	private int empno;
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String phonenum;
	private Timestamp hiredate;
	private Timestamp birth;
	private int approval;
	private String level;
	private int deptno;
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public Timestamp getHiredate() {
		return hiredate;
	}
	public void setHiredate(Timestamp hiredate) {
		this.hiredate = hiredate;
	}
	public Timestamp getBirth() {
		return birth;
	}
	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	@Override
	public String toString() {
		return "UserVO [empno=" + empno + ", id=" + id + ", passwd=" + passwd + ", name=" + name + ", email="
				+ email + ", phonenum=" + phonenum + ", hiredate=" + hiredate + ", birth=" + birth + ", approval="
				+ approval + ", level=" + level + ", deptno=" + deptno + "]";
	}
	
	
	
	
}
