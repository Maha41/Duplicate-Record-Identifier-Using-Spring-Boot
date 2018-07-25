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
			Map<String, Integer> data = new HashMap<String, Integer>();

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
			boolean flag = false;
			Map<String, List<Person>> personByCity = new HashMap<>();
			for (Person p : personList.getPersonList()) { 

				count++;
				persons.put(String.valueOf(count), p);
				ps.setPersons(persons);

				duplicateEmail = people.stream().collect(Collectors.groupingBy(Person::getEmail));

				if (duplicateEmail.get(p.getEmail()).size() >= 2) {// find duplicate entries using email
					System.out.println("Duplicate Set  : ");
					System.out.println(p);
					System.out.println();
					for (Person pe : duplicateEmail.get(p.getEmail())) {

						duplicateList.add(p);
						break;

					}
				} else {
					duplicatePhone = duplicateEmail.get(p.getEmail()).stream()
							.collect(Collectors.groupingBy(Person::getPhoneNumber));

					if (duplicatePhone.get(p.getPhoneNumber()).size() >= 2) {// find duplicate entries using phone
						System.out.println("Duplicate Set : ");
						System.out.println(p);
						for (Person pe : duplicatePhone.get(p.getPhoneNumber())) {

							duplicateList.add(p);
							break;
						}
					} else {

						duplicateState = duplicatePhone.get(p.getPhoneNumber()).stream()
								.collect(Collectors.groupingBy(Person::getState));
						if (duplicateState.get(p.getState()).size() >= 2) {
							for (Person pe : duplicateState.get(p.getState())) {// find duplicate entries using state

								break;
							}
						} else {

							duplicateCity = duplicateState.get(p.getState()).stream()
									.collect(Collectors.groupingBy(Person::getCity));
							if (duplicateCity.get(p.getCity()).size() >= 2) {
								for (Person pe : duplicateCity.get(p.getCity())) {// find duplicate entries using city

									break;
								}
							} else {

								duplicateAddress1 = duplicateCity.get(p.getCity()).stream()
										.collect(Collectors.groupingBy(Person::getAddress1));
								if (duplicateAddress1.get(p.getAddress1()).size() >= 2) {
									for (Person pe : duplicateAddress1.get(p.getAddress1())) {// find duplicate entries using Address1

										break;
									}
								} else {

									duplicateCompany = duplicateAddress1.get(p.getAddress1()).stream()
											.collect(Collectors.groupingBy(Person::getCompany));
									if (duplicateCompany.get(p.getCompany()).size() >= 2) {
										for (Person pe : duplicateCompany.get(p.getCompany())) {// find duplicate entries using company

											break;
										}
									} else {

										duplicateLastName = duplicateCompany.get(p.getCompany()).stream()
												.collect(Collectors.groupingBy(Person::getLastName));
										if (duplicateLastName.get(p.getLastName()).size() >= 2) {
											for (Person pe : duplicateLastName.get(p.getLastName())) {// find duplicate entries using lastname

												break;
											}
										} else {
											duplicateFirstName = duplicateLastName.get(p.getLastName()).stream()
													.collect(Collectors.groupingBy(Person::getFirstName));

											if (duplicateFirstName.get(p.getFirstName()).size() >= 2) {
												System.out.println("Duplicate Set  : ");
												System.out.println(p);
												for (Person pe : duplicateFirstName.get(p.getFirstName())) {// find duplicate entries using firstname and add to duplicate set

													duplicateList.add(p);
													break;
												}
											} else {
												System.out.println("");
												System.out.println("Non Duplicate Set : ");
												System.out.println(p);
											}

										}

									}
								}

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
