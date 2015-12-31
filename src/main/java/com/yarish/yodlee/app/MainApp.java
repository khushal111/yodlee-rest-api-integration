package com.yarish.yodlee.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {

  private final static Logger LOG = LoggerFactory.getLogger(MainApp.class);

  public static void main(String[] args) {

    // step 0 ; load properties
    Properties properties = new Properties();
    InputStream inputStream = null;

    try {
      inputStream = new FileInputStream("./src/main/resources/application.properties");
      properties.load(inputStream);
      LOG.debug(properties.toString());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      LOG.error("problem in loading application.properties");
    } catch (IOException e) {
      LOG.error("problem in loading application.properties");
    }


    // Step 1: Authenticate the cobrand
    LOG.debug("\n\n\nStep 1: Authenticate the cobrand");
    String cobrandLogin = properties.getProperty(Constants.paramNameCobrandLogin);
    String cobrandPassword = properties.getProperty(Constants.paramNameCobrandPassword);

    CoBrand coBrand = new CoBrand(cobrandLogin, cobrandPassword);
    coBrand.login();
    String coBrandSessionToken = coBrand.getCoBrandSessionToken();
    LOG.debug("coBrandSessionToken=" + coBrandSessionToken);

    // Step 2 : Register the user
    // Step 2a : Login as a user
    LOG.debug("\n\n\nStep 2a : Login as a user");
    String username = properties.getProperty("user.username");
    String password = properties.getProperty("user.password");
    String emailId = properties.getProperty("user.emailid");
    String instanceType = properties.getProperty("user.intancetype");

    User user = new User(username, password, emailId, instanceType);
    // Works only In Production
    // user.register(coBrandSessionToken);
    try {
      user.loginUser(coBrandSessionToken, username, password);
    } catch (LoginFailedException e) {
      e.printStackTrace();
    }
    String userSessionToken = user.getUserSessionToken();
    LOG.debug("userSessionToken=" + userSessionToken);

    // Step 3: Search for the required site
    LOG.debug("\n\n\nStep 3: Search for the required site");
    String siteSearchString = "DAG";
    SiteAccount siteAccount = new SiteAccount();

    siteAccount.searchSite(coBrandSessionToken, userSessionToken, siteSearchString);

    // Step 4: Display the login form
    LOG.debug("\n\n\n\n Step 4: getSiteLoginForm ");
    String siteId = properties.getProperty("site.id");;
    // DAG --> 9001
    // String siteId = "2852"; // BoA
    siteAccount.setSiteId(siteId);
    LoginFormInfo loginFormInfo = siteAccount.getSiteLoginForm(coBrandSessionToken, siteId);

    // LOG.debug("\n\n\n\n Step 4a: getSiteInfo ");
    // String siteId = "2852"; // BoA

    // String reqSpecifier = "16441";
    // Step 4: Display the login form
    // siteAccount.getSiteInfo(coBrandSessionToken, userSessionToken, reqSpecifier, siteId);

    // Step 5: Add the site
    LOG.debug("\n\n\n\n Step 5: Add the site ");
    String siteUsername = properties.getProperty("site.username");
    String sitePassword = properties.getProperty("site.password");
    siteAccount
        .addSiteAccount(coBrandSessionToken, userSessionToken, loginFormInfo, siteUsername, sitePassword, siteId);
    // http://pastebin.com/n0fJXUEY

    LOG.debug("\n\n\n\n Step 6: Refresh the site - Attempt1 ");
    // Step 6: Refresh the site
    if (!siteAccount.getSuggestedFlow().equalsIgnoreCase("REFRESH")) {
      try { // 5 min wait time
        LOG.debug("5 min sleep");
        Thread.sleep(1000 * 60 * 5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    siteAccount = siteAccount.refreshTheSite(coBrandSessionToken, userSessionToken, siteId);



    // Step 7: Refresh the site - attempt2
    LOG.debug("\n\n\n\n Step 7: Refresh the site - attempt2");

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

      siteAccount = siteAccount.refreshTheSite(coBrandSessionToken, userSessionToken, siteId);
    }


    // Step 8: Refresh the site
    LOG.debug("\n\n\n\n Step 8: Refresh the site - attempt3");
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

      siteAccount = siteAccount.refreshTheSite(coBrandSessionToken, userSessionToken, siteId);
    }

    // Step 9: GetItemSummariesForSite
    LOG.debug("\n\n\n\n Step 9: GetItemSummariesForSite");
    siteAccount = siteAccount.getItemSummariesForSite(coBrandSessionToken, userSessionToken);
    // http://pastebin.com/6nkP3r41
    InvestmentDataDB investmentDataDB = new InvestmentDataDB();
    investmentDataDB.persistInDB(siteAccount);


    // 10: Refresh button
    LOG.debug("\n\n\n\n Step 10: Refresh button");
    siteAccount.startSiteRefresh(coBrandSessionToken, userSessionToken);
    // http://pastebin.com/ByPgbHcM

    // Step 11 : get user transaction details
    LOG.debug("\n\n\n\n Step 11 : get user transaction details");
    UserTransaction userTransaction = new UserTransaction();
    userTransaction.getuserTransaction(coBrandSessionToken, userSessionToken);
    // http://pastebin.com/ggbwrwKK


  }
}
