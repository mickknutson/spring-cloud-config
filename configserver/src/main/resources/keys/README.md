# Generate P12 Key #


With 365 day validity
-----------------------
`keytool -genkeypair -alias cloudconfigclient
-keyalg RSA
-validity 365
-keystore cloudconfig_clientauth.p12
-storetype PKCS12`



With unlimited validity
-----------------------
`keytool -genkeypair -alias cloudconfigclient
-keypass changeit -keyalg RSA
-keystore cloudconfig_clientauth.p12
-storetype PKCS12`


For examples in this code, I used the password **changeit**

This is a sample of the output for generating this certificate:

```
[mickknutson@~/Documents/workspace/UDEMY/spring-cloud-config/configserver/src/main/resources/keys(master)]$ keytool -genkeypair -alias cloudconfigclient -keypass changeit -keyalg RSA -keystore cloudconfig_clientauth.p12 -storetype PKCS12

Enter keystore password:
Re-enter new password:

What is your first and last name?
  [Unknown]:  configserver.baselogic.io
What is the name of your organizational unit?
  [Unknown]:  baselogic.io
What is the name of your organization?
  [Unknown]:  BASE Logic, Inc.
What is the name of your City or Locality?
  [Unknown]:  Park City
What is the name of your State or Province?
  [Unknown]:  Utah
What is the two-letter country code for this unit?
  [Unknown]:  US
Is CN=configserver.baselogic.io, OU=baselogic.io, O="BASE Logic, Inc.", L=Park City, ST=Utah, C=US correct?
  [no]:  yes
```


## Tomcat Truststore


First, we'll export the public key to a standard certificate file named
cloudconfig_clientauth.cer, as follows:

`keytool -exportcert -alias cloudconfigclient
-keystore cloudconfig_clientauth.p12
-storetype PKCS12
-storepass changeit
-file cloudconfig_clientauth.cer`


Next, we'll import the certificate into the trust store (this will create the trust store,
but in a typical deployment scenario, you'd probably already have some other
certificates in the trust store).

`keytool -importcert -alias cloudconfigclient
-keystore tomcat.truststore
-file cloudconfig_clientauth.cer`


The preceding command will create the trust store called **tomcat.truststore** and
prompt you for a password (we chose the password changeit). You'll also see some
information about the certificate and will finally be asked to confirm that you do
trust the certificate:

```
[mickknutson@~/Documents/workspace/UDEMY/spring-cloud-config/configserver/src/main/resources/keys(master)]$ keytool -importcert -alias cloudconfigclient -keystore tomcat.truststore -file cloudconfig_clientauth.cer
Picked up _JAVA_OPTIONS: -Xms256m -Xmx2048m
Enter keystore password:
Owner: CN=Mick Knutson, OU=baselogic.io, O="BASE Logic, inc.", L=Park City, ST=Utah, C=US
Issuer: CN=Mick Knutson, OU=baselogic.io, O="BASE Logic, inc.", L=Park City, ST=Utah, C=US
Serial number: 695c7c75
Valid from: Tue Dec 26 05:56:45 MST 2017 until: Mon Mar 26 06:56:45 MDT 2018
Certificate fingerprints:
	 MD5:  61:40:68:50:7B:F0:2E:EE:27:20:BF:4C:47:AC:62:31
	 SHA1: A1:67:0B:C6:6A:4F:A9:1A:DA:AC:82:8C:CB:EA:11:46:65:55:D0:3F
	 SHA256: BF:61:D6:18:19:77:29:FE:8A:E3:9E:8F:F2:6B:96:DF:90:F2:3F:7B:9D:EB:6C:FF:52:46:F3:A6:FC:09:F3:09
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

Extensions:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 8D D1 6F 03 3C 09 7F B7   E9 3F 9B D5 26 6F 91 51  ..o.<....?..&o.Q
0010: 87 1F 75 A6                                        ..u.
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore
```

### Print Cert

`keytool -printcert -file cloudconfig_clientauth.cer`



## Tomcat Keystore (prefer PK12)

`keytool -genkey -alias cloudconfigclient
-keypass changeit -keyalg RSA
-keystore tomcat.keystore`

```
[mickknutson@~/Documents/workspace/UDEMY/spring-cloud-config/configserver/src/main/resources/keys(master)]$ keytool -genkey -alias cloudconfigclient -keypass changeit -keyalg RSA -keystore tomcat.keystore
Picked up _JAVA_OPTIONS: -Xms256m -Xmx2048m
Enter keystore password:
Re-enter new password:
What is your first and last name?
  [Unknown]:  Mick Knutson
What is the name of your organizational unit?
  [Unknown]:  baselogic.io
What is the name of your organization?
  [Unknown]:  BASE Logic, Inc.
What is the name of your City or Locality?
  [Unknown]:  Park City
What is the name of your State or Province?
  [Unknown]:  Utah
What is the two-letter country code for this unit?
  [Unknown]:  US
Is CN=Mick Knutson, OU=baselogic.io, O="BASE Logic, Inc.", L=Park City, ST=Utah, C=US correct?
  [no]:  yes


Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore tomcat.keystore -destkeystore tomcat.keystore -deststoretype pkcs12".
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