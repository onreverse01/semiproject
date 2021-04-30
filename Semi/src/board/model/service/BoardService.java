package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.BoardComment;

public class BoardService {
	BoardDao boardDao = new BoardDao();
	
	//boardListServlet 에서 사용되는 페이지당 필요한 list불러오는 메서드
	public List<Board> selectBoardList(int start, int end) {
		List<Board> list = null;
		Connection conn = getConnection();
		try {
			list = boardDao.selectBoardList(conn, start, end);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	//boardListServlet에서 사용되는 총 자유게시판 글수를 불러오는 메서드
	public int selectBoardTotal() {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.selectBoardTotal(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	//boardEnrollServlet에서 사용되는 게시글 추가 메서드
	public int insertBoard(Board b) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.insertBoard(conn,b);
			if(result>0) {
				commit(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	//게시글 번호로 해당 게시글에 댓글이 몇개가 있는지 조회하는 메서드
	public int selectCommentCnt(int boardNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.selectCommentCnt(conn, boardNo);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	//board객체 하나를 조회하는 메서드
	public Board selectBoardOne(int boardNo) {
		Board board = null;
		Connection conn = getConnection();
		try {
			board = boardDao.selectBoardOne(conn, boardNo);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return board;
	}

	//boardView next,prev를 위한 boardNumList가져오기
	public List<Integer> selectBoardNumList() {
		List<Integer> list = null;
		Connection conn = getConnection();
		try {
			list = boardDao.selectBoardNumList(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	//boardUpdate에서 업데이트 실행
	public int updateBoard(Board board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.updateBoard(conn,board);
			if(result>0) {
				commit(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	//boardDelete에서 게시물 삭제
	public int deleteBoard(int boardNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.deleteBoard(conn,boardNo);
			if(result>0) {
				commit(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	//게시물에 해당하는 commentList를 가져오는 메서드
	public List<BoardComment> selectCommentList(int boardNo) {
		List<BoardComment> list = null;
		Connection conn = getConnection();
		try {
			list = boardDao.selectCommentList(conn,boardNo);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	//댓글작성 하는 메서드
	public int insertComment(BoardComment bc) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.insertComment(conn,bc);
			if(result>0) {
				commit(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int deleteBoardComment(int boardCommentNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.deleteBoardComment(conn,boardCommentNo);
			if(result>0) {
				commit(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public List<Board> selectAdminBoardList(int start, int end) {
		List<Board> list = null;
		Connection conn = getConnection();
		try {
			list = boardDao.selectAdminBoardList(conn, start, end);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public int selectAdminBoardTotal() {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.selectAdminBoardTotal(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertAdminBoard(Board b) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.insertAdminBoard(conn,b);
			if(result>0) {
				commit(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public Board selectAdminBoardOne(int boardNo) {
		Board board = null;
		Connection conn = getConnection();
		try {
			board = boardDao.selectAdminBoardOne(conn, boardNo);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return board;
	}

	public List<Integer> selectAdminBoardNumList() {
		List<Integer> list = null;
		Connection conn = getConnection();
		try {
			list = boardDao.selectAdminBoardNumList(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public int updateAdminBoard(Board board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.updateAdminBoard(conn,board);
			if(result>0) {
				commit(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int deleteAdminBoard(int boardNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.deleteAdminBoard(conn,boardNo);
			if(result>0) {
				commit(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public int updateReadCnt(int boardNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result=boardDao.updateReadCnt(conn, boardNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateAdminReadCnt(int boardNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.updateAdminReadCnt(conn, boardNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	/*마이페이지 내가 쓴 글*/

	public List<Board> selectMyBoardList(String writer, int start, int end) {
		List<Board> list = null;
		Connection conn = getConnection();
		try {
			list = boardDao.selectMyBoardList(writer, conn, start, end);
		}
		catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		
		return list;
	}

	/*내가 쓴 글 페이지*/
	
	public int selectMyBoardTotal(String writer) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.selectMyBoardTotal(conn, writer);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/*게시판 검색*/
	
	public List<Board> searchBoardList(Map<String, String> param, int start, int end) {
		Connection conn = getConnection();
		List<Board> list = boardDao.searchBoardList(conn, param, start, end);
		close(conn);
		return list;
	}

	/*게시판 검색 페이지*/
	
	public int searchBoardListCount(Map<String, String> param) {
		Connection conn = getConnection();
		int totalContents = 0;
		totalContents = boardDao.searchBoardListCount(conn, param);
		close(conn);
		return totalContents;
	}

	/*관리자 게시판 검색*/
	
	public List<Board> searchAdminBoardList(Map<String, String> param, int start, int end) {
		Connection conn = getConnection();
		List<Board> list = boardDao.searchAdminBoardList(conn, param, start, end);
		close(conn);
		return list;
	}

	/*관리자 게시판 검색 페이지*/
	
	public int searchAdminBoardListCount(Map<String, String> param) {
		Connection conn = getConnection();
		int totalContents = 0;
		totalContents = boardDao.searchAdminBoardListCount(conn, param);
		close(conn);
		return totalContents;
	}

	/*내가 쓴글 검색*/
	
	public List<Board> searchMyBoardList(String writer, Map<String, String> param, int start, int end) {
		Connection conn = getConnection();
		List<Board> list = boardDao.searchMyBoardList(conn, writer, param, start, end);
		close(conn);
		return list;
	}

	/*내가 쓴글 검색 페이지*/
	
	public int searchMyBoardListCount(String writer, Map<String, String> param) {
		Connection conn = getConnection();
		int totalContents = boardDao.searchMyBoardListCount(conn, writer, param);
		close(conn);
		return totalContents;
	}
}
