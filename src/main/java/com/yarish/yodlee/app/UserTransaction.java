package com.yarish.yodlee.app;

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



public class UserTransaction {
  private final static Logger LOG = LoggerFactory.getLogger(UserTransaction.class);


  public UserTransaction getuserTransaction(String cobrandSessionToken, String userSessionToken) {
    DefaultHttpClient httpclient = new DefaultHttpClient();

    String url = Constants.HOST_URI + Constants.USER_TRANSACTION;
    try {
      HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());

      PostMethod pm = new PostMethod(url);

      pm.addParameter(Constants.paramNameCobSessionToken, cobrandSessionToken);
      pm.addParameter(Constants.paramNameUserSessionToken, userSessionToken);
      pm.addParameter(Constants.containerType, "stocks");
      // pm.addParameter(Constants.containerType, "all");
      pm.addParameter(Constants.higherFetchLimit, "500");
      pm.addParameter(Constants.lowerFetchLimit, "1");
      pm.addParameter(Constants.endNumber, "500");
      pm.addParameter(Constants.startNumber, "1");
      pm.addParameter(Constants.currencyCode, "USD");
      pm.addParameter(Constants.ignoreUserInput, "true");


      HttpClient hc = new HttpClient();
      hc.executeMethod(pm);

      String source = pm.getResponseBodyAsString();
      LOG.debug(source);

      JSONObject jsonObject = new JSONObject(source);
      LOG.debug("jsonObject=" + jsonObject);

      JSONObject searchResultJson = jsonObject.getJSONObject("searchResult");
      JSONArray transactionsArrayJson = searchResultJson.getJSONArray("transactions");
      int noOfTransactions = transactionsArrayJson.length();
      LOG.debug("noOfTransactions count=" + noOfTransactions);


      for (int i = 0; i < noOfTransactions; i++) {
        LOG.debug("=====================================================================================================================");
        LOG.debug("transaction=" + i);
        JSONObject transactionJson = transactionsArrayJson.getJSONObject(i);
        JSONObject viewKeyJson = transactionJson.getJSONObject("viewKey");
        String containerType = viewKeyJson.getString("containerType");
        String transactionId = viewKeyJson.getString("transactionId");
        LOG.debug("containerType=" + containerType);
        LOG.debug("transactionId=" + transactionId);


        String postDate = transactionJson.getString("postDate");
        LOG.debug("postDate=" + postDate);
        String link = transactionJson.getString("link");
        LOG.debug("link=" + link);

        JSONObject amountJson = transactionJson.getJSONObject("amount");
        String amount = amountJson.getString("amount");
        String currencyCode = amountJson.getString("currencyCode");
        LOG.debug("amount=" + amount);
        LOG.debug("currencyCode=" + currencyCode);

        String transactionBaseType = transactionJson.getString("transactionBaseType");
        LOG.debug("transactionBaseType=" + transactionBaseType);
        String isClosingTxn = transactionJson.getString("isClosingTxn");
        LOG.debug("isClosingTxn=" + isClosingTxn);

        JSONObject investmentTransactionViewJson = transactionJson.getJSONObject("investmentTransactionView");
        String cusipNumber = investmentTransactionViewJson.getString("cusipNumber");
        String netCost = investmentTransactionViewJson.getString("netCost");
        String symbol = investmentTransactionViewJson.getString("symbol");
        LOG.debug("cusipNumber=" + cusipNumber);
        LOG.debug("netCost=" + netCost);
        LOG.debug("symbol=" + symbol);

        JSONObject holdingTypeJson = investmentTransactionViewJson.getJSONObject("holdingType");
        String holdingTypeId = holdingTypeJson.getString("holdingTypeId");
        LOG.debug("holdingTypeId=" + holdingTypeId);

        String isPersonal = transactionJson.getString("isPersonal");
        LOG.debug("isPersonal=" + isPersonal);

        JSONObject descriptionJson = transactionJson.getJSONObject("description");
        String viewPref = descriptionJson.getString("viewPref");
        String description = descriptionJson.getString("description");
        String isOlbUserDescription = descriptionJson.getString("isOlbUserDescription");
        LOG.debug("viewPref=" + viewPref);
        LOG.debug("description=" + description);
        LOG.debug("isOlbUserDescription=" + isOlbUserDescription);

        String quantity = transactionJson.getString("quantity");
        String isMedical = transactionJson.getString("isMedical");
        String transactionSearchResultType = transactionJson.getString("transactionSearchResultType");
        String transactionBaseTypeId = transactionJson.getString("transactionBaseTypeId");
        LOG.debug("quantity=" + quantity);
        LOG.debug("isMedical=" + isMedical);
        LOG.debug("transactionSearchResultType=" + transactionSearchResultType);
        LOG.debug("transactionBaseTypeId=" + transactionBaseTypeId);

        JSONObject statusJson = transactionJson.getJSONObject("status");
        String statusId = statusJson.getString("statusId");
        String status_description = statusJson.getString("description");
        String localizedDescription = statusJson.getString("localizedDescription");
        LOG.debug("statusId=" + statusId);
        LOG.debug("status_description=" + status_description);
        LOG.debug("localizedDescription=" + localizedDescription);

        String localizedTransactionBaseType = transactionJson.getString("localizedTransactionBaseType");
        String transactionDate = transactionJson.getString("transactionDate");
        String runningBalance = transactionJson.getString("runningBalance");
        String classUpdationSource = transactionJson.getString("classUpdationSource");
        String siteTransactionId = transactionJson.getString("siteTransactionId");
        String transactionType = transactionJson.getString("transactionType");
        LOG.debug("localizedTransactionBaseType=" + localizedTransactionBaseType);
        LOG.debug("transactionDate=" + transactionDate);
        LOG.debug("runningBalance=" + runningBalance);
        LOG.debug("classUpdationSource=" + classUpdationSource);
        LOG.debug("siteTransactionId=" + siteTransactionId);
        LOG.debug("transactionType=" + transactionType);

        JSONObject categoryJson = transactionJson.getJSONObject("category");
        String categoryName = categoryJson.getString("categoryName");
        String localizedCategoryName = categoryJson.getString("localizedCategoryName");
        String categoryId = categoryJson.getString("categoryId");
        String isBusiness = categoryJson.getString("isBusiness");
        LOG.debug("categoryName=" + categoryName);
        LOG.debug("localizedCategoryName=" + localizedCategoryName);
        LOG.debug("categoryId=" + categoryId);
        LOG.debug("isBusiness=" + isBusiness);

        JSONObject priceJson = transactionJson.getJSONObject("price");
        String price_amount = priceJson.getString("amount");
        String price_currency_code = priceJson.getString("currencyCode");
        LOG.debug("price_amount=" + price_amount);
        LOG.debug("price_currency_code=" + price_currency_code);

        String isReimbursable = transactionJson.getString("isReimbursable");
        String transactionTypeId = transactionJson.getString("transactionTypeId");
        LOG.debug("isReimbursable=" + isReimbursable);
        LOG.debug("transactionTypeId=" + transactionTypeId);

        JSONObject accountJson = transactionJson.getJSONObject("account");
        String isAccountName = accountJson.getString("isAccountName");
        String accountNumber = accountJson.getString("accountNumber");
        String accountName = accountJson.getString("accountName");
        LOG.debug("isAccountName=" + isAccountName);
        LOG.debug("accountNumber=" + accountNumber);
        LOG.debug("accountName=" + accountName);

        JSONObject accountBalanceJson = accountJson.getJSONObject("accountBalance");
        String account_balance_amount = accountBalanceJson.getString("amount");
        String account_balance_currencyCode = accountBalanceJson.getString("currencyCode");
        LOG.debug("account_balance_amount=" + account_balance_amount);
        LOG.debug("account_balance_currencyCode=" + account_balance_currencyCode);


        String itemAccountId = accountJson.getString("itemAccountId");
        String decryptionStatus = accountJson.getString("decryptionStatus");
        String itemAccountStatusId = accountJson.getString("itemAccountStatusId");
        String siteName = accountJson.getString("siteName");
        LOG.debug("itemAccountId=" + itemAccountId);
        LOG.debug("decryptionStatus=" + decryptionStatus);
        LOG.debug("itemAccountStatusId=" + itemAccountStatusId);
        LOG.debug("siteName=" + siteName);


        JSONObject accountDisplayNameJson = accountJson.getJSONObject("accountDisplayName");
        String defaultNormalAccountName = accountDisplayNameJson.getString("defaultNormalAccountName");
        LOG.debug("defaultNormalAccountName=" + defaultNormalAccountName);

        String accessLevelRequired = transactionJson.getString("accessLevelRequired");
        String isBusiness2 = transactionJson.getString("isBusiness");
        LOG.debug("accessLevelRequired=" + accessLevelRequired);
        LOG.debug("isBusiness=" + isBusiness2);
      }



    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return this;

  }

  private static class NullHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
      return true;
    }
  }

  public static void main(String[] args) throws JSONException {

    String jsonString =
        "{\n\t\"searchResult\": {\n\t\t\"transactions\": [{\n\t\t\t\"memo\": {},\n\t\t\t\"viewKey\": {\n\t\t\t\t\"containerType\": \"stocks\",\n\t\t\t\t\"isParentMatch\": false,\n\t\t\t\t\"transactionId\": 10213422,\n\t\t\t\t\"isSystemGeneratedSplit\": false,\n\t\t\t\t\"transactionCount\": 6,\n\t\t\t\t\"rowNumber\": 1\n\t\t\t},\n\t\t\t\"isTaxable\": false,\n\t\t\t\"postDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"link\": \"http://www.altova.com\",\n\t\t\t\"categorisationSourceId\": 0,\n\t\t\t\"transactionPostingOrder\": 0,\n\t\t\t\"amount\": {\n\t\t\t\t\"amount\": 123456,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isClosingTxn\": 0,\n\t\t\t\"transactionBaseType\": \"credit\",\n\t\t\t\"investmentTransactionView\": {\n\t\t\t\t\"cusipNumber\": \"999999999\",\n\t\t\t\t\"lotHandling\": {\n\t\t\t\t\t\"lotHandlingId\": 0\n\t\t\t\t},\n\t\t\t\t\"netCost\": 0,\n\t\t\t\t\"symbol\": \"Transaction2\",\n\t\t\t\t\"holdingType\": {\n\t\t\t\t\t\"holdingTypeId\": 0\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"isPersonal\": false,\n\t\t\t\"description\": {\n\t\t\t\t\"viewPref\": false,\n\t\t\t\t\"description\": \"transdescription\",\n\t\t\t\t\"isOlbUserDescription\": false\n\t\t\t},\n\t\t\t\"quantity\": 2345,\n\t\t\t\"isMedical\": false,\n\t\t\t\"transactionSearchResultType\": \"aggregated\",\n\t\t\t\"transactionBaseTypeId\": 1,\n\t\t\t\"status\": {\n\t\t\t\t\"statusId\": 1,\n\t\t\t\t\"description\": \"posted\",\n\t\t\t\t\"localizedDescription\": \"posted\"\n\t\t\t},\n\t\t\t\"localizedTransactionBaseType\": \"credit\",\n\t\t\t\"checkNumber\": {},\n\t\t\t\"transactionDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"runningBalance\": 0,\n\t\t\t\"classUpdationSource\": \"S\",\n\t\t\t\"siteTransactionId\": \"258369\",\n\t\t\t\"transactionType\": \"loanPayment\",\n\t\t\t\"category\": {\n\t\t\t\t\"categoryName\": \"Uncategorized\",\n\t\t\t\t\"categoryTypeId\": 1,\n\t\t\t\t\"localizedCategoryName\": \"Uncategorized\",\n\t\t\t\t\"categoryId\": 1,\n\t\t\t\t\"isBusiness\": false\n\t\t\t},\n\t\t\t\"price\": {\n\t\t\t\t\"amount\": 123,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isReimbursable\": false,\n\t\t\t\"transactionTypeId\": 202,\n\t\t\t\"account\": {\n\t\t\t\t\"isAccountName\": 1,\n\t\t\t\t\"accountNumber\": \"xxxx5555\",\n\t\t\t\t\"accountName\": \"DAG INVESTMENT\",\n\t\t\t\t\"accountBalance\": {\n\t\t\t\t\t\"amount\": 79629,\n\t\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t\t},\n\t\t\t\t\"itemAccountId\": 10172169,\n\t\t\t\t\"decryptionStatus\": true,\n\t\t\t\t\"sumInfoId\": 11201,\n\t\t\t\t\"itemAccountStatusId\": 1,\n\t\t\t\t\"accountDisplayName\": {\n\t\t\t\t\t\"defaultNormalAccountName\": \"DagInvestments - DAG INVESTMENT\"\n\t\t\t\t},\n\t\t\t\t\"siteName\": \"DagInvestments\"\n\t\t\t},\n\t\t\t\"accessLevelRequired\": 1,\n\t\t\t\"isBusiness\": false\n\t\t}, {\n\t\t\t\"memo\": {},\n\t\t\t\"viewKey\": {\n\t\t\t\t\"containerType\": \"stocks\",\n\t\t\t\t\"isParentMatch\": false,\n\t\t\t\t\"transactionId\": 10213421,\n\t\t\t\t\"isSystemGeneratedSplit\": false,\n\t\t\t\t\"transactionCount\": 6,\n\t\t\t\t\"rowNumber\": 2\n\t\t\t},\n\t\t\t\"isTaxable\": false,\n\t\t\t\"postDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"link\": \"http://www.altova.com\",\n\t\t\t\"categorisationSourceId\": 15,\n\t\t\t\"transactionPostingOrder\": 0,\n\t\t\t\"amount\": {\n\t\t\t\t\"amount\": 123456,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isClosingTxn\": 0,\n\t\t\t\"transactionBaseType\": \"credit\",\n\t\t\t\"investmentTransactionView\": {\n\t\t\t\t\"cusipNumber\": \"999999999\",\n\t\t\t\t\"lotHandling\": {\n\t\t\t\t\t\"lotHandlingId\": 0\n\t\t\t\t},\n\t\t\t\t\"netCost\": 0,\n\t\t\t\t\"symbol\": \"Transaction2\",\n\t\t\t\t\"holdingType\": {\n\t\t\t\t\t\"holdingTypeId\": 0\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"isPersonal\": false,\n\t\t\t\"description\": {\n\t\t\t\t\"viewPref\": false,\n\t\t\t\t\"description\": \"transdescription\",\n\t\t\t\t\"isOlbUserDescription\": false\n\t\t\t},\n\t\t\t\"quantity\": 2345,\n\t\t\t\"isMedical\": false,\n\t\t\t\"transactionSearchResultType\": \"aggregated\",\n\t\t\t\"transactionBaseTypeId\": 1,\n\t\t\t\"status\": {\n\t\t\t\t\"statusId\": 1,\n\t\t\t\t\"description\": \"posted\",\n\t\t\t\t\"localizedDescription\": \"posted\"\n\t\t\t},\n\t\t\t\"localizedTransactionBaseType\": \"credit\",\n\t\t\t\"checkNumber\": {},\n\t\t\t\"localizedTransactionType\": \"sell\",\n\t\t\t\"transactionDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"runningBalance\": 0,\n\t\t\t\"classUpdationSource\": \"S\",\n\t\t\t\"siteTransactionId\": \"258369\",\n\t\t\t\"transactionType\": \"sell\",\n\t\t\t\"category\": {\n\t\t\t\t\"categoryName\": \"Securities Trades\",\n\t\t\t\t\"categoryTypeId\": 4,\n\t\t\t\t\"localizedCategoryName\": \"Securities Trades\",\n\t\t\t\t\"categoryId\": 36,\n\t\t\t\t\"isBusiness\": false\n\t\t\t},\n\t\t\t\"price\": {\n\t\t\t\t\"amount\": 123,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isReimbursable\": false,\n\t\t\t\"transactionTypeId\": 4,\n\t\t\t\"account\": {\n\t\t\t\t\"isAccountName\": 1,\n\t\t\t\t\"accountNumber\": \"xxxx5555\",\n\t\t\t\t\"accountName\": \"DAG INVESTMENT\",\n\t\t\t\t\"accountBalance\": {\n\t\t\t\t\t\"amount\": 79629,\n\t\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t\t},\n\t\t\t\t\"itemAccountId\": 10172169,\n\t\t\t\t\"decryptionStatus\": true,\n\t\t\t\t\"sumInfoId\": 11201,\n\t\t\t\t\"itemAccountStatusId\": 1,\n\t\t\t\t\"accountDisplayName\": {\n\t\t\t\t\t\"defaultNormalAccountName\": \"DagInvestments - DAG INVESTMENT\"\n\t\t\t\t},\n\t\t\t\t\"siteName\": \"DagInvestments\"\n\t\t\t},\n\t\t\t\"accessLevelRequired\": 1,\n\t\t\t\"isBusiness\": false\n\t\t}, {\n\t\t\t\"memo\": {},\n\t\t\t\"viewKey\": {\n\t\t\t\t\"containerType\": \"stocks\",\n\t\t\t\t\"isParentMatch\": false,\n\t\t\t\t\"transactionId\": 10213420,\n\t\t\t\t\"isSystemGeneratedSplit\": false,\n\t\t\t\t\"transactionCount\": 6,\n\t\t\t\t\"rowNumber\": 3\n\t\t\t},\n\t\t\t\"isTaxable\": false,\n\t\t\t\"postDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"link\": \"http://www.altova.com\",\n\t\t\t\"categorisationSourceId\": 15,\n\t\t\t\"transactionPostingOrder\": 0,\n\t\t\t\"amount\": {\n\t\t\t\t\"amount\": 123456,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isClosingTxn\": 0,\n\t\t\t\"transactionBaseType\": \"credit\",\n\t\t\t\"investmentTransactionView\": {\n\t\t\t\t\"cusipNumber\": \"999999999\",\n\t\t\t\t\"lotHandling\": {\n\t\t\t\t\t\"lotHandlingId\": 0\n\t\t\t\t},\n\t\t\t\t\"netCost\": 0,\n\t\t\t\t\"symbol\": \"Transaction2\",\n\t\t\t\t\"holdingType\": {\n\t\t\t\t\t\"holdingTypeId\": 0\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"isPersonal\": false,\n\t\t\t\"description\": {\n\t\t\t\t\"viewPref\": false,\n\t\t\t\t\"description\": \"transdescription\",\n\t\t\t\t\"isOlbUserDescription\": false\n\t\t\t},\n\t\t\t\"quantity\": 2345,\n\t\t\t\"isMedical\": false,\n\t\t\t\"transactionSearchResultType\": \"aggregated\",\n\t\t\t\"transactionBaseTypeId\": 1,\n\t\t\t\"status\": {\n\t\t\t\t\"statusId\": 1,\n\t\t\t\t\"description\": \"posted\",\n\t\t\t\t\"localizedDescription\": \"posted\"\n\t\t\t},\n\t\t\t\"localizedTransactionBaseType\": \"credit\",\n\t\t\t\"checkNumber\": {},\n\t\t\t\"localizedTransactionType\": \"buy\",\n\t\t\t\"transactionDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"runningBalance\": 0,\n\t\t\t\"classUpdationSource\": \"S\",\n\t\t\t\"siteTransactionId\": \"258369\",\n\t\t\t\"transactionType\": \"buy\",\n\t\t\t\"category\": {\n\t\t\t\t\"categoryName\": \"Securities Trades\",\n\t\t\t\t\"categoryTypeId\": 4,\n\t\t\t\t\"localizedCategoryName\": \"Securities Trades\",\n\t\t\t\t\"categoryId\": 36,\n\t\t\t\t\"isBusiness\": false\n\t\t\t},\n\t\t\t\"price\": {\n\t\t\t\t\"amount\": 123,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isReimbursable\": false,\n\t\t\t\"transactionTypeId\": 3,\n\t\t\t\"account\": {\n\t\t\t\t\"isAccountName\": 1,\n\t\t\t\t\"accountNumber\": \"xxxx5555\",\n\t\t\t\t\"accountName\": \"DAG INVESTMENT\",\n\t\t\t\t\"accountBalance\": {\n\t\t\t\t\t\"amount\": 79629,\n\t\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t\t},\n\t\t\t\t\"itemAccountId\": 10172169,\n\t\t\t\t\"decryptionStatus\": true,\n\t\t\t\t\"sumInfoId\": 11201,\n\t\t\t\t\"itemAccountStatusId\": 1,\n\t\t\t\t\"accountDisplayName\": {\n\t\t\t\t\t\"defaultNormalAccountName\": \"DagInvestments - DAG INVESTMENT\"\n\t\t\t\t},\n\t\t\t\t\"siteName\": \"DagInvestments\"\n\t\t\t},\n\t\t\t\"accessLevelRequired\": 1,\n\t\t\t\"isBusiness\": false\n\t\t}, {\n\t\t\t\"memo\": {},\n\t\t\t\"viewKey\": {\n\t\t\t\t\"containerType\": \"stocks\",\n\t\t\t\t\"isParentMatch\": false,\n\t\t\t\t\"transactionId\": 10213409,\n\t\t\t\t\"isSystemGeneratedSplit\": false,\n\t\t\t\t\"transactionCount\": 6,\n\t\t\t\t\"rowNumber\": 4\n\t\t\t},\n\t\t\t\"isTaxable\": false,\n\t\t\t\"postDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"link\": \"http://www.altova.com\",\n\t\t\t\"categorisationSourceId\": 0,\n\t\t\t\"transactionPostingOrder\": 0,\n\t\t\t\"amount\": {\n\t\t\t\t\"amount\": 123456,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isClosingTxn\": 0,\n\t\t\t\"transactionBaseType\": \"credit\",\n\t\t\t\"investmentTransactionView\": {\n\t\t\t\t\"cusipNumber\": \"999999999\",\n\t\t\t\t\"lotHandling\": {\n\t\t\t\t\t\"lotHandlingId\": 0\n\t\t\t\t},\n\t\t\t\t\"netCost\": 0,\n\t\t\t\t\"symbol\": \"Transaction2\",\n\t\t\t\t\"holdingType\": {\n\t\t\t\t\t\"holdingTypeId\": 0\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"isPersonal\": false,\n\t\t\t\"description\": {\n\t\t\t\t\"viewPref\": false,\n\t\t\t\t\"description\": \"transdescription\",\n\t\t\t\t\"isOlbUserDescription\": false\n\t\t\t},\n\t\t\t\"quantity\": 2345,\n\t\t\t\"isMedical\": false,\n\t\t\t\"transactionSearchResultType\": \"aggregated\",\n\t\t\t\"transactionBaseTypeId\": 1,\n\t\t\t\"status\": {\n\t\t\t\t\"statusId\": 1,\n\t\t\t\t\"description\": \"posted\",\n\t\t\t\t\"localizedDescription\": \"posted\"\n\t\t\t},\n\t\t\t\"localizedTransactionBaseType\": \"credit\",\n\t\t\t\"checkNumber\": {},\n\t\t\t\"transactionDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"runningBalance\": 0,\n\t\t\t\"classUpdationSource\": \"S\",\n\t\t\t\"siteTransactionId\": \"258369\",\n\t\t\t\"transactionType\": \"loanPayment\",\n\t\t\t\"category\": {\n\t\t\t\t\"categoryName\": \"Uncategorized\",\n\t\t\t\t\"categoryTypeId\": 1,\n\t\t\t\t\"localizedCategoryName\": \"Uncategorized\",\n\t\t\t\t\"categoryId\": 1,\n\t\t\t\t\"isBusiness\": false\n\t\t\t},\n\t\t\t\"price\": {\n\t\t\t\t\"amount\": 123,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isReimbursable\": false,\n\t\t\t\"transactionTypeId\": 202,\n\t\t\t\"account\": {\n\t\t\t\t\"isAccountName\": 1,\n\t\t\t\t\"accountNumber\": \"xxxx5555\",\n\t\t\t\t\"accountName\": \"DAG INVESTMENT\",\n\t\t\t\t\"accountBalance\": {\n\t\t\t\t\t\"amount\": 79629,\n\t\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t\t},\n\t\t\t\t\"itemAccountId\": 10172137,\n\t\t\t\t\"decryptionStatus\": true,\n\t\t\t\t\"sumInfoId\": 11201,\n\t\t\t\t\"itemAccountStatusId\": 1,\n\t\t\t\t\"accountDisplayName\": {\n\t\t\t\t\t\"defaultNormalAccountName\": \"DagInvestments - DAG INVESTMENT\"\n\t\t\t\t},\n\t\t\t\t\"siteName\": \"DagInvestments\"\n\t\t\t},\n\t\t\t\"accessLevelRequired\": 1,\n\t\t\t\"isBusiness\": false\n\t\t}, {\n\t\t\t\"memo\": {},\n\t\t\t\"viewKey\": {\n\t\t\t\t\"containerType\": \"stocks\",\n\t\t\t\t\"isParentMatch\": false,\n\t\t\t\t\"transactionId\": 10213408,\n\t\t\t\t\"isSystemGeneratedSplit\": false,\n\t\t\t\t\"transactionCount\": 6,\n\t\t\t\t\"rowNumber\": 5\n\t\t\t},\n\t\t\t\"isTaxable\": false,\n\t\t\t\"postDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"link\": \"http://www.altova.com\",\n\t\t\t\"categorisationSourceId\": 15,\n\t\t\t\"transactionPostingOrder\": 0,\n\t\t\t\"amount\": {\n\t\t\t\t\"amount\": 123456,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isClosingTxn\": 0,\n\t\t\t\"transactionBaseType\": \"credit\",\n\t\t\t\"investmentTransactionView\": {\n\t\t\t\t\"cusipNumber\": \"999999999\",\n\t\t\t\t\"lotHandling\": {\n\t\t\t\t\t\"lotHandlingId\": 0\n\t\t\t\t},\n\t\t\t\t\"netCost\": 0,\n\t\t\t\t\"symbol\": \"Transaction2\",\n\t\t\t\t\"holdingType\": {\n\t\t\t\t\t\"holdingTypeId\": 0\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"isPersonal\": false,\n\t\t\t\"description\": {\n\t\t\t\t\"viewPref\": false,\n\t\t\t\t\"description\": \"transdescription\",\n\t\t\t\t\"isOlbUserDescription\": false\n\t\t\t},\n\t\t\t\"quantity\": 2345,\n\t\t\t\"isMedical\": false,\n\t\t\t\"transactionSearchResultType\": \"aggregated\",\n\t\t\t\"transactionBaseTypeId\": 1,\n\t\t\t\"status\": {\n\t\t\t\t\"statusId\": 1,\n\t\t\t\t\"description\": \"posted\",\n\t\t\t\t\"localizedDescription\": \"posted\"\n\t\t\t},\n\t\t\t\"localizedTransactionBaseType\": \"credit\",\n\t\t\t\"checkNumber\": {},\n\t\t\t\"localizedTransactionType\": \"sell\",\n\t\t\t\"transactionDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"runningBalance\": 0,\n\t\t\t\"classUpdationSource\": \"S\",\n\t\t\t\"siteTransactionId\": \"258369\",\n\t\t\t\"transactionType\": \"sell\",\n\t\t\t\"category\": {\n\t\t\t\t\"categoryName\": \"Securities Trades\",\n\t\t\t\t\"categoryTypeId\": 4,\n\t\t\t\t\"localizedCategoryName\": \"Securities Trades\",\n\t\t\t\t\"categoryId\": 36,\n\t\t\t\t\"isBusiness\": false\n\t\t\t},\n\t\t\t\"price\": {\n\t\t\t\t\"amount\": 123,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isReimbursable\": false,\n\t\t\t\"transactionTypeId\": 4,\n\t\t\t\"account\": {\n\t\t\t\t\"isAccountName\": 1,\n\t\t\t\t\"accountNumber\": \"xxxx5555\",\n\t\t\t\t\"accountName\": \"DAG INVESTMENT\",\n\t\t\t\t\"accountBalance\": {\n\t\t\t\t\t\"amount\": 79629,\n\t\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t\t},\n\t\t\t\t\"itemAccountId\": 10172137,\n\t\t\t\t\"decryptionStatus\": true,\n\t\t\t\t\"sumInfoId\": 11201,\n\t\t\t\t\"itemAccountStatusId\": 1,\n\t\t\t\t\"accountDisplayName\": {\n\t\t\t\t\t\"defaultNormalAccountName\": \"DagInvestments - DAG INVESTMENT\"\n\t\t\t\t},\n\t\t\t\t\"siteName\": \"DagInvestments\"\n\t\t\t},\n\t\t\t\"accessLevelRequired\": 1,\n\t\t\t\"isBusiness\": false\n\t\t}, {\n\t\t\t\"memo\": {},\n\t\t\t\"viewKey\": {\n\t\t\t\t\"containerType\": \"stocks\",\n\t\t\t\t\"isParentMatch\": false,\n\t\t\t\t\"transactionId\": 10213407,\n\t\t\t\t\"isSystemGeneratedSplit\": false,\n\t\t\t\t\"transactionCount\": 6,\n\t\t\t\t\"rowNumber\": 6\n\t\t\t},\n\t\t\t\"isTaxable\": false,\n\t\t\t\"postDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"link\": \"http://www.altova.com\",\n\t\t\t\"categorisationSourceId\": 15,\n\t\t\t\"transactionPostingOrder\": 0,\n\t\t\t\"amount\": {\n\t\t\t\t\"amount\": 123456,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isClosingTxn\": 0,\n\t\t\t\"transactionBaseType\": \"credit\",\n\t\t\t\"investmentTransactionView\": {\n\t\t\t\t\"cusipNumber\": \"999999999\",\n\t\t\t\t\"lotHandling\": {\n\t\t\t\t\t\"lotHandlingId\": 0\n\t\t\t\t},\n\t\t\t\t\"netCost\": 0,\n\t\t\t\t\"symbol\": \"Transaction2\",\n\t\t\t\t\"holdingType\": {\n\t\t\t\t\t\"holdingTypeId\": 0\n\t\t\t\t}\n\t\t\t},\n\t\t\t\"isPersonal\": false,\n\t\t\t\"description\": {\n\t\t\t\t\"viewPref\": false,\n\t\t\t\t\"description\": \"transdescription\",\n\t\t\t\t\"isOlbUserDescription\": false\n\t\t\t},\n\t\t\t\"quantity\": 2345,\n\t\t\t\"isMedical\": false,\n\t\t\t\"transactionSearchResultType\": \"aggregated\",\n\t\t\t\"transactionBaseTypeId\": 1,\n\t\t\t\"status\": {\n\t\t\t\t\"statusId\": 1,\n\t\t\t\t\"description\": \"posted\",\n\t\t\t\t\"localizedDescription\": \"posted\"\n\t\t\t},\n\t\t\t\"localizedTransactionBaseType\": \"credit\",\n\t\t\t\"checkNumber\": {},\n\t\t\t\"localizedTransactionType\": \"buy\",\n\t\t\t\"transactionDate\": \"2013-01-17T00:00:00-0800\",\n\t\t\t\"runningBalance\": 0,\n\t\t\t\"classUpdationSource\": \"S\",\n\t\t\t\"siteTransactionId\": \"258369\",\n\t\t\t\"transactionType\": \"buy\",\n\t\t\t\"category\": {\n\t\t\t\t\"categoryName\": \"Securities Trades\",\n\t\t\t\t\"categoryTypeId\": 4,\n\t\t\t\t\"localizedCategoryName\": \"Securities Trades\",\n\t\t\t\t\"categoryId\": 36,\n\t\t\t\t\"isBusiness\": false\n\t\t\t},\n\t\t\t\"price\": {\n\t\t\t\t\"amount\": 123,\n\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t},\n\t\t\t\"isReimbursable\": false,\n\t\t\t\"transactionTypeId\": 3,\n\t\t\t\"account\": {\n\t\t\t\t\"isAccountName\": 1,\n\t\t\t\t\"accountNumber\": \"xxxx5555\",\n\t\t\t\t\"accountName\": \"DAG INVESTMENT\",\n\t\t\t\t\"accountBalance\": {\n\t\t\t\t\t\"amount\": 79629,\n\t\t\t\t\t\"currencyCode\": \"USD\"\n\t\t\t\t},\n\t\t\t\t\"itemAccountId\": 10172137,\n\t\t\t\t\"decryptionStatus\": true,\n\t\t\t\t\"sumInfoId\": 11201,\n\t\t\t\t\"itemAccountStatusId\": 1,\n\t\t\t\t\"accountDisplayName\": {\n\t\t\t\t\t\"defaultNormalAccountName\": \"DagInvestments - DAG INVESTMENT\"\n\t\t\t\t},\n\t\t\t\t\"siteName\": \"DagInvestments\"\n\t\t\t},\n\t\t\t\"accessLevelRequired\": 1,\n\t\t\t\"isBusiness\": false\n\t\t}]\n\t},\n\t\"searchIdentifier\": {\n\t\t\"identifier\": \"172172227-1450247093097--599545251-TX_SEARCH\"\n\t},\n\t\"creditTotalOfProjectedTxns\": {\n\t\t\"amount\": 0,\n\t\t\"currencyCode\": \"USD\"\n\t},\n\t\"debitTotalOfTxns\": {\n\t\t\"amount\": 0,\n\t\t\"currencyCode\": \"USD\"\n\t},\n\t\"debitTotalOfProjectedTxns\": {\n\t\t\"amount\": 0,\n\t\t\"currencyCode\": \"USD\"\n\t},\n\t\"numberOfHits\": 6,\n\t\"countOfAllTransaction\": 6,\n\t\"countOfProjectedTxns\": 0,\n\t\"creditTotalOfTxns\": {\n\t\t\"amount\": 740736,\n\t\t\"currencyCode\": \"USD\"\n\t}\n}";

    JSONObject jsonObject = new JSONObject(jsonString);
    LOG.debug("jsonObject=" + jsonObject);


    JSONObject searchResultJson = jsonObject.getJSONObject("searchResult");
    JSONArray transactionsArrayJson = searchResultJson.getJSONArray("transactions");
    int noOfTransactions = transactionsArrayJson.length();
    LOG.debug("noOfTransactions count=" + noOfTransactions);


    for (int i = 0; i < noOfTransactions; i++) {
      LOG.debug("transaction=" + i);
      JSONObject transactionJson = transactionsArrayJson.getJSONObject(i);
      JSONObject viewKeyJson = transactionJson.getJSONObject("viewKey");
      String containerType = viewKeyJson.getString("containerType");
      String transactionId = viewKeyJson.getString("transactionId");
      LOG.debug("containerType=" + containerType);
      LOG.debug("transactionId=" + transactionId);


      String postDate = transactionJson.getString("postDate");
      LOG.debug("postDate=" + postDate);
      String link = transactionJson.getString("link");
      LOG.debug("link=" + link);

      JSONObject amountJson = transactionJson.getJSONObject("amount");
      String amount = amountJson.getString("amount");
      String currencyCode = amountJson.getString("currencyCode");
      LOG.debug("amount=" + amount);
      LOG.debug("currencyCode=" + currencyCode);

      String transactionBaseType = transactionJson.getString("transactionBaseType");
      LOG.debug("transactionBaseType=" + transactionBaseType);
      String isClosingTxn = transactionJson.getString("isClosingTxn");
      LOG.debug("isClosingTxn=" + isClosingTxn);

      JSONObject investmentTransactionViewJson = transactionJson.getJSONObject("investmentTransactionView");
      String cusipNumber = investmentTransactionViewJson.getString("cusipNumber");
      String netCost = investmentTransactionViewJson.getString("netCost");
      String symbol = investmentTransactionViewJson.getString("symbol");
      LOG.debug("cusipNumber=" + cusipNumber);
      LOG.debug("netCost=" + netCost);
      LOG.debug("symbol=" + symbol);

      JSONObject holdingTypeJson = investmentTransactionViewJson.getJSONObject("holdingType");
      String holdingTypeId = holdingTypeJson.getString("holdingTypeId");
      LOG.debug("holdingTypeId=" + holdingTypeId);

      String isPersonal = transactionJson.getString("isPersonal");
      LOG.debug("isPersonal=" + isPersonal);

      JSONObject descriptionJson = transactionJson.getJSONObject("description");
      String viewPref = descriptionJson.getString("viewPref");
      String description = descriptionJson.getString("description");
      String isOlbUserDescription = descriptionJson.getString("isOlbUserDescription");
      LOG.debug("viewPref=" + viewPref);
      LOG.debug("description=" + description);
      LOG.debug("isOlbUserDescription=" + isOlbUserDescription);

      String quantity = transactionJson.getString("quantity");
      String isMedical = transactionJson.getString("isMedical");
      String transactionSearchResultType = transactionJson.getString("transactionSearchResultType");
      String transactionBaseTypeId = transactionJson.getString("transactionBaseTypeId");
      LOG.debug("quantity=" + quantity);
      LOG.debug("isMedical=" + isMedical);
      LOG.debug("transactionSearchResultType=" + transactionSearchResultType);
      LOG.debug("transactionBaseTypeId=" + transactionBaseTypeId);

      JSONObject statusJson = transactionJson.getJSONObject("status");
      String statusId = statusJson.getString("statusId");
      String status_description = statusJson.getString("description");
      String localizedDescription = statusJson.getString("localizedDescription");
      LOG.debug("statusId=" + statusId);
      LOG.debug("status_description=" + status_description);
      LOG.debug("localizedDescription=" + localizedDescription);

      String localizedTransactionBaseType = transactionJson.getString("localizedTransactionBaseType");
      String transactionDate = transactionJson.getString("transactionDate");
      String runningBalance = transactionJson.getString("runningBalance");
      String classUpdationSource = transactionJson.getString("classUpdationSource");
      String siteTransactionId = transactionJson.getString("siteTransactionId");
      String transactionType = transactionJson.getString("transactionType");
      LOG.debug("localizedTransactionBaseType=" + localizedTransactionBaseType);
      LOG.debug("transactionDate=" + transactionDate);
      LOG.debug("runningBalance=" + runningBalance);
      LOG.debug("classUpdationSource=" + classUpdationSource);
      LOG.debug("siteTransactionId=" + siteTransactionId);
      LOG.debug("transactionType=" + transactionType);

      JSONObject categoryJson = transactionJson.getJSONObject("category");
      String categoryName = categoryJson.getString("categoryName");
      String localizedCategoryName = categoryJson.getString("localizedCategoryName");
      String categoryId = categoryJson.getString("categoryId");
      String isBusiness = categoryJson.getString("isBusiness");
      LOG.debug("categoryName=" + categoryName);
      LOG.debug("localizedCategoryName=" + localizedCategoryName);
      LOG.debug("categoryId=" + categoryId);
      LOG.debug("isBusiness=" + isBusiness);

      JSONObject priceJson = transactionJson.getJSONObject("price");
      String price_amount = priceJson.getString("amount");
      String price_currency_code = priceJson.getString("currencyCode");
      LOG.debug("price_amount=" + price_amount);
      LOG.debug("price_currency_code=" + price_currency_code);

      String isReimbursable = transactionJson.getString("isReimbursable");
      String transactionTypeId = transactionJson.getString("transactionTypeId");
      LOG.debug("isReimbursable=" + isReimbursable);
      LOG.debug("transactionTypeId=" + transactionTypeId);

      JSONObject accountJson = transactionJson.getJSONObject("account");
      String isAccountName = accountJson.getString("isAccountName");
      String accountNumber = accountJson.getString("accountNumber");
      String accountName = accountJson.getString("accountName");
      LOG.debug("isAccountName=" + isAccountName);
      LOG.debug("accountNumber=" + accountNumber);
      LOG.debug("accountName=" + accountName);

      JSONObject accountBalanceJson = accountJson.getJSONObject("accountBalance");
      String account_balance_amount = accountBalanceJson.getString("amount");
      String account_balance_currencyCode = accountBalanceJson.getString("currencyCode");
      LOG.debug("account_balance_amount=" + account_balance_amount);
      LOG.debug("account_balance_currencyCode=" + account_balance_currencyCode);


      String itemAccountId = accountJson.getString("itemAccountId");
      String decryptionStatus = accountJson.getString("decryptionStatus");
      String itemAccountStatusId = accountJson.getString("itemAccountStatusId");
      String siteName = accountJson.getString("siteName");
      LOG.debug("itemAccountId=" + itemAccountId);
      LOG.debug("decryptionStatus=" + decryptionStatus);
      LOG.debug("itemAccountStatusId=" + itemAccountStatusId);
      LOG.debug("siteName=" + siteName);


      JSONObject accountDisplayNameJson = accountJson.getJSONObject("accountDisplayName");
      String defaultNormalAccountName = accountDisplayNameJson.getString("defaultNormalAccountName");
      LOG.debug("defaultNormalAccountName=" + defaultNormalAccountName);

      String accessLevelRequired = transactionJson.getString("accessLevelRequired");
      String isBusiness2 = transactionJson.getString("isBusiness");
      LOG.debug("accessLevelRequired=" + accessLevelRequired);
      LOG.debug("isBusiness=" + isBusiness2);
    }

  }
}
