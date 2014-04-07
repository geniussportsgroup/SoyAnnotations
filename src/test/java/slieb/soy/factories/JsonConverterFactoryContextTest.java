package slieb.soy.factories;

import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.Lists;
import example.AnnotatedCommentExample;
import example.AnnotatedPostExample;
import example.AnnotatedUserExample;
import org.junit.Before;
import org.junit.Test;
import slieb.soy.converters.ClassConverter;
import slieb.soy.converters.JsonObjectConverter;
import slieb.soy.factories.json.JsonConverterFactoryContext;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class JsonConverterFactoryContextTest {

    private JsonConverterFactoryContext converterFactoryContext;

    @Before
    public void setup() {
        converterFactoryContext = new JsonConverterFactoryContext();
    }

    private void assertUserEquals(AnnotatedUserExample userExample, Object userObject) {
        if (userExample != null) {
            assertTrue(userObject instanceof Map);
            assertEquals(userExample.getName(), ((Map) userObject).get("Name"));
            assertEquals(userExample.getEmail(), ((Map) userObject).get("Email"));
            assertEquals(userExample.HomePage, ((Map) userObject).get("HomePage"));
            assertEquals(userExample.getId(), ((Map) userObject).get("Id"));
        } else {
            assertNull(userObject);
        }
    }

    private void assertPostEquals(AnnotatedPostExample postExample, Object postObject) {
        if (postExample != null) {
            assertTrue(postObject instanceof Map);
            assertUserEquals(postExample.user, ((Map) postObject).get("User"));
            assertCommentsEquals(postExample.comments, ((Map) postObject).get("Comments"));
        } else {
            assertNull(postObject);
        }
    }

    private void assertCommentsEquals(List<AnnotatedCommentExample> commentExamples, Object commentsObject) {
        if (commentExamples != null) {
            assertTrue(commentsObject instanceof List);
            for (int i = 0; i < commentExamples.size(); i++) {
                assertCommentEquals(commentExamples.get(i), ((List) commentsObject).get(i));
            }
        } else {
            assertNull(commentsObject);
        }
    }

    private void assertCommentEquals(AnnotatedCommentExample commentExample, Object commentPost) {
        if (commentExample != null) {
            assertTrue(commentPost instanceof Map);
            assertUserEquals(commentExample.user, ((Map) commentPost).get("User"));
            assertEquals(commentExample.id, ((Map) commentPost).get("Id"));
            assertEquals(commentExample.content, ((Map) commentPost).get("Content"));
            assertCommentsEquals(commentExample.comments, ((Map) commentPost).get("Comments"));
        } else {
            assertNull(commentPost);
        }
    }

    @Test
    public void testCreateConverter() throws Exception {
        assertTrue(converterFactoryContext.create(String.class) instanceof JsonObjectConverter);
        assertTrue(converterFactoryContext.create(Object.class) instanceof JsonObjectConverter);
        assertTrue(converterFactoryContext.create(AnnotatedUserExample.class) instanceof ClassConverter);
    }

    @Test
    public void testCreateConverterForUserAndConverterUser() {
        AnnotatedUserExample userExample = new AnnotatedUserExample("domain.com", "John", "john@domain.com", 123);
        Converter<Object, ?> converter = converterFactoryContext.getClassConverterFactory().create(AnnotatedUserExample.class);
        Object object = converter.convert(userExample);
        assertUserEquals(userExample, object);
    }

    @Test
    public void testCreateConverterForPost() {
        AnnotatedPostExample postExample = new AnnotatedPostExample();
        postExample.user = new AnnotatedUserExample("domain.com", "John", "john@domain.com", 123);
        Converter<Object, ?> converter = converterFactoryContext.getClassConverterFactory().create(AnnotatedPostExample.class);
        Object post = converter.convert(postExample);
        assertPostEquals(postExample, post);
    }

    @Test
    public void testCreateConverterForPostWithComments() {
        AnnotatedPostExample postExample = new AnnotatedPostExample();
        postExample.user = new AnnotatedUserExample("domain.com", "John", "john@domain.com", 123);
        AnnotatedCommentExample comment1, comment2, comment3;
        comment1 = new AnnotatedCommentExample(1, postExample.user, "Hello", null);
        comment2 = new AnnotatedCommentExample(2, postExample.user, "Hello There", Lists.newArrayList(comment1));
        comment3 = new AnnotatedCommentExample(3, postExample.user, "Hello guys", Lists.newArrayList(comment2));
        postExample.comments = Lists.newArrayList(comment3);
        Converter<Object, ?> converter = converterFactoryContext.getClassConverterFactory().create(AnnotatedPostExample.class);
        Object post = converter.convert(postExample);
        assertPostEquals(postExample, post);
    }

}