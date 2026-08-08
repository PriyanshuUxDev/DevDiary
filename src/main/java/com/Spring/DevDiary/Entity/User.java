package com.Spring.DevDiary.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany
    private List<Comment> comment=new ArrayList<>();

    @OneToMany
    private List<Post> post=new ArrayList<>();
}

