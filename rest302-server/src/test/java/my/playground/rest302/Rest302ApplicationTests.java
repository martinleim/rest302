package my.playground.rest302;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.web.client.RestTemplate;

@SpringApplicationConfiguration(classes = Rest302Application.class)
@WebIntegrationTest("server.port=9000")
public class Rest302ApplicationTests {

    private static final String TO_NONEXISTING_TARGET = "http://localhost:9000/redirect/nonexisting";

    private static final String TO_EXISTING_TARGET = "http://localhost:9000/redirect/static";

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void spring_redirectGetToExistingTarget() {
        ResponseEntity<String> entity = restTemplate.getForEntity(TO_EXISTING_TARGET, String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity, notNullValue());
    }

    @Test
    public void spring_redirectGetToNonExistingTarget() {
        ResponseEntity<String> entity = restTemplate.getForEntity(TO_NONEXISTING_TARGET, String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void spring_dontRedirectPostToExistingTarget() {
        ResponseEntity<String> entity = restTemplate.postForEntity(TO_EXISTING_TARGET, null, String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.FOUND));
    }

    @Test
    public void spring_dontRedirectPostToNonExistingTarget() {
        ResponseEntity<String> entity = restTemplate.postForEntity(TO_NONEXISTING_TARGET, null, String.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.FOUND));
    }

}
