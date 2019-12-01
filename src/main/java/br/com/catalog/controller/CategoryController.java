package br.com.catalog.controller;

import br.com.catalog.dto.request.CategoryRequestDto;
import br.com.catalog.dto.response.CategoryResponseDto;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/")
    @Cacheable(value = "allCategorys")
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        return categoryService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping(path = "/")
    @CacheEvict(value = "allCategorys", allEntries = true)
    public ResponseEntity<?> save(@RequestBody @Valid CategoryRequestDto categoryDto, UriComponentsBuilder uriComponentsBuilder) {
        CategoryModel categoryModel = categoryService.save(categoryDto);

        URI uri = uriComponentsBuilder.path("/category/{id}").buildAndExpand(categoryModel.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryResponseDto(categoryModel));
    }

    @DeleteMapping(path = "/{id}")
    @CacheEvict(value = "allCategorys", allEntries = true)
    public ResponseEntity delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }

    @PutMapping(path = "/{id}")
    @CacheEvict(value = "allCategorys", allEntries = true)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid CategoryRequestDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

}
