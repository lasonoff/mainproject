package ru.yauroff.test.mainproject.model;

/**
 * Created by ayaurov on 15.04.2021.
 */
public enum Role {
    ADMIN("0"), MODERATOR("1"), USER("2");
    private String role;

    private Role(String role) {
        this.role = role;
    }
}
