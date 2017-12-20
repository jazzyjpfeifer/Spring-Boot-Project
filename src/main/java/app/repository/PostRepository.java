package app.repository;

import app.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    List<Post> findTop5ByOrderByDatePostedDesc();

}
