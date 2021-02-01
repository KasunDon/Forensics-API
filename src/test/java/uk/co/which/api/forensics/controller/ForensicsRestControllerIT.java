package uk.co.which.api.forensics.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ForensicsRestControllerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    public void get_all_directions_forensic_grid() throws Exception {
        mvc.perform(get("/api/email@email.com/directions")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(jsonPath("$[0].direction", is("NORTH")))
            .andExpect(jsonPath("$[0].coordinate.x", is(0)))
            .andExpect(jsonPath("$[0].coordinate.y", is(0)));
    }

    @Test
    public void accept_user_prediction_coordinates() throws Exception {
        mvc.perform(get("/api/email@email.com/location/0/1")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
    }

    @Test
    public void returns_true_if_match_found() throws Exception {
        mvc.perform(get("/api/email@email.com/location/0/0")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(jsonPath("$.found", is(true)));
    }

    @Test
    public void returns_false_if_match_not_found() throws Exception {
        mvc.perform(get("/api/email@email.com/location/0/2")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(jsonPath("$.found", is(false)));
    }

    @Test
    public void accept_user_predictions_upto_5_predictions_otherwise_return_406() throws Exception {
        mvc.perform(get("/api/email1@email.com/location/0/1")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        mvc.perform(get("/api/email1@email.com/location/0/2")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        mvc.perform(get("/api/email1@email.com/location/0/3")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        mvc.perform(get("/api/email1@email.com/location/0/4")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        mvc.perform(get("/api/email1@email.com/location/0/5")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));

        mvc.perform(get("/api/email1@email.com/location/0/6")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isNotAcceptable());
    }
}