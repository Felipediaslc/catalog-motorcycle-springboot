package br.com.catalog.controller;

import br.com.catalog.CatalogApplicationTests;
import br.com.catalog.handler.RequestBodyExceptionHandler;
import br.com.catalog.service.CategoryService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CategoryControllerTest extends CatalogApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private CategoryController categoryController;

    @BeforeEach
    public void initialize(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void saveCategoryWithTypeNullShouldReturn400() throws Exception {
        String json = "{\"type\": \"TESTE\", \"description\": \"TESTE\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Is.is(400)));
    }

}
