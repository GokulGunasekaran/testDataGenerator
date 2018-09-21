package com.example.testDataGenerator.TestDataSteps;

import org.json.simple.JSONObject;


public class JSONResponseValidator {
    private JSONObject response;

    public JSONResponseValidator(JSONObject response) {
        this.response = response;
    }

    public boolean validateStatus(int expectedStatus) {

        int pass = 0;

        if (expectedStatus == 200) {
            pass++;
            return true;
        } else {
            return false;
        }
    }

}
