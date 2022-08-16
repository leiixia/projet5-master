package fr.leahiff.opeclassrooms.project6;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.leahiff.opeclassrooms.project6.domain.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
	
	public List<Person> persons = new ArrayList<>();
	public List<FireStation> fireStations = new ArrayList<>();
	public List<MedicalRecord> medicalRecords = new ArrayList<>();




	public DataLoader(){
		this.load();
	}

	public void load() {

		ObjectMapper mapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			Resource resource = new ClassPathResource("Data.Json");
			DatabaseContent databaseContent = mapper.readValue(resource.getInputStream(), DatabaseContent.class);
			this.persons = databaseContent.persons;
			this.fireStations = databaseContent.firestations;
			this.medicalRecords = databaseContent.medicalrecords;
		} catch (IOException e) {
			e.printStackTrace();
		}




	}

}

