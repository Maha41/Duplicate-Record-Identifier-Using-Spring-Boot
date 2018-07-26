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

	public ArrayList<Person> Identifier(PersonService ps, PersonList personList, String filename) {

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
			for (Person p : personList.getPersonList()) { // make duplicate people list for manipulation

				people.add(p);
			}

			for (Person p : personList.getPersonList()) {

				count++;
				persons.put(String.valueOf(count), p);
				ps.setPersons(persons);

				duplicateEmail = people.stream().collect(Collectors.groupingBy(Person::getEmail));

				if (duplicateEmail.get(p.getEmail()).size() >= 2) {// find duplicate entries using email
					duplicate_set++;
					System.out.println("Duplicate Set " + duplicate_set + " : ");
					System.out.println(p + "\n");
					duplicateList.add(p);
				} else {
					duplicatePhone = duplicateEmail.get(p.getEmail()).stream()
							.collect(Collectors.groupingBy(Person::getPhoneNumber));

					if (duplicatePhone.get(p.getPhoneNumber()).size() >= 2) {// find duplicate entries using phone
						duplicate_set++;
						System.out.println("Duplicate Set " + duplicate_set + " : ");
						System.out.println(p + "\n");
						duplicateList.add(p);

					} else {

						duplicateState = duplicatePhone.get(p.getPhoneNumber()).stream()
								.collect(Collectors.groupingBy(Person::getState));// find duplicate entries using state

						if (duplicateState.get(p.getState()).size() >= 2) {// find duplicate entries using state

						} else {

							duplicateCity = duplicateState.get(p.getState()).stream()
									.collect(Collectors.groupingBy(Person::getCity));

							duplicateAddress1 = duplicateCity.get(p.getCity()).stream()
									.collect(Collectors.groupingBy(Person::getAddress1));

							duplicateCompany = duplicateAddress1.get(p.getAddress1()).stream()
									.collect(Collectors.groupingBy(Person::getCompany));

							duplicateLastName = duplicateCompany.get(p.getCompany()).stream()
									.collect(Collectors.groupingBy(Person::getLastName));

							duplicateFirstName = duplicateLastName.get(p.getLastName()).stream()
									.collect(Collectors.groupingBy(Person::getFirstName));

							if ((duplicateState.get(p.getState()).size() >= 2)
									&& (duplicateCity.get(p.getCity()).size() >= 2)
									&& (duplicateAddress1.get(p.getAddress1()).size() >= 2)
									&& (duplicateCompany.get(p.getCompany()).size() >= 2)
									&& (duplicateLastName.get(p.getLastName()).size() >= 2)
									&& (duplicateFirstName.get(p.getFirstName()).size() >= 2)) {
								duplicate_set++;
								System.out.println("Duplicate Set " + duplicate_set + " : ");
								System.out.println(p + "\n");
								duplicateList.add(p);

							} else {
								System.out.println("Non Duplicate Set : ");
								System.out.println(p + "\n");
							}

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
