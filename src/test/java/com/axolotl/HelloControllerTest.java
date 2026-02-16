import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "spring.datasource.url=jdbc:h2:mem:gateway;DB_CLOSE_ON_EXIT=FALSE"
    }
)
public class HelloControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testHello() {
        when()
            .get("/")
        .then()
            .statusCode(200)
            .body(is("Hello World!"));
    }

    @Test
    void testCalc() {
        given()
            .param("left", 100)
            .param("right", 200)
        .when()
            .get("/calc")
        .then()
            .statusCode(200)
            .body("left", equalTo(100))
            .body("right", equalTo(200))
            .body("answer", equalTo(300));
    }
}
