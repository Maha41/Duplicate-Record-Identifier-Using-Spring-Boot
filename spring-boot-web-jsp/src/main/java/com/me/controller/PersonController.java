package com.me.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.me.pojo.Person;
import com.me.service.PersonService;

@RestController
@RequestMapping("/user")
public class PersonController {

	public static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService ps;

	@RequestMapping("/all")
	public Hashtable<String, Person> getAll(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) { // get all csv records in json format

		Hashtable<String, Person> result = ps.getAll();

		return result;
	}

	@RequestMapping("{id}")
	public ResponseEntity<Person> getPerson(@PathVariable("id") String id) { // get person record accoring to id from csv

		Person result = ps.getPerson(id);

		return ResponseEntity.ok(result);

	}

}
