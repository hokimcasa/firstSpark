package transaction;

import java.sql.Timestamp;

public class Transaction {
	private String id;
	private Timestamp transactionDate;  
	private Integer money;
	private String memberId;
	private String channelId;
	private String remark;
	private String buyerName;
	private String buyerAccountNo;
	private String productList;
	private Timestamp postingDate;

	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public Timestamp getTransactionDate() {
		return transactionDate;
	}




	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}




	public Integer getMoney() {
		return money;
	}




	public void setMoney(Integer money) {
		this.money = money;
	}




	public String getMemberId() {
		return memberId;
	}




	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}




	public String getChannelId() {
		return channelId;
	}




	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	public String getBuyerName() {
		return buyerName;
	}




	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}




	public String getBuyerAccountNo() {
		return buyerAccountNo;
	}




	public void setBuyerAccountNo(String buyerAccountNo) {
		this.buyerAccountNo = buyerAccountNo;
	}




	public String getProductList() {
		return productList;
	}




	public void setProductList(String productList) {
		this.productList = productList;
	}




	public Timestamp getPostingDate() {
		return postingDate;
	}




	public void setPostingDate(Timestamp postingDate) {
		this.postingDate = postingDate;
	}







	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}




	public boolean isValid() {
		
//		return password!=null&&id!=null;
		return false;
	}
}
