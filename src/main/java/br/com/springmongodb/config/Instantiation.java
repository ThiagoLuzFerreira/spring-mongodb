package br.com.springmongodb.config;

import br.com.springmongodb.domain.Post;
import br.com.springmongodb.domain.User;
import br.com.springmongodb.dto.AuthorDTO;
import br.com.springmongodb.dto.CommentDTO;
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

    @Autowired
    private PostRepository postRepositoty;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepositoty.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("28/11/2018"), "Partiu viagem" , "Vou viajar para São Paulo", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia" , "Acordei feliz hoje", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Bom dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

        post1.getCommentList().addAll(Arrays.asList(c1, c2));
        post2.getCommentList().addAll(Arrays.asList(c3));

        postRepositoty.saveAll(Arrays.asList(post1, post2));

        maria.getPostList().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);

    }
}
