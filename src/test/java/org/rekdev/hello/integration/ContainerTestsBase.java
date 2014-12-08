package org.rekdev.hello.integration;

import org.rekdev.hello.util.EmbeddedJettyServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ContainerTestsBase {
  @BeforeSuite
  public void startServer() throws Exception {
    EmbeddedJettyServer.INSTANCE.startIfRequired();
  }

  @AfterSuite
  public void stopServer() throws Exception {
    EmbeddedJettyServer.INSTANCE.stop();
  }
}
