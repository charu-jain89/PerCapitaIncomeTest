package com.abc.agency;

import com.abc.agency.model.Income;
import com.abc.agency.reader.CSVReader;
import com.abc.agency.writer.CSVWriter;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class PerCapitaIncomeApplication {

  public static void main(String[] args) throws IOException {
    List<Income> incomeList = new CSVReader().readInputFile();
    TreeMap<String, TreeMap<String, Double>> avgIncome = new PerCapitaCalculator()
        .calculatePerCapitaIncome(incomeList);
    new CSVWriter().writeFile(avgIncome);
  }
}
