package com.example.sheepopfood.service;

import com.example.sheepopfood.model.Category;
import com.example.sheepopfood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Optional<Category> findById(long id){
        return categoryRepository.findById(id);
    }


}
