package br.com.springmongodb.repository;

import br.com.springmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {


}
