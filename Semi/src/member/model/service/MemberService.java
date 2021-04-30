package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import meeting.model.vo.Attachment;
import meeting.model.vo.Meeting;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	private MemberDao memberDao = new MemberDao();

	public static final String MEMBER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	public static final String EVENT_TRUE = "T";
	public static final String EVENT_FALSE = "F";

	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member member = null;
		try {
			member = memberDao.selectOne(conn, memberId);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return member;
	}

	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Member findId(String memberName, String memberEmail) {
		Connection conn = getConnection();
		Member member = null;
		try {
			member = memberDao.findId(conn, memberName, memberEmail);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return member;
	}

	public Member findPassword(String memberId, String memberEmail) {
		Connection conn = getConnection();
		Member member = null;
		try {
			member = memberDao.findPassword(conn, memberId, memberEmail);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return member;
	}

	// 요한이
	// 멤버 정보수정
	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result=memberDao.updateMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	// 비밀번호 변경
	public int updatePassword(Member member) {

		Connection conn = getConnection();
		int result = 0;
		try {
			result=memberDao.updatePassword(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	// 회원탈퇴
	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result=memberDao.deleteMember(conn, memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	// 승우
	public List<Member> selectList(int start, int end) {
		Connection conn = getConnection();
		List<Member> list = null;
		try {
			list=memberDao.selectList(conn, start, end);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public int selectMemberCount() {
		Connection conn = getConnection();
		int totalContents = 0;
		try {
			totalContents=memberDao.selectMemberCount(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return totalContents;
	}

	public List<Member> searchMember(Map<String, String> param, int start, int end) {
		Connection conn = getConnection();
		List<Member> list = null;
		try {
			list = memberDao.searchMember(conn, param, start, end);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public int searchMemberCount(Map<String, String> param) {
		Connection conn = getConnection();
		int totalContents = 0;
		try {
			totalContents = memberDao.searchMemberCount(param, conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return totalContents;
	}

	public int updateMemberRole(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMemberRole(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateMemberEvent(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMemberEvent(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateMemberEvent2(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMemberEvent2(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteMemberAD(String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.deleteMemberAD(conn, memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertCertification(Map<String, String> map) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.insertCertification(conn, map);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public String selectCertification(String id) {
		Connection conn = getConnection();
		String certification = null;
		try {
			certification = memberDao.selectCertification(conn, id);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return certification;
	}

	public List<Meeting> selectMylist(String memberId) {
		List<Meeting> list = null;
		Connection conn = getConnection();
		try {
			list = memberDao.selectMylist(conn, memberId);
			if (list != null && list.size() != 0) {
				System.out.println("모임리스트 불러오기 성공");
			} else {
				System.out.println("리스트 불러오기 실패 혹은 리스트 사이즈0");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public Attachment selectMyAttach(int meetingNo) {
		Attachment attach = null;
		Connection conn = getConnection();
		try {
			attach = memberDao.selectMyAttach(conn, meetingNo);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return attach;
	}

	public int memberCount() {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.memberCount(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int meetingCount() {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.meetingCount(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteCertification(String memberId) {
		int result =0;
		Connection conn = getConnection();
		try {
			result = memberDao.deleteCertification(conn, memberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int selectBlackList(String memberEmailId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.selectBlackList(conn, memberEmailId);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int selectEmail(String memberEmailId) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.selectEmail(conn, memberEmailId);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

}