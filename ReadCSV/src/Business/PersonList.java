/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;

/**
 *
 * @author amaha
 */
public class PersonList {
    
    ArrayList<Person> personList;
    
    public PersonList(){
        this.personList = new ArrayList<>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }
    
        public Person addPerson() {
       Person person = new Person();
        personList.add(person);
        return person; 
    }
    
    
    
}
