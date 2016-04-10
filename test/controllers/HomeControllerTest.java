package controllers;

import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.*;

public class HomeControllerTest extends WithApplication {

    HomeController controller;

    @Before
    public void setUp() throws Exception {
        controller = new HomeController();
    }

    @Test
    public void shouldReturnHostname() {
        Result result = route(new Http.RequestBuilder());

        assertThat(result.status(), is(OK));
        assertThat(contentAsString(result), allOf(
                containsString("\"ip\":\"127.0.0.1\""),
                containsString("\"hostname\":\"localhost\"")));
    }

    @Test
    public void shouldReturnIpWhenThereIsNoHostname() {
        Result result = route(new Http.RequestBuilder().remoteAddress("127.0.0.2"));

        assertThat(result.status(), is(OK));
        assertThat(contentAsString(result), allOf(
                containsString("\"ip\":\"127.0.0.2\""),
                containsString("\"hostname\":\"127.0.0.2\"")));
    }

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .build();
    }
}