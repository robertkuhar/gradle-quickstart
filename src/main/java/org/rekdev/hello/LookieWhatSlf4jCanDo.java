package org.rekdev.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LookieWhatSlf4jCanDo {
  public static final Logger LOG = LoggerFactory.getLogger(LookieWhatSlf4jCanDo.class);

  public static void main(String[] args) {
    LOG.info("start");
    LOG.info("Two replaceable parameters both strings");
    LOG.info("one:{}, two:{}","one","two");
    LOG.info("Two replaceable parameters, but three actual params");
    LOG.info("one:{}, two:{}", "one", "two", "three");
    LOG.info("One replaceable parameter but two actual params, second is Exception");
    LOG.info("one:{}", "hello", new Exception("what the hell?"));
    LOG.info("One replaceable parameter but two actual params, first is Exception");
    LOG.info("one:{}", new Exception("what the hell?"), "hello");
    LOG.info("Two replaceable parameter but three actual params, last is Exception");
    LOG.info("one:{}, two:{}", "hello", "two", new Exception("what the hell?"));
    LOG.info("done");
  }

}
