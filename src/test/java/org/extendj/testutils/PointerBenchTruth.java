package org.extendj.testutils;

import java.util.List;

public class PointerBenchTruth {
  private int allocId;
  private List<String> mayAlias;
  private List<String> notMayAlias;
  private List<String> mustAlias;
  private List<String> notMustAlias;

  public int getAllocId() {
    return allocId;
  }

  public void setAllocId(int allocId) {
    this.allocId = allocId;
  }

  public List<String> getMayAlias() {
    return mayAlias;
  }

  public void setMayAlias(List<String> mayAlias) {
    this.mayAlias = mayAlias;
  }

  public List<String> getNotMayAlias() {
    return notMayAlias;
  }

  public void setNotMayAlias(List<String> notMayAlias) {
    this.notMayAlias = notMayAlias;
  }

  public List<String> getMustAlias() {
    return mustAlias;
  }

  public void setMustAlias(List<String> mustAlias) {
    this.mustAlias = mustAlias;
  }

  public List<String> getNotMustAlias() {
    return notMustAlias;
  }

  public void setNotMustAlias(List<String> notMustAlias) {
    this.notMustAlias = notMustAlias;
  }

  @Override
  public String toString() {
    return "PointerBenchTruth [allocId=" + allocId + ", mayAlias=" + mayAlias + ", mustAlias=" + mustAlias
      + ", notMayAlias=" + notMayAlias + ", notMustAlias=" + notMustAlias + "]";
  }
}
