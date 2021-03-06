import com.nisumlatam.assignment.AssignmentApplication;
import com.nisumlatam.assignment.domain.Message;
import com.nisumlatam.assignment.listener.MessageSaveMongoEventListener;
import com.nisumlatam.assignment.repository.MessageRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@SpringBootTest(
        classes = AssignmentApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class StepDefAssignment {
    String messageToPost;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MessageSaveMongoEventListener messageSaveMongoEventListener;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    EmbeddedQpidBroker broker;

    @Given("^i have a message to post$")
    public void i_have_a_message_to_post() {
        messageToPost = "a_message_to_post";
    }

    @Given("^rabbitmq in-queue is configured$")
    public void rabbitmq_in_queue_is_configured() throws Exception {
        broker = new EmbeddedQpidBroker();
        broker.start();
    }

    @Given("^listener is up and running$")
    public void listener_is_up_and_running() {
        assertNotNull(messageSaveMongoEventListener);
    }

    @Given("^my spring boot app is up and running$")
    public void my_spring_boot_app_is_up_and_running() {
        assertNotNull(webApplicationContext);
    }

    @When("^I trigger a post endpoint$")
    public void i_trigger_a_post_endpoint() {
        String data = messageToPost;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> request = new HttpEntity<>(data, headers);
        String url = "/messages";
        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }

    @Then("^I should see message posted to rabbitmq in-queue$")
    public void i_should_see_message_posted_to_rabbitmq_in_queue() {
        // unknown how to check message into queue without pull it
    }

    @And("^I should see message saved to mongodb$")
    public void i_should_see_message_saved_to_mongodb() {
        assertSame(messageRepository.findAll().size(), 1);
    }

    @Given("^i have a mongo event listener configured$")
    public void i_have_a_mongo_event_listener_configured() {
        assertNotNull(messageSaveMongoEventListener);
    }

    @When("^message saved into mongo$")
    public void message_saved_into_mongo() {
        Message message = new Message().setMessage("a_message");
        messageRepository.save(message);
    }

    @Then("^I should see mongo listener picks the message$")
    public void i_should_see_mongo_listener_picks_the_message() {
    }

    @Then("^post to rabbitmq out-queue$")
    public void post_to_rabbitmq_out_queue() {
        broker.stop();
    }
}
