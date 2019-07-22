package com.abc.agency.reader;

import com.abc.agency.model.Income;
import java.io.File;
import java.util.List;

public interface InputReader {

  List<Income> readInputFile(File file);
}
