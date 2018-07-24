package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.language.Metaphone;

import com.csvreader.CsvReader;
import com.me.pojo.Person;
import com.me.pojo.PersonList;
import com.me.service.PersonService;

public class ClassifierUtils {
	public ArrayList<Person> Classifier(PersonService ps,PersonList personList,String filename) {
	
		ArrayList<Person> duplicateList = new ArrayList<Person>();

		final Metaphone metaphone = new Metaphone();

		try {

			Map<String, Long> data = new HashMap<String, Long>();

			Hashtable<String, Person> persons = new Hashtable<String, Person>();
		
			int a = 0;
			long b = 0;
			long total = 0;
			String[] stopwords = new String[] { "a", "an", "the", "&" }; //define common stop words

			for (Person p : personList.getPersonList()) {
				String company = p.getCompany().replaceAll("[-,.]", "").replaceAll("\\s", " ").trim();
				if (p.getPhoneNumber().equals("")) {
					b = 0;
				} else {
					long phoneNumber = Long
							.parseLong(p.getPhoneNumber().replaceAll("[-]", "").replaceAll("\\s", " ").trim());
					b = phoneNumber;
				}
				for (int jj = 0; jj < 4; jj++) { //remove stop words
					if (stopwords[jj].contains(company.toLowerCase())) {
						company.replaceAll(stopwords[jj], "");
						break;
					}
				}
			
				for (int j = 1; j < personList.getPersonList().size(); j++) { // replace '-' in phonenumber
					String compareCompany = personList.getPersonList().get(j).getCompany().replaceAll("[-,.]", "")
							.replaceAll("\\s", " ").trim();
					for (int jj = 0; jj < 4; jj++) {
						if (stopwords[jj].contains(compareCompany.toLowerCase())) {
							company.replaceAll(stopwords[jj], "");
							break;
						}
					}

					if (metaphone.isMetaphoneEqual(company, compareCompany)) {
						a = 1;
						total += (100 + b); //assign weights a & b if duplicates or else assign them 0
					}
				}
				if (a == 1) {
					data.put(p.getEmail(), b + 100);
				}

		
			}

			int mapsize = data.size();
			long avg = total / mapsize; //calcuate weight average
			
			System.out.println("##################################################### Duplicate Sets Using Classifier ######################################");
			for (Person p : personList.getPersonList()) {
				for (Map.Entry<String, Long> entry : data.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					if (p.getPhoneNumber().equals("")) {
						b = 0;
					} else {
						long phoneNumber = Long
								.parseLong(p.getPhoneNumber().replaceAll("[-]", "").replaceAll("\\s", " ").trim());
						b = phoneNumber;
					}
					if ((p.getEmail().equals(key)) && data.get(key) != 1) {
						duplicateList.add(p);
						System.out.println(
								p.getPersonId() + " " + p.getFirstName() + " " + p.getLastName()
										+ " " + p.getAddress1() + " " + p.getAddress2() + " " + p.getCompany() + " "
										+ p.getEmail() + " " + p.getPhoneNumber() + " " + p.getRow());
						System.out.println("..........................Expected weights calculated........................." + avg); //Expected weight from classifier
						System.out.println("..........................Actual weights observed.............................." + (b + 100)); //Actual weight calculated as using email
					}
				}
			}
		
				
			

		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {

		}

		return null;
	}
}
