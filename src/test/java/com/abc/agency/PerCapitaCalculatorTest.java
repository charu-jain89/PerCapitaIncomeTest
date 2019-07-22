package com.abc.agency;

import com.abc.agency.model.Currency;
import com.abc.agency.model.Gender;
import com.abc.agency.model.Income;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class PerCapitaCalculatorTest {

  @Test
  public void testCalculatePerCapitaIncomeWhenIncomeIsNull() {
    Assert.assertNull(new PerCapitaCalculator().calculatePerCapitaIncome(null));
  }

  @Test
  public void testCalculatePerCapitaIncomeWhenIncomeIsEmpty() {
    Assert.assertNull(new PerCapitaCalculator().calculatePerCapitaIncome(new ArrayList<>()));
  }

  @Test
  public void testCalculatePerCapitaIncomeWhenIncome() {

    List<Income> incomeList = new ArrayList<>();
    incomeList.add(
        Income
            .builder()
            .country("India")
            .city("Delhi")
            .gender(Gender.F)
            .currency(Currency.INR)
            .avgIncome(70d)
            .build());

    Map<String, Map<String, Double>> countryMap = new HashMap<>();
    Map<String, Double> genderMap = new HashMap<>();

    genderMap.put(Gender.F.getValue(), 1.06d);
    countryMap.put("India", genderMap);

    Assert.assertEquals(countryMap,
        new PerCapitaCalculator().calculatePerCapitaIncome(incomeList));
  }
}
