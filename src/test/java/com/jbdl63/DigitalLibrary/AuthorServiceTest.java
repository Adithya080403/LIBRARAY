package com.jbdl63.DigitalLibrary;

import com.jbdl63.DigitalLibrary.dto.UpdateAddressDto;
import com.jbdl63.DigitalLibrary.exception.BadRequestException;
import com.jbdl63.DigitalLibrary.exception.DataNotFoundException;
import com.jbdl63.DigitalLibrary.model.Author;
import com.jbdl63.DigitalLibrary.repository.AuthorRepository;
import com.jbdl63.DigitalLibrary.service.AuthorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Optional.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewAuthor() throws Exception {
        Author author = Author.builder().authorId(1111).authorName("pranav").authorAddress("nashik").build();
        when(authorRepository.save(any())).thenReturn(author);
        Author a = authorService.addNewAuthor(author);
        Assertions.assertThat(a).isEqualTo(author);
    }

    @Test
    public void testUploadAuthorsDataToDatabase() {
        String fileContent = "authorId, authorName, authorAddress\n" +
                "11, ABC, Kolkata\n" +
                "12, PQR, Jaipur\n" +
                "13, Rahul, Raipur\n" +
                "14, Divya, Nagpur";
        authorService.uploadAuthorsDataToDatabase(fileContent);
        verify(authorRepository, times(1)).saveAll(any());
    }


    @Test
    public void testUpdateAuthor_Exception() {
        when(authorRepository.findById(1)).thenReturn(null);
        Exception exception;
        exception = assertThrows(
                DataNotFoundException.class, () -> {
                    authorService.updateAuthorAddress(UpdateAddressDto.builder().id(1).address("abc").build());
                }
        );
        assertTrue(exception.getMessage().contains("Update operation is failed due to exception"));
    }

    @Test
    public void  testUpdateAuthorAddress()
    {
        Author author = Author.builder().authorId(1111).authorName("pranav").authorAddress("nashik").build();
        when(authorRepository.findById(any())).thenReturn(ofNullable(author));
        when(authorRepository.save(any())).thenReturn(author);
        Author a =authorService.updateAuthorAddress(UpdateAddressDto.builder().id(111).build());
        Assertions.assertThat(a).isEqualTo(author);
    }
}
