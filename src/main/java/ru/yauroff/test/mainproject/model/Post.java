package ru.yauroff.test.mainproject.model;

import java.util.Date;

/**
 * Created by ayaurov on 15.04.2021.
 */
public class Post {
    private Long id;
    private String content;
    private Date created;
    private Date updated;

    public Post() {
    }

    public Post(String content) {
        this.content = content;
    }

    public Post(Long id, String content, Date created, Date updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        if (created != null ? !created.equals(post.created) : post.created != null) return false;
        return updated != null ? updated.equals(post.updated) : post.updated == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", content='" + content + '\'' + ", created=" + created + ", updated=" +
                updated + '}';
    }
}
