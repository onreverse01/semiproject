package claim.model.vo;

import java.util.Date;

public class AnwBoard {

	private int no;
	private int rnum;
	private String writer;
	private String content;
	private Date regDate;
	
	public AnwBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public AnwBoard(int no, int rnum, String writer, String content, Date regDate) {
		super();
		this.no = no;
		this.rnum = rnum;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	@Override
	public String toString() {
		return "AnwBoard [no=" + no + ", rnum=" + rnum + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + "]";
	}

}
