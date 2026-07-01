package kr.co.sist.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.GetConnection;
import kr.co.sist.user.member.MemberDTO;

public class BoardDAO {
	private static BoardDAO mpDAO;

	private BoardDAO() {
	}// MyPageDAO

	public static BoardDAO getInstance() {
		if (mpDAO == null) {
			mpDAO = new BoardDAO();
		} // end if
		return mpDAO;
	}// getInstance

	public int selectTotalCount() throws SQLException {
		int totalCount = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			// 커넥션얻기
			con = gc.getConn("dbcp");
			// 쿼리문 수행 객체 얻기
			String selectTotal = "select count(*) cnt from board";
			pstmt = con.prepareStatement(selectTotal);
			// 바인드 변수에 값 설정

			// 쿼리문 실행 후 결과 얻기
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt("cnt");
			} // end if
		} finally {
			gc.dbClose(rs, pstmt, con);
		} // end finally

		return totalCount;
	}// selectTotalCount

	public List<BoardDTO> selectBoard(RangeDTO rDTO) throws SQLException {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			// 커넥션얻기
			con = gc.getConn("dbcp");
			// 쿼리문 수행 객체 얻기
			StringBuilder selectBoard = new StringBuilder();
			selectBoard.append("	select num, id, title, input_date,cnt	")
					.append("	from (select NUM, ID, TITLE, INPUT_DATE, CNT,	")
					.append("		row_number() over( order by input_date desc) rnum	")
					// .append(" row_number() over( order by num asc) rnum ")
					.append("		from board)	").append("	where rnum between ? and ?	");
			pstmt = con.prepareStatement(selectBoard.toString());
			// 바인드 변수에 값 설정

			pstmt.setInt(1, rDTO.getStartNum());
			pstmt.setInt(2, rDTO.getEndNum());

			// 쿼리문 실행 후 결과 얻기
			rs = pstmt.executeQuery();
			BoardDTO bDTO = null;
			while (rs.next()) {
				bDTO = new BoardDTO();
				bDTO.setNum(rs.getInt("num"));
				bDTO.setId(rs.getString("id"));
				bDTO.setTitle(rs.getString("title"));
				bDTO.setInputDate(rs.getDate("input_date"));
				bDTO.setCnt(rs.getInt("cnt"));

				boardList.add(bDTO);
			} // end
		} finally {
			gc.dbClose(rs, pstmt, con);
		} // end finally

		return boardList;
	}// selectBoard

	public BoardDTO selectBoardDetail(int num) throws SQLException {
		BoardDTO bDTO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			// 커넥션얻기
			con = gc.getConn("dbcp");
			// 쿼리문 수행 객체 얻기
			StringBuilder selectBoard = new StringBuilder();
			selectBoard.append("	select num, id, title, content,input_date,cnt,ip		")
					.append("	from board											")
					.append("	where num=?											");
			pstmt = con.prepareStatement(selectBoard.toString());
			// 바인드 변수에 값 설정

			pstmt.setInt(1, num);

			// 쿼리문 실행 후 결과 얻기
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bDTO = new BoardDTO();
				bDTO.setNum(num);
				bDTO.setId(rs.getString("id"));
				bDTO.setTitle(rs.getString("title"));
				bDTO.setInputDate(rs.getDate("input_date"));
				bDTO.setCnt(rs.getInt("cnt"));
				bDTO.setIp(rs.getString("ip"));

				Clob clob = rs.getClob("content");
				StringBuilder content = new StringBuilder();
				if (clob != null) {// 글의 내용이 존재
					BufferedReader br =new BufferedReader(clob.getCharacterStream());
					try {
						String temp="";
						while((temp=br.readLine())!=null) {
							content.append(temp).append("\n");
						}
						if(br!=null) {br.close();}
					} catch (IOException ie) {
						ie.printStackTrace();
					}
				}
				bDTO.setContent(content.toString());

			} // end
		} finally {
			gc.dbClose(rs, pstmt, con);
		} // end finally

		return bDTO;
	}// selectBoardDetail

	public void updateCnt(int num) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			// 커넥션얻기
			con = gc.getConn("dbcp");
			// 쿼리문 수행 객체 얻기
			StringBuilder insertBoard = new StringBuilder();
			insertBoard
			.append("	update board 	")
			.append("	set cnt=cnt+1 where num=?	");
			
			pstmt = con.prepareStatement(insertBoard.toString());
			// 바인드 변수에 값 설정
			
			pstmt.setInt(1, num);
			
			// 쿼리문 실행 후 결과 얻기
			pstmt.executeUpdate();
			
		} finally {
			gc.dbClose(null, pstmt, con);
		} //
		
	}// updateBoard
	public void insertBoard(BoardDTO bDTO) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;

		GetConnection gc = GetConnection.getInstance();

		try {
			// 커넥션얻기
			con = gc.getConn("dbcp");
			// 쿼리문 수행 객체 얻기
			StringBuilder insertBoard = new StringBuilder();
			insertBoard.append("	insert into board(num, id, title, content, ip) 	")
					.append("	values(seq_board.nextval,?,?,?,?)	");

			pstmt = con.prepareStatement(insertBoard.toString());
			// 바인드 변수에 값 설정

			pstmt.setString(1, bDTO.getId());
			pstmt.setString(2, bDTO.getTitle());
			pstmt.setString(3, bDTO.getContent());
			pstmt.setString(4, bDTO.getIp());

			// 쿼리문 실행 후 결과 얻기
			pstmt.executeUpdate();

		} finally {
			gc.dbClose(null, pstmt, con);
		} //

	}// insertBoard
	public int updateBoard(BoardDTO bDTO) throws SQLException {
		
		int cnt=0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			// 커넥션얻기
			con = gc.getConn("dbcp");
			// 쿼리문 수행 객체 얻기
			StringBuilder updateBoard = new StringBuilder();
			updateBoard
			.append("	update board 										")
			.append("	set title=?, content=?, ip=?						")
			.append("	where id=? and num=?								");
			
			pstmt = con.prepareStatement(updateBoard.toString());
			// 바인드 변수에 값 설정
			
			pstmt.setString(1, bDTO.getTitle());
			pstmt.setString(2, bDTO.getContent());
			pstmt.setString(3, bDTO.getIp());
			pstmt.setString(4, bDTO.getId());
			pstmt.setInt(5, bDTO.getNum());
			
			// 쿼리문 실행 후 결과 얻기
			cnt=pstmt.executeUpdate();
			
		} finally {
			gc.dbClose(null, pstmt, con);
		} //
		return cnt;
		
	}// updateBoard
	public int deleteBoard(BoardDTO bDTO) throws SQLException {
		
		int cnt=0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		GetConnection gc = GetConnection.getInstance();
		
		try {
			// 커넥션얻기
			con = gc.getConn("dbcp");
			// 쿼리문 수행 객체 얻기
			StringBuilder deleteBoard = new StringBuilder();
			deleteBoard
			.append("	delete board 										")
			.append("	where id=? and num=?								");
			
			pstmt = con.prepareStatement(deleteBoard.toString());
			// 바인드 변수에 값 설정
			
			pstmt.setString(1, bDTO.getId());
			pstmt.setInt(2, bDTO.getNum());
			
			// 쿼리문 실행 후 결과 얻기
			cnt=pstmt.executeUpdate();
			
		} finally {
			gc.dbClose(null, pstmt, con);
		} //
		return cnt;
		
	}// updateBoard

}// class
