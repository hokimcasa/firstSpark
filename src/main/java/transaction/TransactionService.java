package transaction;

import java.util.List;

public class TransactionService {
	private TransactionDAO_interface DAO = new TransactionInnerDao();
	
	public List<Transaction> getAll(){
		return DAO.getAll();
	}

	public Transaction getOne(String id){
		return DAO.getOne(id);
	}

	public Transaction update(Transaction transaction){
		return DAO.update(transaction);
	}
	public Transaction insert(Transaction transaction){
		return DAO.insert(transaction);
	}

	public List<Transaction> getWithinTheCondition(String channelId, String startDate, String endDate,
			String membername){
		return DAO.getWithinTheCondition(channelId,startDate,endDate,membername);
	}
	
	public List<Transaction> getWithinTheCondition(String channelId, String startDate, String endDate){
		return DAO.getWithinTheCondition(channelId,startDate,endDate);
	}
}
