package app.controllers;

import app.entity.Category;
import app.entity.Post;
import app.repository.CategoryRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = "/")
    public String index(Model model) {

        List<Post> posts = postRepository.findTop5ByOrderByDatePostedDesc();

        List<Category> categories = categoryRepository.findAllByOrderBySequence();

        List<Post> archives = postRepository.distinctDates();

        model.addAttribute("categories", categories);

        model.addAttribute("archives", archives);

        model.addAttribute("posts", posts);


        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/archives")
    public String archives() {
        return "archives";
    }

}
