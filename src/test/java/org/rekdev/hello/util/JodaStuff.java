package org.rekdev.hello.util;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class JodaStuff {
  @Test
  public void testDateTimeDotParse() {
    final String testName = TestUtils.deriveCallingMethodName();
    String[] testCases =
        { "1999-02-01", "1999-02-01T12", "1999-02-01T12:01", "1999-02-01T12:01:15" };
    for (String testCase : testCases) {
      DateTime dateTime = DateTime.parse(testCase);
      doAssertions(dateTime, testCase, testName);
    }
  }

  @Test
  public void testNewDateTime() {
    final String testName = TestUtils.deriveCallingMethodName();
    String[] testCases =
        { "1999-02-01", "1999-02-01T12", "1999-02-01T12:01", "1999-02-01T12:01:15" };
    for (String testCase : testCases) {
      DateTime dateTime = new DateTime(testCase);
      doAssertions(dateTime, testCase, testName);
    }
  }

  private void doAssertions(DateTime actual, String s, String testName) {
    String message = String.format("%s: \"%s\" produced DateTime %s", testName, s, actual);
    System.out.println(message);
    assertNotNull(actual, message);
    assertEquals(actual.getYear(), 1999, message);
    assertEquals(actual.getMonthOfYear(), 2, message);
    assertEquals(actual.getDayOfMonth(), 1, message);
  }
}
