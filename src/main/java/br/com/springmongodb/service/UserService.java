package br.com.springmongodb.service;

import br.com.springmongodb.domain.User;
import br.com.springmongodb.dto.UserDTO;
import br.com.springmongodb.repository.UserRepository;
import br.com.springmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Iterable<User> insertAll(List<User> objs){
        return repo.saveAll(objs);
    }

    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj, String id){
        obj.setId(id);
        Optional<User> user = findById(obj.getId());
        updateData(user, obj);
        return repo.save(user.orElse(null));
    }

    private void updateData(Optional<User> user, User obj) {
        user.get().setName(obj.getName());
        user.get().setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }

    public List<User> fromDTOList(List<UserDTO> objsDTO){
        return objsDTO.stream().map(x -> new User(x.getId(), x.getName(), x.getEmail())).collect(Collectors.toList());
    }
}
