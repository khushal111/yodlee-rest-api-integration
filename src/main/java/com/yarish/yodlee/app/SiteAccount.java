package com.yarish.yodlee.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

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

public class SiteAccount {
  private String siteId;
  private String siteAccountId;
  private String siteAddStatus;
  private String siteAddStatusId;
  private String siteRefreshStatus;
  private String siteRefreshStatusId;
  private String code;
  private String suggestedFlowId;
  private String suggestedFlow;
  private String memItemId; // TODO it can be many
  private String suggestedFlowReason;
  private LoginFormInfo loginFormInfo;
  private InvestmentData investmentData;


  public InvestmentData getInvestmentData() {
    return investmentData;
  }

  public void setInvestmentData(InvestmentData investmentData) {
    this.investmentData = investmentData;
  }

  public SiteAccount() {
    super();
  }

  public LoginFormInfo getLoginFormInfo() {
    return loginFormInfo;
  }

  public void setLoginFormInfo(LoginFormInfo loginFormInfo) {
    this.loginFormInfo = loginFormInfo;
  }

  public String getSiteId() {
    return siteId;
  }

  public void setSiteId(String siteId) {
    this.siteId = siteId;
  }

  public String getSiteAccountId() {
    return siteAccountId;
  }

  public void setSiteAccountId(String siteAccountId) {
    this.siteAccountId = siteAccountId;
  }

  public String getSiteAddStatus() {
    return siteAddStatus;
  }

  public void setSiteAddStatus(String siteAddStatus) {
    this.siteAddStatus = siteAddStatus;
  }

  public String getSiteAddStatusId() {
    return siteAddStatusId;
  }

  public void setSiteAddStatusId(String siteAddStatusId) {
    this.siteAddStatusId = siteAddStatusId;
  }

  public String getSiteRefreshStatus() {
    return siteRefreshStatus;
  }

  public void setSiteRefreshStatus(String siteRefreshStatus) {
    this.siteRefreshStatus = siteRefreshStatus;
  }

  public String getSiteRefreshStatusId() {
    return siteRefreshStatusId;
  }

  public void setSiteRefreshStatusId(String siteRefreshStatusId) {
    this.siteRefreshStatusId = siteRefreshStatusId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSuggestedFlowId() {
    return suggestedFlowId;
  }

  public void setSuggestedFlowId(String suggestedFlowId) {
    this.suggestedFlowId = suggestedFlowId;
  }

  public String getSuggestedFlow() {
    return suggestedFlow;
  }

  public void setSuggestedFlow(String suggestedFlow) {
    this.suggestedFlow = suggestedFlow;
  }

  public String getMemItemId() {
    return memItemId;
  }

  public void setMemItemId(String memItemId) {
    this.memItemId = memItemId;
  }

  public String getSuggestedFlowReason() {
    return suggestedFlowReason;
  }

  public void setSuggestedFlowReason(String suggestedFlowReason) {
    this.suggestedFlowReason = suggestedFlowReason;
  }



  private final static Logger LOG = LoggerFactory.getLogger(SiteAccount.class);


  public LoginFormInfo getSiteLoginForm(String cobrandSessionToken, String siteId) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.GET_SITE_LOGIN_FORM;
    loginFormInfo = new LoginFormInfo();

    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);
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

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return loginFormInfo;

  }

  public boolean searchSite(String cobrandSessionToken, String userSessionToken, String siteSearchString) {
    boolean result = false;
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.SEARCH_SITE_URL;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(Constants.paramNameUserSessionToken, userSessionToken);
      pm.addParameter("siteSearchString", siteSearchString);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);
      String source = pm.getResponseBodyAsString();
      LOG.debug(source);
      result = true;
      // result http://pastebin.com/kSCCxzJr - BoA
      // result http://pastebin.com/ - BoA
      // http://pastebin.com/HntSqVx7 - ICICI Bank

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

  public void getSiteInfo(String cobrandSessionToken, String userSessionToken, String reqSpecifier, String siteId) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.GET_SITE_INFO;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);
      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(Constants.paramNameUserSessionToken, userSessionToken);
      pm.addParameter("siteFilter.reqSpecifier", reqSpecifier);
      pm.addParameter("siteFilter.siteId", siteId);

      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();

      LOG.debug(source);
      // result http://pastebin.com/qjc6YkSw // BoA
      // result http://pastebin.com/dFxAMheG // ICICI

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return;
  }


  public SiteAccount addSiteAccount(String cobrandSessionToken, String userSessionToken, LoginFormInfo loginFormInfo,
      String userName, String password, String siteId) {


    String siteAccountId = null;
    String siteRefreshStatus = null;
    String suggestedFlow = null;
    String suggestedFlowId = null;
    String code = null;

    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.ADD_SITE_ACC;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(Constants.paramNameUserSessionToken, userSessionToken);

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

      this.siteAccountId = siteAccountId;
      this.siteRefreshStatus = siteRefreshStatus;
      this.code = code;
      this.suggestedFlowId = suggestedFlowId;
      this.suggestedFlow = suggestedFlow;

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }

    return this;
  }


  public SiteAccount refreshTheSite(String cobrandSessionToken, String userSessionToken, String siteId) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.SITE_REFRESH;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(Constants.paramNameUserSessionToken, userSessionToken);
      pm.addParameter(Constants.memSiteAccId, this.siteAccountId);

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

      this.code = code;
      this.siteRefreshStatus = siteRefreshStatus;
      this.siteRefreshStatusId = siteRefreshStatusId;
      this.siteAddStatus = siteAddStatus;
      this.siteAddStatusId = siteAddStatusId;
      this.memItemId = memItemId;
      this.suggestedFlow = suggestedFlow;
      this.suggestedFlowReason = suggestedFlowReason;


    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return this;

  }



  public SiteAccount getItemSummariesForSite(String cobrandSessionToken, String userSessionToken) {

    DefaultHttpClient httpclient = new DefaultHttpClient();
    InvestmentData investmentData = new InvestmentData();

    String url = Constants.HOST_URI + Constants.ITEM_SUMMARIES;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(Constants.paramNameUserSessionToken, userSessionToken);
      pm.addParameter(Constants.memSiteAccId, this.siteAccountId);

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
      JSONObject jsonObject = (JSONObject) jsonArray.get(0); // Level 0

      String itemId = jsonObject.getString("itemId");
      System.out.println("itemId=" + itemId);
      investmentData.setItemId(Integer.parseInt(itemId));
      investmentData.setUserId(102); // TODO link it 
      
      
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
      investmentData.setAccountNumber(accountNumber);
      String accountName = accountJsonObject.getString("accountName");
      investmentData.setAccountName(accountName);
      String investmentAccountId = accountJsonObject.getString("investmentAccountId");
      String link = accountJsonObject.getString("link");
      String accountHolder = accountJsonObject.getString("accountHolder");
      investmentData.setAccountHolder(accountHolder);
      String planName = accountJsonObject.getString("planName");

      JSONObject accountClassificationJson = accountJsonObject.getJSONObject("accountClassification");
      String accountClassificationId = accountClassificationJson.getString("accountClassificationId");
      String accountClassification = accountClassificationJson.getString("accountClassification");
      investmentData.setAccountClassification(accountClassification);
      System.out.println("accountClassificationId=" + accountClassificationId);
      System.out.println("accountClassification=" + accountClassification);

      JSONObject asofDateJson = accountJsonObject.getJSONObject("asofDate");
      String asofDate = asofDateJson.getString("date");
      System.out.println("asofDate=" + asofDate);
      investmentData.setAsOf(new Date()); // TODO convert string to date 



      JSONObject cashJson = accountJsonObject.getJSONObject("cash");
      String cash_amount = cashJson.getString("amount");
      String currencyCode = cashJson.getString("currencyCode");
      investmentData.setCash(Double.parseDouble(cash_amount));


      JSONObject totalBalanceJson = accountJsonObject.getJSONObject("totalBalance");
      String totalBalance_amount = totalBalanceJson.getString("amount");
      String totalBalance_currencyCode = totalBalanceJson.getString("currencyCode");
      investmentData.setTotalBalance(Double.parseDouble(totalBalance_amount));

      JSONObject shortBalanceJson = accountJsonObject.getJSONObject("shortBalance");
      String shortBalance_amount = shortBalanceJson.getString("amount");
      String shortBalance_currencyCode = shortBalanceJson.getString("currencyCode");
      System.out.println("shortBalance=" + shortBalance_amount);
      investmentData.setShortBalance(Double.parseDouble(shortBalance_amount));

      JSONObject totalUnvestedBalanceJson = accountJsonObject.getJSONObject("totalUnvestedBalance");
      String totalUnvestedBalanceJson_amount = totalUnvestedBalanceJson.getString("amount");
      String totalUnvestedBalanceJson_currencyCode = totalUnvestedBalanceJson.getString("currencyCode");
      System.out.println("totalUnvestedBalanceJson=" + totalUnvestedBalanceJson_amount);
      investmentData.setTotalUnVestedBalance(Double.parseDouble(totalUnvestedBalanceJson_amount));
      
      JSONObject t401Json = accountJsonObject.getJSONObject("loan_401k");
      String t401_amount = t401Json.getString("amount");
      String t401currencyCode = t401Json.getString("currencyCode");
      System.out.println("loan_401k=" + t401_amount);
      investmentData.setT401kLoan(Double.parseDouble(t401_amount));

      String planNumber = accountJsonObject.getString("planNumber");
      System.out.println("planNumber=" + planNumber);
      investmentData.setPlanNumber(planNumber);

      JSONObject marginBalanceJson = accountJsonObject.getJSONObject("marginBalance");
      String marginBalance_amount = marginBalanceJson.getString("amount");
      String marginBalance_currencyCode = marginBalanceJson.getString("currencyCode");
      System.out.println("marginBalance=" + marginBalance_amount);
      investmentData.setMarginBalance(Double.parseDouble(marginBalance_amount));

      JSONObject moneyMarketBalanceJson = accountJsonObject.getJSONObject("moneyMarketBalance");
      String moneyMarketBalance_amount = moneyMarketBalanceJson.getString("amount");
      String moneyMarketBalance_currencyCode = moneyMarketBalanceJson.getString("currencyCode");
      System.out.println("moneyMarketBalance=" + moneyMarketBalance_amount);
      investmentData.setMoneyMarketBalance(Double.parseDouble(moneyMarketBalance_amount));

      JSONObject totalVestedBalanceJson = accountJsonObject.getJSONObject("totalVestedBalance");
      String totalVestedBalance_amount = totalVestedBalanceJson.getString("amount");
      String totalVestedBalance_currencyCode = totalVestedBalanceJson.getString("currencyCode");
      investmentData.setTotalVestedBalance(Double.parseDouble(totalVestedBalance_amount));


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
      investmentData.setPlanName(planName);
      System.out.println("cash_amount=" + cash_amount);
      System.out.println("cash_currencyCode=" + currencyCode);
      System.out.println("totalBalance_amount=" + totalBalance_amount);
      System.out.println("totalBalance_currencyCode=" + totalBalance_currencyCode);
      System.out.println("totalVestedBalance_amount=" + totalVestedBalance_amount);
      System.out.println("totalVestedBalance_currencyCode=" + totalVestedBalance_currencyCode);
      System.out.println("fundsOwed_amount=" + fundsOwed_amount);
      System.out.println("fundsOwed_currencyCode=" + fundsOwed_currencyCode);
      System.out.println("totalAccountBalance_amount=" + totalAccountBalance_amount);
      System.out.println("totalAccountBalance_currencyCode=" + totalAccountBalance_currencyCode);


      JSONArray holdingsJSONArray = accountJsonObject.getJSONArray("holdings");
      System.out.println("holdingsJSONArray.length()=" + holdingsJSONArray.length());;

      JSONObject holdingElementJson;

      for (int i = 0; i < holdingsJSONArray.length(); i++) {

        holdingElementJson = (JSONObject) holdingsJSONArray.get(i);
        String holdingId = holdingElementJson.getString("holdingId");
        System.out.println("holdingId=" + holdingId);

        String investmentAccountId1 = holdingElementJson.getString("investmentAccountId");
        System.out.println("holdingId=" + investmentAccountId1);

        JSONObject lastContributionJson = holdingElementJson.getJSONObject("lastContribution");
        String lastContributionAmount = lastContributionJson.getString("amount");
        System.out.println("lastContributionAmount=" + lastContributionAmount);

        String callTypeId = holdingElementJson.getString("callTypeId");
        System.out.println("callTypeId=" + callTypeId);
        String callType = holdingElementJson.getString("callType");
        System.out.println("callType=" + callType);

        String localizedCallType = holdingElementJson.getString("localizedCallType");
        System.out.println("localizedCallType=" + localizedCallType);

        // JSONObject faceValueJson = holdingElementJson.getJSONObject("faceValue");
        // String faceValueAmount = faceValueJson.getString("amount");
        // System.out.println("faceValueAmount=" + faceValueAmount);

        String percentAllocation = holdingElementJson.getString("percentAllocation");
        System.out.println("percentAllocation=" + percentAllocation);

        String daysRemaining = "";
        if (holdingElementJson.has("daysRemaining")) {
          daysRemaining = holdingElementJson.getString("daysRemaining");
        }
        System.out.println("daysRemaining=" + daysRemaining);

        String term = "";
        if (holdingElementJson.has("term")) {
          term = holdingElementJson.getString("term");
        }
        System.out.println("term=" + term);

        String interestRate = "";
        if (holdingElementJson.has("term")) {
          interestRate = holdingElementJson.getString("interestRate");
        }
        System.out.println("interestRate=" + interestRate);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    
    this.setInvestmentData(investmentData);
    return this;

  }


  public SiteAccount startSiteRefresh(String cobrandSessionToken, String userSessionToken) {

    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.ITEM_SUMMARIES;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(Constants.paramNameUserSessionToken, userSessionToken);
      pm.addParameter(Constants.memSiteAccId, this.siteAccountId);
      pm.addParameter("refreshParameters.refreshPriority", "1");
      // pm.addParameter("refreshParameters.refreshMode.refreshModeId", "2");



      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);


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
      LOG.debug("startSiteRefresh () source=" + source);
      JSONArray jsonArray = new JSONArray(source);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return this;
  }


}
