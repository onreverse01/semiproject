package claim.model.vo;

import java.sql.Date;

public class ClaimBoard {

	private int no;
	private int rnum;
	private String writer;
	private String choice;
	private String title;
	private String content;
	private Date regDate;
	private Date anwDate;
	private String state;
	
	public ClaimBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClaimBoard(int no, int rnum, String writer, String choice, String title, String content, Date regDate,
			Date anwDate, String state) {
		super();
		this.no = no;
		this.rnum = rnum;
		this.writer = writer;
		this.choice = choice;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.anwDate = anwDate;
		this.state = state;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getAnwDate() {
		return anwDate;
	}
	public void setAnwDate(Date anwDate) {
		this.anwDate = anwDate;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
		return "ClaimBoard [no=" + no + ", rnum=" + rnum + ", writer=" + writer + ", choice=" + choice + ", title="
				+ title + ", content=" + content + ", regDate=" + regDate + ", anwDate=" + anwDate + ", state=" + state
				+ "]";
	}

	

}
