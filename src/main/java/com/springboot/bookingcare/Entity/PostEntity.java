package com.springboot.bookingcare.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="post" ,
        indexes = {
        @Index(name = "idx_post_postId", columnList = "idPost")
})
@Getter
@Setter
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
    @Column(name="likePost")
    private int likePost;
    @ManyToOne()
    @JoinColumn(name="DoctorId")
    private DoctorEntity doctor;
    @ManyToOne()
    @JoinColumn(name="categoryId")
    private CategoryEntity categoryEntity;

}
