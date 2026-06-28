package bestSale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import kr.co.sist.dao.GetConnection;
import productDetail.ProductDTO;
import usermain.RangeDTO;

public class BestSaleDAO {

	private static BestSaleDAO bsDAO;
	
	private BestSaleDAO() {
		
	}
	
	public static BestSaleDAO getInstance() {
		if(bsDAO==null) {
			bsDAO=new BestSaleDAO();
		}
		return bsDAO;
	}
	//베스트 상품 조회
	public List<ProductDTO> bestProduct(RangeDTO rDTO){
		
		List<ProductDTO> list=new ArrayList<>();

	    Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    
	    GetConnection gc=GetConnection.getInstance();
	    
	    try {
	    	
	    	con=gc.getConn("dbcp");
	    	
	    	String sql="SELECT p.PRODUCT_ID,"
	    			+ "       p.PRODUCT_NAME,"
	    			+ "       p.PRICE,"
	    			+ "       SUM(od.QUANTITY) sales_count"
	    			+ "FROM PRODUCT p"
	    			+ "INNER JOIN ORDER_DETAILS od"
	    			+ "ON p.PRODUCT_ID = od.PRODUCT_ID"
	    			+ "GROUP BY p.PRODUCT_ID,"
	    			+ "         p.PRODUCT_NAME,"
	    			+ "         p.PRICE"
	    			+ "ORDER BY sales_count DESC";
	    	
	    	pstmt=con.prepareStatement(sql);
	    	
	    	rs=pstmt.executeQuery();
	    	
	    	while(rs.next()) {

	    	    ProductDTO pDTO = new ProductDTO();

	    	    pDTO.setPrdID(rs.getString("PRODUCT_ID"));
	    	    pDTO.setPrdName(rs.getString("PRODUCT_NAME"));
	    	    pDTO.setPrice(rs.getInt("PRICE"));

	    	    list.add(pDTO);
	    	}
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }
		
		return list;
	}
	//인기급상숭 상품 조회
	public List<ProductDTO> risingProduct(RangeDTO rDTO){
		
		List<ProductDTO> list=new ArrayList<>();

	    Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    
	    GetConnection gc=GetConnection.getInstance();
	    
	    try {
	    	
	    	con=gc.getConn("dbcp");
	    	
	    	String sql="SELECT p.*, SUM(od.QUANTITY) sales_count "
	    			+ "FROM PRODUCT p "
	    			+ "INNER JOIN ORDER_DETAILS od "
	    			+ "ON p.PRODUCT_ID = od.PRODUCT_ID "
	    			+ "INNER JOIN ORDER o "
	    			+ "ON od.ORDER_ID = o.ORDER_ID "
	    			+ "WHERE o.ORDER_DATE >= SYSDATE - 7 "
	    			+ "GROUP BY p.PRODUCT_ID "
	    			+ "ORDER BY sales_count DESC ";
	    	
	    	pstmt=con.prepareStatement(sql);
	    	
	    	rs=pstmt.executeQuery();
	    	
	    		while(rs.next()) {

	    		    ProductDTO pDTO = new ProductDTO();

	    		    pDTO.setPrdID(rs.getString("PRODUCT_ID"));
	    		    pDTO.setPrdName(rs.getString("PRODUCT_NAME"));
	    		    pDTO.setPrice(rs.getInt("PRICE"));

	    		    list.add(pDTO);
	    		}
	    		
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }
		
		return list;
	}
	
	//알뜰 쇼핑 상품 조회
	public List<ProductDTO> economyProduct(RangeDTO rDTO){
		
		List<ProductDTO> list=new ArrayList<>();

	    Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    
	    GetConnection gc=GetConnection.getInstance();
	    
	    try {
	    	
	    	con=gc.getConn("dbcp");
	    	
	    	String sql="SELECT * FROM PRODUCT ORDER BY PRICE ASC";
	    	
	    	pstmt=con.prepareStatement(sql);
	    	
	    	rs=pstmt.executeQuery();
	    	
	    	while(rs.next()) {

	    	    ProductDTO pDTO = new ProductDTO();

	    	    pDTO.setPrdID(rs.getString("PRODUCT_ID"));
	    	    pDTO.setPrdName(rs.getString("PRODUCT_NAME"));
	    	    pDTO.setPrice(rs.getInt("PRICE"));

	    	    list.add(pDTO);
	    	}
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }
		
		return list;
	}
	
	//반값 세일 상품 조회
	public List<ProductDTO> halfSaleProduct(RangeDTO rDTO){
		
		List<ProductDTO> list=new ArrayList<>();

	    Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    
	    GetConnection gc=GetConnection.getInstance();
	    
	    try {
	    	
	    	con=gc.getConn("dbcp");
	    	
	    	String sql="SELECT * FROM PRODUCT WHERE DISCOUNT >=50 ORDER BY DISCOUNT DESC";
	    	
	    	pstmt=con.prepareStatement(sql);
	    	
	    	rs=pstmt.executeQuery();
	    	
	    	while(rs.next()) {

	    	    ProductDTO pDTO = new ProductDTO();

	    	    pDTO.setPrdID(rs.getString("PRODUCT_ID"));
	    	    pDTO.setPrdName(rs.getString("PRODUCT_NAME"));
	    	    pDTO.setPrice(rs.getInt("PRICE"));

	    	    list.add(pDTO);
	    	}
	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	DBConnection.getInstance().dbClose(rs, pstmt, con);
	    }
		
		return list;
	}
	public int getTotalBestCount(RangeDTO rDTO) {
		int count=0;
		
		return count; 
	}

	public int getTotalRisingCount(RangeDTO rDTO) {
		int count=0;
		
		return count;
	}

	public int getTotalEconomyCount(RangeDTO rDTO) {
		int count=0;
		
		return count;
	}

	public int getTotalHalfSaleCount(RangeDTO rDTO) {
		int count=0;
		
		return count;
	}
	
}
