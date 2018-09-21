package com.example.testDataGenerator.TestDataSteps;

import Data.TestData;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExecutorService {
    String fileForPreRequisite =
        "/Users/gokul/Downloads/testDataGenerator/src/main/resources/Pre-Requisite.csv";
    String fileForStep1 =
        "/Users/gokul/Downloads/testDataGenerator/src/main/resources/Step1.csv";
    String fileForStep3 =
        "/Users/gokul/Downloads/testDataGenerator/src/main/resources/Step3.csv";
    TestData testData = new TestData();
    String cvsSplitBy = ",";


    //TODO: Create a TestData object
    public void prepareTestData() {
        dataGeneratorForPreRequisite();
        getParamTypes();
    }

    public TestData execute() {
        prepareTestData();
        //TODO: CurlGenerator generator = new Curl(this.testData)
        CURLGenerator generator = new CURLGenerator(this.testData);
        JSONObject response = generator.generateCURL();

        JSONResponseValidator validator = new JSONResponseValidator(response);
        boolean status = validator.validateStatus(testData.getStatusCodeValidation());

        return testData;
        //TODO: Validation
    }

    //TODO:Setting values for Headers
    public void dataGeneratorForPreRequisite() {
        HashMap<String, String> headers = new HashMap<String, String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileForPreRequisite));
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] row = line.split(cvsSplitBy);
                if ((row[0].equalsIgnoreCase("BaseUrl"))) {
                    testData.setBaseURL(String.valueOf(row[1]));
                } else if (row[0].equalsIgnoreCase("endPoint")) {
                    testData.setEndPoint(String.valueOf(row[1]));
                } else {
                    headers.put(row[0], row[1]);
                }
            }
            testData.setHeaderData(headers);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //TODO: Read the attribute and method from Step1 and make that as a Map
    public Map<String, String> getParamTypes() {
        HashMap<String, String> pathParamsMap = new HashMap<String, String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileForStep1));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row[4].equalsIgnoreCase("path")) {
                    pathParamsMap = dataGeneratorForStep3();
                    testData.setPathParams(pathParamsMap);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pathParamsMap;
    }

    //TODO:Setting Values for Step1
    public HashMap<String, String> dataGeneratorForStep3() {
        HashMap<String, String> pathParamMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileForStep3));
            String line = "";
            String pathParamName = null;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cols = line.split(cvsSplitBy);
                if (cols[0].equalsIgnoreCase("bagId")) {
                    pathParamName = cols[0];
                } else {
                    pathParamMap.put(pathParamName, cols[0]);
                }
            }

        } catch (Exception e) {
        }
        return pathParamMap;
    }
}
