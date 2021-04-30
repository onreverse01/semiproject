package claim.model.dao;

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
import java.util.Properties;

import claim.model.exception.BoardException;
import claim.model.vo.AnwBoard;
import claim.model.vo.ClaimBoard;

public class ClaimDao {

	private Properties prop = new Properties();

	public ClaimDao() {
		
		String fileName = ClaimDao.class.getResource("/sql/claim/claim-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertBoard(Connection conn, ClaimBoard board) {
		PreparedStatement pstmt = null;
		int result = -1;
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getChoice());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getWriter());
			pstmt.executeUpdate();
			
			result++;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 등록 오류", e);
			
		} finally {
			close(pstmt);
		}
		sql = prop.getProperty("insertAnwBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.executeUpdate();
			
			result++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<ClaimBoard> selectList(Connection conn, int start, int end) {
		List<ClaimBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ClaimBoard board = new ClaimBoard();
				board.setNo(rset.getInt("no"));
				board.setWriter(rset.getString("writer"));
				board.setChoice(rset.getString("choice"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setAnwDate(rset.getDate("anw_date"));
				board.setState(rset.getString("state"));
				
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectBoardCount(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public ClaimBoard selectOneClaim(Connection conn, int no) {
		ClaimBoard oneClaim = new ClaimBoard();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneClaim");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				oneClaim.setNo(rset.getInt("no"));
				oneClaim.setChoice(rset.getString("choice"));
				oneClaim.setTitle(rset.getString("title"));
				oneClaim.setContent(rset.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return oneClaim;
	}

	public int insertAnwBoard(Connection conn, int no, AnwBoard board) {
		PreparedStatement pstmt = null;
		int result = -1;
		String sql = prop.getProperty("updateAnwBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getContent());
			pstmt.setInt(2, no);
			
			pstmt.executeUpdate();
			result++;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 등록 오류", e);
			
		} finally {
			close(pstmt);
		}
		sql = prop.getProperty("updateBoardState");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			result++;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("게시물 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<AnwBoard> selectAnwList(Connection conn) {
		List<AnwBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAnwList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AnwBoard board = new AnwBoard();
				board.setNo(rset.getInt("no"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public AnwBoard selectOneClaimAnw(Connection conn, int no) {
		AnwBoard oneClaimAnw = new AnwBoard();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneClaimAnw");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				oneClaimAnw.setNo(rset.getInt("no"));
				oneClaimAnw.setContent(rset.getString("content"));
				oneClaimAnw.setRegDate(rset.getDate("reg_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return oneClaimAnw;
	}

	public List<ClaimBoard> selectWriterList(Connection conn, String writer, int start, int end) {
		List<ClaimBoard> wlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectWriterList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ClaimBoard board = new ClaimBoard();
				board.setRnum(rset.getInt("rnum"));
				board.setNo(rset.getInt("no"));
				board.setWriter(rset.getString("writer"));
				board.setChoice(rset.getString("choice"));
				board.setTitle(rset.getString("title"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setAnwDate(rset.getDate("anw_date"));
				board.setState(rset.getString("state"));
				
				wlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return wlist;
	}

	public List<AnwBoard> selectWriterAnwList(Connection conn, String writer) {
		List<AnwBoard> wlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectWriterAnwList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				AnwBoard board = new AnwBoard();
				board.setNo(rset.getInt("no"));
				board.setRnum(rset.getInt("rnum"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				
				wlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return wlist;
	}

}
