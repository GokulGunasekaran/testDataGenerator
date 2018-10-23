package com.example.testDataGenerator.TestDataSteps;

import com.example.testDataGenerator.TestDataSteps.Data.TestData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CURLGenerator {

    TestData testData = new TestData();

    CsvFileReader csvFileReader = new CsvFileReader();

    public CURLGenerator(TestData testData) {
        this.testData = testData;
    }

    public CURLGenerator() {}

    public JSONObject generateCURL() {
        JSONObject jsonObject = null;
        StringBuffer content = null;

        try {
            String curlUrl = testData.getBaseURL();
            String newCurlUrl = "";
            for (Map.Entry<String, String> entry : testData.getPathParams().entrySet()) {
                newCurlUrl = curlUrl.concat(testData.getEndPoint()).concat(entry.getValue());
            }
            URL urlGenerator = new URL(newCurlUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlGenerator.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);

            for (Map.Entry<String, String> entry : testData.getHeaderData().entrySet()) {

                String headerKey = entry.getKey();
                String headerValue = entry.getValue();

                httpURLConnection.setRequestProperty(headerKey, headerValue);

            }
            BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                content.append(inputLine);
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(String.valueOf(content));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
