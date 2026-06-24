package findPw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBConnection;
import signup.ClientDTO;

public class FindPwDAO {

	private static FindPwDAO fPWDAO;
	
	private FindPwDAO() {
		
	}
	
	public static FindPwDAO getInstance() {
		if(fPWDAO==null) {
			fPWDAO=new FindPwDAO();
		}
		return fPWDAO;
	}
	//회원 아이디와 이메일로 비밀번호 존재 여부 및 회원 정보 조회
	public ClientDTO selectClientPW(String clientId, String clientEmail) {
		
		ClientDTO cDTO=null;
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		String sql="SELECT * FROM CLIENT WHERE CLIENT_ID=? AND CLIENT_EMAIL=?";
		
		try {
			con=DBConnection.getInstance().getConn();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, clientId);
            pstmt.setString(2, clientEmail);
            
            rs=pstmt.executeQuery();
            
            if(rs.next()) {
            	cDTO=new ClientDTO();
            	
            	cDTO.setClientId(rs.getString("CLIENT_ID"));
            	cDTO.setClientEmail(rs.getString("CLIENT_EMAIL"));
            	cDTO.setClientHash(rs.getString("CLIENT_HASH"));
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.getInstance().dbClose(rs, pstmt, con);
		}
		return cDTO;
		
	}
	//비밀변호 변경(새 비밀번호로 업데이트)
	public int updateClientPw(String clientId, String newPw) {

        int result = 0;
        
        Connection con = null;
        PreparedStatement pstmt = null;

        String sql =
            "UPDATE CLIENT SET CLIENT_HASH=? WHERE CLIENT_ID=?";

        try {
            con = DBConnection.getInstance().getConn();

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, newPw);
            pstmt.setString(2, clientId);

            result = pstmt.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }finally {
			DBConnection.getInstance().dbClose(null, pstmt, con);
		}

        return result;
    }
}
	

