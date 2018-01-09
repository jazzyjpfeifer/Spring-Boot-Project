package app.repository;

import app.entity.Post;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.expression.Expression;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    List<Post> findTop5ByOrderByDatePostedDesc();

    List<Post> findPostByTitleLike(String title);

    List<Post> findPostByMonthYearPosted(String month_year);

    Long countPostByMonthYearPosted(String month_year);

    Long countPostByTitleLike(String title);

    List<Post> findPostByCategoryId(Integer CategoryId);

    Long countPostsByCategoryId(Integer CategoryId);

    @Query("SELECT DISTINCT p.monthYearPosted FROM Post p")
    List<Post> distinctMonthsYearsPosted();

}
