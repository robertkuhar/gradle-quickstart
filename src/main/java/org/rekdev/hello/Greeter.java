package org.rekdev.hello;

public class Greeter {
    private final String format;

    public Greeter( String format ) {
        this.format = format;
    }

    public Greeter() {
        this( "Hello, %s" );
    }

    public String greet( String name ) {
        return String.format( format, name );
    }
}
