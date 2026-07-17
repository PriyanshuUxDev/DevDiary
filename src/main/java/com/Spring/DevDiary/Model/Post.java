package com.Spring.DevDiary.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @JoinColumn(name = "CategoryId")
    @OneToOne
    private Category category;

    @Column(columnDefinition = "LONGTEXT")
    private String summary;

    @ElementCollection
    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}