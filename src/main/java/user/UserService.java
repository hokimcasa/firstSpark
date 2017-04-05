package user;

import java.util.List;

public class UserService {
	private UserDAO_interface DAO = new UserInnerDao();
	
	public List<User> getAll(){
		return DAO.getAll();
	}

	public User getOne(String id){
		return DAO.getOne(id);
	}

	public Boolean loginCheck(User user){
		return DAO.loginCheck(user);
	}
}
