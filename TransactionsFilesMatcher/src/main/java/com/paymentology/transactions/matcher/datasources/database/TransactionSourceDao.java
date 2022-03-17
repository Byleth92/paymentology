package com.paymentology.transactions.matcher.datasources.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.paymentology.transactions.matcher.entities.TransactionSource;


@Repository
public class TransactionSourceDao {

	@Autowired private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<TransactionSource> selectInRange(String query) {
		
		return jdbcTemplate.query(query, new RowMapper<TransactionSource>(){  
		    @Override  
		    public TransactionSource mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	return TransactionSource.builder()
		    							.profileName(rs.getString(2))
		    							.transactionAmount(rs.getBigDecimal(3))
		    							.transactionDate(rs.getTimestamp(4).toLocalDateTime())
		    							.transactionDescription(rs.getString(5))
		    							.transactionId(rs.getString(6))
		    							.transactionNarrative(rs.getString(7))
		    							.walletReference(rs.getString(8))
		    							.build();
		    }});  
	}
}
