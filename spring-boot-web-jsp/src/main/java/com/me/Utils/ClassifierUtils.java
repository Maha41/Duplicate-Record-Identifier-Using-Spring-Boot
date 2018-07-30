package com.me.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.me.pojo.Person;
import com.me.pojo.PersonList;

public class ClassifierUtils {
	public void Classifier(PersonList personList) {

		ArrayList<Person> duplicateList = new ArrayList<Person>();


		try {


			int duplicate_set = 0;
			long w_phonenumber =0;
			long w_email =0;
			long w_firstName =0;
			long w_lastName =0;
			Map<String, List<Person>> duplicateEmail = new HashMap<>();
			Map<String, List<Person>> duplicatePhone = new HashMap<>();
			ArrayList<Person> people = new ArrayList<>();

			String[] stopwords = { "A ","a ", "The ", "the ", "&" , ".", ",", "-"}; // Stop words array

			for (Person p : personList.getPersonList()) { // make duplicate people list for manipulation


				for (int jj = 0; jj < 4; jj++) { //remove stop words
					if (stopwords[jj].contains(p.getCompany().toLowerCase())) {
						p.getCompany().replaceAll(stopwords[jj], "");
						p.getState().replaceAll(stopwords[jj], "");
						p.getCity().replaceAll(stopwords[jj], "");
						p.getAddress1().replaceAll(stopwords[jj], "");
						p.getPhoneNumber().replaceAll(stopwords[jj], "");

					}
				}
				people.add(p);
				System.out.println(p + "\n");

			}

			duplicateEmail = people.stream().collect(Collectors.groupingBy(Person::getEmail));
				for (Person p : personList.getPersonList()) {



				if (duplicateEmail.get(p.getEmail()).size() >= 2) {// find duplicate entries using email
					duplicate_set++;
					System.out.println("Duplicate Set " + duplicate_set + " : ");
					System.out.println(p + "\n");
					duplicateList.add(p);
					//people.remove(p);
					w_email += 1000000000;
					System.out.println("Weight for email number : " + w_email);

				}
				}




			w_email = w_email/duplicate_set;

			System.out.println("Weight for email id : " + w_email);

		} catch (Exception e) {
			e.getMessage();

		} finally {
				System.out.println("Model trained using duplicate emails and phonenumber");
		}


	}
}
