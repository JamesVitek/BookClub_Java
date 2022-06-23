package com.example.crud.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
	public ArrayList<User> findAll();
	//Select * from user where user.name==?
	public User findByName(String username);
	//Select * from user where user.email==?
	public User findByEmail(String email);
}