package app.controllers;

import app.entity.Post;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping()
    public String showAllPosts (Model model) {

        model.addAttribute("posts", postRepository.findAll());

        return "/posts/posts";
    }

    @GetMapping(value = "/add")
    public String showAddPostForm(Model model) {
        Post newPost = new Post();

        model.addAttribute("post", newPost);

        return "/posts/post_add";

    }

    @PostMapping(value = "/save")
    public String savePost(@ModelAttribute("post") Post post) {

        System.out.println("Saving new record...");

        // save the customer using our service
        postRepository.save(post);

        System.out.println("Post was saved to the database successfully!");

        return "redirect:/posts";
    }

    @GetMapping(value = "/edit")
    public String editRoleById(@RequestParam("postId") int id, Model model) {

        model.addAttribute("post", postRepository.findOne(id));

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
