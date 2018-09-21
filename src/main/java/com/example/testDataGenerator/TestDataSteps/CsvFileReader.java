package com.example.testDataGenerator.TestDataSteps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CsvFileReader {

    private String Field;
    private String Type;
    private String Mandatory;
    private String Low_priority;
    private String Param_Type;



    public void setField(String Field)
    {
     this.Field=Field;
    }

    public String getField()
    {
        return Field;
    }

     public void setType(String Type)
     {
         this.Type=Type;
     }

     public String getType()
     {
         return Type;
     }

     public void  setMandatory(String Mandatory)
     {
         this.Mandatory=Mandatory;
     }

    public String getMandatory() {
        return Mandatory;
    }

    public void setLow_priority(String Low_priority)
    {
        this.Low_priority=Low_priority;

    }

    public String getLow_priority() {
        return Low_priority;
    }

    public void setParam_Type(String Param_Type)
    {
        this.Param_Type=Param_Type;
    }


    public String getParam_Type()
    {
        return Param_Type;
    }



    public  List<CsvFileReader> readTheDataFromCsv(String fileName)
    {
        List<CsvFileReader> readCsvList = new ArrayList<>();

        try
        {
            BufferedReader  reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();

            Scanner scanner;

            int index = 0;

            while ((line = reader.readLine())!=null)
            {
                 CsvFileReader csvreader = new CsvFileReader();

                 scanner = new Scanner(line);
                 scanner.useDelimiter(",");

                 while (scanner.hasNext())
                 {
                     String data = scanner.next();

                     if(index == 0)
                       csvreader.setField(data);
                     else if (index == 1)
                         csvreader.setType(data);
                     else if(index == 2)
                         csvreader.setMandatory(data);
                     else if(index == 3)
                          csvreader.setLow_priority(data);
                     else if (index ==4)
                         csvreader.setParam_Type(data);
                     else
                         continue;
                     index ++;
                 }

                 index = 0;

                readCsvList.add(csvreader);

            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to read the csv file");
        } catch (IOException e) {
            System.out.println("Unable to fetch the data from csv");
        }

          return readCsvList;
    }




}
