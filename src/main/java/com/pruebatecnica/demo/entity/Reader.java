package com.pruebatecnica.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "readers")
public class Reader {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, length = 8)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 8, message = "El nombre no puede exceder 8 caracteres")
    private String name;
    
    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BlogsReader> blogsReaders;
    
    // Constructores
    public Reader() {}
    
    public Reader(String name) {
        this.name = name;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<BlogsReader> getBlogsReaders() {
        return blogsReaders;
    }
    
    public void setBlogsReaders(Set<BlogsReader> blogsReaders) {
        this.blogsReaders = blogsReaders;
    }
    
    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}