package findId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import signup.ClientDTO;

public class FindIdDAO {

	private static FindIdDAO fiDAO;
	
	private FindIdDAO() {
	
	}
	
	public static FindIdDAO getInstance() {
		if(fiDAO==null) {
			fiDAO=new FindIdDAO();
		}
		return fiDAO;
	}//FindIdDAO
	
	//회원 이름과 이메일 정보로 회원 ID조회
	public ClientDTO selectClientId(String clientName, String clientEmail) {
		
		ClientDTO cDTO = null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			
			con=DBConnection.getInstance().getConn();
			
			String sql="SELECT CLIENT_ID FROM CLIENT WHERE CLIENT_NAME=? AND CLIENT_EMAIL=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, clientName);
			pstmt.setString(2, clientEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

			    cDTO = new ClientDTO();

			    cDTO.setClientId(rs.getString("CLIENT_ID"));

			}
			
		}catch(SQLException se) {
		    se.printStackTrace();
	    } finally {
	        DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }//try
		
		return cDTO;
		
	}//selectClientId
	
}//class
