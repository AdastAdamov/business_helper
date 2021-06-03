package com.adast_adamov.business_helper.models;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User() {}

    public User(String login, String password, UserType userType) {
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public enum UserType {USER, ADMIN}
}
