package app.controllers;

import app.entity.Category;
import app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public String showCategories(Model model) {

        List<Category> categories = categoryRepository.findAllByOrderBySequence();

        model.addAttribute("categories", categories);

        return "categories/categories";

    }

    @GetMapping("/add")
    public String addCategory(Model model) {

        Category category = new Category();

        model.addAttribute("category", category);

        return "categories/category_add";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {

        categoryRepository.save(category);

        System.out.println(category);

        System.out.println("Category was saved to the database successfully");

        return "redirect:/category";
    }

    @GetMapping("/edit")
    public String editCategoryById(@RequestParam("id") int id, Model model) {

        model.addAttribute("category", categoryRepository.findOne(id));

        return "categories/category_add";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("id") int id) {

        System.out.println("Deleting record...");

        categoryRepository.delete(id);

        System.out.println("Record was removed succesfully from the database");

        return "redirect:/category";

    }


}
