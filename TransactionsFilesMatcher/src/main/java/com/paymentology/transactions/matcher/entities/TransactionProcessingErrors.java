package com.paymentology.transactions.matcher.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction_processing_errors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionProcessingErrors {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Integer id;

	 @Column(name = "profile_name")
	 private String profileName;

	 @Column(name = "transaction_date")
	 private String transactionDate;

	 @Column(name = "transaction_amount")
	 private String transactionAmount;
	 
	 @Column(name = "transaction_narrative")
	 private String transactionNarrative;
	 
	 @Column(name = "transaction_description")
	 private String transactionDescription;
	 
	 @Column(name = "transaction_id")
	 private String transactionId;
	 
	 @Column(name = "transaction_type")
	 private String transactionType;
	 
	 @Column(name = "wallet_reference")
	 private String walletReference;
	 
	 @Column(name = "file")
	 private String file;
}
