package com.Spring.DevDiary.Model;

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

    @Column(name = "UserName")
    private String UserName;

    @Column(name="Email")
    private String Email;

    @Column(name = "Password")
    private String Password;

    @OneToMany
    private List<Comment> comment=new ArrayList<>();

    @OneToMany
    private List<Post> post=new ArrayList<>();
}

