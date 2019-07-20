package com.abc.agency.writer;

import java.io.IOException;
import java.util.TreeMap;

public interface OutputWriter {

  public void writeFile(TreeMap<String, TreeMap<String, Double>> treeMap) throws IOException;
}
