package example.models.blog;

import com.geniussports.soy.annotations.Soy;
import example.models.User;

import java.util.List;

@Soy
public class Comment {

    public final String id;
    public final User user;
    public final Long timestamp;
    public final List<Comment> comments;
    public final Votes votes;


    public Comment(String id, User user, Long timestamp, List<Comment> comments, Votes votes) {
        this.id = id;
        this.user = user;
        this.timestamp = timestamp;
        this.comments = comments;
        this.votes = votes;
    }

    @Soy.Method("Id")
    public String getId() {
        return id;
    }

    @Soy.Method("User")
    public User getUser() {
        return user;
    }

    @Soy.Method("Timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @Soy.Method("Comments")
    public List<Comment> getComments() {
        return comments;
    }

    @Soy.Method("Votes")
    public Votes getVotes() {
        return votes;
    }
}
