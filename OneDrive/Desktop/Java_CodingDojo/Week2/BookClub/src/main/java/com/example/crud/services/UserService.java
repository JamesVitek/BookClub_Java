package com.example.crud.services;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.crud.models.TempUser;
import com.example.crud.models.User;
import com.example.crud.repositories.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;
	
	public User login(TempUser tempUser, BindingResult res)
	{
		User db_user = userRepository.findByEmail(tempUser.getEmail());
		
		if(db_user == null)
		{
			res.rejectValue("email", "email_not_found", "Invalid Email");
			return null;
		}
		
		if(!BCrypt.checkpw(tempUser.getPassword(), db_user.getPassword()))
		{
			res.rejectValue("password", "invalid_password", "Password is Invalid");
			return null;
		}
		
		return db_user;
	}
	
	public User register(User user, BindingResult res)
	{
		if(userRepository.findByEmail(user.getEmail())!=null)
		{
			res.rejectValue("Email", "Email is invalid", "A user with that email already exists");
		}
		
		if(!user.getPassword().equals(user.getConfirm()))
		{
			res.rejectValue("password", "pass_no_match", "Password and Password Confirmation must match");
			res.rejectValue("confirm", "pass_no_match", "Password and Password Confirmation must match");
		}
		
		if(res.hasErrors())
		{
			return null;
		}
		
		
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(8)));
		userRepository.save(user);
		
		return user;
	}
	
	public void logout(HttpSession session)
	{
		if(session.getAttribute("user")!=null)
		{			
			session.removeAttribute("user");;
		}
	}
}
