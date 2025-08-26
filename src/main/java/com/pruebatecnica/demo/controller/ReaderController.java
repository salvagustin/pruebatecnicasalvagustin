package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.Reader;
import com.pruebatecnica.demo.service.ReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/readers")
public class ReaderController {
    
    @Autowired
    private ReaderService readerService;
    
    @GetMapping
    public String listReaders(Model model) {
        model.addAttribute("readers", readerService.findAll());
        return "list_reader";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("reader", new Reader());
        return "reader_form";
    }
    
    @PostMapping
    public String saveReader(@Valid @ModelAttribute Reader reader, 
                           BindingResult result, 
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "reader_form";
        }
        
        try {
            if (readerService.existsByName(reader.getName())) {
                result.rejectValue("name", "error.reader", "Ya existe un lector con este nombre");
                return "reader_form";
            }
            
            readerService.save(reader);
            redirectAttributes.addFlashAttribute("successMessage", "Lector creado exitosamente");
            return "redirect:/readers";
        } catch (Exception e) {
            result.rejectValue("name", "error.reader", e.getMessage());
            return "reader_form";
        }
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Reader> reader = readerService.findById(id);
        if (reader.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lector no encontrado");
            return "redirect:/readers";
        }
        
        model.addAttribute("reader", reader.get());
        return "reader_form";
    }
    
    @PostMapping("/update/{id}")
    public String updateReader(@PathVariable Long id, 
                             @Valid @ModelAttribute Reader reader, 
                             BindingResult result, 
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "reader_form";
        }
        
        try {
            readerService.update(id, reader);
            redirectAttributes.addFlashAttribute("successMessage", "Lector actualizado exitosamente");
            return "redirect:/readers";
        } catch (Exception e) {
            result.rejectValue("name", "error.reader", e.getMessage());
            return "reader_form";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String deleteReader(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            readerService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Lector eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el lector: " + e.getMessage());
        }
        return "redirect:/readers";
    }
}