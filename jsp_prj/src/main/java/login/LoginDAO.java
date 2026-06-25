package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import signup.ClientDTO;

public class LoginDAO {

	private static LoginDAO lDAO;
	
	private LoginDAO() {
		
	}
	
	public static LoginDAO getInstance() {
		if(lDAO==null) {
			lDAO=new LoginDAO();
		}
		return lDAO;
	}
	//로그인 정보(아이디 및 비밀번호)일치 여부 확인
	public ClientDTO selectLoginInfo(String clientId, String clientPassword) {
		
		ClientDTO cDTO=new ClientDTO();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBConnection.getInstance().getConn();
			
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT CLIENT_ID, CLIENT_NAME");
			sql.append("FROM CLIENT");
			sql.append("WHERE CLIENT_ID=? AND CLIENT_HASH=?");
			
			pstmt=con.prepareStatement(sql.toString());
			
			pstmt.setString(1, "CLIENT_ID");
			pstmt.setString(2, "CLIENT_HASH");
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {

                cDTO = new ClientDTO();

                cDTO.setClientId(rs.getString("client_id"));
                cDTO.setClientName(rs.getString("client_name"));

            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return cDTO;
	}
}
