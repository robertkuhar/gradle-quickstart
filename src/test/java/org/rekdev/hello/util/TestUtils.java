package org.rekdev.hello.util;

public class TestUtils {

  /**
   * Returns the calling method's name by using an exception to generate a
   * StackTrace, which is an expensive operation performance wise. IT IS NOT
   * ADVISABLE TO USE THIS ON ANY CODE THAT IS PERFORMANCE SENSITIVE.
   * 
   * @return calling method name as a String
   */
  public static String deriveCallingMethodName() {
    Exception exception = new Exception();
    StackTraceElement[] stackTraceElems = exception.getStackTrace();
    return stackTraceElems.length >= 2 ? stackTraceElems[1].getMethodName() : "unknown";
  }

  public static void sleepALittle(long millis) {
    final long wakeUp = System.currentTimeMillis() + millis;
    while (System.currentTimeMillis() < wakeUp) {
      try {
        Thread.sleep(wakeUp - System.currentTimeMillis());
      } catch (InterruptedException ie) {
        // eat it...
        ;
      }
    }
  }

}
