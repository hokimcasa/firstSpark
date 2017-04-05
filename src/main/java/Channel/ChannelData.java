package Channel;

import java.sql.Date;
import java.util.ArrayList;

public class ChannelData {
	private ArrayList<Channel> channelList = new ArrayList<Channel>();
	public ArrayList<Channel> getChannelList() {
		Channel c1 = new Channel();
		c1.setId("C00001");
		c1.setChannelname("通化街夜市");
		c1.setFee(15);
		c1.setDescription("實體通路");
		c1.setCreateDate(new Date(System.currentTimeMillis()));
		c1.setCreateUser("U00001");	
		channelList.add(c1);
		c1 = new Channel();
		c1.setId("C00002");
		c1.setChannelname("奇怪網路商城");
		c1.setFee(15);
		c1.setDescription("網路通路");
		c1.setCreateDate(new Date(System.currentTimeMillis()));
		c1.setCreateUser("U00001");	
		channelList.add(c1);
		c1 = new Channel();
		c1.setId("C00003");
		c1.setChannelname("某山頭小農");
		c1.setFee(15);
		c1.setDescription("實體通路");
		c1.setCreateDate(new Date(System.currentTimeMillis()));
		c1.setCreateUser("U00002");	
		channelList.add(c1);
		c1 = new Channel();
		c1.setId("C00004");
		c1.setChannelname("奇幻網路遊戲");
		c1.setFee(15);
		c1.setDescription("實體+網路通路");
		c1.setCreateDate(new Date(System.currentTimeMillis()));
		c1.setCreateUser("U00003");	
		channelList.add(c1);
		return channelList;
	}
	
	
}
