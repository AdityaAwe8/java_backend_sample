package levvel.io.controller;

import levvel.io.model.Blog;
import levvel.io.model.Comment;
import levvel.io.service.BlogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private BlogServiceImpl blogServiceImpl;

    @PostMapping("/post")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
        blogServiceImpl.addBlog(blog);
        return ResponseEntity.ok().body(blog);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable("id") String id) {
        Blog blog = blogServiceImpl.getBlog(id);
        return ResponseEntity.ok().body(blog);
    }

    @PostMapping("/post/{id}/comment")
    public ResponseEntity<Comment> addNewComment(@RequestBody Comment com, @PathVariable("id") String id) {
        Blog blog = blogServiceImpl.getBlog(id);
        if (blog == null)
        {
            blog = new Blog(id);
            blogServiceImpl.addBlog(blog);
        }

        List<Comment> commentsList = blog.getAllComments();

        if (commentsList == null)
            commentsList = new ArrayList<>();

        commentsList.add(com);

        return ResponseEntity.ok().body(com);
    }

    @GetMapping("/post/{id}/comment")
    public ResponseEntity<List<Comment>> getCommentsFromId(@PathVariable("id") String id) {
        Blog blog = blogServiceImpl.getBlog(id);
        if (blog == null)
        {
            return ResponseEntity.ok().body(new ArrayList<Comment>());
        }

        List<Comment> allComments = blog.retrieveAllComments();
        return ResponseEntity.ok().body(allComments);
    }
}
