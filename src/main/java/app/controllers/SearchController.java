package app.controllers;

import app.entity.Category;
import app.entity.Post;
import app.repository.CategoryRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = "search")
public class SearchController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public String search(Model model, @RequestParam("search") String title) {

        List<Category> categories = categoryRepository.findAllByOrderBySequence();

        List<Post> posts = postRepository.findPostByTitleLike("%" + title + "%");

        long count = postRepository.countPostByTitleLike("%" + title + "%");

        List<Post> archives = postRepository.distinctDates();

        model.addAttribute("posts", posts);

        model.addAttribute("categories", categories);

        model.addAttribute("archives", archives);

        model.addAttribute("count", count);

        return "search/search";
    }

    @GetMapping("/archives")
    public String searchByDate(Model model,
                               @RequestParam("startDate") Timestamp startDate,
                               @RequestParam("endDate") Timestamp endDate) {

        List<Category> categories = categoryRepository.findAllByOrderBySequence();

        List<Post> posts = postRepository.findPostByDatePostedBetween(startDate, endDate);

        model.addAttribute("posts", posts);

        model.addAttribute("categories", categories);

        return "search/archives";
    }

    @GetMapping("/category")
    public String category(Model model, @RequestParam("CategoryId") int CategoryId) {

        List<Category> categories = categoryRepository.findAllByOrderBySequence();

        List<Post> posts = postRepository.findPostByCategoryId(CategoryId);

        List<Post> archives = postRepository.distinctDates();

        long count = postRepository.countPostsByCategoryId(CategoryId);

        model.addAttribute("posts", posts);

        model.addAttribute("categories", categories);

        model.addAttribute("archives", archives);

        model.addAttribute("count", count);

        return "search/category";
    }



}
