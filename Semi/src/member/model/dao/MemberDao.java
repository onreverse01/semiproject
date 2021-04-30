package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import board.model.dao.BoardDao;
import common.SemiException;
import meeting.model.vo.Attachment;
import meeting.model.vo.Meeting;
import member.model.vo.Member;

public class MemberDao {
	private Properties prop = new Properties();

	public MemberDao() {

		String fileName = BoardDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 맴버 조회 = 로그인
	public Member selectOne(Connection conn, String memberId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectOne");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setPassword(rset.getString("PASSWORD"));
				member.setName(rset.getString("NAME"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setEvent1(rset.getString("EVENT1"));
				member.setEvent2(rset.getString("EVENT2"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setMemberRole(rset.getString("MEMBER_ROLE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("회원정보를 읽는데 실패하였습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;

	}

	// 회원가입
	public int insertMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMember");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getEvent1());
			pstmt.setString(7, member.getEvent2());
			pstmt.setString(8, member.getMemberRole());

			// 쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("회원가입에 실패 하였습니다.");
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 아이디 찾기
	public Member findId(Connection conn, String memberName, String memberEmail) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("findId");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberEmail);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setPassword(rset.getString("PASSWORD"));
				member.setName(rset.getString("NAME"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setEvent1(rset.getString("EVENT1"));
				member.setEvent2(rset.getString("EVENT2"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setMemberRole(rset.getString("MEMBER_ROLE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("아이디 찾기에 실패하였습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public Member findPassword(Connection conn, String memberId, String memberEmail) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("findPassword");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberEmail);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setPassword(rset.getString("PASSWORD"));
				member.setName(rset.getString("NAME"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setEvent1(rset.getString("EVENT1"));
				member.setEvent2(rset.getString("EVENT2"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				member.setMemberRole(rset.getString("MEMBER_ROLE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("비밀번호 찾기에 실패하였습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMember");
		System.out.println("dao : " + member);
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEvent1());
			pstmt.setString(5, member.getEvent2());
			pstmt.setString(6, member.getMemberId());
			// 쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("회원정보 업데이트에 실패하였습니다.", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 요한이
	public int updatePassword(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword");
		// updatePassword = update member set password = ? where member_id = ?

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());

			// 쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("비밀번호 변경에 실패하였습니다.", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember");

		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성
			pstmt.setString(1, memberId);

			// 쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("회원 탈퇴에 실패하였습니다.", e);
		}

		return result;
	}

	// AdminMemberListServlet 회원조회(관리자)
	public List<Member> selectList(Connection conn, int start, int end) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectListPage");
		System.out.println("query@dao = " + query);

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member member = new Member();
				member.setMemberId(rset.getString("member_id"));
				member.setPassword(rset.getString("password"));
				member.setName(rset.getString("name"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setEvent1(rset.getString("event1"));
				member.setEvent2(rset.getString("event2"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setMemberRole(rset.getString("member_role"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("관리자페이지 회원 목록 조회 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectMemberCount(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectMemberCount");
		System.out.println("query@dao = " + query);

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				totalContents = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("페이지 바를 위한 총 회원수 조회에 실패하였습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContents;
	}

	public List<Member> searchMember(Connection conn, Map<String, String> param, int start, int end) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("searchListPage");
		query = setQuery(query, param.get("searchType"), param.get("searchKeyword"));
		System.out.println("query@dao = " + query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while (rset.next()) {
				Member member = new Member();
				member.setMemberId(rset.getString("member_id"));
				member.setPassword(rset.getString("password"));
				member.setName(rset.getString("name"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setEvent1(rset.getString("event1"));
				member.setEvent2(rset.getString("event2"));
				member.setEnrollDate(rset.getDate("enroll_date"));
				member.setMemberRole(rset.getString("member_role"));
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("검색 리스트 조회 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public String setQuery(String query, String searchType, String searchKeyword) {
		switch (searchType) {
		case "memberId":
			query = query.replace("#", " member_id like '%" + searchKeyword + "%'");
			break;
		case "memberName":
			query = query.replace("#", " name like '%" + searchKeyword + "%'");
			break;
		}
		return query;
	}

	public int searchMemberCount(Map<String, String> param, Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("searchMemberCount");
		query = setQuery(query, param.get("searchType"), param.get("searchKeyword"));
		System.out.println("query@dao = " + query);

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				totalContents = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("회원검색창의 페이지바를 위한 총회원수 불러오기에 실패하였습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int updateMemberRole(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMemberRole");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getMemberRole());
			pstmt.setString(2, member.getMemberId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("회원 역할 업데이트에 실패하였습니다.", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateMemberEvent(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMemberEvent");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getEvent1());
			pstmt.setString(2, member.getMemberId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("이벤트동의1 항목을 업데이트 하는데 실패하였습니다.", e);
		}
		return result;
	}

	public int updateMemberEvent2(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMemberEvent2");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getEvent2());
			pstmt.setString(2, member.getMemberId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("이벤트동의2 항목을 업데이트 하는데 실패하였습니다.", e);
		}
		return result;
	}

	public int deleteMemberAD(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMemberAD");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, memberId);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("회원 추방에 실패 하였습니다.");
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertCertification(Connection conn, Map<String, String> map) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertCertification");
		String id = map.get("id");
		String randomString = map.get("randomString");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, randomString);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("인증번호 DB에 접속 실패하였습니다.", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public String selectCertification(Connection conn, String id) {
		String certification = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCertification");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			rset.next();
			certification = rset.getString("certification_code");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("비밀번호변경 = DB에서 인증코드를 가져오지 못했습니다.", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return certification;
	}

	public List<Meeting> selectMylist(Connection conn, String memberId) {
		List<Meeting> list = new ArrayList<Meeting>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMylist");

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Meeting m = new Meeting();
				// 인덱스페이지 에서는 이거 두개만 필요함
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setTitle(rset.getString("title"));
				list.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("미팅 리스트 불러오기 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public Attachment selectMyAttach(Connection conn, int meetingNo) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// select * from attachment where meeting_no = ? and status = 'Y'
		String sql = prop.getProperty("selectMyAttach");

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				attach = new Attachment();
				attach.setAttachNo(rset.getInt("attach_no"));
				attach.setMeetingNo(meetingNo);
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setStatus(rset.getString("status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SemiException("첨부파일 불러오기 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return attach;
	}

	// 총 회원수 카운트하는곳
	public int memberCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("countMember");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("로그인창에 표시될 총회원수를 불러오는데 실패하였습니다.",e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public int meetingCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("meetingCount");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("로그인창에 표시될 총 모임수를 불러오는데 실패하였습니다.",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int deleteCertification(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCertification");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("인증테이블에서 인증코드 삭제실패",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectBlackList(Connection conn, String memberEmailId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBlackList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmailId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result=rset.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("블랙리스트 조회 실패",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int selectEmail(Connection conn, String memberEmailId) {
		int result =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectEmail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmailId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result=rset.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("블랙리스트 조회 실패",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

}