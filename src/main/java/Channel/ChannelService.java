package Channel;

import java.util.List;

public class ChannelService {
	private ChannelDAO_interface DAO = new ChannelInnerDao();
	
	public List<Channel> getAll(){
		return DAO.getAll();
	}

	public Channel getOne(String id){
		return DAO.getOne(id);
	}

}
