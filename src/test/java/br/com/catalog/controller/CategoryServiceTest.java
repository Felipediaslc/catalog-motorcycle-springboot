package br.com.catalog.controller;

import br.com.catalog.dto.request.CategoryRequestDto;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.repository.CategoryRepository;
import br.com.catalog.service.CategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryService.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void saveCategoryShouldReturn201() throws Exception {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setDescription("MOTOCYCLE FOR URBAN");
        categoryRequestDto.setType("URBAN");

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        String json = "{\"type\": \"+ type +\", \"description\": \"+ description +\"}";

        when(categoryRepository.save(any(CategoryModel.class))).thenReturn(new CategoryModel());

        CategoryModel category = categoryService.save(categoryRequestDto);

//        this.mockMvc.perform(post("/category/")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR")
//                .characterEncoding("utf-8")
//                .content(json))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", Matchers.notNullValue()))
//                .andExpect(jsonPath("$.description", Matchers.hasItem(description)))
//                .andExpect(jsonPath("$.type", Matchers.hasItem(type)))
//                .andExpect(jsonPath("$.id", Matchers.notNullValue()));
    }

}
