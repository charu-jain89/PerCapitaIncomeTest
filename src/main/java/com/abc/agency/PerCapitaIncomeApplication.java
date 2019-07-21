package com.abc.agency;

import com.abc.agency.model.Income;
import com.abc.agency.reader.CSVReader;
import com.abc.agency.utils.PerCapitaConstant;
import com.abc.agency.writer.CSVWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PerCapitaIncomeApplication {

  public static void main(String[] args) throws IOException {
    List<Income> incomeList = new CSVReader()
        .readInputFile(PerCapitaConstant.input_csv); //todo: get from resources
    Map<String, Map<String, Double>> avgIncome = new PerCapitaCalculator()
        .calculatePerCapitaIncome(incomeList);
    new CSVWriter().writeFile(avgIncome);
  }
}
