package example.models.blog;

import com.geniussports.soy.annotations.Soy;

import java.util.List;

@Soy
public class BlogPostsPage {

    private final String id;

    private final List<BlogPost> posts;

    public BlogPostsPage(String id, List<BlogPost> posts) {
        this.id = id;
        this.posts = posts;
    }

    @Soy.Method("Id")
    public String getId() {
        return id;
    }

    @Soy.Method("Posts")
    public List<BlogPost> getPosts() {
        return posts;
    }
}
