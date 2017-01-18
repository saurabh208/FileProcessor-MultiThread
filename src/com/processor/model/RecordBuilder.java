package com.processor.model;

public class RecordBuilder {

	

	private static final int FIRST_NAME_MAX_LENGTH = 35;
	private static final int LAST_NAME_MAX_LENGTH = 35;
	private static final int ACCOUNT_MAX_LENGTH = 35;
	private static final String DATE_PATTERN = "YYYY-MM-dd";

	private String id;
	private String firstName;
	private String lastName;
	private String account;
	private String amount;
	private String date;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public Record build() {
		return new Record();
	}
	
	public RecordBuilder(String id, String firstName, String lastName, String account, String amount, String date) {
		super();
		this.id = id;
	}
	
	public RecordBuilder(String id) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
		this.amount = amount;
		this.date = date;
	}
	@Override
	public String toString() {
		return "RecordBuilder [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", account="
				+ account + ", amount=" + amount + ", date=" + date + "]";
	}
	
		
}
