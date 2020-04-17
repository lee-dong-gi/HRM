package attd.model;

import java.util.Date;

public class AttdDto {

	private int attd_no; // 근태 게시판 번호
	private int empno; // 사번
	private String dname; // 부서명
	private String name; // 사원명
	private Date attd_time; // 출근 시간
	private Date off_time; // 퇴근 시간
	private String emp_late; // 지각
	
	public int getAttd_no() {
		return attd_no;
	}
	public void setAttd_no(int attd_no) {
		this.attd_no = attd_no;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAttd_time() {
		return attd_time;
	}
	public void setAttd_time(Date attd_time) {
		this.attd_time = attd_time;
	}
	public Date getOff_time() {
		return off_time;
	}
	public void setOff_time(Date off_time) {
		this.off_time = off_time;
	}
	public String getEmp_late() {
		return emp_late;
	}
	public void setEmp_late(String emp_late) {
		this.emp_late = emp_late;
	}
	@Override
	public String toString() {
		return "AttdDto [attd_no=" + attd_no + ", empno=" + empno + ", dname=" + dname + ", name=" + name
				+ ", attd_time=" + attd_time + ", off_time=" + off_time + ", emp_late=" + emp_late + "]";
	}
}