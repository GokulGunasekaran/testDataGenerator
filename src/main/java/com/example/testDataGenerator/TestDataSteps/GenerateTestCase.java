package com.example.testDataGenerator.TestDataSteps;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class GenerateTestCase
{
    public void generateTestCase()   throws Exception {
        String csvPath = "/Users/gokul/Downloads/testDataGenerator/src/main/resources/Step2e.csv";
        String step3path = "/Users/gokul/Downloads/testDataGenerator/src/main/resources/Step3.csv";
        LineNumberReader lnr = null;
        FileReader fr = null;
        BufferedReader br = null;
        String[] columns = new String[10];


        try {
            File file = new File(csvPath);
            if (file.exists())
            {
                fr = new FileReader(csvPath);
                br = new BufferedReader(new FileReader(file));
                lnr = new LineNumberReader(fr);
                int numberOfLines = 0;
                while (lnr.readLine() != null) {
                    numberOfLines++;
                }
//                System.out.println("Number Of Lines = " + numberOfLines);
                lnr.close();

                //seperating the header data
                String header = br.readLine();
                if (header != null) {
                    columns = header.split(",");
                }
//                for (int i = 0; i < columns.length; i++) {
//                    System.out.println("Headers " + columns[i]);
//                }

                //line data except header using list
                String line = br.readLine();
                String thisLine = "";
                List<String[]> lines = new ArrayList<>();
                while ((thisLine = line) != null) {
                    lines.add(line.split(","));
                    line = br.readLine();
                }

                //List to 2D array
                String[][] Validarray = new String[lines.size()][0];
                lines.toArray(Validarray);
                int row = Validarray.length;
                int col = Validarray[0].length;

                //Loop to find the number of invalid data found
                int invalid=0;
                for(int i=0; i<Validarray.length;i++) {
                    for (int j = 0; j < Validarray[i].length; j++) {
                        if(Validarray[i][j].startsWith("I_"))
                        {
                            invalid++;
                        }
                    }
                }
                //store the invalid data in map
                Map<Integer,List> map = new HashMap<Integer, List>(Validarray.length);
                Map<Integer,List> Validmap = new HashMap<Integer, List>(Validarray.length);

                for (int i=0;i<Validarray.length;i++)
                {
                    for(int j=0;j<Validarray[i].length;j++)
                    {
                        if(Validarray[i][j].startsWith("I_"))
                        {
                            map.put(j,Arrays.asList(Validarray[i][j]));
                        }
                    }
                }
                for (int i=0;i<Validarray.length;i++)
                {
                    for(int j=0;j<Validarray[i].length;j++)
                    {
                        if(Validarray[i][j].startsWith("V_"))
                        {
                            Validmap.put(j,Arrays.asList(Validarray[i][j]));
                        }
                    }
                }
                //New 2d array to generate the combo of test cases
                String [][] invalidArray = new String[invalid][col];
                Object [] array1 = Validmap.values().toArray();
                int refference=0;
                int ref=0;
                for(int i=0;i<col;i++) {
                    List<String> inv_values = null;
                    {
                        if (map.containsKey(i)) {
                            inv_values = map.get(i);
                        } else {
                            continue;
                        }
                        for (String item : inv_values) {
                            invalidArray[refference][i] = item;

                        }
                        refference++;
                        List<String> val_value = null;
                        if (Validmap.containsKey(i)) {
                            if (invalidArray[ref][i] == null) {
                                val_value = Validmap.get(i);
                            } else {
                                continue;
                            }
                            for (String val : val_value) {
                                invalidArray[ref][i] = val;
                            }
                            ref++;

                        }
                    }
                }
                for(int i=0;i<invalid;i++)
                {
                    for (int j=0;j<col;j++)
                    {
                        if(invalidArray[i][j] == null)
                        {
                            invalidArray[i][j] = Validmap.get(j).toString().replace("[","").replace("]","");
                        }
                        System.out.print(invalidArray[i][j]+"  ");
                        }
                    System.out.println();
                }
                int valCout = 0;
                //Positive combos
                for(int i=0; i<Validarray.length;i++) {
                    for (int j = 0; j < Validarray[i].length; j++)
                    {
                        if(Validarray[i][j].startsWith("I_") || Validarray[i][j].isEmpty()) //count conditon is needed
                        {
                            Validarray[i][j] = Validarray[i-1][j];
                        }
                        if(Validarray[i][j].startsWith("V_"))
                        {
                            valCout++;
                        }
                    }
                }
                //CSV writer
                BufferedWriter bufferedWriter= null;
                try {
                    bufferedWriter=new BufferedWriter(new FileWriter(step3path));
                    CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT);
                    bufferedWriter.write("Type OF TESTCASES,");
                    for (String s: columns)
                    {
                        String[] headers = s.split(",");
                        bufferedWriter.write(Arrays.asList(headers).stream().collect(Collectors.joining(",")));
                        bufferedWriter.write(",");

                    }
                    System.out.println("akjdda"+valCout);
                    System.out.println("GOKUL" + col);
                    List<String> ValidValues = new ArrayList<String>();
                    for (int i = 0; i <2; i++)
                    {
                        ValidValues.add("Positive Case-"+i);
                        for (int j = 0; j < col; j++) {
                            ValidValues.add(Validarray[i][j]);
                        }
                        bufferedWriter.write("\n");
                        csvPrinter.printRecord(ValidValues);
                        ValidValues.clear();
                    }
                    List<String> invalidValues = new ArrayList<String>();
                    for (int i = 0; i < invalid; i++)
                    {
                        invalidValues.add("Negative Case-"+i);
                        for (int j = 0; j <col; j++)
                        {
                            invalidValues.add(invalidArray[i][j]);
                        }
                        bufferedWriter.write("\n");
                        csvPrinter.printRecord(invalidValues);
                        invalidValues.clear();
                    }
//                    bufferedWriter.write("\n");

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bufferedWriter!= null)
                        {
                            bufferedWriter.close();
                        }
                    } catch (IOException e) {
                        System.out.println("Error while adding the Data");
                    }
                }
            }
            else
            {
                System.out.println("File Not Exist");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
