package channel;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ChannelData {
	private ArrayList<Channel> channelList = new ArrayList<Channel>();
	public ArrayList<Channel> getChannelList() {
		Channel c1 = new Channel();
		c1.setId("C00001");
		c1.setChannelName("�q�Ƶ�]�����|");
		c1.setFee(15);
		c1.setDescription("����q��");
		c1.setCreateDate(new Timestamp(System.currentTimeMillis()));
		c1.setCreateUser("U00001");	
		channelList.add(c1);
		c1 = new Channel();
		c1.setId("C00002");
		c1.setChannelName("�_�Ǻ����ӫ�");
		c1.setFee(15);
		c1.setDescription("�����q��");
		c1.setCreateDate(new Timestamp(System.currentTimeMillis()));
		c1.setCreateUser("U00001");	
		channelList.add(c1);
		c1 = new Channel();
		c1.setId("C00003");
		c1.setChannelName("�Y�s�Y�p�A");
		c1.setFee(15);
		c1.setDescription("����q��");
		c1.setCreateDate(new Timestamp(System.currentTimeMillis()));
		c1.setCreateUser("U00002");	
		channelList.add(c1);
		c1 = new Channel();
		c1.setId("C00004");
		c1.setChannelName("�_�ۺ����C��");
		c1.setFee(15);
		c1.setDescription("����+�����q��");
		c1.setCreateDate(new Timestamp(System.currentTimeMillis()));
		c1.setCreateUser("U00003");	
		channelList.add(c1);
		return channelList;
	}
	
	
}
