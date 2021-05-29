package com.example.jdbcdemo.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.example.jdbcdemo.model.Person;

public interface IPersonDAO {
	
    int create(Person p);

    Person select(int personID);

    List<Person> selectAll();

    int deleteAll();

    int delete(int personID);
    
    int updatePerson(Person p);

}
