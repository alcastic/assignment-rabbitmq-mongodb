import com.nisumlatam.assignment.AssignmentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = AssignmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class SpringIntegrationTest {

    @Autowired
    protected TestRestTemplate template;

    protected ResponseEntity<String> executeGet(String path) {
        return template.getForEntity(path, String.class);
    }
}