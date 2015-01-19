package org.rekdev.hello.service.api;

import org.rekdev.hello.util.BuildSignature;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("status")
public class StatusResource {

  @GET
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public StatusResponse status() {
    final String buildSignature = BuildSignature.getBuildSignature();
    final String version = BuildSignature.instance.getProperty(BuildSignature.VERSION, "unknown");
    return new StatusResponse(version, buildSignature, BuildSignature.instance.getProperties());
  }

}
