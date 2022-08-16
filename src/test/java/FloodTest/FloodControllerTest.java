package FloodTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.leahiff.opeclassrooms.project6.Project6Application;
import static org.assertj.core.api.Assertions.assertThat;

import fr.leahiff.opeclassrooms.project6.domain.FireStation;
import fr.leahiff.opeclassrooms.project6.domain.House;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;



@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Project6Application.class)
@AutoConfigureMockMvc

public class FloodControllerTest {



    @Autowired
    private MockMvc mockMvc;

    static Logger logger = LoggerFactory.getLogger(Project6Application.class);

    @Test
    public void firestationLoad() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/listFirestation")).andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<FireStation> fireStationsList = mapper.readValue(contentAsString, new TypeReference<List<FireStation>>() {
        });
        assertThat(fireStationsList.size()).isGreaterThan(2);

        int initialNumber = fireStationsList.size();

        resultActions =
                mockMvc.perform(post("/firestation")
                        .contentType("application/json")
                        .param("address", "148 rue du Paradis")
                        .param("station", "1")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        fireStationsList = mapper.readValue(contentAsString, new TypeReference<List<FireStation>>() {
        });
        assertThat(fireStationsList.size()).isEqualTo(initialNumber + 1);

        resultActions =
                mockMvc.perform(post("/firestation")
                        .contentType("application/json")
                        .param("address", "148 rue du Paradis")
                        .param("station", "1")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        fireStationsList = mapper.readValue(contentAsString, new TypeReference<List<FireStation>>() {
        });
        assertThat(fireStationsList.size()).isEqualTo(initialNumber +1);

        resultActions =
                mockMvc.perform(put("/firestation")
                        .contentType("application/json")
                        .param("address", "35 Rue de la Paix")
                        .param("station", "2")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        fireStationsList = mapper.readValue(contentAsString, new TypeReference<List<FireStation>>() {
        });
        assertThat(fireStationsList.size()).isEqualTo(initialNumber+1);

        resultActions =
                mockMvc.perform(delete("/firestation")
                        .contentType("application/json")
                        .param("address", "148 rue du Paradis")
                        .param("station", "1")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        fireStationsList = mapper.readValue(contentAsString, new TypeReference<List<FireStation>>() {
        });
        assertThat(fireStationsList.size()).isEqualTo(initialNumber);
    }

    @Test
    public void getFloodStation() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/flood/stations")
                .contentType("application/json")
                .param("stations", "2")
        );
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<House> fireStationsList = mapper.readValue(contentAsString, new TypeReference<List<House>>() {
        });
        assertThat(fireStationsList.size()).isNotZero();

    }
}
