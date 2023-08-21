package br.com.francelinodev.crudcursosspringboot.controller;

import static org.mockito.Mockito.when;

import br.com.francelinodev.crudcursosspringboot.dto.CursoDTO;
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
        when(cursoService.list()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cursos");
        MockMvcBuilders.standaloneSetup(cursosController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindById() throws Exception {
        when(cursoService.findById(Mockito.<Long>any())).thenReturn(new CursoDTO(1L, "Name", "Category"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cursos/{id}", 1L);
        MockMvcBuilders.standaloneSetup(cursosController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"_id\":1,\"name\":\"Name\",\"category\":\"Category\"}"));
    }


    @Test
    void testUpdate() throws Exception {
        when(cursoService.update(Mockito.<Long>any(), Mockito.<CursoDTO>any()))
                .thenReturn(new CursoDTO(1L, "Name", "Category"));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/api/cursos/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CursoDTO(1L, "Name", "Category")));
        MockMvcBuilders.standaloneSetup(cursosController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"_id\":1,\"name\":\"Name\",\"category\":\"Category\"}"));
    }


    @Test
    void testCreate() throws Exception {
        when(cursoService.list()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/cursos")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CursoDTO(1L, "Name", "Category")));
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

