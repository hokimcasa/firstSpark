package user;

import java.util.List;

public interface UserDAO_interface {
	public List<User> getAll();
	public Boolean loginCheck(User user);
	public User getOne(String id);
}
