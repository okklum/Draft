package ru.stqa.pft.sektor;

import org.testng.annotations.Test;

public class CheckGuaranties extends TestBase {

  public CheckGuaranties() {
    super();
  }


  @Test
  public void checkGuaranties() {
    goToGuarantees();
    checkGoTo();

  }

}
