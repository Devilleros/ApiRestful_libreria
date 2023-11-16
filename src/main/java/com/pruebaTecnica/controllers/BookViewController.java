package com.pruebaTecnica.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pruebaTecnica.models.BookModel;
import com.pruebaTecnica.services.BookServices;

@Controller
@RequestMapping("/books")
public class BookViewController {

    @Autowired
    private BookServices bookServices;

    @GetMapping()
    public String getBooks(Model model) {
        ArrayList<BookModel> books = bookServices.getBooks();
        model.addAttribute("books", books);
        return "index";
    }
    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookModel());
        return "create";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        // Lógica para obtener el libro por ID y pasarlo a la vista
        Optional<BookModel> optionalBook = bookServices.getById(id);
        BookModel book = optionalBook.orElse(null);
        model.addAttribute("book", book);
        return "edit";
    }

}