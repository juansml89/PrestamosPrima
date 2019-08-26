package com.PrestamosPrima.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="description")
	String description;
	
	@Column(name="total_amount")
	double totalAmount;
	
	@OneToMany(mappedBy="account")
    private List<Transaction> transactions;
	
	@ManyToOne
    @JoinColumn(name="user_id")
	User user;
	
	@Transient
	private double modifierValue = 0;
	
	public Account() {
		
	}

	public Account(int id, String description, double totalAmount, List<Transaction> transactions, User user) {
		this.id = id;
		this.description = description;
		this.totalAmount = totalAmount;
		this.transactions = transactions;
		this.user = user;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + "]";
	}

	public double getModifierValue() {
		return modifierValue;
	}

	public void setModifierValue(double modifierValue) {
		this.modifierValue = modifierValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
