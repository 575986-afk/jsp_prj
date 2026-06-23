package findPw;

public class FindPwDAO {

	private static FindPwDAO fPWDAO;
	
	private FindPwDAO() {
		
	}
	
	public FindPwDAO getInstance() {
		if(fPWDAO==null) {
			fPWDAO=new FindPwDAO();
		}
		return fPWDAO;
	}
}
