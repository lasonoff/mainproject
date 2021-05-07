package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.model.Role;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.PostRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPostRepository extends DBRepository<Post, Long> implements PostRepository {
    @Override
    protected String getCreateSql() {
        return "INSERT INTO post(content, created, updated, userId) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getFindAllSql() {
        return "SELECT post.id AS postId,  post.content, post.userId, post.created, post.updated," +
                "user.firstName, user.lastName, user.role, user.regionId, region.name AS regionName " +
                "FROM post " +
                "LEFT OUTER JOIN user ON user.id =  post.userId " +
                "LEFT OUTER JOIN region ON region.id =  user.regionId ";
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT post.id AS postId,  post.content, post.userId, post.created, post.updated," +
                "user.firstName, user.lastName, user.role, user.regionId, region.name AS regionName " +
                "FROM post " +
                "LEFT OUTER JOIN user ON user.id =  post.userId " +
                "LEFT OUTER JOIN region ON region.id =  user.regionId " +
                "WHERE post.id = ?";
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE post SET content = ?, created = ?, updated = ?, userId = ? WHERE ID = ?";
    }

    @Override
    protected String getDeleteByIdSql() {
        return "DELETE FROM post WHERE ID = ?";
    }

    @Override
    protected int updateStatement(PreparedStatement statement, int startWith, Post entity) throws SQLException {
        statement.setString(startWith, entity.getContent());
        statement.setDate(startWith + 1, new java.sql.Date(entity.getCreated().getTime()));
        statement.setDate(startWith + 2, new java.sql.Date(entity.getUpdated().getTime()));
        statement.setLong(startWith + 3, entity.getUserId());
        return startWith + 3;
    }

    @Override
    protected void setIdIntoStatement(PreparedStatement statement, int paramNum, Long id) throws SQLException {
        statement.setLong(paramNum, id);
    }

    @Override
    protected String getMainTableName() {
        return "post";
    }

    @Override
    protected Post getNewEntity(ResultSet rs) throws SQLException {
        Post post = new Post();
        post.setId(rs.getLong("postId"));
        post.setContent(rs.getString("content"));
        post.setCreated(rs.getDate("created"));
        post.setUpdated(rs.getDate("updated"));
        post.setUserId(rs.getLong("userId"));
        if (post.getUserId() != null) {
            User user = new User();
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setRegionId(rs.getLong("regionId"));
            user.setRole(Role.valueOf(rs.getString("role")));
            if (user.getRegionId() != null) {
                Region region = new Region(rs.getLong("regionId"), rs.getString("regionName"));
                user.setRegion(region);
            }
        }
        return post;
    }

    @Override
    protected Long getIdEntity(Post entity) {
        return entity.getId();
    }
}
