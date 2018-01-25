import cucumber.api.java.en.Given;

public class MyStepdefs {
    @Given("^I have (\\d+) cukes in my belly$")
    public void I_have_cukes_in_my_belly(int cukes) {
        System.out.print(String.format("Cukes: %n\n", cukes));
    }
}