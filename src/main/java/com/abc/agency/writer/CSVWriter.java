package com.abc.agency.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CSVWriter implements OutputWriter {

  public void writeFile(Map<String, Map<String, Double>> avgIncomeMap) {
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter("output.csv");
      fileWriter.write("City/Country,Gender,Average Income(in USD)\n");

      for (Map.Entry<String, Map<String, Double>> map : avgIncomeMap.entrySet()) {
        String cityOrCountry = map.getKey();
        Map<String, Double> genderAvgIncome = map.getValue();
        for (Map.Entry<String, Double> income : genderAvgIncome.entrySet()) {
          String gender = income.getKey();
          Double avgIncome = income.getValue();
          fileWriter.append(cityOrCountry).append(",").append(gender).append(",")
              .append(String.valueOf(avgIncome)).append("\n");
        }
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
      try {
        if (fileWriter != null) {
          fileWriter.flush();
          fileWriter.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
