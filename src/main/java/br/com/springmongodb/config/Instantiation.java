package br.com.springmongodb.config;

import br.com.springmongodb.domain.Post;
import br.com.springmongodb.domain.User;
import br.com.springmongodb.repository.PostRepository;
import br.com.springmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    private PostRepository postRepositoty;

    public Instantiation(PostRepository postRepositoty){
        this.postRepositoty = postRepositoty;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        postRepositoty.deleteAll();

        Post post1 = new Post(null, sdf.parse("28/11/2018"), "Partiu viagem" , "Vou viajar para São Paulo", maria);
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia" , "Acordei feliz hoje", maria);

        postRepositoty.saveAll(Arrays.asList(post1, post2));

    }
}
