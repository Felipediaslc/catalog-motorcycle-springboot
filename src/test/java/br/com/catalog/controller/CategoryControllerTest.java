package br.com.catalog.controller;

import br.com.catalog.CatalogApplicationTests;
import br.com.catalog.handler.RequestBodyExceptionHandler;
import br.com.catalog.service.CategoryService;
import org.hamcrest.core.Is;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CategoryControllerTest extends CatalogApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RequestBodyExceptionHandler requestBodyExceptionHandler;

    @Autowired
    private MessageSource messageSource;

    @BeforeEach
    public void initialize(){
        this.mockMvc = standaloneSetup(categoryController)
                .setControllerAdvice(requestBodyExceptionHandler)
                .build();
    }

    @Test
    public void saveCategoryWithTypeNullShouldReturn400() throws Exception {
        String json = "{\"type\": \"\", \"description\": \"TESTE\"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", Is.is(400)))
                .andExpect(jsonPath("$.typeStatus", Is.is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", Is.is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", Matchers.hasItem("type")))
                .andExpect(jsonPath("$.messages.[*].message", Matchers.hasItem("não pode estar em branco")));
    }

}
