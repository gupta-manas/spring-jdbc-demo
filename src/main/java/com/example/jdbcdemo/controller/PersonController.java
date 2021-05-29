package com.example.jdbcdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdbcdemo.dao.IPersonDAO;
import com.example.jdbcdemo.model.Person;

@RestController
@RequestMapping("person")
public class PersonController {
	
	private IPersonDAO dao;
	
	PersonController(IPersonDAO dao){
		this.dao= dao;
	}
	
	@GetMapping("/persons")
	public List<Person> getAllPersons(){
		return dao.selectAll();
	}
	
	@PostMapping("addPerson")
	public void addPerson(@RequestBody Person p) {
		dao.create(p);
	}
}
