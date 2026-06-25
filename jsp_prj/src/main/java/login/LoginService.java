package login;

import changeClientInfo.ChangeClientInfoDAO;
import signup.ClientDTO;

public class LoginService {

	private LoginDAO lDAO;
	
	public LoginService() {
		lDAO=LoginDAO.getInstance();
	}
	//회원아이디와 비밀번호로 로그인처리(로그인 성공시 회원 정보 반환)
	public ClientDTO login(String clientId, String password) {
		return lDAO.selectLoginInfo(clientId, password);
	}
	//회원 아이디로 현재 로그인 상태 여부 확인
	public boolean checkLogin(String cliendId) {
		return false;
	}
}
