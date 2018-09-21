package com.example.testDataGenerator.TestDataSteps;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UsingMap {
    private static final String SAMPLE_CSV_FILE = "/Users/gokul/Downloads/testDataGenerator/src/main/resources/Step2e.csv";

    public int i;

    public static String file = "/Users/gokul/Downloads/testDataGenerator/src/main/resources/Step1.csv";



    public static String correctValue="V_string";


    public static String inCorrectValue = "I_";

    public static String NegativeIntegerValue="I_RT^&%*";

    public static int  validinteger=12345;

//    public static int  invalidinteger=245;

    private int maxCount = 0;

    private int maxCountOfAttributes = 0;
    List<String> testData;


    Map<String, List> map = new LinkedHashMap<String, List>();


    public List<String> generateTestData(CsvFileReader testAttribute) {
        // TODO:  generate Test Data here
        List<String> al = new ArrayList<String>();

        if (testAttribute.getType().equalsIgnoreCase("string") && (testAttribute.getMandatory()
            .equalsIgnoreCase("yes"))) {
          al.add(correctValue);
          al.add(inCorrectValue);
        } else if (testAttribute.getType().equalsIgnoreCase("int") && (testAttribute.getMandatory()
            .equalsIgnoreCase("yes"))) {

            al.add(String.valueOf(validinteger));
            al.add(NegativeIntegerValue);

        } else if (testAttribute.getType().equalsIgnoreCase("boolean") && (testAttribute
            .getMandatory().equalsIgnoreCase("yes"))) {
            al.add(correctValue);
            al.add(inCorrectValue);

        } else if (testAttribute.getType().equalsIgnoreCase("string") && (testAttribute
            .getMandatory().equalsIgnoreCase("no"))) {
            al.add(correctValue);
        } else if (testAttribute.getType().equalsIgnoreCase("int") && (testAttribute.getMandatory()
            .equalsIgnoreCase("no"))) {
            al.add(correctValue);
        } else if (testAttribute.getType().equalsIgnoreCase("boolean") && (testAttribute
            .getMandatory().equalsIgnoreCase("no"))) {
            al.add(inCorrectValue);
        }
        return al;
    }

    public void mapValues() {

        CsvFileReader csvReader = new CsvFileReader();
        List<CsvFileReader> readData = csvReader.readTheDataFromCsv(file);
        for (CsvFileReader read : readData) {
            //List<String> testData = generateTestData(read);
            testData = generateTestData(read);

            for (Map.Entry<String, List> entry : map.entrySet()) {
                if (entry.getValue().size() > maxCount) {
                    maxCount = entry.getValue().size();
                }
            }

            map.put(read.getField(), testData);
            maxCountOfAttributes = map.size();

        }
        System.out.println(map);
        System.out.println(maxCountOfAttributes);
        System.out.println(maxCount);
    }


    public void writeCsv() {
        CsvFileReader csvReader = new CsvFileReader();
        List<CsvFileReader> readData = csvReader.readTheDataFromCsv(file);
        StringBuilder builder = new StringBuilder();
        int rows = maxCount + 1;
        int columns = maxCountOfAttributes;
        String[][] twoDArray = new String[rows][columns];

        int col = 0;
        for (Map.Entry<String, List> attribute : map.entrySet()) {
            twoDArray[0][col] = attribute.getKey();
            col++;
        }

        col = 0;
        for (Map.Entry<String, List> attribute : map.entrySet()) {
            List<String> values = attribute.getValue();
            for (i = 0; i < values.size(); i++) {
                twoDArray[i + 1][col] = values.get(i);
            }
            col++;
        }

        for (CsvFileReader read : readData) {

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {


                List<String> rowValues = new ArrayList<String>();
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        rowValues.add(twoDArray[i][j]);
                    }
                    csvPrinter.printRecord(rowValues);
                    rowValues.clear();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

//    public static void main(String[] args) {
//        UsingMap usingMap = new UsingMap();
//        usingMap.mapValues();
//        usingMap.writeCsv();
//    }
}
