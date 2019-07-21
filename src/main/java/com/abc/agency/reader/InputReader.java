package com.abc.agency.reader;

import com.abc.agency.model.Income;
import java.util.List;

public interface InputReader {

  List<Income> readInputFile(String filePath);
}
