package transaction;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TransactionData {
	private static ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	private static boolean initflag = false;
	private void init()
	{
		Transaction t1 =new Transaction();
		t1.setId("T00001");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()));
		t1.setMoney(200);
		t1.setMemberId("A123456789");
		t1.setBuyerName("»·¶¯");
		t1.setBuyerAccountNo("4556755849763098");
		t1.setProductList("ª£ÄÑ,À_¶»ÄÑ,¦×Ã¼ÄÑ");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
		t1 =new Transaction();
		t1.setId("T00002");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()));
		t1.setMoney(200);
		t1.setMemberId("A123456789");
		t1.setBuyerName("¸Û«~");
		t1.setBuyerAccountNo("5220496195092724");
		t1.setProductList("º±¦×¶º,¦×µ·ª£ÄÑ,¤ô»å");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
		t1 =new Transaction();
		t1.setId("T00003");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()+1000*60*60*24*1));
		t1.setMoney(200);
		t1.setMemberId("B234567898");
		t1.setBuyerName("·s¥ú");
		t1.setBuyerAccountNo("4539335023351721");
		t1.setProductList("°ªÄRµæ,«C¦¿µæ,¥V¥Ê,¥|©u¨§,¨q¬ÃÛ£");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
		t1 =new Transaction();
		t1.setId("T00004");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()+1000*60*60*24*2));
		t1.setMoney(200);
		t1.setMemberId("B234567898");
		t1.setBuyerName("Ä_ÄR");
		t1.setBuyerAccountNo("5556517554420687");
		t1.setProductList("¦a¥Ê¸­,ªãµæ,µÔµæ");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
		t1 =new Transaction();
		t1.setId("T00005");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()+1000*60*60*24*3));
		t1.setMoney(200);
		t1.setMemberId("B234567898");
		t1.setBuyerName("Parking");
		t1.setBuyerAccountNo("4929154604848783");
		t1.setProductList("ÂEÁHÛ£,¬ü¥ÕÛ£,¯óÛ£,¨q¬ÃÛ£,Ä¨Û£");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
	}
	
	public ArrayList<Transaction> getTransactionList() {
		if(!initflag){
			init();
			initflag = true;
		}
		return transactionList;
	}
	
	
}
