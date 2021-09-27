package com.example.servletBankAccountProject.dao.mapper;

import com.example.servletBankAccountProject.model.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements ObjectMapper<Payment> {

    @Override
    public Payment extractFromResultSet(ResultSet rs) throws SQLException {

        Payment payment = new Payment();

        payment.setId(rs.getLong("id"));
        payment.setOwnerId(rs.getLong("ownerId"));
        payment.setReceiverId(rs.getLong("receiverId"));
        payment.setSum(rs.getBigDecimal("sum"));
        payment.setDate(rs.getDate("date"));
        payment.setSenderId(rs.getLong("senderId"));
        payment.setState(rs.getString("status"));

        return payment;
    }

}
