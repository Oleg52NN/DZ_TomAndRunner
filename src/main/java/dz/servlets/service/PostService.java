package dz.servlets.service;

import dz.servlets.exception.NotFoundException;
import dz.servlets.model.Post;
import dz.servlets.repository.PostRepository;

import java.util.concurrent.ConcurrentHashMap;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public ConcurrentHashMap all() {
        return (ConcurrentHashMap) repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

