package org.favqs.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseSpecUtil {

    private static RequestSpecification baseRequestSpec(String authToken, Object payload) {
        return given()
                .header("Authorization", "Token token=\"" + authToken + "\"")
                .contentType(ContentType.JSON)
                .body(payload);
    }

    private static RequestSpecification baseRequestSpec(String authToken, String userToken) {
        return given()
                .header("Authorization", "Token token=\"" + authToken + "\"")
                .header("User-Token", userToken)
                .contentType(ContentType.JSON);
    }

    public static Response postRequest(String authToken, Object payload, String endPoint) {
        RequestSpecification specs = baseRequestSpec(authToken, payload);
        Response response = specs
                .when()
                .post(endPoint);
        return response;
    }

    public static Response getRequest(String authToken, String userSession, String endPoint) {
        RequestSpecification specs = baseRequestSpec(authToken, userSession);
        Response response = specs
                .when()
                .get(endPoint);
        return response;
    }

    public static Response putRequest(String authToken, String userSession, String endPoint) {
        RequestSpecification specs = baseRequestSpec(authToken, userSession);
        Response response = specs
                .when()
                .put(endPoint);
        return response;
    }
}
