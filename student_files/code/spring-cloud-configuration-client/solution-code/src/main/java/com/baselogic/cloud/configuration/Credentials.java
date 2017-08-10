package com.baselogic.cloud.configuration;

/**
 * base properties name:
 * apiGatewayCredentials
 * 
 * @author mickknutson
 *
 */
public class Credentials {
 
    private String provider;
    private String username;
    private String password;
    private int clients;
    
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getClients() {
		return clients;
	}
	public void setClients(int clients) {
		this.clients = clients;
	}
	
	@Override
	public String toString() {
		return "Api Gateway Credentials [provider=" + provider + ", username=" + username + ", password="
				+ password + ", clients=" + clients + "]";
	}

}