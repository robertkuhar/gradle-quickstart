package org.rekdev.hello.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Properties;

/**
 * The PropertiesLoader class populates a Properties object by loading it
 * through the classloader as a resource.
 */
public class PropertiesLoader {
  private static Logger log = LoggerFactory.getLogger(PropertiesLoader.class);

  /**
   * This method uses a Classloader to locate the resource identified by "name"
   * on the classpath and uses it to load a Properties oject with key/value
   * pairs. The assumption is that the passed in name parameter can be located
   * as a Resource by the ClassLoader. If it fails to load the resource, the
   * resulting Properties object will be empty.
   * 
   * @param name name of the resource to be located on the classpath
   * @return Properties object resulting from the processing of the passed in
   *         name; empty if name fails to load.
   */
  public static Properties loadPropertiesAsResourceStream(String name) {
    Properties props = new Properties();
    if (name != null && name.trim().length() > 0) {
      try {
        ClassLoader loader = PropertiesLoader.class.getClassLoader();
        if (loader == null) {
          loader = ClassLoader.getSystemClassLoader();
        }
        URL url = loader.getResource(name);
        if (url != null) {
          props.load(url.openStream());
        } else {
          log.warn("PropertiesLoader could not resolve resource \"{}\".", name);
        }
      } catch (Exception ex) {
        String message = String.format("Problems loading %s:  %s", name, ex.toString());
        log.error(message, ex);
      }
    }
    return props;
  }

}
