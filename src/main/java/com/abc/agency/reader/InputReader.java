package com.abc.agency.reader;

import com.abc.agency.model.Income;
import java.io.IOException;
import java.util.List;

public interface InputReader {

  List<Income> readInputFile() throws IOException;
}
