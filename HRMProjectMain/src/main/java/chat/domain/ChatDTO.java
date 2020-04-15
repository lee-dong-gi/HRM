package chat.domain;

import java.util.Date;


public class ChatDTO {
	int chatroomnum;
	String creatorid;
	String chatroomname;
	String description;
	Date creationdate;
	String formatdate;
	public int getChatroomnum() {
		return chatroomnum;
	}
	public void setChatroomnum(int chatroomnum) {
		this.chatroomnum = chatroomnum;
	}
	public String getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	public String getChatroomname() {
		return chatroomname;
	}
	public void setChatroomname(String chatroomname) {
		this.chatroomname = chatroomname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public String getFormatdate() {
		return formatdate;
	}
	public void setFormatdate(String formatdate) {
		this.formatdate = formatdate;
	}
	@Override
	public String toString() {
		return "ChatDTO [chatroomnum=" + chatroomnum + ", creatorid=" + creatorid + ", chatroomname=" + chatroomname
				+ ", description=" + description + ", creationdate=" + creationdate + ", formatdate=" + formatdate
				+ "]";
	}
	
	
}
