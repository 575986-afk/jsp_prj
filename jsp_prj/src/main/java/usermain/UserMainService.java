package usermain;

import java.util.List;

import productDetail.ProductDTO;

public class UserMainService {

	private UserMainDAO umDAO;

    public UserMainService() {
        umDAO = UserMainDAO.getInstance();
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

    public List<ProductDTO> getCategory(String category) {
        return umDAO.selectCategory(category);
    }
	

}
