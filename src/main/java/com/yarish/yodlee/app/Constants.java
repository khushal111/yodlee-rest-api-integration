package com.yarish.yodlee.app;

public class Constants {
  public static final String HOST_URI = "https://rest.developer.yodlee.com/services/srest/restserver/";
  public static final String COB_LOGIN_URL = "v1.0/authenticate/coblogin";
  public static final String USER_REGISTER_URL = "v1.0/jsonsdk/UserRegistration/register3";
  public static final String USER_LOGIN_URL = "v1.0/authenticate/login";
  public static final String SEARCH_SITE_URL = "v1.0/jsonsdk/SiteTraversal/searchSite";
  public static final String GET_SITE_LOGIN_FORM = "v1.0/jsonsdk/SiteAccountManagement/getSiteLoginForm";
  public static final String ADD_SITE_ACC = "v1.0/jsonsdk/SiteAccountManagement/addSiteAccount1";
  public static final String GET_SITE_INFO = "v1.0/jsonsdk/SiteTraversal/getSiteInfo";
  public static final String SITE_REFRESH = "v1.0/jsonsdk/Refresh/getSiteRefreshInfo";
  public static final String ITEM_SUMMARIES = "v1.0/jsonsdk/DataService/getItemSummariesForSite";
  public static final String START_SITE_REFRESH = "v1.0/jsonsdk/Refresh/startSiteRefresh";
  public static final String USER_TRANSACTION = "v1.0/jsonsdk/TransactionSearchService/executeUserSearchRequest";

  // Cobrand login API parameters
  public static final String paramNameCobrandLogin = "cobrandLogin";
  public static final String paramNameCobrandPassword = "cobrandPassword";

  // Common parameters for all APIs except for cobrand login or cobrand creation APIs
  public static final String paramNameCobSessionToken = "cobSessionToken";
  public static final String paramNameUserSessionToken = "userSessionToken";

  // Create cobrand credentials API parameters
  public static final String paramNameNewUserLogin = "userCredentials.loginName";
  public static final String paramNameNewUserPassword = "userCredentials.password";
  public static final String paramNameInstanceType = "userCredentials.objectInstanceType";
  public static final String paramNameUserEmail = "userProfile.emailAddress";

  // User login API parameters
  public static final String paramNameUserLogin = "login";
  public static final String paramNameUserPassword = "password";

  // Site refresh
  public static final String memSiteAccId = "memSiteAccId";

  // user transaction
  public static final String containerType = "transactionSearchRequest.containerType";
  public static final String higherFetchLimit = "transactionSearchRequest.higherFetchLimit";
  public static final String lowerFetchLimit = "transactionSearchRequest.lowerFetchLimit";
  public static final String endNumber = "transactionSearchRequest.resultRange.endNumber";
  public static final String startNumber = "transactionSearchRequest.resultRange.startNumber";
  public static final String currencyCode = "transactionSearchRequest.searchFilter.currencyCode";
  public static final String ignoreUserInput = "transactionSearchRequest.ignoreUserInput";


}
