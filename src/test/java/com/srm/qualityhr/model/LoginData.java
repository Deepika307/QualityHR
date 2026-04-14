package com.srm.qualityhr.model;

public class LoginData {

    private String scenario;
    private String usernameKey;
    private String passwordKey;
    private boolean valid;
    private String expectedMessage;

    public String getScenario() {
        return scenario;
    }

    public String getUsernameKey() {
        return usernameKey;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public boolean isValid() {
        return valid;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
}
