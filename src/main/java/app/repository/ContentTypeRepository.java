package app.repository;

import app.entity.ContentType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ContentTypeRepository extends CrudRepository<ContentType, Integer>{

    public List<ContentType> findAllByOrderBySequence();

}
