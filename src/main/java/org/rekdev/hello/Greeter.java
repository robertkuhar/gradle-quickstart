package org.rekdev.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Greeter {
  private static final Logger log = LoggerFactory.getLogger(Greeter.class);

  private final String format;

  public Greeter(String format) {
    log.trace("Greeter( format: {} )", format);
    this.format = format;
  }

  public Greeter() {
    this("Hello, %s");
  }

  public String greet(String name) {
    log.trace("greet( name: {} )", name);
    String greeting = String.format(format, name);
    log.debug("greet( name: {} ): {}", name, greeting);
    return greeting;
  }
}
