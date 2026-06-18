package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Connection 얻기와 연결 끊기
 * 05-12-2026 Singleton pattern 도입.
 */
public class DBConnection {
	
	//객체가 생성된 상태를 저장하기 위해서 static변수를 저장
	private static DBConnection dbCon;
	
	/**
	 * 객체 생성을 클래스 외부에서 할 수 없도록 만들기 위해서
	 */
	private DBConnection() {
		
	}
	
	/**
	 * 하나의 객체를 생성하고 얻어가는 method
	 * @return 생성된 하나의 객체
	 */
	public static DBConnection getInstance() {
		if (dbCon==null) {
			dbCon=new DBConnection();
		}//if
		
		return dbCon;
		
	}//getinstance
	
	public Connection getConn() throws SQLException{
		
		File file=new File("src/main/java/db/database.properties");
		
		return getConn(file);
	}
	
	/**
	 * DBMS와 연결된 Connection을 반환하는 일.
	 * @param prop DBMS 의 정보를 로딩한 객체
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection getConn(File file)throws SQLException {
		Connection con=null;
		
		if (!file.exists()) {//설정 정보를 가진 파일이 해당 경로에 조재하지 않으면 
			//DB작업을 할 필요가 없다.
			return null;
		}//if
		
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//catch
		
		//1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2.Connection 얻기
		String url=prop.getProperty("url");
		String id=prop.getProperty("id");
		String pass=prop.getProperty("pass");
		
		con=DriverManager.getConnection(url,id,pass);
		
		return con;
	}//getconn
	
	public void dbClose(ResultSet rs, Statement stmt, Connection con) {
		try {

	        if(rs != null) rs.close();
	        if(stmt != null) stmt.close();

	    } catch(SQLException e) {

	        e.printStackTrace();

	    } finally {

	        try {

	            if(con != null) con.close();

	        } catch(SQLException e) {

	            e.printStackTrace();

	        }

	    }
		
	}//dbclose
	
}//class
