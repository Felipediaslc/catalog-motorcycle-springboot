package br.com.catalog.controller;

import br.com.catalog.dto.response.CategoryResponseDto;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.repository.CategoryRepository;
import br.com.catalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/")
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAll();
    }

}
