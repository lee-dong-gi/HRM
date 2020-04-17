package user.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FreeBoardDto {
	
	private int num;
	private String id;
	private String writer;
	private String subject;	
	private String content;
	private Date time;
	private int ccount;
	
	
	public int getCcount() {
		return ccount;
	}
	public void setCcount(int ccount) {
		this.ccount = ccount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String s = sdf.format(time);
		return s;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	
}
