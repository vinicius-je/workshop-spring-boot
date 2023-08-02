package com.vinicius.course.services;

import com.vinicius.course.entities.Category;
import com.vinicius.course.repositories.CategoryRepository;
import com.vinicius.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Category findById(Long id){
        Optional<Category> obj =  categoryRepository.findById(id);
        return obj.get();
    }

    public Category insert(Category obj){
        return categoryRepository.save(obj);
    }

    public void delete(Long id){
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public Category update(Long id, Category obj){
        try {
            Category entity = categoryRepository.getReferenceById(id);
            entity.setName(obj.getName());
            return categoryRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
