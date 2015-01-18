package org.rekdev.hello.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderDumperServlet extends HttpServlet {

  static class HeaderRecords {
    @JsonProperty("headers")
    public final List<HeaderRecord> headers;

    @JsonCreator
    HeaderRecords(@JsonProperty("headers") List<HeaderRecord> headers) {
      this.headers = headers;
    }
  }

  static class HeaderRecord {
    @JsonProperty("name")
    public final String name;
    @JsonProperty("value")
    public final String value;
    @JsonProperty("values")
    public final List<String> values;

    @JsonCreator
    HeaderRecord(@JsonProperty("name") String name, @JsonProperty("value") String value,
        @JsonProperty("values") List<String> values) {
      this.name = name;
      this.value = value;
      this.values = values;
    }
  }

  private static final Logger log = LoggerFactory.getLogger(HeaderDumperServlet.class);

  private static final long serialVersionUID = 1L;

  @Override
  public void init(ServletConfig config) throws ServletException {
    log.info("Initialized");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    doIt(req, resp);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    doIt(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    doIt(req, resp);
  }

  private void doIt(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {

    List<HeaderRecord> headerRecords = new ArrayList<HeaderRecord>();
    Enumeration<String> headers = req.getHeaderNames();
    while (headers.hasMoreElements()) {
      String headerName = headers.nextElement();
      String headerValue = req.getHeader(headerName);
      List<String> headerValues = Collections.list(req.getHeaders(headerName));
      log.debug("headerName: {} headerValues.size: {}", headerName, headerValues.size());
      headerRecords.add(new HeaderRecord(headerName, headerValue, headerValues));
    }

    resp.setContentType("application/json");
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.writeValue(resp.getWriter(), new HeaderRecords(headerRecords));
  }

}
