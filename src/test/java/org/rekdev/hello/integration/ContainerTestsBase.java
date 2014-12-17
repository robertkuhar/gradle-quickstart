package org.rekdev.hello.integration;

import org.rekdev.hello.util.EmbeddedJettyServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ContainerTestsBase {
  @BeforeSuite(alwaysRun = true)
  public void startServer() throws Exception {
    EmbeddedJettyServer.INSTANCE.startIfRequired();
  }

  @AfterSuite(alwaysRun = true)
  public void stopServer() throws Exception {
    EmbeddedJettyServer.INSTANCE.stop();
  }
}
