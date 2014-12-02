package org.rekdev.hello.service;

import com.google.common.html.HtmlEscapers;

import org.rekdev.hello.Greeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GreeterServlet extends HttpServlet {
  private static final Logger log = LoggerFactory.getLogger(GreeterServlet.class);

  private static final long serialVersionUID = 1L;

  @Override
  public void init(ServletConfig config) throws ServletException {
    log.info("Initialized");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String name = req.getParameter("name");
    if (name == null) {
      name = "unknown";
    }
    Greeter greeter = new Greeter();
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    out.println("<head><title>Greeter</title></head>");
    out.println("<body>");
    out.println(greeter.greet(HtmlEscapers.htmlEscaper().escape(name)));
    out.println("</body>");
  }

}
