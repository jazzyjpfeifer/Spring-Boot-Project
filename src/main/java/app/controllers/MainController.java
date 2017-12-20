package app.controllers;

import app.entity.Post;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PostRepository postRepository;

    @GetMapping(value = "/")
    public String index(Model model) {

        List<Post> posts = postRepository.findTop5ByOrderByDatePostedDesc();

        model.addAttribute("posts", posts);

        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
