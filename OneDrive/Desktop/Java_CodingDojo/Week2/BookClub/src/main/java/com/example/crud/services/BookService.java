package com.example.crud.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.crud.models.Book;
import com.example.crud.repositories.BookRepository;

@Service
public class BookService 
{
	@Autowired
	private BookRepository bookRepository;

	public BookService
	(
			BookRepository bookRepository
	)
	{
		this.bookRepository = bookRepository;
	}
	
	public Book create(Book book, BindingResult res)
	{
		if(res.hasErrors())
		{
			return null;
		}
		bookRepository.save(book);
		return book;
	}
	
	public void update(Book book)
	{
		bookRepository.save(book);
	}
	
	public Book readOne(Long book_id)
	{
		Optional<Book> book=bookRepository.findById(book_id);
		return book.isPresent()?book.get():null;
	}
	
	public ArrayList<Book> ReadAll()
	{
		return (ArrayList<Book>)bookRepository.findAll();
	}
	
	public void delete(Long book_id)
	{
		bookRepository.deleteById(book_id);
	}
}
