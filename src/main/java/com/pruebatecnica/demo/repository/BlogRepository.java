package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    
    @Query("SELECT b FROM Blog b WHERE b.title LIKE %:title%")
    List<Blog> findByTitleContaining(@Param("title") String title);
    
    @Query("SELECT b FROM Blog b WHERE b.description LIKE %:description%")
    List<Blog> findByDescriptionContaining(@Param("description") String description);
    
    boolean existsByTitle(String title);
}
