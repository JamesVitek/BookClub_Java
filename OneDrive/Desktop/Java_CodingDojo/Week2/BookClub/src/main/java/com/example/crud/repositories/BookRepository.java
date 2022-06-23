package com.example.crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.crud.models.Book;

public interface BookRepository extends CrudRepository <Book, Long> 
{

}
