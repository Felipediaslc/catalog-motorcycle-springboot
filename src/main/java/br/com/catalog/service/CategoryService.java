package br.com.catalog.service;

import br.com.catalog.model.CategoryModel;
import br.com.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getAll() {
      return categoryRepository.findAll();
    }

}
