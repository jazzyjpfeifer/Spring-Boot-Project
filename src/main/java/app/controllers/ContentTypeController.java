package app.controllers;

import app.entity.ContentType;
import app.repository.ContentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/content_type")
public class ContentTypeController {

    @Autowired
    ContentTypeRepository contentTypeRepository;

    @GetMapping()
    public String showAllContentTypes (Model model) {

        model.addAttribute("content_types", contentTypeRepository.findAll());

        return "content_type/content_types";
    }

    @GetMapping(value = "/add")
    public String formContentType (Model model) {

        ContentType contentType = new ContentType();

        model.addAttribute("type", contentType);

        return "content_type/content_type_add";
    }

    @PostMapping("/save")
    public String saveContentType(@ModelAttribute("type") ContentType contentType) {

        contentTypeRepository.save(contentType);

        System.out.println("Content Type was saved to the database successfully");

        return "redirect:/content_type";
    }

    @GetMapping("/edit")
    public String editContentTypeById(@RequestParam("id") int id, Model model) {

        model.addAttribute("type", contentTypeRepository.findOne(id));

        return "content_type/content_type_add";
    }

    @GetMapping("/delete")
    public String deleteContentTypeById(@RequestParam("id") int id) {

        System.out.println("Deleting record...");

        contentTypeRepository.delete(id);

        System.out.println("Record was removed succesfully from the database");

        return "redirect:/content_type";

    }


}
