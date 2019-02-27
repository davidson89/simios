package br.com.mercadolivre.simios.infrastructure.controller;

import br.com.mercadolivre.simios.domain.http.DNAStats;
import br.com.mercadolivre.simios.infrastructure.services.DNAService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DNAEndpoint.class)
public class DNAEndpointTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DNAService dnaService;

    @Test
    public void isSimioHTTP200() throws Exception {
        Mockito.when(dnaService.isSimio(Mockito.any())).thenReturn(true);

        mvc.perform(post("/simian")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void isSimioHTTP403() throws Exception {
        Mockito.when(dnaService.isSimio(Mockito.any())).thenReturn(false);

        mvc.perform(post("/simian")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void isSimioHTTP400() throws Exception {
        Mockito.when(dnaService.isSimio(Mockito.any())).thenThrow(new IllegalArgumentException());

        mvc.perform(post("/simian")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void isSimioHTTP500() throws Exception {
        Mockito.when(dnaService.isSimio(Mockito.any())).thenThrow(new NullPointerException());

        mvc.perform(post("/simian")
                .contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void statsHTTP200() throws Exception {
        Mockito.when(dnaService.getStats()).thenReturn(Mockito.mock(DNAStats.class));

        mvc.perform(get("/stats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void statsHTTP500() throws Exception {
        Mockito.when(dnaService.getStats()).thenThrow(new NullPointerException());

        mvc.perform(get("/stats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}