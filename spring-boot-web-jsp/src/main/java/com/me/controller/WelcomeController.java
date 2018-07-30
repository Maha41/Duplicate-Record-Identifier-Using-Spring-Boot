package com.me.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.me.pojo.Person;
import com.me.pojo.PersonList;

import com.me.Utils.CSVReader;
import com.me.Utils.ClassifierUtils;
import com.me.Utils.IdentifierUtils;

@Controller
public class WelcomeController {

	public static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@RequestMapping("/") // get the file name to be tested
	public String fileName() {
		return "filename";
	}

	@RequestMapping("/details") // display duplicates
	public String details(Model model, @RequestParam("name") String filename) throws FileNotFoundException, URISyntaxException {

		File file = new File(filename);
		String absolutePath = file.getAbsolutePath();
		System.out.println(absolutePath);

		CSVReader reader = new CSVReader();
		PersonList readPersonList = reader.loadCSVData(absolutePath);

		IdentifierUtils identifier = new IdentifierUtils();
		List<Person> personList = identifier.Identifier(readPersonList);

		ClassifierUtils classifier = new ClassifierUtils();
	    classifier.Classifier(readPersonList);

		model.addAttribute("duplicateList", personList);

		return "details";

	}


}