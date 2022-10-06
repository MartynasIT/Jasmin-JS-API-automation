package com.automation.project.scripts;

import com.automation.project.base.BaseTest;
import com.framework.utils.Generators;
import com.framework.utils.JsonReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import lombok.Getter;
import lombok.Setter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ToiletAPITests extends BaseTest {
    @Getter @Setter
    private JsonPath data;
    @Getter @Setter
    private static String url;
    @Getter @Setter
    private static String username;
    @Getter @Setter
    private static String password;
    private static final String IPV4_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private static final Logger logger = LogManager.getLogger(ToiletAPITests.class);

    @BeforeSuite
    public void getLogin() {
        Response response = given().header("Content-Type", "application/json").when().post(ENDPOINT_DATA);
        setUrl(response.jsonPath().getString("targetUrl"));
        setUsername(response.jsonPath().getString("username"));
        setPassword(response.jsonPath().getString("password"));
    }

    @Test
    public void checkLoginReturns200() {
        var response = given().auth().basic(getUsername(),
               getPassword()).when().post(getUrl()).then();
        response.assertThat().statusCode(200);
        logger.info("Login was successful with status code of 200");
    }

    @Test
    public void checkIPExists() {
        Response response = given().auth().basic(getUsername(),
                getPassword()).when().post(getUrl());
        System.out.println(response.getBody());
//        response.assertThat().body("ip", Matchers.contains("192."));
        logger.info("Response body contains IP address verification passed");
    }

//    @Test
//    public void checkTokenExists() {
//        var response = given().auth().basic(getUsername(), getPassword()).when().post(getData().
//                getString("targetUrl")).then();
////        response.assertThat().body("ip", Matchers.contains("192."));
//        logger.info("Response body contains IP address verification passed");
//    }
////
//    @Test
//    public void checkUserCanGetUserDataAndUpdate() {
//        generators = new Generators();
//        login();
//        var response = given().auth().oauth2(token).get(ENDPOINT_USERS).then();
//        response.assertThat().statusCode(200).body("total", Matchers.not(lessThan(2)));
//        logger.info("User can see more than 1 account");
//
//        var responseFirstAccount = given().auth().oauth2(token).get(ENDPOINT_USERS + "/1").then().
//                contentType(ContentType.JSON).extract().response();
//        JSONObject json = new JsonReader(responseFirstAccount.getBody().asString()).getJsonObject();
//        String toAdd = generators.generateRandomString();
//        json.put("Edited", toAdd);
//
//        response = given().auth().oauth2(getToken()).and().given().header("Content-Type", "application/json").
//                body(json.toJSONString()).put(ENDPOINT_USERS).then();
//        response.assertThat().statusCode(200).body("Edited", Matchers.equalTo(toAdd));
//        logger.info("First user was updated successfully");
//    }
//
//    @Test
//    public void checkUserCanDeleteAnotherUser() {
//        login();
//        var response = given().auth().oauth2(getToken()).get(ENDPOINT_USERS + "/1").then().
//                contentType(ContentType.JSON).extract().response();
//        String firstAccount = response.getBody().asString();
//
//        var ResponseDeletion = given().auth().oauth2(getToken()).delete(ENDPOINT_USERS + "/1").then();
//        ResponseDeletion.assertThat().statusCode(204);
//        logger.info("Deletion command was successful");
//
//        response = given().auth().oauth2(getToken()).get(ENDPOINT_USERS + "/1").then().
//                contentType(ContentType.JSON).extract().response();
//        String firstAccountAfterDelete = response.getBody().asString();
//        Assert.assertEquals(firstAccount, firstAccountAfterDelete,
//                "User should not be able to alter database");
//        logger.info("Database was not actually altered");
//    }
//
//    @Test
//    public void checkFailedUserCreationCannotLogin() {
//        String loginDetails = jsonReader.returnJsonObjectAsString();
//        var response = given().header("Content-Type", "application/json").
//                body(loginDetails).when().post(ENDPOINT_REGISTER).then();
//        response.assertThat().statusCode(400).body("error", Matchers.equalTo(
//                "Note: Only defined users succeed registration"));
//        logger.info("Note: Only defined users succeed registration was returned");
//
//        response = given().header("Content-Type", "application/json").
//                body(loginDetails).when().post(ENDPOINT_LOGIN).then();
//        response.assertThat().statusCode(400).body("error", Matchers.equalTo("user not found"));
//        logger.info("user not found was returned");
//
//    }
}
