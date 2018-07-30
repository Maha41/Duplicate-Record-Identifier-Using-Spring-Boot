package com.me.pojo;

import java.util.ArrayList;

public class PersonList {
	 ArrayList<Person> personList;

	public PersonList(){
		this.personList = new ArrayList<>();
	}

	public ArrayList<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}

		public Person addPerson() {
	   Person person = new Person();
		personList.add(person);
		return person;
	}
}
