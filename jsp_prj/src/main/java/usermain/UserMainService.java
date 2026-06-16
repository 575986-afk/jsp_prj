package usermain;

import java.util.List;

import productDetail.ProductDTO;

public class UserMainService {

	private UserMainDAO umDAO;

    public UserMainService() {
        umDAO = UserMainDAO.getInstance();
    }
    
    public int pageScale() {
	   return 20;
    }
   
   	public int totalPage(int totalCount, int pageScale) {
   		int page=totalCount/pageScale;
   		if(totalCount%pageScale!=0) {
   			page++;
   		}
   		return page;
   	}
   	
   	public int startNum(int currentPage, int pageScale) {
   		return (currentPage -1)*pageScale+1;
   	}
   	
   	public int endNum(int currentPage, int pageScale) {
   		return currentPage*pageScale;
   	}
   
   	public int totalCount(RangeDTO range) {
   		return umDAO.selectTotalCount(range);
   	}
   
    public List<ProductDTO> searchBest() {
        return umDAO.selectBest();
    }

    public List<ProductDTO> searchSale() {
        return umDAO.selectSale();
    }

    public List<ProductDTO> getBannerProducts() {
        return umDAO.selectBannerProducts();
    }

    public List<ProductDTO> getCategory(RangeDTO range) {
    	
    	int pageScale=pageScale();
    	
    	int start=startNum(range.getStartNum(),pageScale);
    	int end=endNum(range.getStartNum(),pageScale);
    	
    	range.setStartNum(start);
    	range.setEndNum(end);
    	
        return umDAO.selectCategory(range);
    }
	
   

}
