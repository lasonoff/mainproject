package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.PostRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DBPostRepository extends DBRepository<Post, Long> implements PostRepository {
    @Override
    protected String getCreateSql() {
        return "INSERT INTO post(content, created, updated, userId) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT * FROM post WHERE ID = ?";
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
        post.setId(rs.getLong("id"));
        post.setContent(rs.getString("content"));
        post.setCreated(rs.getDate("created"));
        post.setUpdated(rs.getDate("updated"));
        post.setUserId(rs.getLong("userId"));
        return post;
    }

    @Override
    protected Long getIdEntity(Post entity) {
        return entity.getId();
    }

    @Override
    public List<Post> findAll() {
        return super.findAll().stream().peek(x -> injectObjects(x)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findAllById(List<Long> ids) {
        return super.findAllById(ids).stream().peek(x -> injectObjects(x)).collect(Collectors.toList());
    }

    private void injectObjects(Post post) {
        if (post != null) {
            User user = ObjectRepository.getInstance().getUserRepository().findById(post.getUserId()).orElse(null);
            post.setUser(user);
        }
    }
}
