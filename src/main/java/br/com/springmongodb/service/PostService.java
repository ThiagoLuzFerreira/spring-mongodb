package br.com.springmongodb.service;

import br.com.springmongodb.domain.Post;
import br.com.springmongodb.repository.PostRepository;
import br.com.springmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> findAll(){
        return repo.findAll();
    }

    public Optional<Post> findById(String id){
        Optional<Post> user = repo.findById(id);
        if(user.isEmpty()){
            throw new ObjectNotFoundException("Object not found");
        } else {
            return user;
        }
    }
}
