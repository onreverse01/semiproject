package member.model.vo;

import java.sql.Date;

public class Member {
	//파라미터 생성자
	private String memberId;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String event1;
	private String event2;
	private Date enrollDate;
	private String memberRole;
	
	//기본생성자
	public Member() {
		super();
	}
	
	//파라미터생성자
	public Member(String memberId, String password, String name, String email, String phone, String event1,
			String event2, Date enrollDate, String memberRole) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.event1 = event1;
		this.event2 = event2;
		this.enrollDate = enrollDate;
		this.memberRole = memberRole;
	}
	
	//Getter and Setter
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEvent1() {
		return event1;
	}
	public void setEvent1(String event1) {
		this.event1 = event1;
	}
	public String getEvent2() {
		return event2;
	}
	public void setEvent2(String event2) {
		this.event2 = event2;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	
	//toString
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone=" + phone + ", event1=" + event1 + ", event2=" + event2 + ", enrollDate=" + enrollDate
				+ ", memberRole=" + memberRole + "]";
	}
}
