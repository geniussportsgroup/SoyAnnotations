package example.models.blog;

import com.geniussports.soy.annotations.Soy;

@Soy
public class Votes {

    private final String id;

    private final Integer upvotes;

    private final Integer downvotes;

    public Votes(String id, Integer upvotes, Integer downvotes) {
        this.id = id;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    @Soy.Method("Id")
    public String getId() {
        return id;
    }

    @Soy.Method("Upvotes")
    public Integer getUpvotes() {
        return upvotes;
    }

    @Soy.Method("Downvotes")
    public Integer getDownvotes() {
        return downvotes;
    }
}
