package transaction;

import java.util.List;

public interface TransactionDAO_interface {
	public List<Transaction> getAll();
	public Transaction getOne(String id);
	public Transaction update(Transaction member);
	public Transaction insert(Transaction member);
	public List<Transaction> getWithinTheCondition(String channelId,
			String startDate,String endDate,String membername);
	public List<Transaction> getWithinTheCondition(String channelId,
			String startDate,String endDate);
}
