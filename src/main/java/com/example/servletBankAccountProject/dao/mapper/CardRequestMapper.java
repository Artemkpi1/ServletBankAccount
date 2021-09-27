package com.example.servletBankAccountProject.dao.mapper;

import com.example.servletBankAccountProject.model.CardRequest;
import com.example.servletBankAccountProject.model.CreditCard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardRequestMapper implements ObjectMapper<CardRequest> {


    @Override
    public CardRequest extractFromResultSet(ResultSet rs) throws SQLException {
        CardRequest cardRequest = new CardRequest();

        cardRequest.setId(rs.getLong("id"));
        cardRequest.setCreditCard(rs.getLong("cardNameId"));
        cardRequest.setOwner(rs.getLong("ownerId"));

        return cardRequest;
    }
}
