package fr.leahiff.opeclassrooms.project6.controller;

import fr.leahiff.opeclassrooms.project6.DataLoader;
import fr.leahiff.opeclassrooms.project6.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
public class FloodController {

    @Autowired
    DataLoader dataLoader;

    static Logger logger = LoggerFactory.getLogger(FloodController.class);

    @RequestMapping(value= "/flood/stations", method = RequestMethod.GET)
    public List<House> flood (@RequestParam(name = "stations", required = false) String station) {
        logger.info("Entre dans la methode flood/stations avec le parametre station: " + station);
        List<House> result = new ArrayList<>();
        for (Person person : dataLoader.persons) {
               String address = person.getAddress();
               String stationCode = getStationCodeByAddress(address);
               if(stationCode.equalsIgnoreCase(station)){
                   House house = findHouseByAddress(result, address);
                   Person person1 = new Person();
                   person1.setFirstName(person.getFirstName());
                   person1.setLastName(person.getLastName());
                   person1.setPhone(person.getPhone());
                   person1.setAge(Person.getAgeFromBirthDate(Utils.getBirthdate(person1.getFirstName(), person1.getLastName(),dataLoader.medicalRecords)));
                   MedicalRecord mr = getMedicalRecordForPerson(dataLoader.medicalRecords, person1);
                   person1.setMedicalRecord(Utils.getUsefulMedicalInformation(mr));
                   house.addPerson(person1);
               }
        }
        logger.debug("Sortie de la methode avec Json.");
        return result;

    }

    private House findHouseByAddress(List<House> houses, String address){
        for( int i = 0; i < houses.size(); i++){
            House tmp = houses.get(i);
            if( tmp.getAddress().equalsIgnoreCase(address)){
                return tmp;
            }
        }
        House house = new House();
        house.setAddress(address);
        houses.add(house);
        return house;
    }

    private MedicalRecord getMedicalRecordForPerson(List<MedicalRecord> mr, Person person){
        for(int i = 0; i < mr.size() ; i++){
            MedicalRecord medicalRecords = mr.get(i);
            if(medicalRecords.getFirstName().equalsIgnoreCase(person.getFirstName()) && medicalRecords.getLastName().equalsIgnoreCase(person.getLastName())){
                return medicalRecords;
            }
        }
        return null;
    }
    private String getStationCodeByAddress(String address){
        for (FireStation fireStation : dataLoader.fireStations) {
                if(fireStation.getAddress().equalsIgnoreCase(address)){
                   return fireStation.getStation();
                }
        }
        return null;
    }

    @PutMapping(value = "/firestation")
    public List<FireStation> modifyFirestation(@RequestParam String station, @RequestParam String address){
        logger.info("Entre dans la methode firestation avec les parametres station: " + station + " et address: " + address);
        for(FireStation fireStation: dataLoader.fireStations){
            if(address.equalsIgnoreCase(fireStation.getAddress())){
                fireStation.setStation(station);
            }
        }
        logger.debug("Sortie de la methode avec Json.");
        return dataLoader.fireStations;

    }

    @DeleteMapping(value = "/firestation")
    public List<FireStation> deleteFirestation(@RequestParam String address){
        logger.info("Entre dans la methode firestation avec le parametre address: " + address);
        List<FireStation> newFirestation = new ArrayList<>();
        for (FireStation fireStation: dataLoader.fireStations){
            if(address.equalsIgnoreCase(fireStation.getAddress())){
            } else {
                newFirestation.add(fireStation);
            }
        }
        dataLoader.fireStations = newFirestation;
        logger.debug("Sortie de la methode avec Json.");
        return  dataLoader.fireStations;

    }

    @GetMapping(value = "/listFirestation")
    public List<FireStation> listFireStations(){
        return dataLoader.fireStations;
    }

    @PostMapping(value = "/firestation")
    public List<FireStation> addFirestation(@RequestParam String address, @RequestParam String station) {
        logger.info("Entre dans la methode firestation avec les parametres address: " + address + " et station: " + station);
        List<FireStation> newFirestation = new ArrayList<>();
        Boolean exist = false;
        for(FireStation firestation : dataLoader.fireStations){
            if(address.equalsIgnoreCase(firestation.getAddress())){
                exist = true;
            }
        }
        if(exist == false){
            FireStation firestation = new FireStation();
            firestation.setStation(station);
            firestation.setAddress(address);
            dataLoader.fireStations.add(firestation);
        }
        logger.debug("Sortie de la methode avec Json.");
        return dataLoader.fireStations;

    }


}


