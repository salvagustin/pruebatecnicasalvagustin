package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.Blog;
import com.pruebatecnica.demo.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    
    @Autowired
    private BlogService blogService;
    
    @GetMapping
    public String listBlogs(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "list_blog";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog_form";
    }
    
    @PostMapping
    public String saveBlog(@Valid @ModelAttribute Blog blog, 
                          BindingResult result, 
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "blog_form";
        }
        
        try {
            if (blogService.existsByTitle(blog.getTitle())) {
                result.rejectValue("title", "error.blog", "Ya existe un blog con este título");
                return "blog_form";
            }
            
            blogService.save(blog);
            redirectAttributes.addFlashAttribute("successMessage", "Blog creado exitosamente");
            return "redirect:/blogs";
        } catch (Exception e) {
            result.rejectValue("title", "error.blog", e.getMessage());
            return "blog_form";
        }
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Blog no encontrado");
            return "redirect:/blogs";
        }
        
        model.addAttribute("blog", blog.get());
        return "blog_form";
    }
    
    @PostMapping("/update/{id}")
    public String updateBlog(@PathVariable Long id, 
                            @Valid @ModelAttribute Blog blog, 
                            BindingResult result, 
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "blog_form";
        }
        
        try {
            blogService.update(id, blog);
            redirectAttributes.addFlashAttribute("successMessage", "Blog actualizado exitosamente");
            return "redirect:/blogs";
        } catch (Exception e) {
            result.rejectValue("title", "error.blog", e.getMessage());
            return "blog_form";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            blogService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Blog eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el blog: " + e.getMessage());
        }
        return "redirect:/blogs";
    }
}