package com.me.pojo;

import java.util.Objects;

import org.apache.commons.codec.language.Metaphone;

public class Person {
	 String personId;
	    String firstName;
	    String lastName;
	    String company;
	    String email;
	    String address1;
	    String address2;
	    String city;
	    String stateLong;
	    String state;
	    String zip;
	    String phoneNumber;
	    int row;

	    public int getRow() {
	        return row;
	    }

	    public void setRow(int row) {
	        this.row = row;
	    }
	    
	    

	    public String getPersonId() {
	        return personId;
	    }

	    public void setPersonId(String personId) {
	        this.personId = personId;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getCompany() {
	        return company;
	    }

	    public void setCompany(String company) {
	        this.company = company;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getAddress1() {
	        return address1;
	    }

	    public void setAddress1(String address1) {
	        this.address1 = address1;
	    }

	    public String getAddress2() {
	        return address2;
	    }

	    public void setAddress2(String address2) {
	        this.address2 = address2;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getStateLong() {
	        return stateLong;
	    }

	    public void setStateLong(String stateLong) {
	        this.stateLong = stateLong;
	    }

	    public String getState() {
	        return state;
	    }

	    public void setState(String state) {
	        this.state = state;
	    }

	    public String getZip() {
	        return zip;
	    }

	    public void setZip(String zip) {
	        this.zip = zip;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }
	    
	    @Override
	    public String toString() {
	        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,", row,personId, firstName, lastName,
	        		company, email,address1,address2,city, stateLong, state,zip,phoneNumber);
		   
	    }

	    @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 79 * hash + Objects.hashCode(this.personId);
	        hash = 79 * hash + Objects.hashCode(this.firstName);
	        hash = 79 * hash + Objects.hashCode(this.lastName);
	        hash = 79 * hash + Objects.hashCode(this.company);
	        hash = 79 * hash + Objects.hashCode(this.state);
	        hash = 79 * hash + Objects.hashCode(this.city);
	        hash = 79 * hash + Objects.hashCode(this.email);
	        hash = 79 * hash + Objects.hashCode(this.phoneNumber);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final Person other = (Person) obj;
	        if (!Objects.equals(this.personId, other.personId)) {
	            return false;
	        }
	        
	      final  Metaphone similarName = new Metaphone();
	      if (!similarName.isMetaphoneEqual(this.firstName, other.firstName)) {
	            return false;
	        }
	        
	        
	        if (!similarName.isMetaphoneEqual(this.lastName, other.lastName)) {
	            return false;
	        }
	        
	        if (!similarName.isMetaphoneEqual(this.company, other.company)) {
	            return false;
	        }
	        if (!similarName.isMetaphoneEqual(this.city, other.city)) {
	            return false;
	        }
	        if (!similarName.isMetaphoneEqual(this.state, other.state)) {
	            return false;
	        }
	        if (!similarName.isMetaphoneEqual(this.address1, other.address1)) {
	            return false;
	        }
	        if (!Objects.equals(this.email, other.email)) {
	            return false;
	        }
	        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
	            return false;
	        }
	        return true;
	    }
	    
	    
	    
	    
}
