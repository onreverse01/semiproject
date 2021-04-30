package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import admin.model.dao.AdminDao;

public class AdminService {
	private AdminDao adminDao = new AdminDao();

	public List<String> selectBlackList(int start, int end) {
		List<String> list = null;
		Connection conn = getConnection();
		try {
			list = adminDao.selectBlackList(conn, start, end);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return list;
	}

	public int selectBlackListCount() {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = adminDao.selectBlackListCount(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int unbanFromBlackList(String email) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = adminDao.unbanFromBlackList(conn, email);
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
