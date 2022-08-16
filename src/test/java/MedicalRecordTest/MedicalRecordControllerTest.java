package MedicalRecordTest;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.leahiff.opeclassrooms.project6.Project6Application;
import fr.leahiff.opeclassrooms.project6.domain.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Project6Application.class)
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void medicalRecordLoad() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/listMedicalrecord")).andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<MedicalRecord> medicalRecordList = mapper.readValue(contentAsString, new TypeReference<List<MedicalRecord>>() {
        });
        assertThat(medicalRecordList.size()).isGreaterThan(3);

        int initialNumber = medicalRecordList.size();

        resultActions =
                mockMvc.perform(post("/medicalRecord")
                        .contentType("application/json")
                        .param("firstName", "Megane")
                        .param("lastName", "Stark")
                        .param("birthdate", "04/08/2005")
                        .param("medications", "dodoxadin:30mg")
                        .param("allergies", "peanuts")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        medicalRecordList = mapper.readValue(contentAsString, new TypeReference<List<MedicalRecord>>() {
        });
        assertThat(medicalRecordList.size()).isEqualTo(initialNumber+1);

        resultActions =
                mockMvc.perform(put("/medicalRecord")
                        .contentType("application/json")
                        .param("firstName", "Megane")
                        .param("lastName", "Stark")
                        .param("birthdate", "04/08/2005")
                        .param("medications", "")
                        .param("allergies", "illisoxian")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        medicalRecordList = mapper.readValue(contentAsString, new TypeReference<List<MedicalRecord>>() {
        });
        assertThat(medicalRecordList.size()).isEqualTo(initialNumber+1);

        resultActions =
                mockMvc.perform(delete("/medicalRecord")
                        .contentType("application/json")
                        .param("firstName", "Megane")
                        .param("lastName", "Stark")
                );
        result = resultActions.andReturn();
        contentAsString = result.getResponse().getContentAsString();
        medicalRecordList = mapper.readValue(contentAsString, new TypeReference<List<MedicalRecord>>() {
        });
        assertThat(medicalRecordList.size()).isEqualTo(initialNumber);
    }
}
