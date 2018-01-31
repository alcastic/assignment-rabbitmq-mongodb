import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssignmentStepDefs {
    String message = "My Message";

    @Given("^i have a message to post$")
    public void i_have_a_message_to_post() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @Given("^rabbitmq in-queue is configured$")
    public void rabbitmq_in_queue_is_configured() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @Given("^listener is up and running$")
    public void listener_is_up_and_running() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @Given("^my spring boot app is up and running$")
    public void my_spring_boot_app_is_up_and_running() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @When("^I trigger a post endpoint$")
    public void i_trigger_a_post_endpoint() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @Then("^I should see message posted to rabbitmq in-queue$")
    public void i_should_see_message_posted_to_rabbitmq_in_queue() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @Then("^I should see message saved to mongodb$")
    public void i_should_see_message_saved_to_mongodb() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @Given("^i have a mongo event listener configured$")
    public void i_have_a_mongo_event_listener_configured() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @When("^message saved into mongo$")
    public void message_saved_into_mongo() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @Then("^I should see mongo listener picks the message$")
    public void i_should_see_mongo_listener_picks_the_message() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

    @Then("^post to rabbitmq out-queue$")
    public void post_to_rabbitmq_out_queue() throws Exception {
        // Write code here that turns the phrase above into concrete actions
//        throw new RuntimeException();
    }

}
