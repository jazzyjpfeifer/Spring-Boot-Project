package app.repository;

import app.entity.Post;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    List<Post> findTop5ByOrderByDatePostedDesc();

    List<Post> findPostByTitleLike(String title);

    Long countPostByTitleLike(String title);

    List<Post> findPostByCategoryId(Integer CategoryId);

    Long countPostsByCategoryId(Integer CategoryId);

    @Query("SELECT DISTINCT p.datePosted FROM Post p")
    List<Post> distinctDates();

    List<Post> findPostByDatePostedBetween(Timestamp startDate, Timestamp endDate);

}
