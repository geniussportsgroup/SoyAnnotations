package example.models;

import com.geniussports.soy.annotations.Soy;

import java.util.List;

@Soy
public class AnnotatedCommentExample {

    @Soy.Field("Id")
    public Integer id;
    @Soy.Field("User")
    public AnnotatedUserExample user;
    @Soy.Field("Content")
    public String content;
    @Soy.Field("Comments")
    public List<AnnotatedCommentExample> comments;

    public AnnotatedCommentExample(Integer id,
                                   AnnotatedUserExample user,
                                   String content,
                                   List<AnnotatedCommentExample> comments) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.comments = comments;
    }

}
