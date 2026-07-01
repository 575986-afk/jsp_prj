package changeClientInfo;

public class ChangeClientInfoDAO {

	private static ChangeClientInfoDAO cciDAO;
	
	private ChangeClientInfoDAO() {
		
	}
	
	public static ChangeClientInfoDAO getInstance() {
		if(cciDAO==null) {
			cciDAO=new ChangeClientInfoDAO();
		}
		return cciDAO;
	}
}
