package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2UserDAO implements UserDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public User find(int id) throws DAOException {
        Connection connection = connectionPool.getConnection();
        User user;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT id, firstName, lastName, email FROM USER WHERE id=?")) {

            ps.setInt(1, id);
            user = map(ps.executeQuery());

        } catch (SQLException e) {
            throw new DAOException("Failed to find a User.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return user;
    }

    @Override
    public User find(String email, String password) throws DAOException {
        Connection connection = connectionPool.getConnection();
        User user;
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT id, firstName, lastName, email WHERE email=?, password=?")) {

            ps.setString(4, email);
            ps.setString(5, password);

            user = map(ps.executeQuery());

        } catch (SQLException e) {
            throw new DAOException("Failed to find User by email, password.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DAOException {
        Connection connection = connectionPool.getConnection();
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT id, firstName, lastName, email FROM USER")) {

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(map(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to findAll Users.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return users;
    }

    @Override
    public User create(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        User tUser = new User();

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO USER (firstName, lastName, email, password)" +
                        "VALUES (?, ?, ?, ?)")) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                tUser.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to create User.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return tUser;
    }

    @Override
    public int update(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE USER SET firstName=?, lastName=?, email=?, password=? WHERE id=?",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to update User.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    @Override
    public int delete(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM USER WHERE id=?")) {

            ps.setInt(1, user.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete User.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    @Override
    public int changePassword(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE USER SET password=? WHERE id=?")) {

            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to change password.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    private User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        if (resultSet.next()) {
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
        }
        return user;
    }
}