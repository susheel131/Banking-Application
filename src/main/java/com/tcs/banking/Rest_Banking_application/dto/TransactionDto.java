package com.tcs.banking.Rest_Banking_application.dto;

import org.springframework.stereotype.Component;

@Component
public class TransactionDto {
	
	 private String accountNumber;
	    private String receiverAccount; // used only for transfer
	    private double amount;
		public String getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getReceiverAccount() {
			return receiverAccount;
		}
		public void setReceiverAccount(String receiverAccount) {
			this.receiverAccount = receiverAccount;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public TransactionDto(String accountNumber, String receiverAccount, double amount) {
			super();
			this.accountNumber = accountNumber;
			this.receiverAccount = receiverAccount;
			this.amount = amount;
		}
		public TransactionDto() {
			super();
		}

}
