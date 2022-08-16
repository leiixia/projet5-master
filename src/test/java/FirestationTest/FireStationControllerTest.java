package FirestationTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.leahiff.opeclassrooms.project6.Project6Application;
import fr.leahiff.opeclassrooms.project6.domain.Person;
import fr.leahiff.opeclassrooms.project6.domain.ResultFire;
import fr.leahiff.opeclassrooms.project6.domain.ResultatFireStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Project6Application.class)
@AutoConfigureMockMvc

public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPhoneAlert() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/phoneAlert")
                .contentType("application/json")
                .param("firestation", "2")
        );

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<String> phoneList = mapper.readValue(contentAsString, new TypeReference<List<String>>() {
        });
        assertThat(phoneList.size()).isNotZero();
        assertThat(phoneList.contains("841-874-6513")).isTrue();
    }


    @Test
    public void getFire() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/fire")
                .contentType("application/json")
                .param("address", "1509 Culver St")
        );

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        ResultFire resultFire = mapper.readValue(contentAsString, new TypeReference<ResultFire>() {
        });
        assertThat(resultFire.getPersons()).isNotNull();
        List<Person> persons = resultFire.getPersons();
        boolean found = false;
        for (Person person : persons){
            if(person.getAddress().equalsIgnoreCase("1509 Culver St")){
                found = true;
            }
        }
        assertThat(found== true);
    }

    @Test
    public void getFirestation() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/firestation")
                .contentType("application/json")
                .param("stationNumber", "2")
        );
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        ResultatFireStation resultatFireStation = mapper.readValue(contentAsString, new TypeReference<ResultatFireStation>() {
        });
        assertThat(resultatFireStation.getPersons()).isNotNull();

    }


}
