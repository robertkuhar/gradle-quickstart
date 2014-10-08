package org.rekdev.hello;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;

public class GreeterTests {
    @Test
    public void testNoArgConstructor() {
        Greeter greeter = new Greeter();
        assertEquals( "Hello, Robert", greeter.greet( "Robert" ) );
    }

    @Test
    public void testCustomFormatString() {
        Greeter greeter = new Greeter( "Why %s, hello!" );
        assertEquals( "Why Robert, hello!", greeter.greet( "Robert" ) );
    }
}
