package com.yarishllc.misc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yarish.yodlee.app.Credential;
import com.yarish.yodlee.app.LoginFormInfo;



public class Register {

  private final static Logger LOG = LoggerFactory.getLogger(Register.class);

  public static String HOST_URI = "https://rest.developer.yodlee.com/services/srest/restserver/";
  private static String COB_LOGIN_URL = "v1.0/authenticate/coblogin";
  private static String USER_REGISTER_URL = "v1.0/jsonsdk/UserRegistration/register3";
  private static String USER_LOGIN_URL = "v1.0/authenticate/login";
  private static String SEARCH_SITE_URL = "v1.0/jsonsdk/SiteTraversal/searchSite";
  private static String GET_SITE_LOGIN_FORM = "v1.0/jsonsdk/SiteAccountManagement/getSiteLoginForm";
  private static String ADD_SITE_ACC = "v1.0/jsonsdk/SiteAccountManagement/addSiteAccount1";
  private static String GET_SITE_INFO = "v1.0/jsonsdk/SiteTraversal/getSiteInfo";
  private static String SITE_REFRESH = "v1.0/jsonsdk/Refresh/getSiteRefreshInfo";
  private static String ITEM_SUMMARIES = "v1.0/jsonsdk/DataService/getItemSummariesForSite";
  private static String START_SITE_REFRESH = "v1.0/jsonsdk/Refresh/startSiteRefresh";

  // Cobrand login API parameters
  private String paramNameCobrandLogin = "cobrandLogin";
  private String paramNameCobrandPassword = "cobrandPassword";

  // Common parameters for all APIs except for cobrand login or cobrand creation APIs
  private String paramNameCobSessionToken = "cobSessionToken";
  private String paramNameUserSessionToken = "userSessionToken";

  // Create cobrand credentials API parameters
  private String paramNameNewUserLogin = "userCredentials.loginName";
  private String paramNameNewUserPassword = "userCredentials.password";
  private String paramNameInstanceType = "userCredentials.objectInstanceType";
  private String paramNameUserEmail = "userProfile.emailAddress";

  // User login API parameters
  private String paramNameUserLogin = "login";
  private String paramNameUserPassword = "password";

  // Site refresh
  private String memSiteAccId = "memSiteAccId";



  public static void main(String[] args) {
    String cobrandLoginValue = "sbCobstackup"; // TODO load from property file
    String cobrandPasswordValue = "74bea3d6-a3b1-4681-8cfd-d2a6b3b9a66a"; // TODO

    // Step 1: Authenticate the cobrand
    Register tPost = new Register();
    String cobrandSessionToken = null;
    LOG.debug("\n\n\n\n Step 1: loginCobrand ");
    cobrandSessionToken = tPost.loginCobrand(cobrandLoginValue, cobrandPasswordValue);

    // Works only In Production
    // Step 2: Register a consumer to the Yodlee repository
    // String newUsername = "yarish.odesk@gmail.com";
    // String newPassword = "Thulasi123$";
    // String instanceType = "com.yodlee.ext.login.PasswordCredentials";
    // String newEmail = "yarish.odesk@gmail.com";
    // tPost.registerUser(cobrandSessionToken, newUsername, newPassword, instanceType, newEmail);


    // Step 2: Login a consumer who is already registered​
    LOG.debug("\n\n\n\n Step 2: loginUser ");
    // String usernameValue = "sbMemstackup1";
    // String passwordValue = "sbMemstackup1#123";

    String usernameValue = "sbMemstackup2"; // TODO
    String passwordValue = "sbMemstackup2#123";// TODO


    String userSessionToken = tPost.loginUser(cobrandSessionToken, usernameValue, passwordValue);
    LOG.debug("userSessionToken =" + userSessionToken);

    LOG.debug("\n\n\n\n Step 3: searchSite DAG");
    String siteSearchString = "DAG";
    // Step 3: Search for the required site
    tPost.searchSite(cobrandSessionToken, userSessionToken, siteSearchString);
    // result http://pastebin.com/kSCCxzJr - BoA
    // result http://pastebin.com/ - BoA
    // http://pastebin.com/HntSqVx7 - ICICI Bank


    LOG.debug("\n\n\n\n Step 4: getSiteLoginForm ");
    String siteId = "9001"; // DAG
    // Step 4: Display the login form
    LoginFormInfo loginFormInfo = tPost.getSiteLoginForm(cobrandSessionToken, siteId);


    LOG.debug("\n\n\n\n Step 4: getSiteInfo ");
    // String siteId = "2852"; // BoA
    siteId = "9001"; // DAG

    String reqSpecifier = "16441";
    // Step 4: Display the login form
    tPost.getSiteInfo(cobrandSessionToken, userSessionToken, reqSpecifier, siteId);
    // result http://pastebin.com/qjc6YkSw // BoA
    // result http://pastebin.com/dFxAMheG // ICICI


    LOG.debug("\n\n\n\n Step 5: Add the site");

    // String username = "yarish.site16441.2";
    // String password = "site16441.2";

    String username = "yarish.site16441.1";
    String password = "site16441.1";


    // Step 5: Add the site
    SiteAccount siteAccount =
        tPost.addSiteAccount(cobrandSessionToken, userSessionToken, loginFormInfo, username, password, siteId);
    // http://pastebin.com/n0fJXUEY
    String siteAccountId = siteAccount.getSiteAccountId();
    LOG.debug("siteAccountId=" + siteAccountId);



    LOG.debug("\n\n\n\n Step 6: Refresh the site - attempt1 ");
    // Step 6: Refresh the site
    if (!siteAccount.getSuggestedFlow().equalsIgnoreCase("REFRESH")) {
      try { // 5 min
        LOG.debug("5 min sleep");
        Thread.sleep(1000 * 60 * 5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    siteAccount = tPost.refreshTheSite(cobrandSessionToken, userSessionToken, siteAccount, siteId);


    LOG.debug("\n\n\n\n Step 7: Refresh the site - attempt2"); // Step 7: Refresh the site

    if (siteAccount.getSiteAddStatus().equalsIgnoreCase("ADD_IN_PROGRESS")) {
      try { // 5 min
        LOG.debug("5 min sleep");
        Thread.sleep(1000 * 60 * 5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    if (siteAccount.getSiteAddStatus().equalsIgnoreCase("ADDED")
        && siteAccount.getSiteRefreshStatus().equalsIgnoreCase("REFRESH_TRIGGERED")) {

      if (siteAccount.getSuggestedFlow().equalsIgnoreCase("NOT_REFRESHABLE")
          && siteAccount.getSuggestedFlowReason().equalsIgnoreCase("REFRESH_IN_PROGRESS")) {
        try { // 5 min
          Thread.sleep(1000 * 60 * 5);
          LOG.debug("5 min sleep");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      siteAccount = tPost.refreshTheSite(cobrandSessionToken, userSessionToken, siteAccount, siteId);
    }


    LOG.debug("\n\n\n\n Step 8: Refresh the site - attempt3");
    // Step 8: Refresh the site
    if (siteAccount.getSiteAddStatus().equalsIgnoreCase("ADDED")
        && siteAccount.getSiteRefreshStatus().equalsIgnoreCase("REFRESH_TRIGGERED")) {

      if (siteAccount.getSuggestedFlow().equalsIgnoreCase("NOT_REFRESHABLE")
          && siteAccount.getSuggestedFlowReason().equalsIgnoreCase("REFRESH_IN_PROGRESS")) {
        try { // 5 min
          LOG.debug("5 min sleep");
          Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      siteAccount = tPost.refreshTheSite(cobrandSessionToken, userSessionToken, siteAccount, siteId);
    }


    LOG.debug("\n\n\n\n Step 9: GetItemSummariesForSite");
    // Step 9: GetItemSummariesForSite
    tPost.getItemSummariesForSite(cobrandSessionToken, userSessionToken, siteAccount);
    // http://pastebin.com/6nkP3r41


    /*
     * LOG.debug("\n\n\n\n Step 10: Refresh button"); // 10: Refresh button
     * tPost.startSiteRefresh(cobrandSessionToken, userSessionToken, siteAccount); // //
     * http://pastebin.com/ByPgbHcM
     */


  }// End of main

  public String loginCobrand(String cobrandLoginValue, String cobrandPasswordValue) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + COB_LOGIN_URL;

    LOG.debug("Validating Cobrand by Connecting to URL " + url);
    String cobrandSessionToken = null;

    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(paramNameCobrandLogin, cobrandLoginValue);
      pm.addParameter(paramNameCobrandPassword, cobrandPasswordValue);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);
      LOG.debug(pm.getResponseBodyAsString());

      String source = pm.getResponseBodyAsString();

      JSONObject jsonObject = new JSONObject(source);
      JSONObject cobConvCreds = jsonObject.getJSONObject("cobrandConversationCredentials");
      cobrandSessionToken = (String) cobConvCreds.get("sessionToken");

      LOG.debug("\n\n");

      LOG.debug("Cobrand Session " + cobrandSessionToken);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return cobrandSessionToken;
  }



  public String registerUser(String cobrandSessionToken, String newUsername, String newPassword, String instanceType,
      String newEmail) {
    // String userSessionToken=null;
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + USER_REGISTER_URL;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      // Cobrand session token
      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);

      // New cobrand credentials parameters
      pm.addParameter(paramNameNewUserLogin, newUsername);
      pm.addParameter(paramNameNewUserPassword, newPassword);
      pm.addParameter(paramNameInstanceType, instanceType);
      pm.addParameter(paramNameUserEmail, newEmail);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      // LOG.debug(pm.getResponseBodyAsString());
      InputStream is = pm.getResponseBodyAsStream();
      InputStreamReader isr = new InputStreamReader(is);
      StringBuilder sb = new StringBuilder();
      BufferedReader br = new BufferedReader(isr);
      String read = br.readLine();

      while (read != null) {
        // LOG.debug(read);
        sb.append(read);
        read = br.readLine();
      }
      LOG.debug(sb.toString());

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return null;
  }


  public String loginUser(String cobrandSessionToken, String usernameValue, String passwordValue) {
    String userSessionToken = null;
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + USER_LOGIN_URL;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(paramNameUserLogin, usernameValue);
      pm.addParameter(paramNameUserPassword, passwordValue);
      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();
      JSONObject jsonObject = new JSONObject(source);
      JSONObject userContext = jsonObject.getJSONObject("userContext");
      JSONObject userConvCreds = userContext.getJSONObject("conversationCredentials");
      userSessionToken = (String) userConvCreds.get("sessionToken");

      LOG.debug(source);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return userSessionToken;
  }


  public String searchSite(String cobrandSessionToken, String userSessionToken, String siteSearchString) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + SEARCH_SITE_URL;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(paramNameUserSessionToken, userSessionToken);
      pm.addParameter("siteSearchString", siteSearchString);
      // pm.addParameter("siteSearchString.objectInstanceType",
      // "java.lang.String");

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();

      LOG.debug(source);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return userSessionToken;
  }


  public LoginFormInfo getSiteLoginForm(String cobrandSessionToken, String siteId) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + GET_SITE_LOGIN_FORM;
    LoginFormInfo loginFormInfo = new LoginFormInfo();

    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter("siteId", siteId);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();

      LOG.debug(source);

      String valueIdentifier = null;
      String valueMask = null;
      String fieldType = null;
      String size = null;
      String maxlength = null;
      String fieldInfoType = null;
      String name = null;
      String displayName = null;
      String isEditable = null;
      String isOptional = null;
      String isEscaped = null;
      String helpText = null;
      String isOptionalMFA = null;
      String isMFA = null;


      Credential credential0 = null;
      Credential credential1 = null;


      JSONObject jsonObject = new JSONObject(source);
      JSONArray componentList = jsonObject.getJSONArray("componentList");



      for (int i = 0; i < componentList.length(); i++) {
        JSONObject loginComponent = componentList.getJSONObject(i);
        valueIdentifier = loginComponent.getString("valueIdentifier");
        LOG.debug("valueIdentifier=" + valueIdentifier);

        valueMask = loginComponent.getString("valueMask");
        LOG.debug("valueMask=" + valueMask);

        JSONObject fieldTypeJSON = loginComponent.getJSONObject("fieldType");
        fieldType = fieldTypeJSON.getString("typeName");
        LOG.debug("fieldType=" + fieldType);

        size = loginComponent.getString("size");
        LOG.debug("size=" + size);

        maxlength = loginComponent.getString("maxlength");
        LOG.debug("maxlength=" + maxlength);

        fieldInfoType = loginComponent.getString("fieldInfoType");
        LOG.debug("fieldInfoType=" + fieldInfoType);

        name = loginComponent.getString("name");
        LOG.debug("name=" + name);

        displayName = loginComponent.getString("displayName");
        LOG.debug("displayName=" + displayName);

        isEditable = loginComponent.getString("isEditable");
        LOG.debug("isEditable=" + isEditable);

        isOptional = loginComponent.getString("isOptional");
        LOG.debug("isOptional=" + isOptional);

        isEscaped = loginComponent.getString("isEscaped");
        LOG.debug("isEscaped=" + isEscaped);

        helpText = loginComponent.getString("helpText");
        LOG.debug("helpText=" + helpText);

        isOptionalMFA = loginComponent.getString("isOptionalMFA");
        LOG.debug("isOptionalMFA=" + isOptionalMFA);

        isMFA = loginComponent.getString("isMFA");
        LOG.debug("isMFA=" + isMFA);

        if (i == 0) {
          credential0 =
              new Credential(valueIdentifier, valueMask, fieldType, size, maxlength, fieldInfoType, name, displayName,
                  isEditable, isOptional, isEscaped, helpText, isOptionalMFA, isMFA);
        } else if (i == 1) {
          credential1 =
              new Credential(valueIdentifier, valueMask, fieldType, size, maxlength, fieldInfoType, name, displayName,
                  isEditable, isOptional, isEscaped, helpText, isOptionalMFA, isMFA);
        } else {
          ;
        }

      }


      Credential[] credentials = {credential0, credential1};
      loginFormInfo.setCredentials(credentials);

      // JSONObject userConvCreds = userContext.getJSONObject("conversationCredentials");
      // String userSessionToken = (String) userConvCreds.get("sessionToken");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return loginFormInfo;

  }

  public String getSiteInfo(String cobrandSessionToken, String userSessionToken, String reqSpecifier, String siteId) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    // String excludeContentServiceInfo = "false";
    // String reqSpecifier = "128";
    // String siteId = "16441";

    String url = HOST_URI + GET_SITE_INFO;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(paramNameUserSessionToken, userSessionToken);

      // spm.addParameter("siteFilter.excludeContentServiceInfo" ,
      // excludeContentServiceInfo);
      // pm.addParameter("siteFilter.excludeContentServiceInfo.objectInstanceType","java.lang.Boolean");
      pm.addParameter("siteFilter.reqSpecifier", reqSpecifier);
      // pm.addParameter("siteFilter.reqSpecifier.objectInstanceType" ,
      // "java.lang.Integer");
      pm.addParameter("siteFilter.siteId", siteId);
      // pm.addParameter("siteFilter.siteId.objectInstanceType" ,
      // "java.lang.Long");

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();

      LOG.debug(source);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return userSessionToken;
  }



  public SiteAccount addSiteAccount(String cobrandSessionToken, String userSessionToken, LoginFormInfo loginFormInfo,
      String userName, String password, String siteId) {

    SiteAccount siteAccount = null;
    String siteAccountId = null;
    String siteRefreshStatus = null;
    String suggestedFlow = null;
    String suggestedFlowId = null;
    String code = null;

    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + ADD_SITE_ACC;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(paramNameUserSessionToken, userSessionToken);

      pm.addParameter("siteId", siteId);
      pm.addParameter("credentialFields.enclosedType", "com.yodlee.common.FieldInfoSingle");

      pm.addParameter("credentialFields[0].displayName", loginFormInfo.getCredentials()[0].getDisplayName());
      pm.addParameter("credentialFields[0].fieldType.typeName", loginFormInfo.getCredentials()[0].getFieldType()); // LOGIN
      pm.addParameter("credentialFields[0].helpText", loginFormInfo.getCredentials()[0].getHelpText());
      pm.addParameter("credentialFields[0].maxlength", loginFormInfo.getCredentials()[0].getMaxlength());
      pm.addParameter("credentialFields[0].name", loginFormInfo.getCredentials()[0].getName());
      pm.addParameter("credentialFields[0].size", loginFormInfo.getCredentials()[0].getSize());
      pm.addParameter("credentialFields[0].value", userName);
      pm.addParameter("credentialFields[0].valueIdentifier", loginFormInfo.getCredentials()[0].getValueIdentifier());
      pm.addParameter("credentialFields[0].valueMask", loginFormInfo.getCredentials()[0].getValueMask());
      pm.addParameter("credentialFields[0].isEditable", loginFormInfo.getCredentials()[0].getIsEditable());

      // pm.addParameter("credentialFields[0].isOptional",
      // loginFormInfo.getCredentials()[0].getIsOptional());
      // pm.addParameter("credentialFields[0].valuePattern", "null");
      // pm.addParameter("credentialFields[0].defaultValue", "null");
      // pm.addParameter("credentialFields[0].validValues", "null");
      // pm.addParameter("credentialFields[0].displayValidValues", "null");
      // pm.addParameter("credentialFields[0].validationRules", "null");
      // pm.addParameter("credentialFields[0].userProfileMappingExpression", "null");
      // pm.addParameter("credentialFields[0].fieldErrorCode", "1");
      // pm.addParameter("credentialFields[0].fieldErrorMessage", "null");


      pm.addParameter("credentialFields[1].displayName", loginFormInfo.getCredentials()[1].getDisplayName());
      pm.addParameter("credentialFields[1].fieldType.typeName", loginFormInfo.getCredentials()[1].getFieldType());
      pm.addParameter("credentialFields[1].helpText", loginFormInfo.getCredentials()[1].getHelpText());
      pm.addParameter("credentialFields[1].maxlength", loginFormInfo.getCredentials()[1].getMaxlength());
      pm.addParameter("credentialFields[1].name", loginFormInfo.getCredentials()[1].getName());
      pm.addParameter("credentialFields[1].size", loginFormInfo.getCredentials()[1].getSize());
      pm.addParameter("credentialFields[1].value", password);
      pm.addParameter("credentialFields[1].valueIdentifier", loginFormInfo.getCredentials()[1].getValueIdentifier());
      pm.addParameter("credentialFields[1].valueMask", loginFormInfo.getCredentials()[1].getValueMask());
      pm.addParameter("credentialFields[1].isEditable", loginFormInfo.getCredentials()[1].getIsEditable());



      // pm.addParameter("credentialFields[1].name", loginFormInfo.getCredentials()[1].getName());
      // pm.addParameter("credentialFields[1].displayName",
      // loginFormInfo.getCredentials()[1].getDisplayName());
      // pm.addParameter("credentialFields[1].isEditable",
      // loginFormInfo.getCredentials()[1].getIsEditable());
      // pm.addParameter("credentialFields[1].isOptional",
      // loginFormInfo.getCredentials()[1].getIsOptional());
      // pm.addParameter("credentialFields[1].helpText",
      // loginFormInfo.getCredentials()[1].getHelpText());
      // pm.addParameter("credentialFields[1].valuePattern", "null");
      // pm.addParameter("credentialFields[1].defaultValue", "null");
      // pm.addParameter("credentialFields[1].value", password);
      // pm.addParameter("credentialFields[1].validValues", "null");
      // pm.addParameter("credentialFields[1].displayValidValues", "null");
      // pm.addParameter("credentialFields[1].valueIdentifier",
      // loginFormInfo.getCredentials()[1].getValueIdentifier());
      // pm.addParameter("credentialFields[1].valueMask",
      // loginFormInfo.getCredentials()[1].getValueMask());
      // pm.addParameter("credentialFields[1].fieldType",
      // loginFormInfo.getCredentials()[0].getFieldType());
      // pm.addParameter("credentialFields[1].validationRules", "null");
      // pm.addParameter("credentialFields[1].size", loginFormInfo.getCredentials()[0].getSize());
      // pm.addParameter("credentialFields[1].maxlength",
      // loginFormInfo.getCredentials()[0].getMaxlength());
      // pm.addParameter("credentialFields[1].userProfileMappingExpression", "null");
      // pm.addParameter("credentialFields[1].fieldErrorCode", "1");
      // pm.addParameter("credentialFields[1].fieldErrorMessage", "null");
      // pm.addParameter("credentialFields.objectInstanceType",
      // "[Lcom.yodlee.common.FieldInfoSingle;");


      // pm.addParameter("siteId.objectInstanceType", "long");

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();
      LOG.debug(source);

      JSONObject jsonObject = new JSONObject(source);
      siteAccountId = jsonObject.getString("siteAccountId");
      JSONObject siteRefreshInfo = jsonObject.getJSONObject("siteRefreshInfo");
      siteRefreshStatus = siteRefreshInfo.getString("siteRefreshStatus");
      code = siteRefreshInfo.getString("code");
      JSONObject suggestedFlowJson = siteRefreshInfo.getJSONObject("suggestedFlow");
      suggestedFlowId = suggestedFlowJson.getString("suggestedFlowId");
      suggestedFlow = suggestedFlowJson.getString("suggestedFlow");

      LOG.debug("siteAccountId=" + siteAccountId);
      LOG.debug("siteRefreshStatus=" + siteRefreshStatus);
      LOG.debug("code=" + code);
      LOG.debug("suggestedFlowId=" + suggestedFlowId);
      LOG.debug("suggestedFlow=" + suggestedFlow);


      siteAccount = new SiteAccount(siteAccountId, siteRefreshStatus, code, suggestedFlowId, suggestedFlow);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return siteAccount;
  }



  /*
   * public SiteAccount refreshTheSite(String cobrandSessionToken, String userSessionToken,
   * SiteAccount siteAccount, String siteId) { DefaultHttpClient httpclient = new
   * DefaultHttpClient();
   * 
   * String url = HOST_URI + SITE_REFRESH; try { HttpsURLConnection.setDefaultHostnameVerifier(new
   * NullHostnameVerifier());
   * 
   * PostMethod pm = new PostMethod(url);
   * 
   * pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
   * pm.addParameter(paramNameUserSessionToken, userSessionToken); pm.addParameter(memSiteAccId,
   * siteAccount.getSiteAccountId());
   * 
   * HttpClient hc = new HttpClient(); hc.executeMethod(pm);
   * 
   * String source = pm.getResponseBodyAsString(); LOG.debug(source);
   * 
   * JSONObject jsonObject = new JSONObject(source); String code = jsonObject.getString("code");
   * JSONObject siteRefreshStatusJson = jsonObject.getJSONObject("siteRefreshStatus"); String
   * siteRefreshStatusId = siteRefreshStatusJson.getString("siteRefreshStatusId"); String
   * siteRefreshStatus = siteRefreshStatusJson.getString("siteRefreshStatus");
   * 
   * JSONObject siteAddStatusJson = jsonObject.getJSONObject("siteAddStatus"); String siteAddStatus
   * = siteAddStatusJson.getString("siteAddStatus"); String siteAddStatusId =
   * siteAddStatusJson.getString("siteAddStatusId");
   * 
   * 
   * LOG.debug("code=" + code);
   * 
   * LOG.debug("siteRefreshStatusId=" + siteRefreshStatusId); LOG.debug("siteRefreshStatus=" +
   * siteRefreshStatus);
   * 
   * LOG.debug("siteAddStatus=" + siteAddStatus); LOG.debug("siteAddStatusId=" + siteAddStatusId);
   * 
   * siteAccount.setCode(code); siteAccount.setSiteRefreshStatus(siteRefreshStatus);
   * siteAccount.setSiteRefreshStatusId(siteRefreshStatusId);
   * siteAccount.setSiteAddStatus(siteAddStatus); siteAccount.setSiteAddStatusId(siteAddStatusId);
   * 
   * } catch (Exception e) { e.printStackTrace(); } finally {
   * httpclient.getConnectionManager().shutdown(); } return siteAccount;
   * 
   * }
   */

  public SiteAccount refreshTheSite(String cobrandSessionToken, String userSessionToken, SiteAccount siteAccount,
      String siteId) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + SITE_REFRESH;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(paramNameUserSessionToken, userSessionToken);
      pm.addParameter(memSiteAccId, siteAccount.getSiteAccountId());

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();
      LOG.debug(source);

      JSONObject jsonObject = new JSONObject(source);
      String code = jsonObject.getString("code");
      JSONObject siteRefreshStatusJson = jsonObject.getJSONObject("siteRefreshStatus");
      String siteRefreshStatusId = siteRefreshStatusJson.getString("siteRefreshStatusId");
      String siteRefreshStatus = siteRefreshStatusJson.getString("siteRefreshStatus");

      JSONObject siteAddStatusJson = jsonObject.getJSONObject("siteAddStatus");
      String siteAddStatus = siteAddStatusJson.getString("siteAddStatus");
      String siteAddStatusId = siteAddStatusJson.getString("siteAddStatusId");

      String suggestedFlow = null;
      String suggestedFlowReason = null;
      String memItemId = null;
      // TODO can be multiple items
      try {
        JSONArray itemRefreshInfoJsonArray = jsonObject.getJSONArray("itemRefreshInfo");
        JSONObject itemRefreshInfoJson = itemRefreshInfoJsonArray.getJSONObject(0);
        memItemId = itemRefreshInfoJson.getString("memItemId");
        JSONObject itemSuggestedFlowJson = itemRefreshInfoJson.getJSONObject("itemSuggestedFlow");
        JSONObject itemSuggestedFlowReasonJson = itemRefreshInfoJson.getJSONObject("itemSuggestedFlowReason");
        suggestedFlow = itemSuggestedFlowJson.getString("suggestedFlow");
        suggestedFlowReason = itemSuggestedFlowReasonJson.getString("suggestedFlowReason");
      } catch (JSONException jsonException) {
        LOG.error(jsonException.getMessage());
        LOG.error(jsonException.getCause().toString());
        LOG.error("itemRefreshInfo might be missing It is OK ");
      }

      LOG.debug("code=" + code);

      LOG.debug("siteRefreshStatusId=" + siteRefreshStatusId);
      LOG.debug("siteRefreshStatus=" + siteRefreshStatus);

      LOG.debug("siteAddStatus=" + siteAddStatus);
      LOG.debug("siteAddStatusId=" + siteAddStatusId);
      LOG.debug("memItemId=" + memItemId);

      LOG.debug("suggestedFlow=" + suggestedFlow);

      LOG.debug("suggestedFlowReason=" + suggestedFlowReason);

      siteAccount.setCode(code);
      siteAccount.setSiteRefreshStatus(siteRefreshStatus);
      siteAccount.setSiteRefreshStatusId(siteRefreshStatusId);
      siteAccount.setSiteAddStatus(siteAddStatus);
      siteAccount.setSiteAddStatusId(siteAddStatusId);
      siteAccount.setMemItemId(memItemId);
      siteAccount.setSuggestedFlow(suggestedFlow);
      siteAccount.setSuggestedFlowReason(suggestedFlowReason);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return siteAccount;

  }



  public SiteAccount getItemSummariesForSite(String cobrandSessionToken, String userSessionToken,
      SiteAccount siteAccount) {

    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + ITEM_SUMMARIES;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(paramNameUserSessionToken, userSessionToken);
      pm.addParameter(memSiteAccId, siteAccount.getSiteAccountId());

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      // String source = pm.getResponseBodyAsString();
      // LOG.debug(source);

      InputStream is = pm.getResponseBodyAsStream();
      InputStreamReader isr = new InputStreamReader(is);
      StringBuilder sb = new StringBuilder();
      BufferedReader br = new BufferedReader(isr);
      String read = br.readLine();

      while (read != null) {
        // LOG.debug(read);
        sb.append(read);
        read = br.readLine();
      }
      String source = sb.toString();
      LOG.debug("source=" + source);


      // JSONObject jsonArray = new JSONObject(source);

      JSONArray jsonArray = new JSONArray(source);
      JSONObject jsonObject = (JSONObject) jsonArray.get(0); // Level 0

      JSONObject contentServiceInfoJson = jsonObject.getJSONObject("contentServiceInfo"); // Level 1
      JSONObject containerInfoJson = contentServiceInfoJson.getJSONObject("containerInfo");
      String containerName = containerInfoJson.getString("containerName");
      System.out.println("containerName=" + containerName);

      String itemDisplayName = jsonObject.getString("itemDisplayName");
      System.out.println("itemDisplayName=" + itemDisplayName);


      JSONObject refreshInfoJson = jsonObject.getJSONObject("refreshInfo");
      String statusCode = refreshInfoJson.getString("statusCode");
      System.out.println("statusCode=" + statusCode);

      JSONObject itemDataJson = jsonObject.getJSONObject("itemData");
      JSONArray accountsJsonArray = itemDataJson.getJSONArray("accounts");
      JSONObject accountJsonObject = (JSONObject) accountsJsonArray.get(0);
      String accountNumber = accountJsonObject.getString("accountNumber");
      String accountName = accountJsonObject.getString("accountName");
      String investmentAccountId = accountJsonObject.getString("investmentAccountId");
      String link = accountJsonObject.getString("link");
      String accountHolder = accountJsonObject.getString("accountHolder");
      String planName = accountJsonObject.getString("planName");

      JSONObject cashJson = accountJsonObject.getJSONObject("cash");
      String amount = cashJson.getString("amount");
      String currencyCode = cashJson.getString("currencyCode");


      JSONObject totalBalanceJson = accountJsonObject.getJSONObject("totalBalance");
      String totalBalance_amount = totalBalanceJson.getString("amount");
      String totalBalance_currencyCode = totalBalanceJson.getString("currencyCode");


      JSONObject totalVestedBalanceJson = accountJsonObject.getJSONObject("totalVestedBalance");
      String totalVestedBalance_amount = totalVestedBalanceJson.getString("amount");
      String totalVestedBalance_currencyCode = totalVestedBalanceJson.getString("currencyCode");


      JSONObject fundsOwedJson = accountJsonObject.getJSONObject("fundsOwed");
      String fundsOwed_amount = fundsOwedJson.getString("amount");
      String fundsOwed_currencyCode = fundsOwedJson.getString("currencyCode");


      JSONObject totalAccountBalanceJson = accountJsonObject.getJSONObject("totalAccountBalance");
      String totalAccountBalance_amount = totalAccountBalanceJson.getString("amount");
      String totalAccountBalance_currencyCode = totalAccountBalanceJson.getString("currencyCode");



      System.out.println("accountName=" + accountName);
      System.out.println("accountNumber=" + accountNumber);
      System.out.println("investmentAccountId=" + investmentAccountId);
      System.out.println("link=" + link);
      System.out.println("accountHolder=" + accountHolder);
      System.out.println("planName=" + planName);
      System.out.println("cash_amount=" + amount);
      System.out.println("cash_currencyCode=" + currencyCode);
      System.out.println("totalBalance_amount=" + totalBalance_amount);
      System.out.println("totalBalance_currencyCode=" + totalBalance_currencyCode);
      System.out.println("totalVestedBalance_amount=" + totalVestedBalance_amount);
      System.out.println("totalVestedBalance_currencyCode=" + totalVestedBalance_currencyCode);
      System.out.println("fundsOwed_amount=" + fundsOwed_amount);
      System.out.println("fundsOwed_currencyCode=" + fundsOwed_currencyCode);
      System.out.println("totalAccountBalance_amount=" + totalAccountBalance_amount);
      System.out.println("totalAccountBalance_currencyCode=" + totalAccountBalance_currencyCode);



    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return siteAccount;

  }


  public SiteAccount startSiteRefresh(String cobrandSessionToken, String userSessionToken, SiteAccount siteAccount) {

    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = HOST_URI + ITEM_SUMMARIES;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(paramNameUserSessionToken, userSessionToken);
      pm.addParameter(memSiteAccId, siteAccount.getSiteAccountId());
      pm.addParameter("refreshParameters.refreshPriority", "1");
      // pm.addParameter("refreshParameters.refreshMode.refreshModeId", "2");



      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      // String source = pm.getResponseBodyAsString();
      // LOG.debug(source);

      InputStream is = pm.getResponseBodyAsStream();
      InputStreamReader isr = new InputStreamReader(is);
      StringBuilder sb = new StringBuilder();
      BufferedReader br = new BufferedReader(isr);
      String read = br.readLine();

      while (read != null) {
        // LOG.debug(read);
        sb.append(read);
        read = br.readLine();
      }
      String source = sb.toString();
      LOG.debug("source=" + source);


      JSONArray jsonArray = new JSONArray(source);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return siteAccount;


  }

  private static class NullHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
      return true;
    }
  }

}
