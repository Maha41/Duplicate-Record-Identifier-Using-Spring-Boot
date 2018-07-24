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

public class IdentifierUtils {

	public ArrayList<Person> Identifier(PersonService ps,PersonList personList, String filename) {
	
		ArrayList<Person> duplicateList = new ArrayList<Person>();

	try {


			int count = 0;
			Map<String, Integer> data = new HashMap<String, Integer>();

			Hashtable<String, Person> persons = new Hashtable<String, Person>();

			for (Person p : personList.getPersonList()) { // find duplicate entries using email
				count++;
				persons.put(String.valueOf(count), p);
				ps.setPersons(persons);
				String Company = p.getCompany().replaceAll("[-,.]", "").replaceAll("\\s", " ").trim();
				// Saves the email and the number of occurrences as its value
				if (!data.containsKey(p.getEmail())) {
					data.put(p.getEmail(), 1);
				} else {
					data.put(p.getEmail(), data.get(p.getEmail()) + 1);
					

				}

				
			}
			System.out.println("###################################### Duplicate Sets ###########################################"); // Print duplicate entries on console
			int index =0;
			for (Person p : personList.getPersonList()) {
				for (Map.Entry<String, Integer> entry : data.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					if ((p.getEmail().equals(key)) && data.get(key) != 1) {
						duplicateList.add(p);
						System.out.println("********************************Set "+ index + "*********************************");
						System.out.println(
								p.getPersonId() + " " + p.getFirstName() + " " + p.getLastName()
										+ " " + p.getAddress1() + " " + p.getAddress2() + " " + p.getCompany() + " "
										+ p.getEmail() + " " + p.getPhoneNumber() + " " + p.getRow());
						index++;
					}
				}
			}
			
			System.out.println("###################################### Non- Duplicate Sets ###########################################"); // Print duplicate entries on console
					
			for (Map.Entry<String, Integer> entry : data.entrySet()) { //Print non- duplicate entries to the console
				String key = entry.getKey();
				Object value = entry.getValue();

				if (data.get(key) == 1) {
					for (Person p : personList.getPersonList()) {

						if (p.getEmail().equals(key)) {
							
							System.out.println( p.getPersonId() + " " + p.getFirstName() + " " + p.getLastName()
											+ " " + p.getAddress1() + " " + p.getAddress2() + " " + p.getCompany() + " "
											+ p.getEmail() + " " + p.getPhoneNumber() + " " + p.getRow());
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
