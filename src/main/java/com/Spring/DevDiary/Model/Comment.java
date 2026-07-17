package com.Spring.DevDiary.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Comment")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String text;
    private String CreatedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private  Post post;



}
