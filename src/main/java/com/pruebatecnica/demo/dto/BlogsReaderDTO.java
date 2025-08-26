package com.pruebatecnica.demo.dto;

public class BlogsReaderDTO {
    private Long id;
    private String readerName;
    private String blogTitle;
    private String blogDescription;
    
    public BlogsReaderDTO() {}
    
    public BlogsReaderDTO(Long id, String readerName, String blogTitle, String blogDescription) {
        this.id = id;
        this.readerName = readerName;
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getReaderName() {
        return readerName;
    }
    
    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
    
    public String getBlogTitle() {
        return blogTitle;
    }
    
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
    
    public String getBlogDescription() {
        return blogDescription;
    }
    
    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }
}
