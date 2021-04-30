package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.model.Role;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DBUserRepository extends DBRepository<User, Long> implements UserRepository {

    @Override
    protected String getCreateSql() {
        return "INSERT INTO user(firstName, lastName, regionId, role) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT * FROM user WHERE ID = ?";
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE user SET firstName = ?, lastName = ?, regionId = ?, role = ? WHERE ID = ?";
    }

    @Override
    protected String getDeleteByIdSql() {
        return "DELETE FROM user WHERE ID = ?";
    }

    @Override
    protected int updateStatement(PreparedStatement statement, int startWith, User entity) throws SQLException {
        statement.setString(startWith, entity.getFirstName());
        statement.setString(startWith + 1, entity.getLastName());
        statement.setLong(startWith + 2, entity.getRegionId());
        statement.setString(startWith + 3, entity.getRole().name());
        return startWith + 3;
    }

    @Override
    protected void setIdIntoStatement(PreparedStatement statement, int paramNum, Long id) throws SQLException {
        statement.setLong(paramNum, id);
    }

    @Override
    protected String getMainTableName() {
        return "user";
    }

    @Override
    protected User getNewEntity(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setRegionId(rs.getLong("regionId"));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    }

    @Override
    protected Long getIdEntity(User entity) {
        return entity.getId();
    }

    @Override
    public List<User> findAll() {
        return super.findAll().stream().peek(x -> injectObjects(x)).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllById(List<Long> ids) {
        return super.findAllById(ids).stream().peek(x -> injectObjects(x)).collect(Collectors.toList());
    }

    private void injectObjects(User user) {
        if (user != null) {
            Region region = ObjectRepository.getInstance().getRegionRepository().findById(user.getRegionId()).orElse(null);
            user.setRegion(region);
        }
    }
}
