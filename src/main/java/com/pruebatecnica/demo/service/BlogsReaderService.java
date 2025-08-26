package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.Blog;
import com.pruebatecnica.demo.entity.BlogsReader;
import com.pruebatecnica.demo.entity.Reader;
import com.pruebatecnica.demo.repository.BlogsReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlogsReaderService {
    
    @Autowired
    private BlogsReaderRepository blogsReaderRepository;
    
    @Autowired
    private ReaderService readerService;
    
    @Autowired
    private BlogService blogService;
    
    public List<BlogsReader> findAll() {
        return blogsReaderRepository.findAll();
    }
    
    public Optional<BlogsReader> findById(Long id) {
        return blogsReaderRepository.findById(id);
    }
    
    public BlogsReader save(BlogsReader blogsReader) {
        // Validar que no existe ya la relación
        if (blogsReaderRepository.existsByReaderAndBlog(
                blogsReader.getReader(), blogsReader.getBlog())) {
            throw new RuntimeException("El lector ya está asociado a este blog");
        }
        return blogsReaderRepository.save(blogsReader);
    }
    
    public BlogsReader createAssociation(Long readerId, Long blogId) {
        Optional<Reader> reader = readerService.findById(readerId);
        Optional<Blog> blog = blogService.findById(blogId);
        
        if (reader.isEmpty()) {
            throw new RuntimeException("Lector no encontrado con id: " + readerId);
        }
        if (blog.isEmpty()) {
            throw new RuntimeException("Blog no encontrado con id: " + blogId);
        }
        
        BlogsReader blogsReader = new BlogsReader(reader.get(), blog.get());
        return save(blogsReader);
    }
    
    public void deleteById(Long id) {
        if (!blogsReaderRepository.existsById(id)) {
            throw new RuntimeException("Asociación no encontrada con id: " + id);
        }
        blogsReaderRepository.deleteById(id);
    }
    
    public List<BlogsReader> findByReaderId(Long readerId) {
        return blogsReaderRepository.findByReaderId(readerId);
    }
    
    public List<BlogsReader> findByBlogId(Long blogId) {
        return blogsReaderRepository.findByBlogId(blogId);
    }
    
    public Long countReadersByBlogId(Long blogId) {
        return blogsReaderRepository.countReadersByBlogId(blogId);
    }
}