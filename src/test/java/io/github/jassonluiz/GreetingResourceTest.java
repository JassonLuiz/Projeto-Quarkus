package io.github.jassonluiz;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }
    
    public void testRedis() {
    	given()
    		.accept(ContentType.JSON)
    		.when()
    		.get("/tarefas")
    		.then()
    		.statusCode(200)
    		.body("size()", is(0));
    	
        given()
       		.accept(ContentType.JSON)
       		.when()
       		.body("{\"key\":\"first-key\",\"value\":0}")
       		.when()
       		.post("/tarefas")
       		.then()
       		.statusCode(200)
       		.body("key", is("first-key"))
       		.body("value", is(0));
    }

    
    
    
    
    
    
    
    
    
    
}