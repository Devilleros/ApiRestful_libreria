package com.pruebaTecnica.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pruebaTecnica.services.BookServices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookViewController.class)
public class BookViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookServices bookServices;

    @Test
    public void testGetBooks() throws Exception {
        mockMvc.perform(get("/books"))
               .andExpect(status().isOk());
    }

}
