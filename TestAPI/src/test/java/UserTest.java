import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.ResponseAwareMatcher.*;



public class UserTest {

    static String  Base = "https://gorest.co.in/public-api/";

    @Test (priority = 1)
    public void getUserListAPI(){

        RestAssured.baseURI=Base+"users";
        RestAssured.given()
                .when()
                .get()
                .then()
                .assertThat()
                .log()
                .all()
                .statusCode(200);

    }

    @Test (priority = 2)
    public void getUserSpecificUser(){

        RestAssured.baseURI=Base+"users/35";
        Response response = RestAssured.given().header("Content-Type","application/json").when().get();

        System.out.println(response.getBody().asString());
        System.out.println("statusCode"+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);


    }

    @Test (priority = 3)
    public void createUserSpecificUser(){

        HeaderConfig headerConfig = new HeaderConfig();
        String JsonBody = "{\"name\":\"Tenali "+Math.random()+" \", \"gender\":\"Male\", \"email\":\"tenali.ramakrishna@"+Math.random()+".com\", \"status\":\"Active\"}";

        RestAssured.baseURI=Base+"users";
        Response response = RestAssured.given().headers(headerConfig.defaultHeaderswithToken()).body(JsonBody).when().post();

        System.out.println(response.getBody().asString());
        System.out.println("statusCode"+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test (priority = 4)
    public void updateUserSpecificUser(){

        HeaderConfig headerConfig = new HeaderConfig();
        String JsonBody = "{\"name\":\"Tenali "+Math.random()+" \", \"gender\":\"Male\", \"email\":\"tenali.ramakrishna@"+Math.random()+".com\", \"status\":\"Active\"}";

        RestAssured.baseURI=Base+"users/1716";
        Response response = RestAssured.given().headers(headerConfig.defaultHeaderswithToken()).body(JsonBody).when().put();

        System.out.println(response.getBody().asString());
        System.out.println("statusCode"+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath jsonPath = JsonPath.from(response.getBody().asString());
        String actualString = jsonPath.getString("data.name");
        Assert.assertTrue(actualString.contains("Tenali"));

    }

}
