package com.me.Utils;

import java.io.FileReader;
import com.csvreader.CsvReader;
import com.me.pojo.Person;
import com.me.pojo.PersonList;

public class CSVReader {

	PersonList personList = new PersonList();
	public  PersonList loadCSVData(String filename) //read csv files
	  	{
		  try
			{

		CsvReader reader = new CsvReader(
				new FileReader(filename));

		boolean readHeader = false;
		int count = 0;
		while (reader.readRecord()) {

			if (readHeader) {
			Person person = personList.addPerson();
			String personId = reader.get(0).replaceAll("\\s", " ").trim();
			String firstName = reader.get(1).replaceAll("\\s", " ").trim();
			String lastName = reader.get(2).replaceAll("\\s", " ").trim();
			String company = reader.get(3).replaceAll("\\s", " ").trim();
			String email = reader.get(4).replaceAll("\\s", " ").trim();
			String address1 = reader.get(5).replaceAll("\\s", " ").trim();
			String address2 = reader.get(6).replaceAll("\\s", " ").trim();
			String zip = reader.get(7).replaceAll("\\s", " ").trim();
			String city = reader.get(8).replaceAll("\\s", " ").trim();
			String stateLong = reader.get(9).replaceAll("\\s", " ").trim();
			String state = reader.get(10).replaceAll("\\s", " ").trim();
			String phoneNumber = reader.get(11).replaceAll("\\s", " ").trim();

			person.setPersonId(personId);
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setEmail(email);
			person.setCompany(company);
			person.setAddress1(address1);
			person.setAddress2(address2);
			person.setStateLong(stateLong);
			person.setState(state);
			person.setZip(zip);
			person.setCity(city);
			person.setPhoneNumber(phoneNumber);
			count++;
			person.setRow(count);
			}
			readHeader = true;
		}
			}
		 catch (Exception ex)
	        {
	            System.out.println("Exception while loading CSV Data");
	        }

		return personList;

	  }
}
