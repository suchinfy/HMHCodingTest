package org.favqs.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseSpecUtil {

    private static RequestSpecification baseRequestSpec(String authToken, Object payload) {
        return given()
                .header("Authorization", "Token token=\"" + authToken+"\"")
                .header("Content-Type", "application/json")
                .body(payload);
    }



    private static RequestSpecification baseRequestSpec(String authToken) {
        return given()
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON);
    }

    private static RequestSpecification baseRequestSpec(String authToken, String userToken) {
        return given()
                .header("Authorization", "Token token=\"" + authToken+"\"")
                .header("User-Token", userToken)
                .contentType(ContentType.JSON);
    }

    private static RequestSpecification baseRequestSpec(Map<String, Object> headers, Map<String, Object> pathParam, Map<String, Object> payload) {
        return given()
                .headers(headers)
                .pathParams(pathParam)
                .contentType(ContentType.JSON)
                .body(payload);
    }

    private static RequestSpecification baseRequestSpec(Map<String, Object> headers, Map<String, Object> payload) {
        return given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload);
    }

    private static RequestSpecification baseRequestSpec(String authToken, Map<String, String> queryParams) {
        return given()
                .header("Authorization", "Token token=\"" + authToken+"\"")
                .queryParams(queryParams)
                .contentType(ContentType.JSON);
    }

    public static Response postRequest(String authToken, Object payload, String endPoint) {
        RequestSpecification specs = baseRequestSpec(authToken,payload);
        Response response = specs
                .when()
                .post(endPoint);
        return response;
    }

    public static Response postRequest(Map<String,Object>headers ,Map<String, Object> payload, String endPoint) {
        RequestSpecification specs = baseRequestSpec(headers,payload);
        Response response = specs.body(payload)
                .when()
                .post(endPoint);
        return response;
    }

    public static Response getRequest(String authToken, String userSession, String endPoint) {
        RequestSpecification specs = baseRequestSpec(authToken, userSession);
        Response response = specs.log().all()
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
