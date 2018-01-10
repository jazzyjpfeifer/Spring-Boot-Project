package app.repository;

import app.entity.Post;
import app.entity.PostDetail;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostDetailRepository extends CrudRepository<PostDetail, Integer> {

    List<PostDetail> findAllByPostOrderBySequence(Post post);






}
