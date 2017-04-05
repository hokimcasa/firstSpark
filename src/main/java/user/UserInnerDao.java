package user;

import java.util.List;

public class UserInnerDao implements UserDAO_interface {
	@Override
	public List<User> getAll() {
		UserData userList = new UserData();
		return 	 userList.getUserList();
	}

	@Override
	public Boolean loginCheck(User user) {
		UserInnerDao Dao = new UserInnerDao();
		return Dao.getAll().contains(user);
	}

	@Override
	public User getOne(String id) {
		UserInnerDao Dao = new UserInnerDao();
		for (User tmp : Dao.getAll()) {
			if(tmp.getId().equals(id)){
				return tmp;
			}		
		}
		return null;
	}

}
