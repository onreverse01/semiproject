package meeting.model.vo;

public class Chat {
	private String writer;
	private String content;
	private int meetingNo;
	
	public Chat() {
		super();
	}
	public Chat(String writer, String content) {
		super();
		this.writer = writer;
		this.content = content;
	}
	
	public Chat(String writer, String content, int meetingNo) {
		super();
		this.writer = writer;
		this.content = content;
		this.meetingNo = meetingNo;
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
	public int getMeetingNo() {
		return meetingNo;
	}
	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}
	@Override
	public String toString() {
		return "Chat [writer=" + writer + ", content=" + content + ", meetingNo=" + meetingNo + "]";
	}
	
	

}
