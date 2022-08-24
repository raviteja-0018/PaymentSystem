package com.payments.paymentsystem.model;


public class AccountTransaction {

	private long sequenceId;
	private String transactionReference;
	private int amount;
	private int debit;
	private String description;
	private int billReferenceNumber;
	public long getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(long sequenceId) {
		this.sequenceId = sequenceId;
	}
	public String getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getDebit() {
		return debit;
	}
	public void setDebit(int debit) {
		this.debit = debit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBillReferenceNumber() {
		return billReferenceNumber;
	}
	public void setBillReferenceNumber(int billReferenceNumber) {
		this.billReferenceNumber = billReferenceNumber;
	}
	
	
	
}
