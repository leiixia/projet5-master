package fr.leahiff.opeclassrooms.project6.controller;

import java.util.*;

import fr.leahiff.opeclassrooms.project6.DataLoader;
import fr.leahiff.opeclassrooms.project6.domain.MedicalRecord;
import fr.leahiff.opeclassrooms.project6.domain.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import fr.leahiff.opeclassrooms.project6.domain.Person;

@RestController
public class PersonController {

	@Autowired
	DataLoader dataLoader;

	static Logger logger = LoggerFactory.getLogger(PersonController.class);

	@RequestMapping(value = "/personInfo", method = RequestMethod.GET)
	public List<Person> personInfo(@RequestParam(name = "firstName", required = false) String firstName, @RequestParam(name = "lastName", required = false) String lastName) {
		logger.info("Entre dans la methode personInfo avec les parametres firstName: " + firstName + " et lastName: " +lastName);
		List<Person> resultat = new ArrayList<>();
		for (Person person : dataLoader.persons) {
			boolean match = false;
			if (firstName == null && lastName == null) {
				match = true;
			} else if (firstName == null) {
				if (lastName.equalsIgnoreCase(person.getLastName())) {
					match = true;
				}
			} else if (lastName == null) {
				if (firstName.equalsIgnoreCase(person.getFirstName())) {
					match = true;
				}
			} else {
				if (firstName.equalsIgnoreCase(person.getFirstName()) && lastName.equalsIgnoreCase(person.getLastName())) {
					match = true;
				}
			}
			if (match == true) {
				String tmp = Utils.getBirthdate(person.getFirstName(), person.getLastName(), dataLoader.medicalRecords);
				try {
					int age = Person.getAgeFromBirthDate(tmp);
					person.setAge(age);
				} catch (Exception e) {
					logger.error("Echec de l'execution de la methode");

				}

				resultat.add(person);
			}
			MedicalRecord mr = getMedicalRecordForPerson(dataLoader.medicalRecords, person);
			person.setMedicalRecord(Utils.getUsefulMedicalInformation(mr));

		}
		logger.debug("Sortie de la methode avec Json");
		return resultat;

	}

	private MedicalRecord getMedicalRecordForPerson(List<MedicalRecord> medicalRecords, Person person) {
		for (int i = 0; i < medicalRecords.size(); i++) {
			MedicalRecord mr = medicalRecords.get(i);
			if (mr.getFirstName().equalsIgnoreCase(person.getFirstName()) && mr.getLastName().equalsIgnoreCase(person.getLastName())) {
				return mr;
			}
		}
		return null;
	}


	@RequestMapping(value = "/communityEmail", method = RequestMethod.GET)
	public Set<String> communityEmail(@RequestParam(name = "city", required = false) String city) {
		logger.info("Entre dans la methode communityEmail avec le parametre city:" + city);
		Set<String> result = new HashSet<>();
		for (Person person : dataLoader.persons) {
			if (city.equalsIgnoreCase(person.getCity())) {
				result.add(person.getEmail());
			}
		}
		logger.debug("Sortie de la methode avec Json");
		return result;
	}


	@DeleteMapping(value = "/person")
	public List<Person> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
		logger.info("Entre dans la methode person avec les parametres firstName:" + firstName + " et lastName: " + lastName);
		List<Person> newPersons = new ArrayList<>();
		for (Person person : dataLoader.persons) {
			if (firstName.equalsIgnoreCase(person.getFirstName()) && (lastName.equalsIgnoreCase(person.getLastName()))) {
			} else {
				newPersons.add(person);
			}
		}
		dataLoader.persons = newPersons;
		logger.debug("Sortie de la methode avec Json");
		return dataLoader.persons;

	}

	@GetMapping(value = "/list")
	public List<Person> listPersons(){
		return dataLoader.persons;
	}

	@PutMapping(value = "/person")
	public List<Person> modifyPerson(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String city, @RequestParam Integer zip, @RequestParam String phone, @RequestParam String email ){
		logger.info("Entre dans la methode person avec les parametres firstName: " + firstName + ", lastName: " + lastName + ", address: " + address + ", city: " + city + ", zip: " + zip + ", phone: " + phone + ", email: " + email);
		for(Person person : dataLoader.persons){
			if (firstName.equalsIgnoreCase(person.getFirstName()) && (lastName.equalsIgnoreCase(person.getLastName()))){
				person.setAddress(address);
				person.setCity(city);
				person.setZip(zip);
				person.setPhone(phone);
				person.setEmail(email);
			}
		}
		logger.debug("Sortie de la methode avec Json");
		return dataLoader.persons;

	}

	@PostMapping(value = "/person")
	public List<Person> addPerson(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String address, @RequestParam String city,@RequestParam Integer zip, @RequestParam String phone, @RequestParam String email){
		logger.info("Entre dans la methode person avec les parametres firstName: " + firstName + ", lastName: " + lastName + ", address: " + address + ", city: " + city + ", zip: " + zip + ", phone: " + phone + ", email: " + email);
		List<Person> newPerson = new ArrayList<>();
		boolean exist = false;
		for(Person person: dataLoader.persons){
			if(firstName.equalsIgnoreCase(person.getFirstName()) && lastName.equalsIgnoreCase(person.getLastName())){
				exist = true;
			}
		}
		if(exist == false){
			Person person = new Person();
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setAddress(address);
			person.setCity(city);
			person.setZip(zip);
			person.setPhone(phone);
			person.setEmail(email);
			dataLoader.persons.add(person);
		}
		logger.debug("Sortie de la methode avec Json");
		return dataLoader.persons;

	}
}
