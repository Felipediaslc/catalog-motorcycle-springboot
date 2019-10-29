package br.com.catalog.service;

import br.com.catalog.dto.request.CategoryRequestDto;
import br.com.catalog.dto.response.CategoryResponseDto;
import br.com.catalog.model.CategoryModel;
import br.com.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAll() {
        List<CategoryModel> categories = categoryRepository.findAll();

        return categories.stream().map(CategoryResponseDto::new).collect(Collectors.toList());
    }

    public void save(CategoryRequestDto categoryDto) {
        CategoryModel categoryModel = new CategoryModel(categoryDto.getType(), categoryDto.getDescription());

        categoryRepository.save(categoryModel);
    }

}
