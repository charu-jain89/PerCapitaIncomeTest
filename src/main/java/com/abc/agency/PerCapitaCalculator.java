package com.abc.agency;

import static com.abc.agency.utils.PerCapitaUtil.isNullOrEmpty;

import com.abc.agency.model.Income;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PerCapitaCalculator {

  public Map<String, Map<String, Double>> calculatePerCapitaIncome(List<Income> incomes) {
    if (isNullOrEmpty(incomes)) {
      return null;
    }
    Map<String, Map<String, List<Double>>> countryOrCityGenderIncomes =
        calculateCityOrCountryGenderIncomeList(incomes);

    Map<String, Map<String, Double>> countryOrCityGenderAvgIncomes =
        calculateCityOrCountryGenderAvgIncome(countryOrCityGenderIncomes);

    System.out.println(countryOrCityGenderAvgIncomes);
    return countryOrCityGenderAvgIncomes;
  }

  private Map<String, Map<String, Double>> calculateCityOrCountryGenderAvgIncome(
      Map<String, Map<String, List<Double>>> countryOrCityGenderIncomes) {
    Map<String, Map<String, Double>> countryOrCityGenderAvgIncomes = new TreeMap<>();
    for (Map.Entry<String, Map<String, List<Double>>> entry : countryOrCityGenderIncomes
        .entrySet()) {
      Map<String, Double> perGenderAvgIncome = new TreeMap<>();
      String cityOrCountry = entry.getKey();
      Map<String, List<Double>> genderIncomes = entry.getValue();
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
    return countryOrCityGenderAvgIncomes;
  }

  private Map<String, Map<String, List<Double>>> calculateCityOrCountryGenderIncomeList(
      List<Income> incomes) {
    Map<String, Map<String, List<Double>>> countryOrCityGenderIncomes = new HashMap<>();
    for (Income income : incomes) {
      Map<String, List<Double>> genderIncomes = new HashMap<>();
      List<Double> incomeList = new ArrayList<>();

      String cityOrCountry =
          !isNullOrEmpty(income.getCountry()) ? income.getCountry() : income.getCity();
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
    return countryOrCityGenderIncomes;
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
