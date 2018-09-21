package com.example.testDataGenerator.TestDataSteps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader_deleltethis {

    public void csvReader() {

        String csvFile =
            "/Users/gokul/Downloads/testDataGenerator/src/main/java/Data/TestData.java";
        String line  = "";
        String cvsSplitBy = ",";


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((br.readLine()) != null) {
                String[] bagDetails = line.split(cvsSplitBy);

                System.out
                    .println("Bag Details key: " + bagDetails[0] + "\tValues :" + bagDetails[1]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
