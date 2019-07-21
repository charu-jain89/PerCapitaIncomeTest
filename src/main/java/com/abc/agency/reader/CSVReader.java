package com.abc.agency.reader;

import com.abc.agency.model.Currency;
import com.abc.agency.model.Gender;
import com.abc.agency.model.Income;
import com.abc.agency.utils.PerCapitaConstant;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements InputReader {

  public List<Income> readInputFile(String filePath) {
    BufferedReader bufferedReader = null;
    try {
      List<Income> incomes = new ArrayList<>();
      bufferedReader = new BufferedReader(
          new FileReader(PerCapitaConstant.input_csv));
      bufferedReader.readLine();//read header of csv file

      String line = "";
      while ((line = bufferedReader.readLine()) != null) {
        String[] inputData = line.split(",");
        incomes.add(
            Income
                .builder()
                .city(inputData[0])
                .country(inputData[1])
                .gender(Gender.valueOf(inputData[2]))
                .currency(Currency.valueOf(inputData[3]))
                .avgIncome(Double.valueOf(inputData[4]))
                .build()
        );
      }
      return incomes;
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return null;
    } finally {
      try {
        if (bufferedReader != null) {
          bufferedReader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
