package fr.leahiff.opeclassrooms.project6.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultatFireStation {

    static Logger logger = LoggerFactory.getLogger(ResultatFireStation.class);

    private List<Person> persons = new ArrayList<>();
    private int adultNumber;
    private int childNumber;


    public void addPerson (Person person ){
              persons.add(person);
              if(person.getAge() > 18){
                  adultNumber = adultNumber+1;
              }
              else {
                  childNumber = childNumber+1;
              }
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public int getChildNumber() {
        return childNumber;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public static int getAgeFromBirthDate(String birth){
        try {
            Date birthdate = new SimpleDateFormat("MM/dd/yyyy").parse(birth);
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localBirthdate = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = Period.between(localBirthdate, localDate).getYears();
            return age;
        }
        catch (Exception e){
            logger.error("Sortie de la methode en retournant 0");
            return 0;
        }

    }
}
