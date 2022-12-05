package api;

import com.github.javafaker.Faker;
import models.CasesStepsBody;
import models.TestCaseBody;
import io.restassured.RestAssured;


import static specs.LoginSpecs.getRequestSpec;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

public class TestCaseApi {

    public static int createTestCase() {
        Faker faker = new Faker();
        String testCaseName = faker.name().nameWithMiddle();

        TestCaseBody testCaseBody = new TestCaseBody();
        testCaseBody.setName(testCaseName);

        return RestAssured.given(getRequestSpec())
                .body(testCaseBody)
                .queryParam("projectId", "1722")
                .post("/api/rs/testcasetree/leaf")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", is(testCaseName))
                .body("automated", is(false))
                .body("external", is(false))
                .extract()
                .path("id");
    }

    public static void createTestStep(int id, CasesStepsBody payload) {
        RestAssured.given(getRequestSpec())
                .body(payload)
                .post(String.format("/api/rs/testcase/%s/scenario", id))
                .then()
                .log().body()
                .statusCode(200)
                .body("steps", hasSize(2));
    }

}
