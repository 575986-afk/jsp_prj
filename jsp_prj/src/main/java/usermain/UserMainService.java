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

    public String[] getBannerImages() {
    	
    	String[] banners=new String[3];
    	
    	banners[0]="images/imgbanner1.png";
    	banners[1]="images/imgbanner2.png";
    	banners[2]="images/imgbanner3.png";
    	
        return banners;
    }

    public List<ProductDTO> getCategory(RangeDTO range, int page) {

        int pageScale = 20;

        int start = (page - 1) * pageScale + 1;
        int end = page * pageScale;

        range.setStartNum(start);
        range.setEndNum(end);

        return umDAO.selectCategory(range);
    }
	
   

}
