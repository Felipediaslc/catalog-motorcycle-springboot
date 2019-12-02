package br.com.catalog.service;

import br.com.catalog.dto.request.CategoryRequestDto;
import br.com.catalog.dto.response.CategoryResponseDto;
import br.com.catalog.exception.CategoryNotFoundException;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        List<CategoryModel> categories = categoryRepository.findAll();

        List<CategoryResponseDto> categoriesDto =
                categories.stream().map(CategoryResponseDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(categoriesDto);
    }

    public Optional<CategoryModel> getById(Integer id) {
        return categoryRepository.findById(id);
    }

    public CategoryModel save(CategoryRequestDto categoryDto) {
        CategoryModel categoryModel = new CategoryModel(categoryDto.getType(), categoryDto.getDescription());

        return categoryRepository.save(categoryModel);
    }

    public ResponseEntity delete(Integer id) {
        Optional<CategoryModel> optional = categoryRepository.findById(id);

        if(optional.isPresent()) {
            categoryRepository.delete(optional.get());

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity update(Integer id, CategoryRequestDto categoryDto) {
        Optional<CategoryModel> optional = categoryRepository.findById(id);

        if(optional.isPresent()) {
            optional.get().setDescription(categoryDto.getDescription());
            optional.get().setType(categoryDto.getType());
            categoryRepository.save(optional.get());

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
