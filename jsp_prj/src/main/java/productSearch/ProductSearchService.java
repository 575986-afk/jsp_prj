package productSearch;

import java.util.List;

import productDetail.ProductDTO;

public class ProductSearchService {

	private ProductSearchDAO psDAO;
	
	public ProductSearchService() {
		psDAO=ProductSearchDAO.getInstance();
	}
	
	public List<ProductDTO> searchProduct(String prdName){
		
		return psDAO.selectProduct(prdName);
	}
	
	
}
