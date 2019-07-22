package com.abc.agency.utils;

import com.abc.agency.model.Income;
import java.util.List;

public class PerCapitaUtil {

  public static final String input_csv = "D:\\Personal_data\\Java Practice\\PerCapitaSapient\\src\\main\\resources\\com\\abc\\agency\\input_file.csv";

  public static boolean isNullOrEmpty(List<Income> incomeList) {
    return (incomeList == null || incomeList.size() == 0);
  }

  public static boolean isNullOrEmpty(String str) {
    return (str == null || str.trim().length() == 0);
  }

}
