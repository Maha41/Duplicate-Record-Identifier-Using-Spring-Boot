/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readcsv;

import Business.PersonList;

import Business.Person;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author amaha
 */
public class ReadCsv {

    public static void main(String[] args) {

        PersonList personList = new PersonList();

        File file = new File("normal.csv");
        String absolutePath = file.getAbsolutePath();
        try {

            CsvRead read = new CsvRead();
            personList = read.loadCSVData(absolutePath);
            System.out.println("********************************CSV Records*********************************");

            for (Person p : personList.getPersonList()) {

                System.out.println(
                        p.getPersonId() + " " + p.getFirstName() + " " + p.getLastName()
                        + " " + p.getAddress1() + " " + p.getAddress2() + " " + p.getCompany() + " "
                        + p.getEmail() + " " + p.getPhoneNumber());

            }

        } catch (Exception e) {

            e.getMessage();
        }
    }
}
