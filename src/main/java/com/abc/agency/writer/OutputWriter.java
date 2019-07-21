package com.abc.agency.writer;

import java.util.Map;

public interface OutputWriter {

  public void writeFile(Map<String, Map<String, Double>> treeMap);
}
