package board.model.vo;

import java.sql.Date;

public class BoardComment {
	
	//필드
	private int commentNo;
	private int commentLevel;
	private String writer;
	private String content;
	private int boardNo;
	private int commentRef;
	private Date regDate;
	
	//기본생성자
	public BoardComment() {
		super();
	}

	//파라미터생성자
	public BoardComment(int commentNo, int commentLevel, String writer, String content, int boardNo, int commentRef,
			Date regDate) {
		super();
		this.commentNo = commentNo;
		this.commentLevel = commentLevel;
		this.writer = writer;
		this.content = content;
		this.boardNo = boardNo;
		this.commentRef = commentRef;
		this.regDate = regDate;
	}

	//Getter and Setter
	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
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

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCommentRef() {
		return commentRef;
	}

	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	//toString
	@Override
	public String toString() {
		return "BoardComment [commentNo=" + commentNo + ", commentLevel=" + commentLevel + ", writer=" + writer
				+ ", content=" + content + ", boardNo=" + boardNo + ", commentRef=" + commentRef + ", regDate="
				+ regDate + "]";
	}
}
