package br.com.catalog.controller;

import br.com.catalog.CatalogApplicationTests;
import br.com.catalog.dto.request.CategoryRequestDto;
import br.com.catalog.dto.response.CategoryResponseDto;
import br.com.catalog.handler.RequestBodyExceptionHandler;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.service.CategoryService;
import com.sun.jndi.toolkit.url.Uri;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest extends CatalogApplicationTests {

    private MockMvc mockMvc;

    @MockBean
    private CategoryController categoryController;

    @MockBean
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
    public void saveCategoryWithTypeEmptyShouldReturn400() throws Exception {
        String json = "{\"type\": \"\", \"description\": \"MOTOCYCLE FOR URBAN\"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.typeStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", hasItem("type")))
                .andExpect(jsonPath("$.messages.[*].message", hasItem("não pode estar vazio")));
    }

    @Test
    public void saveCategoryWithTypeBlankShouldReturn400() throws Exception {
        String json = "{\"type\": \" \", \"description\": \"MOTOCYCLE FOR URBAN\"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.typeStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", hasItem("type")))
                .andExpect(jsonPath("$.messages.[*].message", hasItem("não pode estar em branco")));
    }

    @Test
    public void saveCategoryWithTypeNullShouldReturn400() throws Exception {
        String json = "{\"description\":\"MOTOCYCLE FOR URBAN\"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.typeStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", hasItem("type")))
                .andExpect(jsonPath("$.messages.[*].message", hasItem("não pode ser nulo")));
    }

    @Test
    public void saveCategoryWithTypeLargerSizeShouldReturn400() throws Exception {
        String json = "{\"type\": \"ASDFDSDFSADFDSAFGDSAFDSADFSADFS\", \"description\":\"MOTOCYCLE FOR URBAN\"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.typeStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", hasItem("type")))
                .andExpect(jsonPath("$.messages.[*].message", hasItem("tamanho deve estar entre 1 e 20")));
    }

    @Test
    public void saveCategoryWithDescriptionEmptyShouldReturn400() throws Exception {
        String json = "{\"type\": \"URBAN\", \"description\": \"\"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.typeStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", hasItem("description")))
                .andExpect(jsonPath("$.messages.[*].message", hasItem("não pode estar vazio")));
    }

    @Test
    public void saveCategoryWithDescriptionBlankShouldReturn400() throws Exception {
        String json = "{\"type\": \"URBAN\", \"description\": \" \"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.typeStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", hasItem("description")))
                .andExpect(jsonPath("$.messages.[*].message", hasItem("não pode estar em branco")));
    }

    @Test
    public void saveCategoryWithDescriptionNullShouldReturn400() throws Exception {
        String json = "{\"type\": \"URBAN\"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.typeStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", hasItem("description")))
                .andExpect(jsonPath("$.messages.[*].message", hasItem("não pode ser nulo")));
    }

    @Test
    public void saveCategoryWithDescriptionLargerSizeShouldReturn400() throws Exception {
        String json = "{\"type\": \"URBAN\"," +
                " \"description\":\"AFDGDSAGFSDVDSCSSFGVCSDSCSDDSGFSFGDASDASDASDSADSADSADSAFJHKHDFSADSADASDASDAS\"}";

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.typeStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.typeError", is("invalidFieldValue")))
                .andExpect(jsonPath("$.messages.[*].field", hasItem("description")))
                .andExpect(jsonPath("$.messages.[*].message", hasItem("tamanho deve estar entre 10 e 50")));
    }

    @Test
    public void saveCategoryShouldReturn201() throws Exception {
        String type = "URBAN";
        String description = "MOTOCYCLE FOR URBAN";
        String json = String.format("{\"type\": \"%s\",\"description\":\"%s\"}", type, description);

        CategoryModel categoryModel = new CategoryModel(type, description);
        categoryModel.setId(1);

        when(categoryController.save(any(CategoryRequestDto.class), any(UriComponentsBuilder.class)))
                .thenReturn(new ResponseEntity(new CategoryResponseDto(categoryModel), HttpStatus.CREATED));

        mockMvc.perform(post("/category/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
                .characterEncoding("utf-8")
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type", is(type)))
                .andExpect(jsonPath("$.description", is(description)));
    }

}
