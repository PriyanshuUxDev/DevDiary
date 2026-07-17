package com.Spring.DevDiary.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String description;

    @OneToOne
    @JoinColumn(name = "PostId")
    private Post post;

}
