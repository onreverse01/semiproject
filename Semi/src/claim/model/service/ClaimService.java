package claim.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import claim.model.dao.ClaimDao;
import claim.model.vo.AnwBoard;
import claim.model.vo.ClaimBoard;

public class ClaimService {

	private ClaimDao claimDao = new ClaimDao();
	
	public int insertBoard(ClaimBoard board) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = claimDao.insertBoard(conn, board);
			
			//생성된 board_no를 가져오기
//			int boardNo = claimDao.selectLastBoardNo(conn);
			//redirect location설정
//			board.setNo(boardNo);
			
//			System.out.println("boardNo@service = " + boardNo);
			
			commit(conn);
			
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public List<ClaimBoard> selectList(int start, int end) {
		Connection conn = getConnection();
		List<ClaimBoard> list = claimDao.selectList(conn, start, end);
		close(conn);
		return list;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContents = claimDao.selectBoardCount(conn);
		close(conn);
		return totalContents;
	}

	public ClaimBoard selectOneClaim(int no) {
		Connection conn = getConnection();
		ClaimBoard oneClaim = claimDao.selectOneClaim(conn, no);
		close(conn);
		return oneClaim;
	}

	public int insertAnwBoard(int no, AnwBoard board) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = claimDao.insertAnwBoard(conn, no, board);
			
			//생성된 board_no를 가져오기
//			int boardNo = claimDao.selectLastBoardNo(conn);
			//redirect location설정
//			board.setNo(boardNo);
			
//			System.out.println("boardNo@service = " + boardNo);
			
			commit(conn);
			
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {			
			close(conn);
		}
		return result;
	}

	public List<AnwBoard> selectAnwList() {
		Connection conn = getConnection();
		List<AnwBoard> list = claimDao.selectAnwList(conn);
		close(conn);
		return list;
	}

	public AnwBoard selectOneClaimAnw(int no) {
		Connection conn = getConnection();
		AnwBoard oneClaimAnw = claimDao.selectOneClaimAnw(conn, no);
		close(conn);
		return oneClaimAnw;
	}

	public List<ClaimBoard> selectWriterList(String writer, int start, int end) {
		Connection conn = getConnection();
		List<ClaimBoard> wlist = claimDao.selectWriterList(conn, writer, start, end);
		close(conn);
		return wlist;
	}

	public List<AnwBoard> selectWriterAnwList(String writer) {
		Connection conn = getConnection();
		List<AnwBoard> wlist = claimDao.selectWriterAnwList(conn, writer);
		close(conn);
		return wlist;
	}
	
}
