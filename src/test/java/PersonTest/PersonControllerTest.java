package PersonTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.leahiff.opeclassrooms.project6.Project6Application;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import fr.leahiff.opeclassrooms.project6.controller.PersonController;

import static org.assertj.core.api.Assertions.assertThat;

import fr.leahiff.opeclassrooms.project6.domain.Person;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.websocket.Endpoint;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Project6Application.class)
@AutoConfigureMockMvc


public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoad() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/list")).andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<Person> personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isGreaterThan(5);

        int initialNumber = personList.size();

        resultActions =
                mockMvc.perform(post("/person")
                        .contentType("application/json")
                        .param("firstName", "Megane")
                        .param("lastName", "Stark")
                        .param("address", "125 Rue du Paradis")
                        .param("city", "France")
                        .param("zip", "3957395")
                        .param("phone", "065-485-456")
                        .param("email", "mStark@gmail.com")

                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isEqualTo(initialNumber + 1);

        resultActions =
                mockMvc.perform(post("/person")
                        .contentType("application/json")
                        .param("firstName", "Megane")
                        .param("lastName", "Stark")
                        .param("address", "125 Rue du Paradis")
                        .param("city", "France")
                        .param("zip", "3957395")
                        .param("phone", "065-485-456")
                        .param("email", "mStark@gmail.com")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isEqualTo(initialNumber + 1);


        resultActions =
                mockMvc.perform(put("/person")
                        .contentType("application/json")
                        .param("firstName", "Megane")
                        .param("lastName", "Stark")
                        .param("address", "35 Rue de la Paix")
                        .param("city", "New York")
                        .param("zip", "39578537")
                        .param("phone", "354-564-595")
                        .param("email", "meganeS@gmail.com")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isEqualTo(initialNumber +1);


        resultActions =
                mockMvc.perform(delete("/person")
                        .contentType("application/json")
                        .param("firstName", "Megane")
                        .param("lastName", "Stark")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isEqualTo(initialNumber);

    }

    @Test
    public void getCommunityEmail() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/communityEmail")
                .contentType("application/json")
                .param("city", "Culver")

        );

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Set<String> emailResult = mapper.readValue(contentAsString, new TypeReference<Set<String>>() {
        });
        assertThat(emailResult.size()).isGreaterThan(1);
        assertThat(emailResult.contains("jaboyd@email.com")).isTrue();



    }

    @Test
    public void basicTestPersonInfo() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/personInfo")
                .contentType("application/json")
                .param("firstName", "John")
                .param("lastName", "Boyd")
        );
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<Person> personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isEqualTo(1);
        Person john = personList.get(0);
        assertThat(john.getMedicalRecord()).isNotNull();
        assertThat(john.getMedicalRecord().getMedications()).isNotNull();
        assertThat(john.getMedicalRecord().getMedications().size()).isGreaterThan(0);



    }

    @Test
    public void getPersonInfoWithLastNameOnly() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/personInfo")
                .contentType("application/json")
                .param("lastName", "Boyd")
        );
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<Person> personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isGreaterThan(1);

        for (Person person : personList) {
            assertThat(person.getLastName()).isEqualTo("Boyd");
        }


    }

    @Test
    public void getPersonInfoWithFirstNameOnly() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/personInfo")
                .contentType("application/json")
                .param("firstName", "John")
        );
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<Person> personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isEqualTo(1);

        for (Person person : personList) {
            assertThat(person.getFirstName()).isEqualTo("John");
        }
    }
    @Test
    public void getPersonInfoWithoutLastNameAndFirstName() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/personInfo")
                .contentType("application/json")
        );
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<Person> personList = mapper.readValue(contentAsString, new TypeReference<List<Person>>() {
        });
        assertThat(personList.size()).isGreaterThan(10);

        boolean present = false;
        int counter = 0;
        for (Person person : personList) {
            if(person.getLastName().equalsIgnoreCase("Marrack")){
                present = true;
            }
            if(person.getLastName().equalsIgnoreCase("Zemicks")){
                counter++;
            }
        }
        assertThat(present).isTrue();
        assertThat(counter).isEqualTo(3);
    }
}





