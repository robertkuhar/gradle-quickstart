package org.rekdev.hello.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JerseyApplication extends ResourceConfig {
    private static final Logger log = LoggerFactory.getLogger( JerseyApplication.class );

    public JerseyApplication() {
        log.trace( "JerseyApplication()" );
        register( JacksonFeature.class );
        packages( "org.rekdev.hello.service.api" );
    }
}
