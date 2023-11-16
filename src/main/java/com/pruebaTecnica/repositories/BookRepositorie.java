package com.pruebaTecnica.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pruebaTecnica.models.BookModel;

@Repository

public interface BookRepositorie extends CrudRepository<BookModel, Long>{
    public abstract ArrayList<BookModel> findByAnio(Integer anio);
}
