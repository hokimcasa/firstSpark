package transaction;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import member.Member;
import member.MemberInnerDao;

public class TransactionInnerDao implements TransactionDAO_interface {
	@Override
	public List<Transaction> getAll() {
		TransactionData transactionList = new TransactionData();
		return 	 transactionList.getTransactionList();
	}

	@Override
	public Transaction getOne(String id) {
		TransactionInnerDao Dao = new TransactionInnerDao();
		for (Transaction tmp : Dao.getAll()) {
			if(tmp.getId().equals(id)){
				return tmp;
			}		
		}
		return null;
	}

	@Override
	public Transaction update(Transaction member) {
		TransactionInnerDao Dao = new TransactionInnerDao();
		Transaction updateTransaction = Dao.getOne(member.getId());
		int index = Dao.getAll().indexOf(updateTransaction);
		Dao.getAll().set(index, updateTransaction);
		return updateTransaction;
	}

	@Override
	public Transaction insert(Transaction member) {
		new TransactionInnerDao().getAll().add(member);
		return member;
	}

	@Override
	public List<Transaction> getWithinTheCondition(String channelId, String startDate, String endDate,
			String membername) {
		List<Transaction> resultList = new ArrayList<Transaction>();
		TransactionInnerDao dao = new TransactionInnerDao();
		MemberInnerDao memdao = new MemberInnerDao();
		Timestamp start = null;
		Timestamp end = null;
		start = new Timestamp(Long.parseLong(startDate));
		end = new Timestamp(Long.parseLong(endDate));
		
		
		for (Transaction tmp : dao.getAll()) {
			Member memtmp = memdao.getOne(tmp.getMemberId());
			if(!memtmp.getChannelId().equals(channelId)){
				continue;
			}		
			if(!tmp.getTransactionDate().after(start)&&
			   !tmp.getTransactionDate().before(end)){
				continue;
			}
			if(!memtmp.getName().equals(membername)){
				continue;
			}	
			resultList.add(tmp);
		}
		return resultList;
	}

	@Override
	public List<Transaction> getWithinTheCondition(String channelId, String startDate, String endDate) {
		List<Transaction> resultList = new ArrayList<Transaction>();
		TransactionInnerDao dao = new TransactionInnerDao();
		MemberInnerDao memdao = new MemberInnerDao();
		Timestamp start = null;
		Timestamp end = null;
		start = new Timestamp(Long.parseLong(startDate));
		end = new Timestamp(Long.parseLong(endDate));
		
		for (Transaction tmp : dao.getAll()) {
			String channel = memdao.getOne(tmp.getMemberId()).getChannelId();
			if(!channel.equals(channelId)){
				continue;
			}		
			System.out.println("!tmp.getTransactionDate().after(start) = "+!tmp.getTransactionDate().after(start));
			System.out.println("!tmp.getTransactionDate().before(end) = "+!tmp.getTransactionDate().before(end));
			
			if(!tmp.getTransactionDate().after(start)||
			   !tmp.getTransactionDate().before(end)){
				continue;
			}
			resultList.add(tmp);
		}
		return resultList;
	}

}
