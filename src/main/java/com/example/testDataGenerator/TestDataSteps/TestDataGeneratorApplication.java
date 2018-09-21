package com.example.testDataGenerator.TestDataSteps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestDataGeneratorApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestDataGeneratorApplication.class, args);


        UsingMap usingMap = new UsingMap();
        usingMap.mapValues();
        usingMap.writeCsv();

        GenerateTestCase generateTestCase = new GenerateTestCase();
        generateTestCase.generateTestCase();

//
//        ExecutorService executorService = new ExecutorService();
//        TestData testData = executorService.execute();
//
//        CURLGenerator curlGenerator = new CURLGenerator(testData);
//        System.out.println("<==== CURL Response : ====>\n" + curlGenerator.generateCURL());

    }

}
