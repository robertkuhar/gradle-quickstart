package org.rekdev.hello;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class JoinerSplitterTests {

  @Test
  public void listToStringTest() {
    List<String> legals = new ArrayList<String>();
    legals.add("a");
    legals.add("b");
    legals.add("c");
    String actual = Joiner.on(",").join(legals);
    assertEquals(actual, "a,b,c");
  }

  @Test
  public void stringToListTest() {
    List<String> expected = new ArrayList<String>();
    expected.add("a");
    expected.add("b");
    expected.add("c");

    String legals = "a,b,c";
    List<String> strings = Lists.newArrayList(Splitter.on(",").split(legals));
    assertEquals(strings, expected);
  }
}
