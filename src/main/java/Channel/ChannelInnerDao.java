package channel;

import java.util.List;

public class ChannelInnerDao implements ChannelDAO_interface {
	@Override
	public List<Channel> getAll() {
		ChannelData memberList = new ChannelData();
		return 	 memberList.getChannelList();
	}

	@Override
	public Channel getOne(String id) {
		ChannelInnerDao Dao = new ChannelInnerDao();
		for (Channel tmp : Dao.getAll()) {
			if(tmp.getId().equals(id)){
				return tmp;
			}		
		}
		return null;
	}

}
