package meeting.model.vo;

public class Attachment {
	//필드
	private int attachNo;
	private int meetingNo;
	private String originalFilename;
	private String renamedFilename;
	private String status;
	
	//기본생성자
	public Attachment() {
		super();
	}
	
	//파라미터 생성자
	public Attachment(int attachNo, int meetingNo, String originalFilename, String renamedFilename, String status) {
		super();
		this.attachNo = attachNo;
		this.meetingNo = meetingNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.status = status;
	}
	
	//getter and setter
	public int getAttachNo() {
		return attachNo;
	}
	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}
	public int getMeetingNo() {
		return meetingNo;
	}
	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//toString
	@Override
	public String toString() {
		return "Attachment [attachNo=" + attachNo + ", meetingNo=" + meetingNo + ", originalFilename="
				+ originalFilename + ", renamedFilename=" + renamedFilename + ", status=" + status + "]";
	}
}
