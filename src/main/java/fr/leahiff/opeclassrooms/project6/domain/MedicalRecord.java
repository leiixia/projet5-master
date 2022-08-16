package fr.leahiff.opeclassrooms.project6.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalRecord {

    private  String firstName;
    private  String lastName;
    private  String birthdate;
    private  List<String> medications;
    private  List<String> allergies;


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
    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate( String birthdate){
        this.birthdate = birthdate;
    }
    public List<String> getMedications(){
        return medications;
    }
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }
    public List<String> getAllergies(){
        return allergies;
    }
    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }


}

