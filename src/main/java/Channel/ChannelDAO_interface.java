package channel;

import java.util.List;

public interface ChannelDAO_interface {
	public List<Channel> getAll();
	public Channel getOne(String id);
	public Channel update(Channel channel);
}