package com.paymentology.transactions.matcher.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "probable_matches")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProbableMatchTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "probable_match_bidirectional_id")
	private String probableMatchBidirectionalId;
	
	@Column(name = "transaction_source_id")
	private Integer transactionSourceId;

	@Column(name = "profile_name")
	private String profileName;

	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;

	@Column(name = "transaction_amount")
	private BigDecimal transactionAmount;
	 
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

}
