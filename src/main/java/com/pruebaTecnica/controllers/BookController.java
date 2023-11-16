package com.pruebaTecnica.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaTecnica.models.BookModel;
import com.pruebaTecnica.services.BookServices;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private boolean isValidBook(BookModel book) {
        return book != null && book.getTitulo() != null && book.getAutor() != null && book.getAnio() != null && book.getGenero() != null;
    }
    
    @Autowired
    private BookServices bookServices;

    @GetMapping()

    public ArrayList<BookModel> getBook(){
        return bookServices.getBooks();
    }
    
    @GetMapping(path = "/{id}")
    public Optional<BookModel> getBookById(@PathVariable ("id") Long id){
        return this.bookServices.getById(id);
    }

    @GetMapping("/query")
    public ArrayList<BookModel> getBookByAnio(@RequestParam("anio") Integer anio){
        return this.bookServices.getBookByAnio(anio);
    }

    @PostMapping()
    public ResponseEntity<String> postBook(@ModelAttribute BookModel book) {
        if (isValidBook(book)) {
            BookModel createdBook = this.bookServices.postBook(book);
            if (createdBook != null) {
                // Devuelve un fragmento de JavaScript que redirige al catálogo después de crear el libro
                return ResponseEntity.ok("<script>alert('Libro creado correctamente'); window.location.href = '/books';</script>");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el libro");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los campos no pueden estar vacíos");
        }
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<String> editBook(@PathVariable Long id, @ModelAttribute BookModel book) {
        // Lógica para editar el libro y guardar los cambios
        Optional<BookModel> existingBookOptional = bookServices.getById(id);
        
        if (existingBookOptional.isPresent()) {
            BookModel existingBook = existingBookOptional.get();

            // Actualiza los campos del libro existente con los valores del formulario
            existingBook.setTitulo(book.getTitulo());
            existingBook.setAutor(book.getAutor());
            existingBook.setAnio(book.getAnio());
            existingBook.setGenero(book.getGenero());

            // Guarda el libro actualizado en la base de datos
            BookModel updatedBook = bookServices.postBook(book);

            if (updatedBook != null) {
                // Devuelve un fragmento de JavaScript que redirige al catálogo después de editar el libro
                String response = "<script>alert('Libro editado correctamente'); window.location.href = '/books';</script>";
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el libro");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado");
        }
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
    boolean deleted = this.bookServices.deleteBook(id);
    if (deleted) {
        // Devuelve un fragmento de JavaScript que muestra un alert y recarga la página
        String response = "<script>alert('Libro eliminado correctamente'); window.location.href = '/books';</script>";
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el libro");
    }
}
}