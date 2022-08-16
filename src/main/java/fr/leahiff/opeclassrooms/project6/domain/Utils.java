package fr.leahiff.opeclassrooms.project6.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Utils {

    static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static String getBirthdate(String firstName, String lastName, List<MedicalRecord> medicalRecords) {
        logger.info("Entre dans la methode avec les parametres requis");
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (firstName.equalsIgnoreCase(medicalRecord.getFirstName()) && lastName.equalsIgnoreCase(medicalRecord.getLastName())) {
                logger.debug("Sortie de la methode executee avec succes");
                return medicalRecord.getBirthdate();
            }
        }
        logger.debug("Sortie de la methode par un retour null");
        return null;
    }


    public static MedicalRecord getUsefulMedicalInformation(MedicalRecord mr) {
        if (mr == null) {
            return null;
        } else {
            MedicalRecord tmp = new MedicalRecord();
            if (mr.getMedications() != null && mr.getMedications().size() > 0) {
                tmp.setMedications(mr.getMedications());
            }

            if (mr.getAllergies() != null && mr.getAllergies().size() > 0) {
                tmp.setAllergies(mr.getAllergies());
            }
            if (tmp.getAllergies() != null || tmp.getMedications() != null) {
                return tmp;
            } else {
                return null;
            }
        }

    }
}

