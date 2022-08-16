package fr.leahiff.opeclassrooms.project6.controller;

import fr.leahiff.opeclassrooms.project6.DataLoader;
import fr.leahiff.opeclassrooms.project6.domain.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicalRecordController {


    @Autowired
    DataLoader dataLoader;


    static Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    @PostMapping(value = "/medicalRecord")
    public List<MedicalRecord> addMedicalRecord(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthdate, @RequestParam List<String> medications, @RequestParam List<String> allergies) {
        logger.info("Entre dans la methode medicalRecord avec les parametres firstName: " + firstName + ", lastName: " + lastName + ", birthdate: " + birthdate + ", medications: " + medications + ", allergies: " + allergies);
        List<MedicalRecord> newMedicalRecord = new ArrayList<>();
        Boolean exist = false;
        for (MedicalRecord medicalRecord : dataLoader.medicalRecords) {
            if (firstName.equalsIgnoreCase(medicalRecord.getFirstName() )&& lastName.equalsIgnoreCase(medicalRecord.getLastName())) {
                exist = true;
            }
        }
        if (exist == false) {
            MedicalRecord medicalRecord = new MedicalRecord();

            medicalRecord.setFirstName(firstName);
            medicalRecord.setLastName(lastName);
            medicalRecord.setMedications(medications);
            medicalRecord.setAllergies(allergies);
            dataLoader.medicalRecords.add(medicalRecord);
        }
        logger.debug("Sortie de la methode avec Json");
        return dataLoader.medicalRecords;

    }

    @PutMapping(value = "/medicalRecord")
    public List<MedicalRecord> modifyMedicalRecord(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthdate, @RequestParam List<String> medications, @RequestParam List<String> allergies){
        logger.info("Entre dans la methode medicalRecord avec les parametres firstName: " + firstName + ", lastName: " + lastName + ", birthdate: " + birthdate + ", medications: " + medications + ", allergies: " + allergies);
        for(MedicalRecord medicalRecord : dataLoader.medicalRecords){
            if(firstName.equalsIgnoreCase(medicalRecord.getFirstName()) && (lastName.equalsIgnoreCase(medicalRecord.getLastName()))){
                medicalRecord.setBirthdate(birthdate);
                medicalRecord.setAllergies(allergies);
                medicalRecord.setMedications(medications);
            }
        }
        logger.debug("Sortie de la methode avec Json");
        return dataLoader.medicalRecords;

    }

    @DeleteMapping(value = "/medicalRecord")
    public List<MedicalRecord> deleteMedical(@RequestParam String firstName, @RequestParam String lastName){
        logger.info("Entre dans la methode medicalRecord avec les parametres firstName: " + firstName + "et lastName: " + lastName);
        List<MedicalRecord> newMedicalRecord = new ArrayList<>();
        for(MedicalRecord medicalRecord : dataLoader.medicalRecords){
            if(firstName.equalsIgnoreCase(medicalRecord.getFirstName()) && (lastName.equalsIgnoreCase(medicalRecord.getLastName()))){
            } else {
                newMedicalRecord.add(medicalRecord);
            }
        }
        dataLoader.medicalRecords = newMedicalRecord;
        logger.debug("Sortie de la methode avec Json");
        return dataLoader.medicalRecords;

    }

    @GetMapping(value = "/listMedicalrecord")
    public List<MedicalRecord> listMedicalRecord(){
        return dataLoader.medicalRecords;
    }
}