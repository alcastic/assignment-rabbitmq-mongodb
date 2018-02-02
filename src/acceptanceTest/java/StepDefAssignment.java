import com.nisumlatam.assignment.AssignmentApplication;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = AssignmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class StepDefAssignment {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Given("^i have a message to post$")
    public void i_have_a_message_to_post() {
    }

    @Given("^rabbitmq in-queue is configured$")
    public void rabbitmq_in_queue_is_configured() {
    }

    @Given("^listener is up and running$")
    public void listener_is_up_and_running() {
    }

    @Given("^my spring boot app is up and running$")
    public void my_spring_boot_app_is_up_and_running() {
    }

    @When("^I trigger a post endpoint$")
    public void i_trigger_a_post_endpoint() {
    }

    @Then("^I should see message posted to rabbitmq in-queue$")
    public void i_should_see_message_posted_to_rabbitmq_in_queue() {
    }

    @Then("^I should see message saved to mongodb$")
    public void i_should_see_message_saved_to_mongodb() {
    }

    @Given("^i have a mongo event listener configured$")
    public void i_have_a_mongo_event_listener_configured() {
    }

    @When("^message saved into mongo$")
    public void message_saved_into_mongo() {
    }

    @Then("^I should see mongo listener picks the message$")
    public void i_should_see_mongo_listener_picks_the_message() {
    }

    @Then("^post to rabbitmq out-queue$")
    public void post_to_rabbitmq_out_queue() {
    }
}
