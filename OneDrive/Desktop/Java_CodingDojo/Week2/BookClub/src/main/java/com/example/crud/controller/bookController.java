package com.example.crud.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crud.models.Book;
import com.example.crud.models.User;
import com.example.crud.services.BookService;

@Controller
@RequestMapping("/books")
public class bookController
{
	@Autowired
	private BookService bookService;
	
	@GetMapping("")
	public String books(HttpSession session, Model model)
	{
		if(session.getAttribute("user")==null)
		{
			return "redirect:/";
		}
		model.addAttribute("books", bookService.ReadAll());
		return "books";
	}
	
	@GetMapping("/new")
	public String newBookGet
		(
			Model model,
			HttpSession session
		)
	{
		if(session.getAttribute("user")==null)
		{
			return "redirect:/";
		}
		model.addAttribute("book", new Book());
		return "newBook";
	}
	
	@PostMapping("/new")
	public String newBookPost
		(
			@Valid @ModelAttribute("book") Book book,
			BindingResult res,
			Model model,
			HttpSession session
		)
	{
		bookService.create(book, res);
		
		if(res.hasErrors())
		{
			return "newBook";
		}
		
		return "redirect:/books";
	}
	
	@GetMapping("/{id}")
	public String view
		(
			@PathVariable("id") Long bookId,
			Model model,
			HttpSession session
		)
	{
		if(session.getAttribute("user")==null)
		{
			return "redirect:/";
		}
		model.addAttribute(bookService.readOne(bookId));
		return "viewBook";
	}
	
	@GetMapping("/{id}/edit")
	public String editGet
		(
			@PathVariable("id") Long bookId,
			Model model,
			HttpSession session
		)
	{
		User user = (User) session.getAttribute("user");
		if(user==null)
		{
			return "redirect:/";
		}
		if(!(user.getId()==bookService.readOne(bookId).getReader().getId()))
		{
			return "redirect:/books";
		}
		model.addAttribute(bookService.readOne(bookId));
		return "editBook";
	}
	
	@PostMapping("/{id}/edit")
	public String editPost(@Valid @ModelAttribute("book") Book book, BindingResult res)
	{
		if(res.hasErrors()) 
		{
			return "editBook";
		}
		bookService.update(book);
		return "redirect:/books";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId)
	{
		bookService.delete(bookId);
		return "redirect:/books";
	}
}
