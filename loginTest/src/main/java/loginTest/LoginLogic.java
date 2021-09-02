package loginTest;

public class LoginLogic {
	public boolean execute(User user) {
		DBManager dbManager = DBManager.getInstance();
		String savedPass = dbManager.getSavedPass(user);
		if(user.getPass().equals(savedPass)) {
			return true;
		}
		return false;
	}

}
