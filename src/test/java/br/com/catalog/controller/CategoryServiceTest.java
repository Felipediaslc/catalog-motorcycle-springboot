package br.com.catalog.controller;

import br.com.catalog.dto.request.CategoryRequestDto;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.repository.CategoryRepository;
import br.com.catalog.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryService.class)
public class CategoryServiceTest{

    @Autowired
    CategoryService categoryService;

    @MockBean
    CategoryRepository categoryRepository;

    @Test
    public void saveCategoryShouldReturn201() {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setDescription("MOTOCYCLE FOR URBAN");
        categoryRequestDto.setType("URBAN");

        when(categoryRepository.save(any(CategoryModel.class)))
                .thenReturn(new CategoryModel(categoryRequestDto.getType(), categoryRequestDto.getDescription()));

        CategoryModel category = categoryService.save(categoryRequestDto);

        assertEquals("Valor do campo type não é igual", categoryRequestDto.getType(), category.getType());
        assertEquals("Valor do campo description não é igual", categoryRequestDto.getDescription(), category.getDescription());

//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", Matchers.notNullValue()))
//                .andExpect(jsonPath("$.description", Matchers.hasItem(description)))
//                .andExpect(jsonPath("$.type", Matchers.hasItem(type)))
//                .andExpect(jsonPath("$.id", Matchers.notNullValue()));
    }

}
