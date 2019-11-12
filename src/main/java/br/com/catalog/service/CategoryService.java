package br.com.catalog.service;

import br.com.catalog.dto.request.CategoryRequestDto;
import br.com.catalog.dto.response.CategoryResponseDto;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    public ResponseEntity<CategoryResponseDto> getById(Integer id) {
        Optional<CategoryModel> optinal = categoryRepository.findById(id);

        if(optinal.isPresent()) {
            return ResponseEntity.ok(new CategoryResponseDto(optinal.get()));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity save(CategoryRequestDto categoryDto, UriComponentsBuilder uriComponentsBuilder) {
        CategoryModel categoryModel = new CategoryModel(categoryDto.getType(), categoryDto.getDescription());

        categoryRepository.save(categoryModel);
        URI uri = uriComponentsBuilder.path("/category/{id}").buildAndExpand(categoryModel.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoryResponseDto(categoryModel));
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
