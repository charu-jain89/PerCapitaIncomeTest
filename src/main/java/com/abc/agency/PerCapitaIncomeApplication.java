package com.abc.agency;

import static com.abc.agency.utils.PerCapitaUtil.isNullOrEmpty;

import com.abc.agency.model.Income;
import com.abc.agency.reader.CSVReader;
import com.abc.agency.writer.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class PerCapitaIncomeApplication {

  public static void main(String[] args) throws FileNotFoundException {
    ClassLoader classLoader = PerCapitaIncomeApplication.class.getClassLoader();
    File file = new File(classLoader.getResource("input_file.csv").getFile());
    if (file.exists()) {
      List<Income> incomeList = new CSVReader().readInputFile(file);
      if (!isNullOrEmpty(incomeList)) {
        Map<String, Map<String, Double>> avgIncome = new PerCapitaCalculator()
            .calculatePerCapitaIncome(incomeList);
        new CSVWriter().writeFile(avgIncome);
      } else {
        System.out.println("IncomeList is empty or null");
      }
    } else {
      throw new FileNotFoundException("File not found");
    }
  }
}
