package com.pruebatecnica.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "blogs")
public class Blog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", nullable = false, length = 50)
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 50, message = "El título no puede exceder 50 caracteres")
    private String title;
    
    @Column(name = "description", length = 4000)
    @Size(max = 4000, message = "La descripción no puede exceder 4000 caracteres")
    private String description;
    
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BlogsReader> blogsReaders;
    
    // Constructores
    public Blog() {}
    
    public Blog(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<BlogsReader> getBlogsReaders() {
        return blogsReaders;
    }
    
    public void setBlogsReaders(Set<BlogsReader> blogsReaders) {
        this.blogsReaders = blogsReaders;
    }
    
    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}