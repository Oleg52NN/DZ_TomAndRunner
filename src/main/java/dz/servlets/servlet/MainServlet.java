package dz.servlets.servlet;

import dz.servlets.controller.PostController;
import dz.servlets.repository.PostRepository;
import dz.servlets.service.PostService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServlet extends HttpServlet {

    private PostController controller;
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String DELETE = "DELETE";
    private static final String PATH = "/api/posts";
    private static final String PATH_REGULAR = "/api/posts/\\d+";
    private static final String SEP = "/";

    @Override
    public void init() {
        final var context = new AnnotationConfigApplicationContext("dz.servlets");
        //final var repository = new PostRepository();
        //final var service = new PostService(repository);
        controller = context.getBean(PostController.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            if (method.equals(GET) && path.equals(PATH)) {
                controller.all(resp);
                return;
            }
            if (method.equals(GET) && path.matches(PATH_REGULAR)) {
                final var id = Long.parseLong(path.substring(path.lastIndexOf(SEP) + 1));
                controller.getById(id, resp);
                return;
            }
            if (method.equals(POST) && path.equals(PATH)) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (method.equals(DELETE) && path.matches(PATH_REGULAR)) {
                final var id = Long.parseLong(path.substring(path.lastIndexOf(SEP) + 1));
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
