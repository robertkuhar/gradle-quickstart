package org.rekdev.hello.integration;

import static org.testng.AssertJUnit.assertNotNull;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

public class GreeterServletTests extends ContainerTestsBase {

  @Test(groups = { "integration" })
  public void happyPathGreeter() throws ClientProtocolException, IOException {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("http://localhost:8080/greeter");
    CloseableHttpResponse response = httpclient.execute(httpget);
    assertEquals(HttpServletResponse.SC_OK, response.getStatusLine().getStatusCode());

    HttpEntity entity = response.getEntity();
    assertNotNull(entity);
    if (entity.getContentLength() <= 2048) {
      String responseBody = EntityUtils.toString(entity);
      assertTrue(responseBody.contains("Hello, unknown"));
    } else {
      fail("expect content leng <= 2048, actual " + entity.getContentLength());
    }
  }

  @Test(groups = { "integration" })
  public void greeterPlusParam() throws ClientProtocolException, IOException {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("http://localhost:8080/greeter?name=Robert");
    CloseableHttpResponse response = httpclient.execute(httpget);
    assertEquals(HttpServletResponse.SC_OK, response.getStatusLine().getStatusCode());

    HttpEntity entity = response.getEntity();
    assertNotNull(entity);
    if (entity.getContentLength() <= 2048) {
      String responseBody = EntityUtils.toString(entity);
      assertTrue(responseBody.contains("Hello, Robert"));
    } else {
      fail("expect content leng <= 2048, actual " + entity.getContentLength());
    }
  }

}
