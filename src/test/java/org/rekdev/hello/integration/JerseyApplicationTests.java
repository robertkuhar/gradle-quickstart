package org.rekdev.hello.integration;

import static org.testng.AssertJUnit.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTestNg;
import org.rekdev.hello.service.JerseyApplication;
import org.testng.annotations.Test;

public class JerseyApplicationTests extends JerseyTestNg.ContainerPerClassTest {

    @Override
    protected Application configure() {
        Application app = new JerseyApplication();
        return app;
    }

    @Test
    public void testGreeterUnknownTextPlain() {
        final Response response = target( "greeter/greet" ).request( MediaType.TEXT_PLAIN ).get();
        assertEquals( HttpServletResponse.SC_OK, response.getStatus() );
        assertEquals( "Hello, unknown", response.readEntity( String.class ) );
    }

    @Test
    public void testGreeterRobertTextPlain() {
        final Response response = target( "greeter/greet" ).queryParam( "name", "Rob<e>rt" ).request( MediaType.TEXT_PLAIN ).get();
        assertEquals( HttpServletResponse.SC_OK, response.getStatus() );
        assertEquals( "Hello, Rob<e>rt", response.readEntity( String.class ) );
    }

    @Test
    public void testGreeterRobertApplicationJSON() {
        final Response response = target( "greeter/greet" ).queryParam( "name", "Rob<e>rt" ).request( MediaType.APPLICATION_JSON )
                .get();
        assertEquals( HttpServletResponse.SC_OK, response.getStatus() );
        assertEquals( "{\"greeting\":\"Hello, Rob<e>rt\"}", response.readEntity( String.class ) );
    }

    @Test
    public void testGreeterRobertApplicationXML() {
        final Response response = target( "greeter/greet" ).queryParam( "name", "Rob<e>rt" ).request( MediaType.APPLICATION_XML )
                .get();
        assertEquals( HttpServletResponse.SC_OK, response.getStatus() );
        assertEquals(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><greeterResponse><greeting>Hello, Rob&lt;e&gt;rt</greeting></greeterResponse>",
                response.readEntity( String.class ) );
    }

}
