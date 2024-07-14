package com.jbdl63.DigitalLibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "library_book")
public class Book implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(nullable = false,length = 200)
    private String bookName;

    @Column(nullable = false)
    private String publicationYear;

    @Column(nullable = false,length=200)
    private String bookEdition;

    @Column(nullable = false)
    private Double bookPrice;

    @Column(nullable = false)
    private String bookCategory;


    @CreationTimestamp
    private LocalDateTime creationTime;

    @UpdateTimestamp
    public LocalDateTime updationTime;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @JsonBackReference
    @ManyToMany(mappedBy = "issuedBooks")
    private List<User> users;


}
