package com.pruebaTecnica.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaTecnica.models.BookModel;
import com.pruebaTecnica.repositories.BookRepositorie;

@Service

public class BookServices {
    @Autowired
    BookRepositorie bookRepositorie;

    public ArrayList<BookModel> getBooks(){
        return (ArrayList<BookModel>) bookRepositorie.findAll();
    }

    public BookModel postBook(BookModel book){
        return bookRepositorie.save(book);
    }

    public Optional<BookModel> getById(Long id){
        return bookRepositorie.findById(id);
    }

    public ArrayList<BookModel> getBookByAnio(Integer anio){
        return bookRepositorie.findByAnio(anio);
    }

    public boolean deleteBook(Long id){
        try {
            bookRepositorie.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
