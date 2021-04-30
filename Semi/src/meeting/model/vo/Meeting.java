package meeting.model.vo;

import java.sql.Date;

public class Meeting {
	//필드
	private int meetingNo;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private String place;
	private Date time;
	private int maxPeople;
	private int cost;
	private String categoryCode;
	private String categoryName;
	private String locationCode;
	private String locationName;
	private int countParticipation;
	private Attachment attach;
	
	//기본생성자
	public Meeting() {
		super();
	}
	
	//파라미터 생성자
	public Meeting(int meetingNo, String title, String writer, String content, Date regDate, String place, Date time,
			int maxPeople, int cost, String categoryCode, String categoryName, String locationCode, String locationName,
			int countParticipation, Attachment attach) {
		super();
		this.meetingNo = meetingNo;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.place = place;
		this.time = time;
		this.maxPeople = maxPeople;
		this.cost = cost;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.locationCode = locationCode;
		this.locationName = locationName;
		this.countParticipation = countParticipation;
		this.attach = attach;
	}

	//getter and setter
	public int getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(int meetingNo) {
		this.meetingNo = meetingNo;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getCountParticipation() {
		return countParticipation;
	}

	public void setCountParticipation(int countParticipation) {
		this.countParticipation = countParticipation;
	}

	public Attachment getAttach() {
		return attach;
	}

	public void setAttach(Attachment attach) {
		this.attach = attach;
	}

	//toString
	@Override
	public String toString() {
		return "Meeting [meetingNo=" + meetingNo + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regDate=" + regDate + ", place=" + place + ", time=" + time + ", maxPeople=" + maxPeople
				+ ", cost=" + cost + ", categoryCode=" + categoryCode + ", categoryName=" + categoryName
				+ ", locationCode=" + locationCode + ", locationName=" + locationName + ", countParticipation="
				+ countParticipation + ", attach=" + attach + "]";
	}

//	@Override
//	public String toString() {
//		return meetingNo + "," + title + "," + writer + "," + content
//				+ "," + regDate + "," + place + "," + time + "," + maxPeople
//				+ "," + cost + "," + categoryCode + "," + categoryName
//				+ "," + locationCode + "," + locationName + ","
//				+ countParticipation + "," + (attach != null ? attach.getRenamedFilename() : null);
//	}
	
	
}
