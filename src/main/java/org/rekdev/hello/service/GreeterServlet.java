package org.rekdev.hello.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rekdev.hello.Greeter;

public class GreeterServlet extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        String name = req.getParameter( "name" );
        if ( name == null ) {
            name = "unknown";
        }
        Greeter greeter = new Greeter();
        resp.setContentType( "text/html" );
        PrintWriter out = resp.getWriter();
        out.println( "<head><title>Greeter</title></head>" );
        out.println( "<body>" );
        // TODO: Your really should be HTML Escaping name
        out.println( greeter.greet( name ) );
        out.println( "</body>" );
    }

}
