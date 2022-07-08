package Api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ApiTesting {

    int randomUserId = ThreadLocalRandom.current().nextInt(1,10);

    @BeforeSuite
    public void baseUrl()
    {
       RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

    }

    @Test
    public void getUserEmail()
    {
        RestAssured.basePath = "users/"+randomUserId;
       String email = given().contentType(ContentType.JSON).log().all().get().then().extract().path("email");
       System.out.println("UserID is: " +randomUserId);
       System.out.println("User Email is : " +email);
    }

    @Test
    public void validatePostIDs()
    {
        boolean num = false;
        RestAssured.basePath = "posts";
        Response r = given().queryParam("userId",randomUserId).contentType(ContentType.JSON).log().all().get();
        List<Integer> e = r.getBody().jsonPath().getList("id");
        for (int m: e)
        {
            if (m>0 && m<100){
                 num = true;
            }
            else {
                num=false;
                break;
            }
        }
        Assert.assertTrue(num);
    }

    @Test
    public void validatePostApi()
    {


    }

}
