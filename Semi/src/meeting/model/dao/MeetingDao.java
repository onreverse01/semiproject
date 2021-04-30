package meeting.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import common.SemiException;
import meeting.model.vo.Attachment;
import meeting.model.vo.Chat;
import meeting.model.vo.Meeting;

public class MeetingDao {
	private Properties prop = new Properties();

	public MeetingDao() {
		
		String fileName = MeetingDao.class.getResource("/sql/meeting/meeting-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Meeting> selectIndexRecentlist(Connection conn) {
		List<Meeting> list = new ArrayList<Meeting>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectIndexRecentlist");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Meeting m = new Meeting();
				//인덱스페이지 에서는 이거 두개만 필요함
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setTitle(rset.getString("title"));
				list.add(m);
			}
			
		} catch (Exception e) {
			throw new SemiException("미팅 리스트 불러오기 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		} 
		
		return list;
	}

	public Attachment selectAttachOne(Connection conn, int meetingNo) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//select * from attachment where meeting_no = ? and status = 'Y'
		String sql = prop.getProperty("selectAttachOne");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				attach = new Attachment();
				attach.setAttachNo(rset.getInt("attach_no"));
				attach.setMeetingNo(meetingNo);
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setStatus(rset.getString("status"));
			}
			
		} catch (Exception e) {
			throw new SemiException("미팅 리스트 불러오기 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		} 
		
		return attach;
	}

	public List<Meeting> selectMeetingList(Connection conn, int start, int end, String location, String category, String search) {
		List<Meeting> list = new ArrayList<Meeting>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMeetingList");
		sql=sql.replace("@", category);
		sql=sql.replace("#", location);
		sql=sql.replace("$", search);
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Meeting m = new Meeting();
				//인덱스페이지 에서는 이거 두개만 필요함
				m.setMeetingNo(rset.getInt("meeting_no"));
				m.setTitle(rset.getString("title"));
				m.setTime(rset.getDate("time"));
				list.add(m);
			}
			
		} catch (Exception e) {
			throw new SemiException("미팅 리스트 불러오기 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		} 
		
		return list;
	}

	public int selectMeetingTotalContent(Connection conn, String location, String category, String search) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMeetingTotalContent").replace("@", location).replace("#", category).replace("$", search);
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("count(*)");
			}
		} catch (SQLException e) {
			throw new SemiException("미팅리스트 totalContent 불러오기 실패", e);
		}
		
		return result;
	}

	public Meeting selectOne(Connection conn, int meetingNo) {
		Meeting m=null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Meeting();
				m.setMeetingNo(meetingNo);
				m.setTitle(rset.getString("title"));
				m.setWriter(rset.getString("writer"));
				m.setContent(rset.getString("content"));
				m.setRegDate(rset.getDate("reg_date"));
				m.setPlace(rset.getString("place"));

				//resultSet의 getDate메서드는 시분초를 가져오지 못한다...
				int year = Integer.parseInt(rset.getString("time").substring(0, 4));
				int month = Integer.parseInt(rset.getString("time").substring(5,7));
				int dayOfMonth = Integer.parseInt(rset.getString("time").substring(8,10));
				int hourOfDay = Integer.parseInt(rset.getString("time").substring(11,13));
				int minute = Integer.parseInt(rset.getString("time").substring(14,16));
				Calendar cal = new GregorianCalendar(year, month-1, dayOfMonth, hourOfDay, minute);
				m.setTime(new Date(cal.getTimeInMillis()));
				
				m.setMaxPeople(rset.getInt("max_people"));
				m.setCost(rset.getInt("cost"));
				m.setCategoryCode(rset.getString("category_code"));
				m.setCategoryName(rset.getString("cname"));
				m.setLocationCode(rset.getString("location_code"));
				m.setLocationName(rset.getString("lname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("미팅정보 조회에 실패하였습니다.",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}

	public int selectParticiCnt(Connection conn, int meetingNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int cnt = 0;
		String sql = prop.getProperty("selectParticiCnt");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, meetingNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				cnt = rset.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("참여중인 인원 불러오기 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cnt;
	}

	public List<String> selectParticiList(Connection conn, int meetingNo) {
		List<String> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectParticiList");
		System.out.println(sql);
		
		//select * from participation where meeting_no=?
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, meetingNo);
			rset = pstmt.executeQuery();
			list=new ArrayList<String>();
			while(rset.next()) {
				list.add(rset.getString("partici_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("참여중인 인원목록 불러오기 실패", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertParticipation(Connection conn, String memberId, int meetingNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertParticipation");
		//insert into participation values(seq_participation.nextval, ?, ?, sysdate, 'Y')
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, meetingNo);
			pstmt.setString(2, memberId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("모임 참여 실패", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateUnParticipation(Connection conn, String memberId, int meetingNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateUnParticipation");
		System.out.println(sql);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, meetingNo);
			pstmt.setString(2, memberId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("모임 취소 실패", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMeeting(Connection conn, int meetingNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMeeting");
		System.out.println(sql);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, meetingNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("모임 삭제 실패", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMeeting(Connection conn, Meeting m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMeeting");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
		String date ="to_date('"+sdf.format(new java.util.Date(m.getTime().getTime()))+"','yy/mm/dd HH24:MI')";
		sql=sql.replace("#", date);
		System.out.println("SQL@updateDao = "+sql);
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getTitle());
			pstmt.setString(2, m.getContent());
			pstmt.setString(3, m.getPlace());
			pstmt.setInt(4, m.getMaxPeople());
			pstmt.setInt(5, m.getCost());
			pstmt.setString(6, m.getCategoryCode());
			pstmt.setString(7, m.getLocationCode());
			pstmt.setInt(8, m.getMeetingNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("업데이트에 실패했습니다.",e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteAttachment(Connection conn, String attachNo) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attachNo);
			result=pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("첨부파일 삭제 실패했습니다.",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertAttach(Connection conn, Attachment attach) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttach");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getMeetingNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			result=pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("첨부파일 삭제 실패했습니다.",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertMeeting(Connection conn, Meeting m) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMeeting");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
		String date ="to_date('"+sdf.format(new java.util.Date(m.getTime().getTime()))+"','yy/mm/dd HH24:MI')";
		sql=sql.replace("#", date);
		System.out.println("SQL@inserDAO = "+sql);
		//
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getTitle());
			pstmt.setString(2,m.getWriter());
			pstmt.setString(3, m.getContent());
			pstmt.setString(4, m.getPlace());
			pstmt.setInt(5, m.getMaxPeople());
			pstmt.setInt(6, m.getCost());
			pstmt.setString(7, m.getCategoryCode());
			pstmt.setString(8, m.getLocationCode());
			result=pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("첨부파일 삭제 실패했습니다.",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertAttach2(Connection conn, Attachment attach) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttach2");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attach.getOriginalFilename());
			pstmt.setString(2, attach.getRenamedFilename());
			result=pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("첨부파일 삭제 실패했습니다.",e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	public List<Chat> selectChat(Connection conn, int no){
		List<Chat> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectChat");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			list=new ArrayList<Chat>();
			while(rset.next()) {
				Chat c = new Chat();
				c.setWriter(rset.getString("writer"));
				c.setContent(rset.getString("contents"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("채팅 목록을 불러오지 못했습니다",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int insertChat(Connection conn, Chat c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertChat");
		//insert into meeting_chat values(seq_meeting_chat.nextval, ?, ?, ?, sysdate)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getMeetingNo());
			pstmt.setString(2, c.getWriter());
			pstmt.setNString(3, c.getContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SemiException("채팅 추가 실패");
		} finally {
			close(pstmt);
		}
		return result;
	}
}
