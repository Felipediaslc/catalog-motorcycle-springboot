package br.com.catalog.controller;

import br.com.catalog.dto.request.CategoryRequestDto;
import br.com.catalog.dto.response.CategoryResponseDto;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.repository.CategoryRepository;
import br.com.catalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/")
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping(path = "/")
    public void save(@RequestBody @Valid CategoryRequestDto categoryDto) {
        categoryService.save(categoryDto);
    }



}
