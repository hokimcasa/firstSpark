package channel;

import java.util.List;

import member.Member;

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

	@Override
	public Channel update(Channel channel) {
		ChannelInnerDao Dao = new ChannelInnerDao();
		Channel updateChannel = Dao.getOne(channel.getId());
		int index = Dao.getAll().indexOf(updateChannel);
		System.out.println(channel.toString());
		updateChannel.setChannelName(channel.getChannelName());
		updateChannel.setFee(channel.getFee());
		updateChannel.setDescription(channel.getDescription());
		Dao.getAll().set(index, updateChannel);
		
		return updateChannel;
	}

}
