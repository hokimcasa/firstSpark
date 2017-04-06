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
	
}
