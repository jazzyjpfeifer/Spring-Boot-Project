package app.controllers;

import app.entity.ContentType;
import app.entity.Post;
import app.entity.PostDetail;
import app.repository.ContentTypeRepository;
import app.repository.PostDetailRepository;
import app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/post_details")
public class PostDetailsController {

    @Autowired
    PostDetailRepository postDetailRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ContentTypeRepository contentTypeRepository;

    @Autowired
    private Environment env;


    @GetMapping("/show")
    public String showPostDetailsByPostId(@RequestParam("postId") int post_id, Model model) {

        Post post = postRepository.findOne(post_id);

        post.getPostdetails();

        model.addAttribute("post", post);

        return "post_details/post_details_show";
    }

    @GetMapping(value = "/add")
    public String showPostDetailsForm(@RequestParam("postId") int post_id, Model model) {

        Post post = postRepository.findOne(post_id);

        PostDetail newPostDetail = new PostDetail();

        model.addAttribute("post", post);
        model.addAttribute("postdetails", newPostDetail);
        model.addAttribute("allTypes", contentTypeRepository.findAll());

        return "post_details/post_details_add";

    }

    @PostMapping(value = "/save")
    public String savePostDetails(RedirectAttributes redirectAttributes,
                                  @ModelAttribute("postdetail") PostDetail postDetail,
                                  @RequestParam("postId") int post_id,
                                  @RequestParam("content_id") int content_id,
                                  @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                String filename = file.getOriginalFilename();
                String directory = env.getProperty("upload.directory");
                String filepath = Paths.get(directory, filename).toString();

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String image_name = file.getOriginalFilename();

        //Saving original file name to db
        if(file.isEmpty()) {
            postDetail.setImage(null);
        } else {
            postDetail.setImage(file.getOriginalFilename());
        }

        //Getting Post by ID
        Post post = postRepository.findOne(post_id);

        //Getting Content by ID
        ContentType contentType = contentTypeRepository.findOne(content_id);

        postDetail.setContentType(contentType);

        //Assigning post to post_detail
        post.add(postDetail);

        //Saving post_details
        postDetailRepository.save(postDetail);

        redirectAttributes.addAttribute("postId", post_id);

        return "redirect:/post_details/show";
    }

    //Need to fix this edit tag

    @GetMapping(value = "/edit")
    public String editPostDetailById(@RequestParam("post_detail_id") int post_detail_id,
                                     @RequestParam("postId") int post_id,
                                     Model model) {

        model.addAttribute("post", postRepository.findOne(post_id));
        model.addAttribute("postdetails", postDetailRepository.findOne(post_detail_id));
        model.addAttribute("allTypes", contentTypeRepository.findAll());

        return "post_details/post_details_add";
    }

    @GetMapping(value = "/delete")
    public String deletePostDetailById(RedirectAttributes redirectAttributes,
                                       @RequestParam("post_detail_id") int id,
                                       @RequestParam("postId") int post_id,
                                       @ModelAttribute("post") Post post) {

        System.out.println("Deleting record...");

        postDetailRepository.delete(id);

        System.out.println("Record was removed successfully form the database");

        redirectAttributes.addAttribute("postId", post_id);

        return "redirect:/post_details/show";
    }




}
