import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PostTest {

    static String  Base = "https://gorest.co.in/public-api/";

    @Test (priority = 1)
    public void getPostsListAPI(){

        RestAssured.baseURI=Base+"posts";
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
    public void getUserSpecificPosts(){

        RestAssured.baseURI=Base+"posts/32";
        Response response = RestAssured.given().header("Content-Type","application/json").when().get();

        System.out.println(response.getBody().asString());
        System.out.println("statusCode"+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);


    }

    @Test (priority = 3)
    public void createPostSpecificPosts(){

        HeaderConfig headerConfig = new HeaderConfig();


        RestAssured.baseURI=Base+"posts/35";
        Response response1 = RestAssured.given().header("Content-Type","application/json").when().get();

        System.out.println(response1.getBody().asString());
        System.out.println("statusCode"+response1.getStatusCode());
        Assert.assertEquals(response1.getStatusCode(),200);
        JsonPath jsonPath = JsonPath.from(response1.getBody().asString());
        String userId = jsonPath.getString("data.id");


        String JsonBody = "{\n" +
                "    \"user_id\": "+userId+",\n" +
                "    \"title\": \"Deserunt vinum aro copia debeo exercitationem absque synagoga degenero sapiente triginta vitae vis alo dolor repudiandae cubo.\",\n" +
                "    \"body\": \"Vultuosus qui maiores. Quidem corrumpo conscendo. Confugo solio voluntarius. Terminatio surculus defleo. Ago voveo creptio. Quam cicuta copiose. Viriliter deprecator cunae. Coaegresco caveo qui. Occaecati clementia vel. Acquiro clibanus varietas. Nemo ullus cenaculum. Dolore adicio admiratio. Tepidus sumptus taceo. Uredo aestivus velit. Minus cupiditate super. Aequitas atque tutamen. Contigo autem tribuo. Voluptatem auris suppellex. Aeneus rem aegrotatio. Denique defetiscor mollitia. Combibo occaecati aut. Culpo dolorum sol.\"\n" +
                "  }";

        RestAssured.baseURI=Base+"posts";
        Response response = RestAssured.given().headers(headerConfig.defaultHeaderswithToken()).body(JsonBody).when().post();

        System.out.println(response.getBody().asString());
        System.out.println("statusCode"+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
