# Generate P12 Key #


With 365 day validity
-----------------------
`
keytool -genkeypair \
-alias cloudconfigclient
-dname 'CN=configserver.baselogic.io, OU=baselogic.io, O=BASE Logic inc., C=US' \
-keypass changeit \
-keyalg RSA \
-validity 365 \
-keystore cloudconfig_clientauth.p12 \
-storetype PKCS12 \
-storepass changeit
`



With unlimited validity
-----------------------
`
keytool -genkeypair \
-alias cloudconfigclient \
-dname 'CN=configserver.baselogic.io, OU=baselogic.io, O=BASE Logic inc., C=US' \
-keypass changeit \
-keyalg RSA \
-keystore cloudconfig_clientauth.p12 \
-storetype PKCS12 \
-storepass changeit
`

Second Version:
`
keytool -genkeypair \
-alias microserviceclient \
-dname 'CN=alternate.baselogic.io, OU=baselogic.io, O=BASE Logic inc., C=US' \
-keypass changeit \
-keyalg RSA \
-keysize 4096 \
-keystore cloudconfig_clientauth.p12 \
-storetype PKCS12 \
-storepass changeit
`


For examples in this code, I used the password **changeit**

List the contents of the keystore:

`
keytool -list -keystore cloudconfig_clientauth.p12 -storepass changeit -storetype pkcs12
`






## Truststore


First, we'll export the public key to a standard certificate file named
cloudconfig_clientauth.cer, as follows:

`
keytool -exportcert \
-alias cloudconfigclient \
-keystore cloudconfig_clientauth.p12 \
-storetype PKCS12 \
-storepass changeit \
-file cloudconfig_clientauth.cer
`


Next, we'll import the certificate into the trust store (this will create the trust store,
but in a typical deployment scenario, you'd probably already have some other
certificates in the trust store).

`
keytool -importcert \
-alias cloudconfigclient \
-keystore cloudconfig_clientauth.truststore \
-keypass changeit \
-file cloudconfig_clientauth.cer
`

Could this be a JKS?

The preceding command will create the trust store called **cloudconfig_clientauth.truststore** and
prompt you for a password (we chose the password changeit). You'll also see some
information about the certificate and will finally be asked to confirm that you do
trust the certificate:



```
[~/spring-cloud-config/configserver/src/main/resources/keys(master)]$ keytool -importcert \
> -alias cloudconfigclient \
> -keystore cloudconfig_clientauth.truststore \
> -keypass changeit \
> -file cloudconfig_clientauth.cer

Enter keystore password:
Re-enter new password:
Owner: CN=configserver.baselogic.io, OU=baselogic.io, O=BASE Logic inc., C=US
Issuer: CN=configserver.baselogic.io, OU=baselogic.io, O=BASE Logic inc., C=US
Serial number: 76f3d417
Valid from: Wed Jan 17 04:53:59 EET 2018 until: Tue Apr 17 05:53:59 EEST 2018
Certificate fingerprints:
	 MD5:  D7:CF:EA:2F:70:F7:74:8F:4A:17:3F:9A:48:09:D6:F6
	 SHA1: 40:4D:4F:6E:4E:E1:43:7F:79:6A:2C:25:FE:2B:26:38:46:79:46:D5
	 SHA256: FA:28:74:CA:52:C3:89:53:A7:6F:6C:6A:FF:DF:EC:63:74:4D:5B:27:5E:FE:C5:31:35:F9:C8:1F:EA:C5:4B:2E
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: FF 8B B6 AF FF F5 00 2C   E4 82 6B 18 5E D1 94 CD  .......,..k.^...
0010: 8B 1B 9A 59                                        ...Y
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore

```

### Print Cert

`keytool -printcert -file cloudconfig_clientauth.cer`



## Java Keystore (JKS)

`
keytool -genkeypair \
-alias cloudconfigclient-jks \
-dname 'CN=configserver.baselogic.io, OU=baselogic.io, O=BASE Logic inc., C=US' \
-keyalg RSA \
-keypass changeit \
-keystore cloudconfig_clientauth.jks \
-storepass changeit
`


```
[mickknutson@~/Documents/workspace/UDEMY/spring-cloud-config/configserver/src/main/resources/keys(master)]$ keytool -genkeypair \
> -alias cloudconfigclient-jks \
> -dname 'CN=configserver.baselogic.io, OU=baselogic.io, O=BASE Logic inc., C=US' \
> -keyalg RSA \
> -keypass changeit \
> -keystore cloudconfig_clientauth.jks \
> -storepass changeit
Picked up _JAVA_OPTIONS: -Xms256m -Xmx2048m

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using
"keytool -importkeystore -srckeystore cloudconfig_clientauth.jks -destkeystore cloudconfig_clientauth.jks -deststoretype pkcs12".
```





## NOTE ##

```
What's the difference between a key store and a trust store?
The Java Secure Socket Extension (JSSE) documentation defines a key
store as a storage mechanism for private keys and their corresponding
public keys. The key store (containing key pairs) is used to encrypt
or decrypt secure messages and so on. The trust store is intended
to store only public keys for trusted communication partners when
verifying identity (similar to how the trust store is used in certificate
authentication). In many common administration scenarios, however,
the key store and trust store are combined into a single file (in Tomcat,
this would be done through the use of the keystoreFile and
truststoreFile attributes of the connector). The format of the files
themselves can be exactly the same (really, each file can be any JSSEsupported
keystore format, including Java Key Store (JKS), PKCS 12,
and so on).
```


https://docs.bmc.com/docs/ServerAutomation/86/configuring-after-installation/administering-security/using-certificates-to-secure-communication-between-clients-and-application-servers
