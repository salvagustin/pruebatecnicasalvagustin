package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.Blog;
import com.pruebatecnica.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlogService {
    
    @Autowired
    private BlogRepository blogRepository;
    
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }
    
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }
    
    public Blog save(Blog blog) {
        if (blog.getTitle() != null && blog.getTitle().length() > 50) {
            throw new IllegalArgumentException("El título no puede exceder 50 caracteres");
        }
        if (blog.getDescription() != null && blog.getDescription().length() > 4000) {
            throw new IllegalArgumentException("La descripción no puede exceder 4000 caracteres");
        }
        return blogRepository.save(blog);
    }
    
    public Blog update(Long id, Blog blogDetails) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isPresent()) {
            Blog blog = optionalBlog.get();
            blog.setTitle(blogDetails.getTitle());
            blog.setDescription(blogDetails.getDescription());
            return blogRepository.save(blog);
        } else {
            throw new RuntimeException("Blog no encontrado con id: " + id);
        }
    }
    
    public void deleteById(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new RuntimeException("Blog no encontrado con id: " + id);
        }
        blogRepository.deleteById(id);
    }
    
    public boolean existsByTitle(String title) {
        return blogRepository.existsByTitle(title);
    }
    
    public List<Blog> findByTitleContaining(String title) {
        return blogRepository.findByTitleContaining(title);
    }
}