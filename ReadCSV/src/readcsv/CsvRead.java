/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readcsv;

/**
 *
 * @author amaha
 */
import Business.Person;
import Business.PersonList;
import com.csvreader.CsvReader;
import java.io.FileReader;

public class CsvRead {

    public PersonList loadCSVData(String filename) {

        PersonList personList = new PersonList();

        try {
            CsvReader reader = new CsvReader(new FileReader(filename));

            boolean readHeader = false;

            int count = 0;

            while (reader.readRecord()) { //Read CSV Data
                if (readHeader) {

                    Person person = personList.addPerson();
                    String personId = reader.get(0);
                    String firstName = reader.get(1);
                    String lastName = reader.get(2);
                    String company = reader.get(3);
                    String email = reader.get(4);
                    String address1 = reader.get(5);
                    String address2 = reader.get(6);
                    String zip = reader.get(7);
                    String city = reader.get(8);
                    String stateLong = reader.get(9);
                    String state = reader.get(10);
                    String phoneNumber = reader.get(11);

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
                }
                readHeader = true;
            }
            
            
          
        } catch (Exception ex) {
            System.out.println("Exception while loading CSV Data");
        }


return personList;
    }
    
}
