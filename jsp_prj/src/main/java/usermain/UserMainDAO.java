package usermain;

import java.util.ArrayList;
import java.util.List;

import productDetail.ProductDTO;

public class UserMainDAO {

	private static UserMainDAO umDAO;
	
	private UserMainDAO() {
		
	}
	
	public static UserMainDAO getInstance() {
		if(umDAO==null) {
			umDAO=new UserMainDAO();
		}
		return umDAO;
	}
	
	public List<ProductDTO> selectBest(){
		List<ProductDTO> list=new ArrayList<>();
		return list;
	}
	public List<ProductDTO> selectSale(){
		List<ProductDTO> list=new ArrayList<>();
		return list;
	}
	public List<ProductDTO> selectBannerProducts() {
		List<ProductDTO> list=new ArrayList<>();
		return list;
	}
	public List<ProductDTO> selectCategory(String category){
		List<ProductDTO> list=new ArrayList<>();
		return list;
	}

}
