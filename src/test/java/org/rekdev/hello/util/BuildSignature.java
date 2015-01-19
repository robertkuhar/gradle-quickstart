package org.rekdev.hello.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Singleton which processes buildsignature.properties (if found on classpath)
 * and produces a Build Signature string to indentify who, what, where, and when
 * a build of the DM application was produced. There is error handling logic in
 * this class that WARNs on bad or missing buildsignature.properties at
 * instantiation time.
 */
public class BuildSignature {
  private static final Logger log = LoggerFactory.getLogger(BuildSignature.class);

  public static final String BUILDSIGNATURE_DOT_PROPERTIES = "buildsig.properties";
  public static final String VERSION = "buildsig.version";
  public static final String BUILDTIMESTAMP = "buildsig.timestamp";
  public static final String BUILDUSER = "buildsig.user";
  public static final String BUILDSYSTEM = "buildsig.system";
  public static final String BUILDTAG = "buildsig.tag";

  public static final BuildSignature instance = new BuildSignature();
  private static Properties buildSignatureProperties;

  private BuildSignature() {
    buildSignatureProperties =
        PropertiesLoader.loadPropertiesAsResourceStream(BUILDSIGNATURE_DOT_PROPERTIES);
    if (buildSignatureProperties.isEmpty()) {
      log.warn("{} is Empty.", BUILDSIGNATURE_DOT_PROPERTIES);
    }
  }

  public String getProperty(String key, String defaultValue) {
    log.trace("getProperty( key: {}, defaultValue: {} )", key, defaultValue);
    String value = buildSignatureProperties.getProperty(key, defaultValue);
    log.debug("getProperty( key: {}, defaultValue: {} ): {}", new Object[] { key, defaultValue,
        value });
    return value;
  }

  public Properties getProperties() {
    Properties p = new Properties();
    if (buildSignatureProperties != null) {
      p.putAll(buildSignatureProperties);
    }
    return p;
  }

  public static String makeMissingMessage(String key) {
    return "!" + key + "?";
  }

  private String makeBuildSignature() {
    String buildSignature =
        String.format("Version %s built %s on %s by %s as %s.",
            getProperty(VERSION, makeMissingMessage(VERSION)),
            getProperty(BUILDTIMESTAMP, makeMissingMessage(BUILDTIMESTAMP)),
            getProperty(BUILDSYSTEM, makeMissingMessage(BUILDSYSTEM)),
            getProperty(BUILDUSER, makeMissingMessage(BUILDUSER)),
            getProperty(BUILDTAG, makeMissingMessage(BUILDTAG)));
    return buildSignature;
  }

  /**
   * Produces the Build Signature for the application. If a
   * buildsignature.properties file was located on the classpath, this string is
   * like...</br>
   * <p>
   * Version 1.1.0 built 2012-06-19 16:05:48 PDT on jenkins by jenkins as
   * jenkins-GradleQuickstart_Build-29.
   * </p>
   * If no buildsignature.properties file was located on the classpath, this
   * string is like...
   * <p>
   * Version !missing_buildsignature.version built
   * !missing_buildsignature.buildTimestamp on
   * !missing_buildsignature.buildSystem by !missing_buildsignature.buildUser as
   * !missing_buildsignature.buildTag.
   * </p>
   * 
   * @return the build signature.
   */
  public static String getBuildSignature() {
    return instance.makeBuildSignature();
  }

}
