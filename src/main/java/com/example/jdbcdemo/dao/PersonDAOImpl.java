package com.example.jdbcdemo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.jdbcdemo.model.Person;

@Repository
public class PersonDAOImpl implements IPersonDAO {
	
	JdbcTemplate jdbcTemplate;
	
	PersonDAOImpl(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate= jdbcTemplate;
	}
	
	BeanPropertyRowMapper<Person> rowMapper= BeanPropertyRowMapper.newInstance(Person.class);

	@Override
	public int create(Person p) {
		return jdbcTemplate
			.update("insert into PERSON(FIRSTNAME, LASTNAME) values (?,?);", p.getFirstName(), p.getLastName());
	}

	@Override
	public Person select(int personID) {
		
		return jdbcTemplate
					.queryForObject("Select ID as personID, FIRSTNAME, LASTNAME from PERSON where ID=:id;", 
						rowMapper, personID);
	}

	@Override
	public List<Person> selectAll() {
		return jdbcTemplate
					.query("SELECT ID as personID, FIRSTNAME, LASTNAME from PERSON;", rowMapper);
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("delete from PERSON;");
	}

	@Override
	public int delete(int personID) {
		Map<String, Integer> params= new HashMap<>();
		params.put("id", personID);
		return jdbcTemplate.update("delete from PERSON where ID=?;", params);
	}
	
	public int updatePerson(Person person) {
		return jdbcTemplate
			.update("update PERSON set FIRSTNAME=?, LASTNAME=? where ID=?", 
					person.getFirstName(), person.getLastName(), person.getPersonID());
	}

}
