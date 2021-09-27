package com.example.servletBankAccountProject.dao;

import com.example.servletBankAccountProject.dao.mapper.CreditCardMapper;
import com.example.servletBankAccountProject.exceptions.CreditCardException;
import com.example.servletBankAccountProject.model.CardRequest;
import com.example.servletBankAccountProject.model.CreditCard;
import com.example.servletBankAccountProject.model.CreditCard;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class CreditCardDAO implements Crud<CreditCard> {

    Optional<CreditCard> findCreditCardByOwnerAndCardNumberAndStatus(Long ownerId, String cardNumber, CreditCard.Status status) {
        Connection connection = DataSource.getConnection();
        CreditCard creditCard = null;
        CreditCardMapper creditCardMapper=new CreditCardMapper();
        String findCreditCardByOwnerAndCardNumberAndStatus = "SELECT * FROM creditCards WHERE ownerId=? AND cardNumber=? AND status=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(findCreditCardByOwnerAndCardNumberAndStatus)) {
            preparedStatement.setLong(1, ownerId);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(3, status.toString());
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()) {
                creditCard = creditCardMapper.extractFromResultSet(rs);
            }
        } catch (Exception e) {
            creditCard = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }

        return Optional.ofNullable(creditCard);
    };

    public Optional<CreditCard> findCreditCardByCardNumberAndStatus(String receiverCard, CreditCard.Status status) {
        Connection connection = DataSource.getConnection();
        CreditCard creditCard = null;
        CreditCardMapper creditCardMapper=new CreditCardMapper();
        String findCreditCardByOwnerAndCardNumberAndStatus = "SELECT * FROM creditCards WHERE receiverCard=? AND status=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(findCreditCardByOwnerAndCardNumberAndStatus)) {
            preparedStatement.setString(1, receiverCard);
            preparedStatement.setString(2, status.toString());
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()) {
                creditCard = creditCardMapper.extractFromResultSet(rs);
            }
        } catch (Exception e) {
            creditCard = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }

        return Optional.ofNullable(creditCard);
    };

    public List<CreditCard> findCreditCardByOwner(Long ownerId) {
        Connection connection = DataSource.getConnection();
        CreditCard creditCard = null;
        CreditCardMapper creditCardMapper=new CreditCardMapper();
        String findCreditCardByOwner = "SELECT * FROM creditCards WHERE ownerId=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(findCreditCardByOwner)) {
            preparedStatement.setLong(1, ownerId);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()) {
                creditCard = creditCardMapper.extractFromResultSet(rs);
            }
        } catch (Exception e) {
            creditCard = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }

        return ;
    };

    public Optional<CreditCard> findCreditCardByCardNumber(String cardNumber) throws CreditCardException {
        Connection connection = DataSource.getConnection();
        CreditCard creditCard = null;
        CreditCardMapper creditCardMapper=new CreditCardMapper();
        String findCreditCardByCardNumber = "SELECT * FROM creditCards WHERE cardNumber=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(findCreditCardByCardNumber)) {
            preparedStatement.setString(1, cardNumber);
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()) {
                creditCard = creditCardMapper.extractFromResultSet(rs);
            }
        } catch (Exception e) {
            creditCard = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }

        return Optional.ofNullable(creditCard);
    }

    @Override
    public Optional<CreditCard> findById(int id) throws CreditCardException {
        Connection connection = DataSource.getConnection();
        CreditCard creditCard = null;
        CreditCardMapper creditCardMapper=new CreditCardMapper();
        String find = "SELECT * FROM creditCards WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(find)) {
            preparedStatement.setInt(1, id);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()) {
                creditCard = creditCardMapper.extractFromResultSet(rs);
            }

        } catch (Exception e) {
            creditCard = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }

        return Optional.ofNullable(creditCard);
    }

    @Override
    public void update(CreditCard creditCard) {
        Connection connection = DataSource.getConnection();
        String UPDATE_USER_STATUS_BALANCE_REQUEST = "UPDATE cardRequests SET status=status + ?, balance=balance + ?, request=request + ? WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USER_STATUS_BALANCE_REQUEST)){
            preparedStatement.setString(1, creditCard.getStatus().toString());
            preparedStatement.setBigDecimal(2, creditCard.getBalance());
            preparedStatement.setString(3, creditCard.getRequest().toString());
            preparedStatement.setLong(4, creditCard.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void create(CreditCard entity) {
        Connection connection = DataSource.getConnection();
        String insert = "INSERT INTO creditCard (cardName, cardNumber, status, balance, ownerId, request) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement=connection.prepareStatement(insert)) {

            preparedStatement.setString(1, entity.getCardName());
            preparedStatement.setString(2, entity.getCardNumber());
            preparedStatement.setString(3, entity.getStatus().toString());
            preparedStatement.setBigDecimal(4, entity.getBalance());
            preparedStatement.setLong(5, entity.getOwnerId());
            preparedStatement.setString(6, entity.getRequest().toString());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DataSource.closeConnection(connection);
        }
    }


}
