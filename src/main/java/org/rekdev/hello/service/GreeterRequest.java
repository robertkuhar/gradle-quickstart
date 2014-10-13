package org.rekdev.hello.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rekdev.hello.Greeter;

@Path( "greeter" )
public class GreeterRequest {

    @Path( "greet" )
    @GET
    @Produces( MediaType.TEXT_PLAIN )
    public String greetText( @QueryParam( "name" ) String name ) {
        return doGreeting( name ).greeting;
    }
    
    @Path( "greet" )
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public GreeterResponse greetJson( @QueryParam( "name" ) String name ) {
        return doGreeting( name );
    }
    
    private GreeterResponse doGreeting( String name ) {
        if ( name == null ) {
            name = "unknown";
        }
        Greeter greeter = new Greeter();
        GreeterResponse greeterResponse = new GreeterResponse( greeter.greet( name ) );
        return greeterResponse;
    }
}
