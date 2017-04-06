package transaction;

import java.util.List;

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

}
