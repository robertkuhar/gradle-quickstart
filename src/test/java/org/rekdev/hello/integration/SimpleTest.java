package org.rekdev.hello.integration;

import static org.testng.AssertJUnit.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.annotations.Test;

public class SimpleTest extends JerseyTestNg.ContainerPerClassTest {

    @Path( "hello" )
    public static class HelloResource {
        @GET
        public String getHello() {
            return "Hello World!";
        }
        
        @GET
        @Path( "jello" )
        public String getJello() {
            return "Jello World!";
        }
    }

    @Override
    protected Application configure() {
        return new ResourceConfig( HelloResource.class );
    }

    @Test
    public void test() {
        final String hello = target( "hello" ).request().get( String.class );
        assertEquals( "Hello World!", hello );
    }

    @Test
    public void test2() {
        final String jello = target( "hello/jello" ).request().get( String.class );
        assertEquals( "Jello World!", jello );
    }

}
