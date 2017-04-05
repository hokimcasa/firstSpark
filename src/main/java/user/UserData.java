package user;

import java.util.ArrayList;

public class UserData {
	private ArrayList<User> userList = new ArrayList<User>();
	public ArrayList<User> getUserList() {
		for(int i=0;i<5;i++){
			User temp = new User();
			temp.setGroupId("UG000"+i);
			temp.setId("U0000"+i);
			temp.setPassword("U0000"+i);
			String Name = "";
			switch(i){
				case 0:{
					Name = "uc";
					break;
				}
				case 1:{
					Name = "tom";
					break;
				}
				case 2:{
					Name = "joe";
					break;
				}
				case 3:{
					Name = "hello";
					break;
				}
				case 4:{
					Name = "tiny";
					break;
				}
					
			}
			temp.setName(Name);
			userList.add(temp);
		}
		
		
		return userList;
	}
	
	
}
