package com.yarish.yodlee.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {

  private final static Logger LOG = LoggerFactory.getLogger(User.class);

  private String username;
  private String password;
  private String emailId;
  private String instanceType;
  private String userSessionToken;

  public User(String username, String password, String emailId, String instanceType) {
    super();
    this.username = username;
    this.password = password;
    this.emailId = emailId;
    this.instanceType = instanceType;
  }


  public String getUserSessionToken() {
    return userSessionToken;
  }


  public void setUserSessionToken(String userSessionToken) {
    this.userSessionToken = userSessionToken;
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



  public String getEmailId() {
    return emailId;
  }



  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }



  public String getInstanceType() {
    return instanceType;
  }



  public void setInstanceType(String instanceType) {
    this.instanceType = instanceType;
  }



  public boolean register(String cobrandSessionToken) {

    boolean result = false;

    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.USER_REGISTER_URL;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      // Cobrand session token
      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);

      // New cobrand credentials parameters
      pm.addParameter(Constants.paramNameNewUserLogin, this.username);
      pm.addParameter(Constants.paramNameNewUserPassword, this.password);
      pm.addParameter(Constants.paramNameInstanceType, instanceType);
      pm.addParameter(Constants.paramNameUserEmail, this.emailId);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      InputStream is = pm.getResponseBodyAsStream();
      InputStreamReader isr = new InputStreamReader(is);
      StringBuilder sb = new StringBuilder();
      BufferedReader br = new BufferedReader(isr);
      String read = br.readLine();

      while (read != null) {
        sb.append(read);
        read = br.readLine();
      }
      LOG.debug(sb.toString());

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return result;
  }


  public boolean loginUser(String cobrandSessionToken, String usernameValue, String passwordValue)
      throws LoginFailedException {
    String userSessionToken = null;
    boolean result = false;
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.USER_LOGIN_URL;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(Constants.paramNameUserLogin, usernameValue);
      pm.addParameter(Constants.paramNameUserPassword, passwordValue);
      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();
      LOG.debug(source);
      JSONObject jsonObject = new JSONObject(source);
      JSONObject userContext = jsonObject.getJSONObject("userContext");
      JSONObject userConvCreds = userContext.getJSONObject("conversationCredentials");
      userSessionToken = (String) userConvCreds.get("sessionToken");
      this.userSessionToken = userSessionToken;


    } catch (JSONException jsonException) {
      throw new LoginFailedException("login failed");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return result;
  }

  private static class NullHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
      return true;
    }
  }


}
