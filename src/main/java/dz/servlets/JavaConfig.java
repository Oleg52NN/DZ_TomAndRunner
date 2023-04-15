package dz.servlets;

import dz.servlets.controller.PostController;
import dz.servlets.repository.PostRepository;
import dz.servlets.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("dz.servlets")
public class JavaConfig {
    @Bean
    public PostController postController(PostService service) {
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }
}
