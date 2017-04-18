package example.models.blog;

import com.geniussports.soy.annotations.Soy;

import java.util.List;

@Soy
public class BlogPost {

    private final String id;
    private final String content;
    private final List<Comment> comments;

    public BlogPost(String id, String content, List<Comment> comments) {
        this.id = id;
        this.content = content;
        this.comments = comments;

    }

    @Soy.Method("Id")
    public String getId() {
        return id;
    }

    @Soy.Method("Content")
    public String getContent() {
        return content;
    }

    @Soy.Method("Comments")
    public List<Comment> getComments() {
        return comments;
    }
}
