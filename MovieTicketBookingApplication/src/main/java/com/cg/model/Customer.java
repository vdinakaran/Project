package com.cg.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String customerName;
	public Customer(String customerName, String address, String mobileNumber, String email, String password) {
		super();
		this.customerName = customerName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
	}
	private String address;
	private String mobileNumber;
	private String email;
	private String password;
//	@JsonIgnore
//	@OneToOne
//	private User user;
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<TicketBooking> booking;
	public Customer(int customerId, String customerName, String address, String mobileNumber, String email,
			String password) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", password=" + password + ", booking="
				+ booking + "]";
	}
	public Customer(int customerId, String customerName, String address, String mobileNumber, String email, String password,
		List<TicketBooking> booking) {
	super();
	this.customerId = customerId;
	this.customerName = customerName;
	this.address = address;
	this.mobileNumber = mobileNumber;
	this.email = email;
	this.password = password;
	this.booking = booking;
}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<TicketBooking> getBooking() {
		return booking;
	}
	public void setBooking(List<TicketBooking> booking) {
		this.booking = booking;
	}

	
}
		
			