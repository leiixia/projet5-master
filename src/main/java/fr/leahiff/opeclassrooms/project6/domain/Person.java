package fr.leahiff.opeclassrooms.project6.domain;



import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

	static Logger logger = LoggerFactory.getLogger(Person.class);

	private  String firstName;
	private  String lastName;
	private  String address;
	private  String city;
	private  Integer zip;
	private  String phone;
	private  String email;
	private  Integer age;
	private  MedicalRecord medicalRecord;


	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public  Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public  String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public  String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public  String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public  String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public  Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public  String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public  String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

