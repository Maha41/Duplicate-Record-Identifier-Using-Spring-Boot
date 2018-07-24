package com.me.service;


import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.me.pojo.Person;

@Service
public class PersonService {
	
	Hashtable<String, Person> persons = new Hashtable<String, Person>();
	public Hashtable<String, Person> getPersons() {
		return persons;
	}

	public void setPersons(Hashtable<String, Person> persons) {
		this.persons = persons;
	}

	public PersonService() {
		
	}

	public Person getPerson(String id) {
		if (persons.containsKey(id))
			return persons.get(id);
		else
			return null;
	}
	public Hashtable<String, Person> getAll() {
		return persons;
	}
	
}
