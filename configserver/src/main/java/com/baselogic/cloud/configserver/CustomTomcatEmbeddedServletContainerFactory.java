package com.baselogic.cloud.configserver;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This configuration class will force all HTTP traffic to redirect to HTTPS.
 *
 *
 *
 * @author mickknutson
 */
@Configuration
@Profile({"TLS"})
public class CustomTomcatEmbeddedServletContainerFactory {

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {

            @Override
            protected void postProcessContext(final Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };

//        tomcat.addAdditionalTomcatConnectors(tlsConnector());
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    /**
     * Create an HTTP redirect connector
     * NOTE: This only works with HTTP/1.1
     * @return
     */
    private Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8888);
        connector.setSecure(false);
        connector.setRedirectPort(8443);

        return connector;
    }

    /**
     * Note this connector uses 'tomcat.keystore'
     * TODO: Regenerate keystore
     *
     * FIXME: Does not redirect !
     * @return
     */
    /*private Connector tlsConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");

        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(8443);

            File keystore = new ClassPathResource("keys/tomcat.keystore").getFile();
            File truststore = new ClassPathResource("keys/tomcat.truststore").getFile();

            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(keystore.getAbsolutePath());
            protocol.setKeystorePass("changeit");
            protocol.setKeyAlias("jbcpcalendar");

            protocol.setTruststoreFile(truststore.getAbsolutePath());
            protocol.setTruststorePass("changeit");
            protocol.setSslProtocol("TLS");
            return connector;
        }
        catch (IOException ex) {
            throw new IllegalStateException("can't access keystore: [" + "keystore"
                    + "] or truststore: [" + "keystore" + "]", ex);
        }
    }*/


} // The End...
