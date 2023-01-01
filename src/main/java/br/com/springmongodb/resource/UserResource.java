package br.com.springmongodb.resource;

import br.com.springmongodb.domain.User;
import br.com.springmongodb.dto.UserDTO;
import br.com.springmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        Optional<User> obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO objDTO){
        User obj = service.insert(service.fromDTO(objDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/saveall")
    public ResponseEntity<Iterable<User>> insertAll(@RequestBody List<UserDTO> objsDTO){
        service.insertAll(service.fromDTOList(objsDTO));
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
