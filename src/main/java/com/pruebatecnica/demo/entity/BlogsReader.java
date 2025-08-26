package com.pruebatecnica.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blogs_readers")
public class BlogsReader {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_id", nullable = false)
    private Reader reader;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_id", nullable = false)
    private Blog blog;
    
    // Constructores
    public BlogsReader() {}
    
    public BlogsReader(Reader reader, Blog blog) {
        this.reader = reader;
        this.blog = blog;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Reader getReader() {
        return reader;
    }
    
    public void setReader(Reader reader) {
        this.reader = reader;
    }
    
    public Blog getBlog() {
        return blog;
    }
    
    public void setBlog(Blog blog) {
        this.blog = blog;
    }
    
    @Override
    public String toString() {
        return "BlogsReader{" +
                "id=" + id +
                ", reader=" + (reader != null ? reader.getName() : null) +
                ", blog=" + (blog != null ? blog.getTitle() : null) +
                '}';
    }
}