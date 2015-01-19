package org.rekdev.hello.service.api;

import java.util.Properties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatusResponse {
  public String version;
  public String buildSignature;
  public Properties properites;

  public StatusResponse() {
  }
  
  public StatusResponse(String version, String buildSignature, Properties properties) {
    this.version = version;
    this.buildSignature = buildSignature;
    this.properites = properties;
  }

}
