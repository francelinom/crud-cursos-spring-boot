package br.com.francelinodev.crudcursosspringboot.controller;

import static org.mockito.Mockito.when;

import br.com.francelinodev.crudcursosspringboot.dto.CursoDTO;
import br.com.francelinodev.crudcursosspringboot.dto.CursoPageDTO;
import br.com.francelinodev.crudcursosspringboot.service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CursosController.class})
@ExtendWith(SpringExtension.class)
class CursosControllerTest {
    @MockBean
    private CursoService cursoService;

    @Autowired
    private CursosController cursosController;


    @Test
    void testList() throws Exception {
        int page = 0;
        int size = 10;
        when(cursoService.list(page, size)).thenReturn(new CursoPageDTO(new ArrayList<>(), 0L, 0L));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cursos");
        MockMvcBuilders.standaloneSetup(cursosController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testDelete() throws Exception {
        when(cursoService.delete(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/cursos/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cursosController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }


    @Test
    void testDelete2() throws Exception {
        when(cursoService.delete(Mockito.<Long>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/cursos/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cursosController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

