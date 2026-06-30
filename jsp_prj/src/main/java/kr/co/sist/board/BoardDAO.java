package kr.co.sist.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.sist.dao.GetConnection;
import kr.co.sist.user.member.MemberDTO;

public class BoardDAO {
	private static BoardDAO mpDAO;
	private BoardDAO() {
	}//MyPageDAO
	
	public static BoardDAO getInstance() {
		if(mpDAO == null) {
			mpDAO=new BoardDAO();
		}//end if
		return mpDAO;
	}//getInstance
	
	public int selectTotalCount()throws SQLException {
		int totalCount=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		GetConnection gc=GetConnection.getInstance();
		
		try {
			//커넥션얻기
			con=gc.getConn("dbcp");
			//쿼리문 수행 객체 얻기
			String selectTotal="select count(*) cnt from board";
			pstmt=con.prepareStatement(selectTotal);
			//바인드 변수에 값 설정
			
			//쿼리문 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			if(rs.next()){
				totalCount=rs.getInt("cnt");
			}//end if
		}finally {
			gc.dbClose(rs, pstmt, con);
		}//end finally		
		
		return totalCount;
	}//selectUserInfo
	
}//class
