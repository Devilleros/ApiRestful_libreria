package com.pruebaTecnica.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pruebaTecnica.models.BookModel;
import com.pruebaTecnica.repositories.BookRepositorie;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServicesTest {

    @Autowired
    private BookServices bookServices;

    @MockBean
    private BookRepositorie bookRepositorie;

    @Test
    public void testGetBookById() {
        Long bookId = 1L;
        BookModel mockBook = new BookModel(); // crea un libro de prueba
        when(bookRepositorie.findById(bookId)).thenReturn(Optional.of(mockBook));

        Optional<BookModel> result = bookServices.getById(bookId);
        assertEquals(mockBook, result.orElse(null));
    }

    // Agrega más pruebas para otras funciones del servicio
}

