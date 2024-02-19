package org.favqs;

public class RequestBodyBuilder {
    public static String buildAddBookRequestBody(String email, String password) {
        return "{\n" +
                "\"user\": {\n" +
                "  \"login\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}\n" +
                "}";
    }
}
