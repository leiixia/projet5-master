package fr.leahiff.opeclassrooms.project6.controller;

import fr.leahiff.opeclassrooms.project6.DataLoader;
import fr.leahiff.opeclassrooms.project6.domain.ChildAlertResult;
import fr.leahiff.opeclassrooms.project6.domain.Person;
import fr.leahiff.opeclassrooms.project6.domain.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ChildAlertController {

    @Autowired
    DataLoader dataLoader;

    static Logger logger = LoggerFactory.getLogger(ChildAlertController.class);

    @RequestMapping(value = "/childAlert", method = RequestMethod.GET)
    public ChildAlertResult childAlert(@RequestParam(name = "address", required = true) String address) {
        logger.info("Entre dans la methode ChildAlert avec le parametre address: " + address);
        ChildAlertResult result = new ChildAlertResult();
        for(Person person: dataLoader.persons){
            if(address.equalsIgnoreCase(person.getAddress())){
                Person person1 = new Person();
                person1.setFirstName(person.getFirstName());
                person1.setLastName(person.getLastName());
                String tmp = Utils.getBirthdate(person.getFirstName(), person.getLastName(), dataLoader.medicalRecords);

                int age = person.getAgeFromBirthDate(tmp);
                if(age <= 18){
                    person1.setAge(age);
                    result.getChildList().add(person1);
                }
                else {
                    result.getAdultList().add(person1);
                }
            }
        }

                if(result.getChildList().size() == 0){
                    logger.debug("Sortie de la methode avec retour null");
                    return null;
                }
                else {
                    logger.debug("Sortie de la methode avec Json.");
                    return result;
                }


    }


}
