package fr.leahiff.opeclassrooms.project6.domain;

import java.util.ArrayList;
import java.util.List;

public class ResultFire {


    private List<Person> persons = new ArrayList<>();
    private String station;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }



    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }



    public void addPerson(Person person){
        persons.add(person);
    }

}
