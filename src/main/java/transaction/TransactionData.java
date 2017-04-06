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
		t1.setBuyerName("����");
		t1.setBuyerAccountNo("4556755849763098");
		t1.setProductList("����,�_����,��ü��");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
		t1 =new Transaction();
		t1.setId("T00002");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()));
		t1.setMoney(200);
		t1.setMemberId("A123456789");
		t1.setBuyerName("�۫~");
		t1.setBuyerAccountNo("5220496195092724");
		t1.setProductList("���׶�,�׵�����,����");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
		t1 =new Transaction();
		t1.setId("T00003");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()+1000*60*60*24*1));
		t1.setMoney(200);
		t1.setMemberId("B234567898");
		t1.setBuyerName("�s��");
		t1.setBuyerAccountNo("4539335023351721");
		t1.setProductList("���R��,�C����,�V��,�|�u��,�q��ۣ");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
		t1 =new Transaction();
		t1.setId("T00004");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()+1000*60*60*24*2));
		t1.setMoney(200);
		t1.setMemberId("B234567898");
		t1.setBuyerName("�_�R");
		t1.setBuyerAccountNo("5556517554420687");
		t1.setProductList("�a�ʸ�,���,�Ե�");
		t1.setPostingDate(new Timestamp(t1.getTransactionDate().getTime()+1000*60*60*24*7));
		transactionList.add(t1);
		t1 =new Transaction();
		t1.setId("T00005");
		t1.setTransactionDate(new Timestamp(System.currentTimeMillis()+1000*60*60*24*3));
		t1.setMoney(200);
		t1.setMemberId("B234567898");
		t1.setBuyerName("Parking");
		t1.setBuyerAccountNo("4929154604848783");
		t1.setProductList("�E�Hۣ,����ۣ,��ۣ,�q��ۣ,Ĩۣ");
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
