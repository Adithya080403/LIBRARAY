package com.jbdl63.DigitalLibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "library_author")
public class Author implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(unique = true,nullable = false)
    @NotBlank(message = "Author name should not be blank")
    private String authorName;

    private String authorAddress;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "author",fetch = FetchType.EAGER)
    @JsonBackReference
    public List<Book> bookList;


}
