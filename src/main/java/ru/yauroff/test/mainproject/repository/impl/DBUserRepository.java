package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.model.Role;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserRepository extends DBRepository<User, Long> implements UserRepository {

    @Override
    protected String getCreateSql() {
        return "INSERT INTO user(firstName, lastName, regionId, role) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getFindAllSql() {
        return "SELECT user.id AS userId, user.firstName, user.lastName, user.role, user.regionId, " +
                "region.name AS regionName FROM user LEFT OUTER JOIN region ON region.id =  user.regionId";
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT user.id AS userId, user.firstName, user.lastName, user.role, user.regionId, " +
                "region.name AS regionName FROM user LEFT OUTER JOIN region ON region.id =  user.regionId " +
                "WHERE user.id =  ?";
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
        user.setId(rs.getLong("userId"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setRegionId(rs.getLong("regionId"));
        user.setRole(Role.valueOf(rs.getString("role")));
        if (user.getRegionId() != null) {
            Region region = new Region(rs.getLong("regionId"), rs.getString("regionName"));
            user.setRegion(region);
        }
        return user;
    }

    @Override
    protected Long getIdEntity(User entity) {
        return entity.getId();
    }
}
