package com.example.servletBankAccountProject.dao.mapper;

import com.example.servletBankAccountProject.model.CreditCard;
import com.example.servletBankAccountProject.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardMapper implements ObjectMapper<CreditCard> {

    @Override
    public CreditCard extractFromResultSet(ResultSet rs) throws SQLException {

        CreditCard creditCard = new CreditCard();

        creditCard.setId(rs.getLong("id"));
        creditCard.setCardName(rs.getString("cardName"));
        creditCard.setCardNumber(rs.getString("cardNumber"));
        creditCard.setBalance(rs.getBigDecimal("balance"));
        creditCard.setOwnerId(rs.getLong("ownerId"));
        creditCard.setRequest(rs.getString("request"));
        creditCard.setStatus(rs.getString("status"));

        return creditCard;
    }
}
