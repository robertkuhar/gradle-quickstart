package org.rekdev.hello.util;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedJettyServer {
  private Server server;

  private static final Logger log = LoggerFactory.getLogger(EmbeddedJettyServer.class);
  public static final EmbeddedJettyServer INSTANCE = new EmbeddedJettyServer();

  public synchronized void startIfRequired() throws Exception {
    if (server == null) {
      log.info("starting");
      server = new Server(8080);
      WebAppContext context = new WebAppContext();
      context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
      context.setResourceBase("src/main/webapp");
      context.setContextPath("/");
      server.setHandler(context);
      server.start();
    }
    log.info("started");
  }

  public synchronized Server getServer() {
    return server;
  }

  public synchronized void stop() throws Exception {
    if (server != null) {
      log.info("stopping");
      server.stop();
      server.join();
      server.destroy();
      server = null;
    }
    log.info("stopped");
  }

  public static void main(String[] args) {
    try {
      EmbeddedJettyServer.INSTANCE.startIfRequired();
      // Let it run for 1 minute...
      Thread.sleep(60000);
      EmbeddedJettyServer.INSTANCE.stop();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
