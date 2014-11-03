package org.rekdev.hello.service.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GreeterResponse {
  public String greeting;

  public GreeterResponse() {}

  public GreeterResponse(String greeting) {
    this();
    this.greeting = greeting;
  }

  public String getGreeting() {
    return this.greeting;
  }

}
