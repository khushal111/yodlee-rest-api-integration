package com.yarish.yodlee.app;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoBrand {

  private final static Logger LOG = LoggerFactory.getLogger(CoBrand.class);

  private String cobrandLoginValue;
  private String cobrandPasswordValue;
  private String coBrandSessionToken;


  public CoBrand(String cobrandLoginValue, String cobrandPasswordValue) {
    super();
    this.cobrandLoginValue = cobrandLoginValue;
    this.cobrandPasswordValue = cobrandPasswordValue;
  }


  public String getCobrandLoginValue() {
    return cobrandLoginValue;
  }

  public void setCobrandLoginValue(String cobrandLoginValue) {
    this.cobrandLoginValue = cobrandLoginValue;
  }

  public String getCobrandPasswordValue() {
    return cobrandPasswordValue;
  }

  public void setCobrandPasswordValue(String cobrandPasswordValue) {
    this.cobrandPasswordValue = cobrandPasswordValue;
  }

  public String getCoBrandSessionToken() {
    return coBrandSessionToken;
  }

  public void setCoBrandSessionToken(String coBrandSessionToken) {
    this.coBrandSessionToken = coBrandSessionToken;
  }


  public boolean login() {
    boolean result = false;
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.COB_LOGIN_URL;

    LOG.debug("Validating Cobrand by Connecting to URL " + url);
    String cobrandSessionToken = null;

    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(Constants.paramNameCobrandLogin, cobrandLoginValue);
      pm.addParameter(Constants.paramNameCobrandPassword, cobrandPasswordValue);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);
      LOG.debug(pm.getResponseBodyAsString());

      String source = pm.getResponseBodyAsString();

      JSONObject jsonObject = new JSONObject(source);
      JSONObject cobConvCreds = jsonObject.getJSONObject("cobrandConversationCredentials");
      cobrandSessionToken = (String) cobConvCreds.get("sessionToken");

      LOG.debug("\n\n");

      LOG.debug("Cobrand Session " + cobrandSessionToken);
      this.coBrandSessionToken = cobrandSessionToken;
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
      result = false;
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
