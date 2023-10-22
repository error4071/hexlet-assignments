package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Test
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testListArticles1() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts").asString();
        String body = response.getBody();

        assertEquals(200, response.getStatus());
        assertThat(body, containsString("Ring of Bright Water"));
        assertThat(body, containsString("Sleep the Brave"));
        assertThat(body, is(not(containsString("The Way of All Flesh"))));
        assertThat(body, containsString("?page=2"));
    }

    @Test
    void testListArticles2() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts?page=2").asString();
        String body = response.getBody();

        assertEquals(200, response.getStatus());
        assertThat(body, containsString("The Way of All Flesh"));
        assertThat(body, containsString("That Hideous Strength"));
        assertThat(body, is(not(containsString("The Green Bay Tree"))));
        assertThat(body, is(not(containsString("Time of our Darkness"))));
        assertThat(body, containsString("?page=1"));
        assertThat(body, containsString("?page=3"));
    }

    @Test
    void testShowArticle() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts/9").asString();
        String body = response.getBody();

        assertEquals(200, response.getStatus());
        assertThat(body, containsString("Ring of Bright Water"));
        assertThat(body, containsString("Iste veniam ullam rerum facere."));
    }

    @Test
    void testPostNotFound() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts/999").asString();
        assertEquals(404, response.getStatus());
    }
}