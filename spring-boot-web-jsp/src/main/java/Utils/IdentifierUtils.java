package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.csvreader.CsvReader;
import com.me.pojo.Person;
import com.me.pojo.PersonList;
import com.me.service.PersonService;
import java.util.stream.Collectors;

public class IdentifierUtils {

	public ArrayList<Person> Identifier(PersonService ps, PersonList personList) {

		ArrayList<Person> duplicateList = new ArrayList<Person>();

		try {

			int count = 0;
			int duplicate_set = 0;
			Hashtable<String, Person> persons = new Hashtable<String, Person>();

			Map<String, List<Person>> duplicateEmail = new HashMap<>();
			Map<String, List<Person>> duplicatePhone = new HashMap<>();
			Map<String, List<Person>> duplicateCompany = new HashMap<>();
			Map<String, List<Person>> duplicateState = new HashMap<>();
			Map<String, List<Person>> duplicateCity = new HashMap<>();
			Map<String, List<Person>> duplicateAddress1 = new HashMap<>();
			Map<String, List<Person>> duplicateFirstName = new HashMap<>();
			Map<String, List<Person>> duplicateLastName = new HashMap<>();
			ArrayList<Person> people = new ArrayList<>();
			
			String[] stopwords = new String[] { "A ","a ", "The ", "the ", "&" , ".", ",", "-"}; // Stop words array
			
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
				
			}

			
			duplicateEmail = people.stream().collect(Collectors.groupingBy(Person::getEmail));
			

			for (Person p : personList.getPersonList()) {

				count++;
				persons.put(String.valueOf(count), p);
				ps.setPersons(persons);

			
				if (duplicateEmail.get(p.getEmail()).size() >= 2) {// find duplicate entries using email
					duplicate_set++;
					System.out.println("Duplicate Set " + duplicate_set + " : ");
					System.out.println(p + "\n");
					duplicateList.add(p);
people.remove(p);
				} else {
					duplicatePhone = people.stream()
							.collect(Collectors.groupingBy(Person::getPhoneNumber));
					if (duplicatePhone.get(p.getPhoneNumber()).size() >= 2) {// find duplicate entries using phone
						duplicate_set++;
						System.out.println("Duplicate Set " + duplicate_set + " : ");
						System.out.println(p + "\n");
						duplicateList.add(p);
						people.remove(p);

					} else {

						duplicateState = people.stream()
								.collect(Collectors.groupingBy(Person::getState));// find duplicate entries using state

						duplicateCity = people.stream() // find duplicate entries using City
								.collect(Collectors.groupingBy(Person::getCity));

						duplicateAddress1 = people.stream() // find duplicate entries using Address1
								.collect(Collectors.groupingBy(Person::getAddress1));

						duplicateCompany = people.stream() // find duplicate entries using Company
								.collect(Collectors.groupingBy(Person::getCompany));

						duplicateLastName = people.stream() // find duplicate entries using LastName
								.collect(Collectors.groupingBy(Person::getLastName));

						duplicateFirstName = people.stream()
								.collect(Collectors.groupingBy(Person::getFirstName));// find duplicate entries using FirstName


						if ((duplicateState.get(p.getState()).size() >= 2
								|| duplicateState.get(p.getState()).equals(""))
								&& (duplicateCity.get(p.getCity()).size() >= 2
										|| duplicateState.get(p.getState()).equals(""))
								&& (duplicateAddress1.get(p.getAddress1()).size() >= 2
										|| duplicateState.get(p.getState()).equals(""))
								&& (duplicateCompany.get(p.getCompany()).size() >= 2
										|| duplicateState.get(p.getState()).equals(""))
								&& (duplicateLastName.get(p.getLastName()).size() >= 2)
								&& (duplicateFirstName.get(p.getFirstName()).size() >= 2)) {
							duplicate_set++;
							System.out.println("Duplicate Set " + duplicate_set + " : ");
							System.out.println(p + "\n");
							duplicateList.add(p);
							people.remove(p);

						} else {
							System.out.println("Non Duplicate Set : ");
							System.out.println(p + "\n");
						}

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
		return duplicateList;
	}

}
