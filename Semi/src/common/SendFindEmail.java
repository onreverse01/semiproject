package common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import member.model.service.MemberService;
import member.model.vo.Member;

public class SendFindEmail {

	final String username = "meetpeople.kh";
	final String password = "meetpeople.kh";
	MemberService memberService = new MemberService();
	int result = 0;

	public Member sendEmailPassword(Member member) {
		String id = member.getMemberId();
		String email = member.getEmail();

		// 랜덤값 부여
		String randomString = MvcUtils.randomAlphaWord(30);

		// db에 저장
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("randomString", randomString);
		int result = 0;
		result = memberService.insertCertification(map);
		if (result > 0) {
			System.out.println("인증코드 테이블 저장 성공");
		} else {
			System.out.println("인증코드 테이블 저장 실패");
		}

		String mail = "http://meetpeople.kro.kr/member/newpassword?id=" + id + "&randomString=" + randomString
				+ "&email=" + email;

		// 메일용 api
		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("MEPLE"));//
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));// 받는사람이메일 입력받는곳
			message.setSubject("[미플] 비밀번호 변경 링크 메일입니다!","utf-8");// 제목
//			message.setText(mail+"  해당링크를 클릭해서 비밀번호를 변경하세요.");// 내용

			message.setContent(new MimeMultipart());
			Multipart mp = (Multipart) message.getContent();
//			mp.addBodyPart(
//					getContents("<html><head></head><body><h1>"+mail+"</h1></body></html>"));
			mp.addBodyPart(
					getContents("<!DOCTYPE html>\r\n" + 
							"<html lang=\"ko\">\r\n" + 
							"  <head> </head>\r\n" + 
							"  <body>\r\n" + 
							"    <div\r\n" + 
							"      style=\"\r\n" + 
							"        margin: 0;\r\n" + 
							"        padding: 0;\r\n" + 
							"        background: #fd9000;\r\n" + 
							"        padding-top: 100px;\r\n" + 
							"        padding-bottom: 100px;\r\n" + 
							"      \"\r\n" + 
							"    >\r\n" + 
							"      <div\r\n" + 
							"        style=\"\r\n" + 
							"          width: 600px;\r\n" + 
							"          height: 300px;\r\n" + 
							"          background: white;\r\n" + 
							"          border: 1px solid black;\r\n" + 
							"          margin: 0 auto;\r\n" + 
							"          padding: 30px;\r\n" + 
							"          font-size: 16px;\r\n" + 
							"        \"\r\n" + 
							"      >\r\n" + 
							"        <img\r\n" + 
							"          src=\"https://kimyunsu-temp.s3.ap-northeast-2.amazonaws.com/Logo.png\"\r\n" + 
							"          style=\"width: 120px; height: 50px\"\r\n" + 
							"        />\r\n" + 
							"        <h2 style='text-align:center;'>\r\n" + 
							"          안녕하세요! No1.취미모임 <u style=\"font-size: 30px; margin-top: 0;\">미플</u>입니다!\r\n" + 
							"        </h2>\r\n" + 
							"        <div style=\"margin-bottom: 10px\">\r\n" + 
							"          <div></div>\r\n" + 
							"          <div style=\"margin-bottom: 5px\">미플에 돌아오신걸 환영합니다.</div>\r\n" + 
							"          <div style=\"margin-bottom: 5px\">\r\n" + 
							"            아래 링크를 통해 접속하시고 비밀번호를 변경하시면 됩니다.\r\n" + 
							"          </div>\r\n" + 
							"          <div style=\"margin-bottom: 5px\">\r\n" + 
							"            변경후에는 아래 링크는 만료되어 소멸됩니다.\r\n" + 
							"          </div>\r\n" + 
							"          <div style=\"margin-bottom: 5px\">\r\n" + 
							"            지금 변경하시려면\r\n" + 
							"            <a href=\""+ mail +
							"\" style=\"font-size: 20px; font-weight: bold\">클릭</a\r\n" + 
							"            >해주세요\r\n" + 
							"          </div>\r\n" + 
							"          <div style=\"margin-top: 30px; text-align: center\">\r\n" + 
							"            * 본 메일은 발신전용 메일입니다. *\r\n" + 
							"          </div>\r\n" + 
							"        </div>\r\n" + 
							"      </div>\r\n" + 
							"    </div>\r\n" + 
							"  </body>\r\n" + 
							"</html>\r\n" + 
							""));

			System.out.println("send!!!");
			Transport.send(message);
			System.out.println("SEND");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	public Member sendEmailId(Member member) {
		String id = member.getMemberId();
		String email = member.getEmail();
		System.out.println(id + "이게 보내줄 아이디다");

		// 메일용 api
		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("MEPLE"));//
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));// 받는사람이메일 입력받는곳
			message.setSubject("[미플] 아이디 찾기 메일입니다.","utf-8");// 제목
			message.setContent(new MimeMultipart());
			Multipart mp = (Multipart) message.getContent();
			mp.addBodyPart(
					getContents("<!DOCTYPE html>\r\n" + 
							"<html lang=\"ko\">\r\n" + 
							"  <head> </head>\r\n" + 
							"  <body>\r\n" + 
							"    <div\r\n" + 
							"      style=\"\r\n" + 
							"        margin: 0;\r\n" + 
							"        padding: 0;\r\n" + 
							"        background: #fd9000;\r\n" + 
							"        padding-top: 100px;\r\n" + 
							"        padding-bottom: 100px;\r\n" + 
							"      \"\r\n" + 
							"    >\r\n" + 
							"      <div\r\n" + 
							"        style=\"\r\n" + 
							"          width: 600px;\r\n" + 
							"          height: 300px;\r\n" + 
							"          background: white;\r\n" + 
							"          border: 1px solid black;\r\n" + 
							"          margin: 0 auto;\r\n" + 
							"          padding: 30px;\r\n" + 
							"          font-size: 16px;\r\n" + 
							"        \"\r\n" + 
							"      >\r\n" + 
							"        <img\r\n" + 
							"          src=\"https://kimyunsu-temp.s3.ap-northeast-2.amazonaws.com/Logo.png\"\r\n" + 
							"          style=\"width: 120px; height: 50px\"\r\n" + 
							"        />\r\n" + 
							"        <h2 style=\"text-align: center\">\r\n" + 
							"          안녕하세요! No1.취미모임\r\n" + 
							"          <u style=\"font-size: 30px; margin-top: 0\">미플</u>입니다!\r\n" + 
							"        </h2>\r\n" + 
							"        <div style=\"margin-bottom: 10px\">\r\n" + 
							"          <div></div>\r\n" + 
							"          <div style=\"margin-bottom: 5px\">미플에 돌아오신걸 환영합니다.</div>\r\n" + 
							"          <div style=\"margin-bottom: 5px\">\r\n" + 
							"            아이디를 깜박하셨다고 도움을 요청하셨군요!\r\n" + 
							"          </div>\r\n" + 
							"          <div style=\"margin-bottom: 5px\">\r\n" + 
							"            회원님께서 사용중이신 아이디는 아래와 같습니다.\r\n" + 
							"          </div>\r\n" + 
							"          <div style=\"margin-bottom: 5px; font-size: 20px; font-weight: bold\">\r\n" + 
							"            ["
							+ 			id
							+ 			"]\r\n" + 
							"          </div>\r\n" + 
							"          <div style=\"margin-top: 30px; text-align: center\">\r\n" + 
							"            * 본 메일은 발신전용 메일입니다. *\r\n" + 
							"          </div>\r\n" + 
							"        </div>\r\n" + 
							"      </div>\r\n" + 
							"    </div>\r\n" + 
							"  </body>\r\n" + 
							"</html>\r\n" + 
							""));
			System.out.println("send!!!");
			Transport.send(message);
			System.out.println("SEND");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	private BodyPart getContents(String html) throws MessagingException {
		BodyPart mbp = new MimeBodyPart();
		// setText를 이용할 경우 일반 텍스트 내용으로 설정된다.
//		 mbp.setText(html);
		// html 형식으로 설정
		mbp.setContent(html, "text/html; charset=utf-8");
		return mbp;
	}

}