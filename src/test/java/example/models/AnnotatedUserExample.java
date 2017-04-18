package example.models;

import com.geniussports.soy.annotations.Soy;

@Soy
public class AnnotatedUserExample extends AbstractUser {

    @Soy.Field("HomePage")
    public final String HomePage;

    public AnnotatedUserExample(String homePage, String name, String email, Integer id) {
        super(name, email, id);
        this.HomePage = homePage;
    }

    @Soy.Method("Name")
    public String getName() {
        return name;
    }

    @Soy.Method("Email")
    public String getEmail() {
        return email;
    }

    @Soy.Method("Id")
    public Integer getId() {
        return id;
    }
}
