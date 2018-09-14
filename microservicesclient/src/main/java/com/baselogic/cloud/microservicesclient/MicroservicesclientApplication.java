package com.baselogic.cloud.microservicesclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.net.ssl.HttpsURLConnection;

@SpringBootApplication

@EnableConfigurationProperties({ MicroservicesClientProperties.class })

public class MicroservicesclientApplication {


    // FIXME: Attempting to enable SSL client handshake.
    // FIXME: Did not work :-(
    static {

        System.setProperty("javax.net.debug", "all");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
        System.setProperty("https.protocols", "TLSv1.2");
        System.setProperty("javax.net.ssl.trustStore", "/Users/mickknutson/keys/cloudconfig_clientauth.p12");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        System.setProperty("javax.net.ssl.keyStore",  "/Users/mickknutson/keys/cloudconfig_clientauth.p12");
        System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

        // Java 8 syntax:
        HttpsURLConnection.setDefaultHostnameVerifier ((hostname, session) -> true);

        // Java 7 syntax:
//        final HostnameVerifier defaultHostnameVerifier = javax.net.ssl.HttpsURLConnection.getDefaultHostnameVerifier ();
//        final HostnameVerifier localhostAcceptedHostnameVerifier = new javax.net.ssl.HostnameVerifier () {
//
//            public boolean verify ( String hostname, javax.net.ssl.SSLSession sslSession ) {
//                if ( hostname.equals ( "localhost" ) ) {
//                    return true;
//                }
//                return defaultHostnameVerifier.verify ( hostname, sslSession );
//            }
//        };
//        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier ( localhostAcceptedHostnameVerifier );

    }

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesclientApplication.class, args);
	}

} // The end...
