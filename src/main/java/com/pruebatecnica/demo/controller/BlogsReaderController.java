package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.BlogsReader;
import com.pruebatecnica.demo.service.BlogService;
import com.pruebatecnica.demo.service.BlogsReaderService;
import com.pruebatecnica.demo.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blogs-readers")
public class BlogsReaderController {

    @Autowired
    private BlogsReaderService blogsReaderService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private BlogService blogService;

    // ✅ Listado principal
    @GetMapping
    public String listBlogsReaders(Model model) {
        model.addAttribute("blogsReaders", blogsReaderService.findAll());
        return "blogs-readers"; // Usa blogs-readers.html
    }

    // ✅ Formulario de creación
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("blogsReader", new BlogsReader());
        model.addAttribute("readers", readerService.findAll());
        model.addAttribute("blogs", blogService.findAll());
        return "blogreader_form";
    }

    // ✅ Guardar asociación
    @PostMapping
    public String saveBlogsReader(@RequestParam Long readerId,
                                 @RequestParam Long blogId,
                                 RedirectAttributes redirectAttributes) {
        try {
            blogsReaderService.createAssociation(readerId, blogId);
            redirectAttributes.addFlashAttribute("successMessage", "Asociación creada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al crear la asociación: " + e.getMessage());
            return "redirect:/blogs-readers/new";
        }
        return "redirect:/blogs-readers";
    }

    // ✅ Eliminar asociación
    @GetMapping("/delete/{id}")
    public String deleteBlogsReader(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            blogsReaderService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Asociación eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la asociación: " + e.getMessage());
        }
        return "redirect:/blogs-readers";
    }
}
