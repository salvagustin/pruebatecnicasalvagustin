package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Blog;
import com.pruebatecnica.demo.entity.BlogsReader;
import com.pruebatecnica.demo.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogsReaderRepository extends JpaRepository<BlogsReader, Long> {
    
    @Query("SELECT br FROM BlogsReader br WHERE br.reader.id = :readerId")
    List<BlogsReader> findByReaderId(@Param("readerId") Long readerId);
    
    @Query("SELECT br FROM BlogsReader br WHERE br.blog.id = :blogId")
    List<BlogsReader> findByBlogId(@Param("blogId") Long blogId);
    
    @Query("SELECT br FROM BlogsReader br WHERE br.reader = :reader AND br.blog = :blog")
    Optional<BlogsReader> findByReaderAndBlog(@Param("reader") Reader reader, @Param("blog") Blog blog);
    
    boolean existsByReaderAndBlog(Reader reader, Blog blog);
    
    @Query("SELECT COUNT(br) FROM BlogsReader br WHERE br.blog.id = :blogId")
    Long countReadersByBlogId(@Param("blogId") Long blogId);
}