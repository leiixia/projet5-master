package ChildAlertTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.leahiff.opeclassrooms.project6.Project6Application;
import fr.leahiff.opeclassrooms.project6.domain.ChildAlertResult;
import fr.leahiff.opeclassrooms.project6.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.transform.Result;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Project6Application.class)
@AutoConfigureMockMvc

public class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getChild() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/childAlert")
                .contentType("application/json")
                .param("address", "834 Binoc Ave")
        );
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        ChildAlertResult childList = mapper.readValue(contentAsString, new TypeReference<ChildAlertResult>() {
        });
        assertThat(childList.getChildList()).isNotNull();
    }

    @Test
    public void getAdult() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/childAlert")
                .contentType("application/json")
                .param("address", "1509 Culver St")
        );
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        ChildAlertResult adultList = mapper.readValue(contentAsString, new TypeReference<ChildAlertResult>() {
        });
        assertThat(adultList.getAdultList()).isNotNull();



    }
}
