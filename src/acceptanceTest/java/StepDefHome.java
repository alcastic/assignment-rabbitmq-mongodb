import com.nisumlatam.assignment.AssignmentApplication;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(
        classes = AssignmentApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class StepDefHome {
    ResponseEntity<String> response;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    protected TestRestTemplate template;

    @Given("my spring boot app is running")
    public void my_spring_boot_app_is_running() {
        assertNotNull(webApplicationContext);
    }

    @When("^client calls \"([^\"]*)\"$")
    public void client_calls(String path) {
        response = template.getForEntity(path, String.class);
    }

    @Then("^client resive the message \"([^\"]*)\"$")
    public void client_resive_the_message(String expected) {
        String result = response.getBody().toString();
        assertEquals(result, expected);
    }
}
