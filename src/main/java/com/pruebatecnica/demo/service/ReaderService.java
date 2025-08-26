package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.Reader;
import com.pruebatecnica.demo.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReaderService {
    
    @Autowired
    private ReaderRepository readerRepository;
    
    public List<Reader> findAll() {
        return readerRepository.findAll();
    }
    
    public Optional<Reader> findById(Long id) {
        return readerRepository.findById(id);
    }
    
    public Reader save(Reader reader) {
        if (reader.getName() != null && reader.getName().length() > 8) {
            throw new IllegalArgumentException("El nombre no puede exceder 8 caracteres");
        }
        return readerRepository.save(reader);
    }
    
    public Reader update(Long id, Reader readerDetails) {
        Optional<Reader> optionalReader = readerRepository.findById(id);
        if (optionalReader.isPresent()) {
            Reader reader = optionalReader.get();
            reader.setName(readerDetails.getName());
            return readerRepository.save(reader);
        } else {
            throw new RuntimeException("Lector no encontrado con id: " + id);
        }
    }
    
    public void deleteById(Long id) {
        if (!readerRepository.existsById(id)) {
            throw new RuntimeException("Lector no encontrado con id: " + id);
        }
        readerRepository.deleteById(id);
    }
    
    public boolean existsByName(String name) {
        return readerRepository.existsByName(name);
    }
    
    public List<Reader> findByNameContaining(String name) {
        return readerRepository.findByNameContaining(name);
    }
}