package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="post")
public class PostEntity {
    @Id
    @Column(name="idPost")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    @SequenceGenerator(name = "post_seq", sequenceName = "post_postid_seq", allocationSize = 1)
    private int postId;
    @Column(name="Title")
    private String title;
    @Column(name="content",columnDefinition = "TEXT")
    private String content;
    @Column(name="createAt")
    private LocalDateTime createAt;
    @Column(name="updateAt")
    private LocalDateTime updateAt;
    @Column(name="Image")
    private String image;
    @ManyToOne()
    @JoinColumn(name="DoctorId")
    private DoctorEntity doctor;
}
