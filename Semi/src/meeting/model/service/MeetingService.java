package meeting.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import meeting.model.dao.MeetingDao;
import meeting.model.vo.Attachment;
import meeting.model.vo.Chat;
import meeting.model.vo.Meeting;

public class MeetingService {
	private MeetingDao meetingDao = new MeetingDao();

	public List<Meeting> selectIndexRecentlist() {
		List<Meeting> list = null;
		Connection conn = getConnection();
		try {
			list=meetingDao.selectIndexRecentlist(conn);
			if(list!=null && list.size()!=0) {
				System.out.println("모임리스트 불러오기 성공");
			}else {
				System.out.println("리스트 불러오기 실패 혹은 리스트 사이즈0");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public Attachment selectAttachOne(int meetingNo) {
		Attachment attach = null;
		Connection conn = getConnection();
		try {
			attach = meetingDao.selectAttachOne(conn, meetingNo);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return attach;
	}

	public List<Meeting> selectMeetingList(int start, int end, String location, String category, String search) {
		List<Meeting> list = null;
		Connection conn = getConnection();
		try {
			list=meetingDao.selectMeetingList(conn, start, end, location, category, search);
			if(list!=null && list.size()!=0) {
				System.out.println("모임리스트 불러오기 성공");
			}else {
				System.out.println("리스트 불러오기 실패 혹은 리스트 사이즈0");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public int selectMeetingTotalContent(String location, String category, String search) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = meetingDao.selectMeetingTotalContent(conn,location, category, search);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Meeting selectOne(int meetingNo) {
		Connection conn = getConnection();
		Meeting m=null;
		try {
			m = meetingDao.selectOne(conn,meetingNo);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return m;
	}

	public int selectParticiCnt(int meetingNo) {
		int cnt = 0;
		Connection conn = getConnection();
		try {
			cnt = meetingDao.selectParticiCnt(conn, meetingNo);
		} catch (Exception e) {
			throw e;
		} finally{
			close(conn);
		}
		return cnt;
	}

	public List<String> selectParticiList(int meetingNo) {
		List<String> list = null;
		Connection conn = getConnection();
		try {
			list = meetingDao.selectParticiList(conn, meetingNo);
		} catch (Exception e) {
			throw e;
		} finally{
			close(conn);
		}
		return list;
	}

	public int insertParticipation(String memberId, int meetingNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = meetingDao.insertParticipation(conn,memberId, meetingNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally{
			close(conn);
		}
		return result;
	}

	public int updateUnParticipation(String memberId, int meetingNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = meetingDao.updateUnParticipation(conn,memberId, meetingNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally{
			close(conn);
		}
		return result;
	}

	public int deleteMeeting(int meetingNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = meetingDao.deleteMeeting(conn, meetingNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally{
			close(conn);
		}
		return result;
	}

	public int updateMeeting(Meeting m) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = meetingDao.updateMeeting(conn, m);
			if(m.getAttach()!=null) {
				result=meetingDao.insertAttach(conn,m.getAttach());
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteAttachment(String attachNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = meetingDao.deleteAttachment(conn, attachNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertMeeting(Meeting m) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = meetingDao.insertMeeting(conn, m);
			if(m.getAttach()!=null) {
				result=meetingDao.insertAttach2(conn,m.getAttach());
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Chat> selectChat(int no) {
		List<Chat> list = null;
		Connection conn = getConnection();
		try {
			list = meetingDao.selectChat(conn, no);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public int insertChat(Chat c) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = meetingDao.insertChat(conn, c);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
}
