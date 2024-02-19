package org.favqs.utils;

public class RequestBodyBuilder {
    public static String buildCreateSessionRequestBody(String email, String password) {
        return "{\n" +
                "\"user\": {\n" +
                "  \"login\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}\n" +
                "}";
    }
}
