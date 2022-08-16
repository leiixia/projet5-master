package fr.leahiff.opeclassrooms.project6.domain;

import java.util.ArrayList;
import java.util.List;

public class ChildAlertResult {

    private List<Person> childList = new ArrayList<>();
    private List<Person> adultList = new ArrayList<>();

    public List<Person> getChildList() {
        return childList;
    }

    public void setChildList(List<Person> childList) {
        this.childList = childList;
    }

    public List<Person> getAdultList() {
        return adultList;
    }

    public void setAdultList(List<Person> adultList) {
        this.adultList = adultList;
    }




}
