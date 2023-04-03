package dz.servlets.repository;

import dz.servlets.model.Post;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
    AtomicLong idCounter;
    ConcurrentHashMap<Long, String> postMap;

    public PostRepository() {
        idCounter = new AtomicLong(1);
        postMap = new ConcurrentHashMap<Long, String>();
    }

    public ConcurrentHashMap all() {
        return postMap;
    }

    public Optional<Post> getById(long id) {
        return Optional.of(new Post(id, postMap.get(id)));
    }

    public Post save(Post post) {

        long valId = post.getId();

        if (valId != 0) {
            if (valId > idCounter.get() + 1) {
                valId = idCounter.get();
                idCounter.incrementAndGet();
            }
            postMap.put(valId, post.getContent());
            return post;
        } else {
            postMap.put(idCounter.get(), post.getContent());
            idCounter.incrementAndGet();
        }
        return post;
    }

    public void removeById(long id) {
        if (!postMap.containsKey(id)) {
            return;
        }
        postMap.remove(id);
    }
}
