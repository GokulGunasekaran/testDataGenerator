package com.example.testDataGenerator.TestDataSteps.Data;

import java.util.HashMap;

public class TestData {

    private HashMap<String, String> headerData;
    private HashMap<String, String> pathParams;
    private HashMap<String, String> queryParams;
    private String baseURL;
    private String endPoint;
    private String type;
    private int statusCodeValidation;

    public int getStatusCodeValidation() {
        return statusCodeValidation;
    }

    public void setStatusCodeValidation(int statusCodeValidation) {
        this.statusCodeValidation = statusCodeValidation;
    }

    public HashMap<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(HashMap<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public HashMap<String, String> getPathParams() {
        return pathParams;
    }

    public void setPathParams(HashMap<String, String> pathParams) {
        this.pathParams = pathParams;
    }

    public HashMap<String, String> getHeaderData() {
        return headerData;
    }

    public void setHeaderData(HashMap<String, String> headerData) {
        this.headerData = headerData;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    /*@Override
    public String toString(){
        return (this.baseURL + " " + this.headerData);
    }*/
}

