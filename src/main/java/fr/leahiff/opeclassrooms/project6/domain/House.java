package fr.leahiff.opeclassrooms.project6.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class House {

    private String address;
    private List<Person> persons = new ArrayList<>();

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person){
        persons.add(person);
    }
    public void put(Person person1, Integer existing) {
    }

    public Integer get(Person person1) {
        return null;
    }

}
