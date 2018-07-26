package com.me.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.language.Metaphone;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.csvreader.CsvReader;
import com.me.pojo.Person;
import com.me.pojo.PersonList;
import com.me.service.PersonService;

import Utils.CSVReader;
import Utils.ClassifierUtils;
import Utils.IdentifierUtils;

@Controller

@ComponentScan({ "com.me.service" })
public class WelcomeController {
	
	public static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	@Autowired
	PersonService ps;



	@RequestMapping("/") // get the file name to be tested
	public String fileName(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("details");

		return "filename";
	}

	
	@RequestMapping("/details") // display duplicates 
	public ModelAndView details(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, URISyntaxException {
		ModelAndView mv = new ModelAndView();
		
		String Name = request.getParameter("name");
		mv.addObject("filename", Name);

		ArrayList<Person> personList = new ArrayList<Person>();
		
		
		File file = new File(request.getParameter("filename"));
		String absolutePath = file.getAbsolutePath();
		System.out.println(absolutePath);
		
		CSVReader reader = new CSVReader();
		PersonList readPersonList = new PersonList();
		
		readPersonList = reader.loadCSVData(absolutePath);
		
		IdentifierUtils identifier = new IdentifierUtils();
		personList= identifier.Identifier(ps,readPersonList);
		
		ClassifierUtils classifier = new ClassifierUtils();
	    classifier.Classifier(readPersonList);
		
		mv.addObject("duplicateList", personList);
		mv.setViewName("details");
		
		return mv;

	}


}