package com.jbdl63.DigitalLibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookDto
{
    @NotNull(message = "Book id should not ne null in updateDto")
    private Integer id;

    @NotBlank(message = "Book Name should not be null or blank")
    private String bookName;

}
