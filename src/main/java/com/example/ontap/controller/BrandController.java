package com.example.ontap.controller;

import com.example.ontap.entity.Brand;
import com.example.ontap.repository.IBrandRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brand")
public class BrandController {

    private final IBrandRepository brandRepository;

    public BrandController(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    public String listBrands(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        return "brand-list";
    }


}
