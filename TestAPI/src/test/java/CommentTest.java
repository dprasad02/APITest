import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CommentTest {

    static String  Base = "https://gorest.co.in/public-api/";

    @Test (priority = 1)
    public void getCommentsListAPI(){

        RestAssured.baseURI=Base+"comments";
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
    public void getCommentsSpecificPosts(){

        RestAssured.baseURI=Base+"comments/32";
        Response response = RestAssured.given().header("Content-Type","application/json").when().get();

        System.out.println(response.getBody().asString());
        System.out.println("statusCode"+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);


    }

    @Test (priority = 3)
    public void createCommentsSpecificPosts(){

        HeaderConfig headerConfig = new HeaderConfig();


        RestAssured.baseURI=Base+"comments/32";
        Response response1 = RestAssured.given().header("Content-Type","application/json").when().get();

        System.out.println(response1.getBody().asString());
        System.out.println("statusCode"+response1.getStatusCode());
        Assert.assertEquals(response1.getStatusCode(),200);
        JsonPath jsonPath = JsonPath.from(response1.getBody().asString());
        String userId = jsonPath.getString("data.id");

        RestAssured.baseURI=Base+"comments/32";
        Response response2 = RestAssured.given().header("Content-Type","application/json").when().get();

        JsonPath jsonPath1 = JsonPath.from(response2.getBody().asString());
        String postId = jsonPath1.getString("data.id");


        String JsonBody = "{\n" +
                "    \"id\": "+userId+",\n" +
                "    \"post_id\": "+postId+",\n" +
                "    \"name\": \"Sushma Nair\",\n" +
                "    \"email\": \"sushma_nair@runolfsdottir.co\",\n" +
                "    \"body\": \"Illum et labore. Quasi voluptas iure. Distinctio omnis iusto.\"\n" +
                "  }";

        RestAssured.baseURI=Base+"comments";
        Response response = RestAssured.given().headers(headerConfig.defaultHeaderswithToken()).body(JsonBody).when().post();

        System.out.println(response.getBody().asString());
        System.out.println("statusCode"+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
