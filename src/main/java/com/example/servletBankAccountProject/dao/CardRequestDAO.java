package com.example.servletBankAccountProject.dao;

import com.example.servletBankAccountProject.dao.mapper.CardRequestMapper;
import com.example.servletBankAccountProject.exceptions.CardRequestException;
import com.example.servletBankAccountProject.model.CardRequest;
import com.example.servletBankAccountProject.model.CreditCard;
import com.example.servletBankAccountProject.model.CardRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRequestDAO implements Crud<CardRequest> {


    @Override
    public Optional<CardRequest> findById(int id) throws CardRequestException {
        Connection connection = DataSource.getConnection();
        CardRequest cardRequest = null;
        CardRequestMapper cardRequestMapper=new CardRequestMapper();
        String find = "SELECT * FROM cardRequests WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(find)) {
            preparedStatement.setInt(1, id);
            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()) {
                cardRequest = cardRequestMapper.extractFromResultSet(rs);
            }

        } catch (Exception e) {
            cardRequest = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }

        return Optional.ofNullable(cardRequest);
    }

    @Override
    public void create(CardRequest entity) {
        Connection connection = DataSource.getConnection();
        String insert = "INSERT INTO cardRequests (owner, creditCard) VALUES (?, ?)";
        try (PreparedStatement preparedStatement=connection.prepareStatement(insert)) {

            preparedStatement.setLong(1, entity.getOwnerId());
            preparedStatement.setLong(2, entity.getCreditCardId());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public List<CardRequest> findAll() {
        Connection connection = DataSource.getConnection();
        List<CardRequest> cardRequests;
        CardRequestMapper cardRequestMapper=new CardRequestMapper();
        String SELECT_ALL_CARDREQUESTS = "SELECT * FROM cardRequests";
        try (PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_CARDREQUESTS)) {
            ResultSet rs=preparedStatement.executeQuery();
            cardRequests=new ArrayList<>();
            while (rs.next()) {
                cardRequests.add(cardRequestMapper.extractFromResultSet(rs));
            }
        } catch (Exception e) {
            cardRequests = null;
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }
        return cardRequests;
    }

    @Override
    public void update(CardRequest cardRequest) {
        Connection connection = DataSource.getConnection();
        String UPDATE_USER_STATE = "UPDATE cardRequests SET state=state + ? WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USER_STATE)){
            preparedStatement.setString(1, cardRequest.getCreditCardState());
            preparedStatement.setLong(2, cardRequest.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }
    }

    @Override
    public void delete(CardRequest cardRequest) {
        Connection connection = DataSource.getConnection();
        String DELETE_USER_CARDREQUEST = "DELETE cardRequests WHERE id=?";
        try (PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USER_CARDREQUEST)){
            preparedStatement.setLong(1, cardRequest.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DataSource.closeConnection(connection);
        }
    }
}
