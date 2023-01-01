package br.com.springmongodb.service;

import br.com.springmongodb.domain.User;
import br.com.springmongodb.dto.UserDTO;
import br.com.springmongodb.repository.UserRepository;
import br.com.springmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public Optional<User> findById(String id){
        Optional<User> user = repo.findById(id);
        if(user.isEmpty()){
            throw new ObjectNotFoundException("Object not found");
        } else {
            return user;
        }
    }

    public User insert(User obj){
        return repo.insert(obj);
    }

    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
}
