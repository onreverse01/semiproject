package board.model.vo;

import java.sql.Date;

public class Board {
	//필드
	private int boardNo;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int commentCnt;
	private int readCnt;
	
	//기본생성자
	public Board() {
		super();
	}
	
	//파라미터생성자
	public Board(int boardNo, String title, String writer, String content, Date regDate, int commentCnt) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.commentCnt = commentCnt;
	}
	
	
	public Board(int boardNo, String title, String writer, String content, Date regDate, int commentCnt, int readCnt) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.commentCnt = commentCnt;
		this.readCnt = readCnt;
	}

	//Getter and Setter
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	//toString
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + ", commentCnt=" + commentCnt + ", readCnt=" + readCnt + "]";
	}
}
