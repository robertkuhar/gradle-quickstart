package org.rekdev.hello.integration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.rekdev.hello.service.api.GreeterResponse;
import org.testng.annotations.Test;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

public class GreeterJerseyTests extends ContainerTestsBase {
  @Test(groups = { "integration" })
  public void testGreeterUnknownTextPlain() throws ClientProtocolException, IOException {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("http://localhost:8080/api/greeter/greet");
    httpget.addHeader("Accept", ContentType.TEXT_PLAIN.getMimeType());
    CloseableHttpResponse response = httpclient.execute(httpget);

    assertEquals(HttpServletResponse.SC_OK, response.getStatusLine().getStatusCode());
    HttpEntity httpEntity = response.getEntity();
    assertNotNull(httpEntity);
    assertEquals(ContentType.TEXT_PLAIN.getMimeType(), ContentType.get(httpEntity).getMimeType());

    if (httpEntity.getContentLength() <= 2048) {
      String responseBody = EntityUtils.toString(httpEntity);
      assertTrue(responseBody.contains("Hello, unknown"));
    } else {
      fail("expect content leng <= 2048, actual " + httpEntity.getContentLength());
    }
    response.close();
  }

  @Test(groups = { "integration" })
  public void testGreeterRobertTextPlain() throws ClientProtocolException, IOException {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("http://localhost:8080/api/greeter/greet?name=Robert");
    httpget.addHeader("Accept", ContentType.TEXT_PLAIN.getMimeType());
    CloseableHttpResponse response = httpclient.execute(httpget);

    assertEquals(HttpServletResponse.SC_OK, response.getStatusLine().getStatusCode());
    HttpEntity httpEntity = response.getEntity();
    assertNotNull(httpEntity);
    assertEquals(ContentType.TEXT_PLAIN.getMimeType(), ContentType.get(httpEntity).getMimeType());

    if (httpEntity.getContentLength() <= 2048) {
      String responseBody = EntityUtils.toString(httpEntity);
      assertTrue(responseBody.contains("Hello, Robert"));
    } else {
      fail("expect content leng <= 2048, actual " + httpEntity.getContentLength());
    }
    response.close();
  }

  @Test(groups = { "integration" })
  public void testGreeterRobertApplicationJSON() throws ClientProtocolException, IOException {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("http://localhost:8080/api/greeter/greet?name=Robert");
    httpget.addHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
    CloseableHttpResponse response = httpclient.execute(httpget);

    assertEquals(HttpServletResponse.SC_OK, response.getStatusLine().getStatusCode());
    HttpEntity httpEntity = response.getEntity();
    assertNotNull(httpEntity);
    assertEquals(ContentType.APPLICATION_JSON.getMimeType(), ContentType.get(httpEntity)
        .getMimeType());

    if (httpEntity.getContentLength() <= 2048) {
      ObjectMapper mapper = new ObjectMapper();
      GreeterResponse greeterResponse =
          mapper.readValue(httpEntity.getContent(), GreeterResponse.class);
      assertNotNull(greeterResponse);
      assertEquals("Hello, Robert", greeterResponse.greeting);
    } else {
      fail("expect content leng <= 2048, actual " + httpEntity.getContentLength());
    }
    response.close();
  }

  @Test(groups = { "integration" })
  public void testGreeterRobertApplicationXML() throws ClientProtocolException, IOException,
      JAXBException {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("http://localhost:8080/api/greeter/greet?name=Robert");
    httpget.addHeader("Accept", ContentType.APPLICATION_XML.getMimeType());
    CloseableHttpResponse response = httpclient.execute(httpget);

    assertEquals(HttpServletResponse.SC_OK, response.getStatusLine().getStatusCode());
    HttpEntity httpEntity = response.getEntity();
    assertNotNull(httpEntity);
    assertEquals(ContentType.APPLICATION_XML.getMimeType(), ContentType.get(httpEntity)
        .getMimeType());

    if (httpEntity.getContentLength() <= 2048) {
      JAXBContext jaxbContext = JAXBContext.newInstance(GreeterResponse.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      GreeterResponse greeterResponse =
          (GreeterResponse) jaxbUnmarshaller.unmarshal(httpEntity.getContent());
      assertNotNull(greeterResponse);
      assertEquals("Hello, Robert", greeterResponse.greeting);
    } else {
      fail("expect content leng <= 2048, actual " + httpEntity.getContentLength());
    }
    response.close();
  }
}
