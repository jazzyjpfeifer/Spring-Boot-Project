package app.controllers;

import app.entity.Category;
import app.entity.Post;
import app.repository.CategoryRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping()
    public String showAllPosts (Model model) {

        model.addAttribute("posts", postRepository.findAll());

        return "/posts/posts";
    }

    @GetMapping(value = "/add")
    public String showAddPostForm(Model model) {
        Post newPost = new Post();

        List<Category> categories = categoryRepository.findAllByOrderBySequence();

        model.addAttribute("allCategories", categories);

        model.addAttribute("post", newPost);

        return "/posts/post_add";

    }

    @PostMapping(value = "/save")
    public String savePost(@ModelAttribute("post") Post post, @RequestParam("category") int category_id) {

        System.out.println("Saving new record...");

        Category category = categoryRepository.findOne(category_id);

        post.setCategory(category);

        LocalDate todaysdate = LocalDate.now();

        //Setting Start Month and Year for date range searches
        String startDate = String.valueOf(todaysdate.withDayOfMonth(1));

        //Formatting text and then concatenate for month

        String posted_month1 = todaysdate.getMonth().name().substring(0,1).toUpperCase();
        String posted_month2 = todaysdate.getMonth().name().substring(1).toLowerCase();
        String posted_month = posted_month1 + posted_month2;

        Integer posted_year = todaysdate.getYear();

        //Setting End Month and Year for date range searches
        String endDate = String.valueOf(todaysdate.withDayOfMonth(31));

        post.setMonthPosted(posted_month);

        post.setYearPosted(posted_year);

        post.setMonthYearPosted(posted_month + ' ' + posted_year);

        // save the customer using our service
        postRepository.save(post);

        System.out.println("Post was saved to the database successfully!");

        return "redirect:/posts";
    }

    @GetMapping(value = "/edit")
    public String editRoleById(@RequestParam("postId") int id, Model model) {

        List<Category> categories = categoryRepository.findAllByOrderBySequence();

        model.addAttribute("post", postRepository.findOne(id));

        model.addAttribute("allCategories", categories);

        return "/posts/post_add";
    }



    @GetMapping(value = "/delete")
    public String deletePostById(@RequestParam("postId") int id) {

        System.out.println("Deleting record...");

        postRepository.delete(id);

        System.out.println("Record was removed successfully form the database");

        return "redirect:/posts";
    }




}
