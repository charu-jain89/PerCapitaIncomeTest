package com.abc.agency;

import com.abc.agency.model.Income;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;

public class PerCapitaCalculator {

  public TreeMap<String, TreeMap<String, Double>> calculatePerCapitaIncome(List<Income> incomes) {
    HashMap<String, HashMap<String, List<Double>>> countryOrCityGenderIncomes = new HashMap<>();

    for (Income income : incomes) {
      HashMap<String, List<Double>> genderIncomes = new HashMap<>();
      List<Double> incomeList = new ArrayList<>();

      String cityOrCountry =
          StringUtils.isNotBlank(income.getCountry()) ? income.getCountry() : income.getCity();
      if (countryOrCityGenderIncomes.containsKey(cityOrCountry)) {
        genderIncomes = countryOrCityGenderIncomes.get(cityOrCountry);
        if (genderIncomes.containsKey(income.getGender().getValue())) {
          incomeList = genderIncomes.get(income.getGender().getValue());
          incomeList.add(getCurrencyConversionRate(income));
        } else {
          incomeList.add(getCurrencyConversionRate(income));
          genderIncomes.put(income.getGender().getValue(), incomeList);
        }
      } else {
        incomeList.add(getCurrencyConversionRate(income));
        genderIncomes.put(income.getGender().getValue(), incomeList);
        countryOrCityGenderIncomes.put(cityOrCountry, genderIncomes);
      }
    }

    TreeMap<String, TreeMap<String, Double>> countryOrCityGenderAvgIncomes = new TreeMap<>();
    for (Map.Entry<String, HashMap<String, List<Double>>> entry : countryOrCityGenderIncomes
        .entrySet()) {
      TreeMap<String, Double> perGenderAvgIncome = new TreeMap<>();
      String cityOrCountry = entry.getKey();
      HashMap<String, List<Double>> genderIncomes = entry.getValue();
      for (Map.Entry<String, List<Double>> genderIncome : genderIncomes.entrySet()) {
        String gender = genderIncome.getKey();
        Double avgIncomePerGender = getAvgIncome(genderIncome.getValue());
        if (countryOrCityGenderAvgIncomes.containsKey(cityOrCountry)) {
          perGenderAvgIncome = countryOrCityGenderAvgIncomes.get(cityOrCountry);
          perGenderAvgIncome.put(gender, avgIncomePerGender);
        } else {
          perGenderAvgIncome.put(gender, avgIncomePerGender);
          countryOrCityGenderAvgIncomes.put(cityOrCountry, perGenderAvgIncome);
        }
      }
    }

    System.out.println(countryOrCityGenderAvgIncomes);
    return countryOrCityGenderAvgIncomes;
  }

  private Double getCurrencyConversionRate(Income income) {
    return (income.getAvgIncome() / income.getCurrency().getRate());
  }

  private Double getAvgIncome(List<Double> incomeValues) {
    double sum = 0d;
    for (Double income : incomeValues) {
      sum += income;
    }
    return Double.valueOf(new DecimalFormat("#.##").format(sum / incomeValues.size()));
  }
}
