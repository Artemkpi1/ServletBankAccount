package com.example.servletBankAccountProject.dao;

import com.example.servletBankAccountProject.dao.mapper.PaymentMapper;
import com.example.servletBankAccountProject.exceptions.PaymentException;
import com.example.servletBankAccountProject.model.Payment;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PaymentDAO implements Crud<Payment> {

    @Override
    public Optional<Payment> findById(int id) throws PaymentException {
        Connection connection = DataSource.getConnection();
        Payment payment = null;
        PaymentMapper paymentMapper=new PaymentMapper();
        String find = "SELECT * FROM payments WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(find)) {
            preparedStatement.setInt(1, id);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()) {
                payment = paymentMapper.extractFromResultSet(rs);
            }

        } catch (Exception e) {
            payment = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }

        return Optional.ofNullable(payment);
    }

    @Override
    public void create(Payment entity) {
        Connection connection = DataSource.getConnection();
        String insert = "INSERT INTO payments (senderId, receiverId, date, ownerId, state, sum) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement=connection.prepareStatement(insert)) {
            preparedStatement.setLong(1, entity.getSenderId());
            preparedStatement.setLong(2, entity.getReceiverId());
            preparedStatement.setLong(3, entity.getDate().getTime());
            preparedStatement.setLong(4, entity.getOwnerId());
            preparedStatement.setBigDecimal(5, entity.getSum());
            preparedStatement.setString(6, entity.getState().toString());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<Payment> findAll() {
        Connection connection = DataSource.getConnection();
        List<Payment> payments;
        PaymentMapper paymentMapper=new PaymentMapper();
        String SELECT_ALL_PAYMENTS = "SELECT * FROM payments";
        try (PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_PAYMENTS)) {
            ResultSet rs=preparedStatement.executeQuery();
            payments=new ArrayList<>();
            while (rs.next()) {
                payments.add(paymentMapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            payments = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }
        return payments;
    }

    @Override
    public void update(Payment payment) {
        Connection connection = DataSource.getConnection();
        String UPDATE_USER_STATE = "UPDATE payments SET state=state + ? WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USER_STATE)){
            preparedStatement.setString(1, payment.getState().toString());
            preparedStatement.setLong(2, payment.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
