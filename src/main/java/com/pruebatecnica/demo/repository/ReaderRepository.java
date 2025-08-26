package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    
    Optional<Reader> findByName(String name);
    
    @Query("SELECT r FROM Reader r WHERE r.name LIKE %:name%")
    List<Reader> findByNameContaining(@Param("name") String name);
    
    boolean existsByName(String name);
}
