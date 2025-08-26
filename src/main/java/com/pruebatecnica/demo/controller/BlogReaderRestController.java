package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.BlogsReader;
import com.pruebatecnica.demo.service.BlogsReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs-readers")
public class BlogReaderRestController {
    
    @Autowired
    private BlogsReaderService blogsReaderService;
    
    @GetMapping
    public ResponseEntity<List<BlogsReader>> getAllBlogsReaders() {
        List<BlogsReader> blogsReaders = blogsReaderService.findAll();
        return ResponseEntity.ok(blogsReaders);
    }
    
    @GetMapping("/by-blog/{blogId}")
    public ResponseEntity<List<BlogsReader>> getReadersByBlog(@PathVariable Long blogId) {
        List<BlogsReader> blogsReaders = blogsReaderService.findByBlogId(blogId);
        return ResponseEntity.ok(blogsReaders);
    }
    
    @GetMapping("/by-reader/{readerId}")
    public ResponseEntity<List<BlogsReader>> getBlogsByReader(@PathVariable Long readerId) {
        List<BlogsReader> blogsReaders = blogsReaderService.findByReaderId(readerId);
        return ResponseEntity.ok(blogsReaders);
    }
    
    @GetMapping("/count/{blogId}")
    public ResponseEntity<Long> countReadersByBlog(@PathVariable Long blogId) {
        Long count = blogsReaderService.countReadersByBlogId(blogId);
        return ResponseEntity.ok(count);
    }
}