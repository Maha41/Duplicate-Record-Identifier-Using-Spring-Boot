package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.csvreader.CsvReader;
import com.me.pojo.Person;
import com.me.pojo.PersonList;
import com.me.service.PersonService;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;

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

			
			
			

			for (Person p : personList.getPersonList()) {

				count++;
				persons.put(String.valueOf(count), p);
				ps.setPersons(persons);
if((duplicateEmail.get(p.getEmail())!=null)) {
	duplicateEmail = people.stream().collect(Collectors.groupingBy(Person::getEmail));
				if (duplicateEmail.get(p.getEmail()).size() >= 2) {// find duplicate entries using email
					duplicate_set++;
					System.out.println("Duplicate Set " + duplicate_set + " : ");
					System.out.println(duplicateEmail.get(p.getEmail()) + "\n");
					duplicateList.addAll(duplicateEmail.get(p.getEmail()));
					people.removeAll(duplicateEmail.get(p.getEmail()));
					System.out.println(people);

				}
			
				else if (duplicatePhone.get(p.getPhoneNumber())!=null) {	
					duplicatePhone = people.stream()
							.collect(Collectors.groupingBy(Person::getPhoneNumber));
					if (duplicatePhone.get(p.getPhoneNumber()).size() >= 2) {// find duplicate entries using phone
						duplicate_set++;
						System.out.println("Duplicate Set " + duplicate_set + " : ");
						System.out.println(duplicatePhone.get(p.getPhoneNumber()) + "\n");
						duplicateList.addAll(duplicatePhone.get(p.getPhoneNumber()));
						people.removeAll(duplicatePhone.get(p.getPhoneNumber()));
					} else {
						
					}
				}		
			
}else {
						duplicateLastName = people.stream() // find duplicate entries using LastName
								.collect(Collectors.groupingBy(Person::getLastName));

						duplicateFirstName = people.stream()
								.collect(Collectors.groupingBy(Person::getFirstName));// find duplicate entries using FirstName


						if ((duplicateFirstName.get(p.getFirstName())!=null) && (duplicateLastName.get(p.getLastName())!=null) 
								&& (duplicateLastName.get(p.getLastName()).size() >= 2)
								&& (duplicateFirstName.get(p.getFirstName()).size() >= 2)) {
							duplicate_set++;
							System.out.println("Duplicate Set " + duplicate_set + " : ");
							System.out.println(duplicateFirstName.get(p.getFirstName()) + "\n");
							duplicateList.addAll(duplicateFirstName.get(p.getFirstName()));
							people.removeAll(duplicateFirstName.get(p.getFirstName()));
							//people.removeAll(duplicateLastName.get(p.getLastName()));
							
							
							
						}else if(!duplicateList.contains(p)) {
							System.out.println("Non Duplicate Set : ");
							System.out.println(p + "\n");
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
