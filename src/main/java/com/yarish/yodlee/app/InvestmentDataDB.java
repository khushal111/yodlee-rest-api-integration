package com.yarish.yodlee.app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InvestmentDataDB {

  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL =
      "jdbc:mysql://dev-db-instance1-encrypted.cuwneoknj5z2.us-west-2.rds.amazonaws.com/yodleedata";

  static final String USER_NAME = "dbuser1";
  static final String PASSWORD = "J&4!6pqm55IW";



  public static void main(String[] args) throws JSONException {

    String source =
        "[{\"itemId\":10149817,\"contentServiceId\":11201,\"contentServiceInfo\":{\"contentServiceId\":11201,\"siteId\":9001,\"containerInfo\":{\"containerName\":\"stocks\",\"assetType\":1}},\"itemDisplayName\":\"DagInvestments\",\"refreshInfo\":{\"itemId\":10149817,\"statusCode\":0,\"refreshType\":2,\"refreshRequestTime\":0,\"lastUpdatedTime\":1447363370,\"lastUpdateAttemptTime\":1447363370,\"itemAccessStatus\":{\"name\":\"ACCESS_VERIFIED\"},\"userActionRequiredType\":{\"name\":\"NONE\"},\"userActionRequiredCode\":0,\"lastDataUpdateAttempt\":{\"date\":\"2015-11-12T13:22:50-0800\",\"status\":{\"name\":\"SUCCESS\"},\"statusCode\":0,\"type\":{\"name\":\"USER_REQUESTED\"}},\"lastUserRequestedDataUpdateAttempt\":{\"date\":\"2015-11-12T13:22:50-0800\",\"status\":{\"name\":\"SUCCESS\"},\"statusCode\":0,\"type\":{\"name\":\"USER_REQUESTED\"}},\"lastSuccessfulDataUpdate\":\"2015-11-12T13:22:50-0800\",\"itemCreateDate\":\"2015-11-12T13:15:58-0800\",\"nextUpdateTime\":1447458241,\"responseCodeType\":{\"responseCodeTypeId\":1},\"retryCount\":0,\"refreshMode\":\"NORMAL\"},\"isCustom\":false,\"isDisabled\":false,\"isItemRefreshInProgress\":false,\"isOauthEnabled\":false,\"itemData\":{\"accounts\":[{\"isSeidFromDataSource\":0,\"isSeidMod\":0,\"acctTypeId\":1,\"acctType\":\"unknown\",\"localizedAcctType\":\"unknown\",\"srcElementId\":\"11111\",\"invAcctBaseTypeId\":6,\"invAcctBaseType\":\"unknown\",\"localizedInvAcctBaseType\":\"unknown\",\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"accountNumber\":\"xx5555\",\"planNumber\":\"2\",\"link\":\"http://www.altova.com\",\"accountHolder\":\"DAG\",\"tranListToDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"tranListFromDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"isOptionAllowed\":0,\"planName\":\"plannamepavan\",\"isMarginAllowed\":0,\"cash\":{\"amount\":1000,\"currencyCode\":\"USD\"},\"totalBalance\":{\"amount\":79629,\"currencyCode\":\"USD\"},\"totalVestedBalance\":{\"amount\":2432,\"currencyCode\":\"USD\"},\"totalUnvestedBalance\":{\"amount\":23242,\"currencyCode\":\"USD\"},\"fundsOwed\":{\"amount\":2323,\"currencyCode\":\"USD\"},\"availableLoan\":{\"amount\":55000,\"currencyCode\":\"USD\"},\"buyingPower\":{\"amount\":234,\"currencyCode\":\"USD\"},\"marginBalance\":{\"amount\":2369,\"currencyCode\":\"USD\"},\"unsettledFunds\":{\"amount\":5340,\"currencyCode\":\"USD\"},\"shortBalance\":{\"amount\":15000,\"currencyCode\":\"USD\"},\"moneyMarketBalance\":{\"amount\":1000,\"currencyCode\":\"USD\"},\"loan_401k\":{\"amount\":100,\"currencyCode\":\"USD\"},\"totalAccountBalance\":{\"amount\":1331825789,\"currencyCode\":\"USD\"},\"accountName\":\"DAG INVESTMENT\",\"accountCurrency\":\"string\",\"asofDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"holdings\":[{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"955340540\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":4,\"holdingType\":\"CD\",\"localizedHoldingType\":\"CD\",\"holdingId\":10081326,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"lastContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"faceValue\":{\"amount\":5660,\"currencyCode\":\"USD\"},\"percentAllocation\":2000,\"nextCouponDate\":{},\"daysRemaining\":2340,\"nextCallDate\":{},\"term\":\"bondterm\",\"interestRate\":2000,\"employerContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"CDRisk\",\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"string\",\"employeeContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"link\":\"http://www.altova.com\",\"grantDate\":{},\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"CDDesc\",\"expirationDate\":{},\"maturityDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"yieldAsofDate\":{},\"isin\":\"123456789012\",\"sedol\":\"1234567\",\"exchange\":{\"exchangeId\":1,\"exchangeCode\":\"nse\"},\"domicile\":\"US\",\"isUnderlyingSecurity\":1,\"isIsinNormalized\":0,\"isSedolNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081326,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081326\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"955340120\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":6,\"holdingType\":\"moneyMarketFund\",\"localizedHoldingType\":\"moneyMarketFund\",\"holdingId\":10081327,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"lastContribution\":{\"amount\":2960,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"dailyChange\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentAllocation\":2650,\"nextCouponDate\":{},\"nextCallDate\":{},\"price\":{\"amount\":2550,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":2360,\"currencyCode\":\"USD\"},\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"12\",\"cusipNumber\":\"999999999\",\"mmfNumber\":2078,\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"11111\",\"quantity\":2000,\"value\":{\"amount\":3400,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2370,\"currencyCode\":\"USD\"},\"link\":\"http://www.altova.com\",\"grantDate\":{},\"symbol\":\"moneyMarketFund\",\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"moneyMarketFund\",\"expirationDate\":{},\"maturityDate\":{},\"yieldAsofDate\":{},\"isin\":\"123456789012\",\"sedol\":\"1234567\",\"exchange\":{\"exchangeId\":1,\"exchangeCode\":\"nse\"},\"domicile\":\"US\",\"isUnderlyingSecurity\":1,\"isSymbolNormalized\":0,\"isCusipNormalized\":0,\"isIsinNormalized\":0,\"isSedolNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081327,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081327\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"955340110\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":5,\"holdingType\":\"option\",\"localizedHoldingType\":\"option\",\"holdingId\":10081328,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"lastContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"percentAllocation\":30,\"nextCouponDate\":{},\"daysRemaining\":600,\"contractAdjustment\":260,\"nextCallDate\":{},\"price\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"strikePrice\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"costBasis\":{\"amount\":2589,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"string\",\"isSettleTypeAmerican\":0,\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"string\",\"quantity\":2000,\"fiAssetClass\":\"option\",\"value\":{\"amount\":20670,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"link\":\"http://www.altova.com\",\"grantDate\":{},\"symbol\":\"option\",\"isShort\":0,\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"option\",\"expirationDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"maturityDate\":{},\"yieldAsofDate\":{},\"isin\":\"123456789012\",\"sedol\":\"1234567\",\"exchange\":{\"exchangeId\":1,\"exchangeCode\":\"nse\"},\"domicile\":\"US\",\"isSymbolNormalized\":0,\"isIsinNormalized\":0,\"isSedolNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081328,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081328\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"934560540\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":11,\"holdingType\":\"currency\",\"localizedHoldingType\":\"currency\",\"holdingId\":10081329,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"lastContribution\":{\"amount\":4340,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"dailyChange\":{\"amount\":234,\"currencyCode\":\"USD\"},\"percentAllocation\":2000,\"nextCouponDate\":{},\"nextCallDate\":{},\"price\":{\"amount\":253,\"currencyCode\":\"USD\"},\"costBasis\":{\"amount\":253,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":23430,\"currencyCode\":\"USD\"},\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"currency\",\"currencyType\":\"currency\",\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"string\",\"quantity\":24340,\"value\":{\"amount\":25430,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":234,\"currencyCode\":\"USD\"},\"percentageChange\":2000,\"link\":\"http://www.altova.com\",\"grantDate\":{},\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"currency\",\"expirationDate\":{},\"maturityDate\":{},\"yieldAsofDate\":{},\"isin\":\"123456789012\",\"sedol\":\"1234567\",\"exchange\":{\"exchangeId\":1,\"exchangeCode\":\"nse\"},\"domicile\":\"US\",\"isIsinNormalized\":0,\"isSedolNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081329,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081329\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"9453250\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":10,\"holdingType\":\"commodity\",\"localizedHoldingType\":\"commodity\",\"holdingId\":10081330,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\"},\"lastContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"percentAllocation\":2000,\"nextCouponDate\":{},\"nextCallDate\":{},\"price\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"costBasis\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"commodity\",\"cusipNumber\":\"999999999\",\"commodityType\":\"commodity\",\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"3444\",\"value\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"link\":\"http://www.altova.com\",\"contractQuantity\":2000,\"grantDate\":{},\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"commodity\",\"expirationDate\":{},\"maturityDate\":{},\"yieldAsofDate\":{},\"isin\":\"123456789012\",\"sedol\":\"1234567\",\"exchange\":{\"exchangeId\":1,\"exchangeCode\":\"nse\"},\"domicile\":\"US\",\"isCusipNormalized\":0,\"isIsinNormalized\":0,\"isSedolNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081330,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081330\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"955340539\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":3,\"holdingType\":\"bond\",\"localizedHoldingType\":\"bond\",\"holdingId\":10081325,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"lastContribution\":{\"amount\":230,\"currencyCode\":\"USD\"},\"callTypeId\":1,\"callType\":\"other\",\"localizedCallType\":\"other\",\"dailyChange\":{\"amount\":300,\"currencyCode\":\"USD\"},\"percentAllocation\":1200,\"nextCouponDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"moodyRating\":\"bondmodyrating\",\"nextCallDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"price\":{\"amount\":1500,\"currencyCode\":\"USD\"},\"yieldToCall\":2000,\"costBasis\":{\"amount\":3500,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":5400,\"currencyCode\":\"USD\"},\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"bondrisk\",\"cusipNumber\":\"999999999\",\"couponRate\":2000,\"spRating\":\"sprating\",\"currentYield\":2300,\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"nominalYield\":2700,\"internalRefNo\":\"string\",\"quantity\":2,\"fiAssetClass\":\"FIAssetClass\",\"value\":{\"amount\":5600,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2300,\"currencyCode\":\"USD\"},\"parValue\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentageChange\":2000,\"link\":\"http://www.altova.com\",\"grantDate\":{},\"yieldToMaturity\":2400,\"couponFreqId\":1,\"couponFreq\":\"other\",\"localizedCouponFreq\":\"other\",\"description\":\"bonddesc\",\"callPrice\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"expirationDate\":{},\"maturityDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"yieldAsofDate\":{},\"isin\":\"123456789012\",\"sedol\":\"1234567\",\"exchange\":{\"exchangeId\":1,\"exchangeCode\":\"nse\"},\"domicile\":\"US\",\"isUnderlyingSecurity\":1,\"scrapedPrice\":{\"amount\":1500,\"currencyCode\":\"USD\"},\"scrapedQuantity\":2000,\"isCusipNormalized\":0,\"isIsinNormalized\":0,\"isSedolNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081325,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081325\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"944440540\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":9,\"holdingType\":\"future\",\"localizedHoldingType\":\"future\",\"holdingId\":10081332,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"futureHoldingName\":\"string\",\"lastContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"dailyChange\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentAllocation\":2000,\"nextCouponDate\":{},\"nextCallDate\":{},\"price\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"costBasis\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"future\",\"cusipNumber\":\"999999999\",\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"4567\",\"quantity\":2000,\"value\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentageChange\":2000,\"link\":\"http://www.altova.com\",\"grantDate\":{},\"symbol\":\"future\",\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"future\",\"expirationDate\":{},\"maturityDate\":{},\"yieldAsofDate\":{},\"isin\":\"123456789012\",\"sedol\":\"1234567\",\"exchange\":{\"exchangeId\":1,\"exchangeCode\":\"nse\"},\"domicile\":\"US\",\"isSymbolNormalized\":0,\"isCusipNormalized\":0,\"isIsinNormalized\":0,\"isSedolNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081332,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081332\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"955340537\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":1,\"holdingType\":\"stock\",\"localizedHoldingType\":\"stock\",\"holdingId\":10081322,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"lastContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"dailyChange\":{\"amount\":2340,\"currencyCode\":\"USD\"},\"percentAllocation\":2000,\"nextCouponDate\":{},\"nextCallDate\":{},\"price\":{\"amount\":236,\"currencyCode\":\"USD\"},\"costBasis\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"isMarginable\":0,\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"rcategory\",\"cusipNumber\":\"999999999\",\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"isRestricted\":0,\"internalRefNo\":\"string\",\"quantity\":2000,\"value\":{\"amount\":5600,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentageChange\":20,\"link\":\"http://www.altova.com\",\"grantDate\":{},\"symbol\":\"holdingsymbol\",\"isShort\":0,\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"holdingdesc\",\"expirationDate\":{},\"maturityDate\":{},\"yieldAsofDate\":{},\"isUnderlyingSecurity\":1,\"isSymbolNormalized\":0,\"isCusipNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081322,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081322\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"955340538\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":2,\"holdingType\":\"mutualFund\",\"localizedHoldingType\":\"mutualFund\",\"holdingId\":10081323,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"lastContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"dailyChange\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentAllocation\":2000,\"nextCouponDate\":{},\"nextCallDate\":{},\"price\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"mfNumber\":2000,\"costBasis\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"isMarginable\":0,\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"yield\":2000,\"newspaperAbbrev\":\"newspaper\",\"riskCategory\":\"MFrisk\",\"cusipNumber\":\"999999999\",\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"internalrefNo\",\"quantity\":2000,\"fiAssetClass\":\"FIAssetclass\",\"value\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentageChange\":2000,\"link\":\"http://www.altova.com\",\"grantDate\":{},\"symbol\":\"MFSymbol\",\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"mutualfunddesc\",\"expirationDate\":{},\"maturityDate\":{},\"yieldAsofDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"isUnderlyingSecurity\":1,\"isSymbolNormalized\":0,\"isCusipNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081323,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081323\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"955340538\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":2,\"holdingType\":\"mutualFund\",\"localizedHoldingType\":\"mutualFund\",\"holdingId\":10081324,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"localFormat\":\"yyyy-MM-dd\"},\"lastContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"dailyChange\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentAllocation\":2000,\"nextCouponDate\":{},\"nextCallDate\":{},\"price\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"mfNumber\":2000,\"costBasis\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"isMarginable\":0,\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"yield\":2000,\"newspaperAbbrev\":\"newspaper\",\"riskCategory\":\"MFrisk\",\"cusipNumber\":\"999999999\",\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"internalrefNo\",\"quantity\":2000,\"fiAssetClass\":\"FIAssetclass\",\"value\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"percentageChange\":2000,\"link\":\"http://www.altova.com\",\"grantDate\":{},\"symbol\":\"MFSymbol2\",\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"mutualfunddesc\",\"expirationDate\":{},\"maturityDate\":{},\"yieldAsofDate\":{\"localFormat\":\"yyyy-MM-dd\"},\"isUnderlyingSecurity\":1,\"isSymbolNormalized\":0,\"isCusipNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081324,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081324\",\"refreshTime\":1447363370},{\"optionTypeId\":0,\"optionType\":\"unknown\",\"localizedOptionType\":\"unknown\",\"isSeidFromDataSource\":0,\"bondTypeId\":0,\"bondType\":\"unknown\",\"localizedBondType\":\"unknown\",\"isSeidMod\":0,\"bondClassId\":2,\"bondClass\":\"unknown\",\"localizedBondClass\":\"unknown\",\"srcElementId\":\"9553670\",\"mutualFundTypeId\":0,\"mutualFundType\":\"unknown\",\"localizedMutualFundType\":\"unknown\",\"holdingTypeId\":8,\"holdingType\":\"remic\",\"localizedHoldingType\":\"remic\",\"holdingId\":10081331,\"investmentAccountId\":10029485,\"isDeleted\":0,\"lastUpdated\":1447363371,\"hasDetails\":0,\"lastContributionDate\":{\"date\":\"2013-01-17T00:00:00-0800\",\"localFormat\":\"yyyy-MM-dd\"},\"remicHoldingName\":\"remic\",\"lastContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"callTypeId\":2,\"callType\":\"unknown\",\"localizedCallType\":\"unknown\",\"percentAllocation\":2000,\"nextCouponDate\":{},\"nextCallDate\":{},\"price\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"costBasis\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employerContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"vestingDate\":{},\"stockOptionTypeId\":1,\"stockOptionType\":\"unknown\",\"localizedStockOptionType\":\"unknown\",\"riskCategory\":\"remic\",\"cusipNumber\":\"999999999\",\"assetClassId\":0,\"assetClass\":\"unknown\",\"localizedAssetClass\":\"unknown\",\"internalRefNo\":\"3457\",\"quantity\":2000,\"chngSinceLastPriced\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"value\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"employeeContribution\":{\"amount\":2500,\"currencyCode\":\"USD\"},\"link\":\"http://www.altova.com\",\"grantDate\":{},\"couponFreqId\":2,\"couponFreq\":\"unknown\",\"localizedCouponFreq\":\"unknown\",\"description\":\"remic\",\"expirationDate\":{},\"maturityDate\":{},\"yieldAsofDate\":{},\"isin\":\"123456789012\",\"sedol\":\"1234567\",\"exchange\":{\"exchangeId\":1,\"exchangeCode\":\"nse\"},\"domicile\":\"US\",\"isUnderlyingSecurity\":1,\"isCusipNormalized\":0,\"isIsinNormalized\":0,\"isSedolNormalized\":0,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10081331,\"level\":2,\"itemAccountId\":10172169,\"baseTagDataId\":\"HOLDING:10150017:2:10081331\",\"refreshTime\":1447363370}],\"isPaperlessStmtOn\":0,\"investmentAccBalance\":{\"amount\":1331825789,\"currencyCode\":\"USD\"},\"created\":1447362958,\"accountClassification\":{\"accountClassificationId\":2,\"accountClassification\":\"PERSONAL\"},\"itemDataTableId\":47,\"itemAccountStatusId\":1,\"includeInNetworth\":1,\"isLinkedItemAccount\":false,\"rowLastUpdated\":\"2015-11-12T13:15:58-0800\",\"isUpdatePastTransaction\":false,\"isUpdateTxCategory\":true,\"createOpeningTxn\":true,\"isEbillEnrolledAccount\":false,\"isDocumentAvailable\":false,\"cacheItemId\":10150017,\"isItemAccountDeleted\":0,\"accountId\":10029485,\"level\":0,\"accountDisplayName\":{\"defaultNormalAccountName\":\"DagInvestments - DAG INVESTMENT\"},\"itemAccountId\":10172169,\"baseTagDataId\":\"INVESTMENT_ACCOUNT:10150017:0:10029485\",\"refreshTime\":1447363370}]},\"itemStatus\":0,\"isHeld\":0,\"secondsSinceLastUpdated\":295,\"exchangeRate\":1,\"isSharedItem\":false,\"isPrepop\":false,\"memSiteAccId\":10097949}]";

    JSONArray jsonArray = new JSONArray(source);
    JSONObject jsonObject = (JSONObject) jsonArray.get(0); // Level 0

    String itemId = jsonObject.getString("itemId");
    System.out.println("itemId=" + itemId);

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

    JSONObject accountClassificationJson = accountJsonObject.getJSONObject("accountClassification");
    String accountClassificationId = accountClassificationJson.getString("accountClassificationId");
    String accountClassification = accountClassificationJson.getString("accountClassification");
    System.out.println("accountClassificationId=" + accountClassificationId);
    System.out.println("accountClassification=" + accountClassification);

    JSONObject asofDateJson = accountJsonObject.getJSONObject("asofDate");
    String asofDate = asofDateJson.getString("date");
    System.out.println("asofDate=" + asofDate);



    JSONObject cashJson = accountJsonObject.getJSONObject("cash");
    String cash_amount = cashJson.getString("amount");
    String currencyCode = cashJson.getString("currencyCode");


    JSONObject totalBalanceJson = accountJsonObject.getJSONObject("totalBalance");
    String totalBalance_amount = totalBalanceJson.getString("amount");
    String totalBalance_currencyCode = totalBalanceJson.getString("currencyCode");

    JSONObject shortBalanceJson = accountJsonObject.getJSONObject("shortBalance");
    String shortBalance_amount = shortBalanceJson.getString("amount");
    String shortBalance_currencyCode = shortBalanceJson.getString("currencyCode");
    System.out.println("shortBalance=" + shortBalance_amount);


    JSONObject totalUnvestedBalanceJson = accountJsonObject.getJSONObject("totalUnvestedBalance");
    String totalUnvestedBalanceJson_amount = totalUnvestedBalanceJson.getString("amount");
    String totalUnvestedBalanceJson_currencyCode = totalUnvestedBalanceJson.getString("currencyCode");
    System.out.println("totalUnvestedBalanceJson=" + totalUnvestedBalanceJson_amount);


    String planNumber = accountJsonObject.getString("planNumber");
    System.out.println("planNumber=" + planNumber);


    JSONObject marginBalanceJson = accountJsonObject.getJSONObject("marginBalance");
    String marginBalance_amount = marginBalanceJson.getString("amount");
    String marginBalance_currencyCode = marginBalanceJson.getString("currencyCode");
    System.out.println("marginBalance=" + marginBalance_amount);

    JSONObject moneyMarketBalanceJson = accountJsonObject.getJSONObject("moneyMarketBalance");
    String moneyMarketBalance_amount = moneyMarketBalanceJson.getString("amount");
    String moneyMarketBalance_currencyCode = moneyMarketBalanceJson.getString("currencyCode");
    System.out.println("moneyMarketBalance=" + moneyMarketBalance_amount);

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

      // String daysRemaining = holdingElementJson.getString("daysRemaining");
      // System.out.println("daysRemaining=" + daysRemaining);

      String term = holdingElementJson.getString("term");
      System.out.println("term=" + term);
      
      String interestRate = holdingElementJson.getString("interestRate");
      System.out.println("interestRate=" + interestRate);

    }



    // DB persist starts

    System.out.println("InvestmentDataDB!");
    Connection connection = null;
    PreparedStatement prepStatement = null;
    Statement statement = null;

    try { // Step 2 - register and load the driver Class.forName(JDBC_DRIVER);

      // step 3 : open connection System.out.println("Connecting to database"); connection =
      connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);


      // /STEP 4: Execute a query System.out.println("Creating statement...");

      String sql =
          "INSERT INTO InvestmentData (itemId,UserID,accountType,AccountNumber,accountName,accountClassification,planName,asOf,cash,marginBalance,totalBalance,totalVestedBalance,accountHolder,moneyMarketBalance,accruedInterest,yodleeAccountStatusID,totalUnVestedBalance,totalInVestedAmount,t401kLoan,secondaryAccountHolderName,linkedBankAccountNumber,nomineeName,accountOpenDate,accountCloseDate,planNumber,mutualFundFolioNumber,annuityBalance,annuityDeathBenefit,cashOptionBuyingPower,dayTradingMarginBuyingPower,dividendEarnedAsPayOut,shortBalance,yodleeInvestmentDataID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


      System.out.println("sql=" + sql);
      System.out.println("itemId=" + itemId);
      prepStatement = connection.prepareStatement(sql);
      prepStatement.setInt(1, 10149817);
      prepStatement.setString(2, "101");
      prepStatement.setString(3, "1");
      prepStatement.setString(4, accountNumber);
      prepStatement.setString(5, accountName);
      prepStatement.setString(6, accountClassification);
      prepStatement.setString(7, planName);
      prepStatement.setString(8, asofDate);// TODO convert to date
      prepStatement.setString(9, cash_amount);
      prepStatement.setString(10, marginBalance_amount);
      prepStatement.setString(11, totalBalance_amount);
      prepStatement.setString(12, totalVestedBalance_amount);
      prepStatement.setString(13, accountHolder);
      prepStatement.setString(14, moneyMarketBalance_amount);
      prepStatement.setString(15, "null");
      prepStatement.setString(16, "null");
      prepStatement.setString(17, totalUnvestedBalanceJson_amount);
      prepStatement.setString(18, "null");
      prepStatement.setString(19, "null");
      prepStatement.setString(20, "null");
      prepStatement.setString(21, "null");
      prepStatement.setString(22, "null");
      prepStatement.setString(23, "null");
      prepStatement.setString(24, "null");
      prepStatement.setString(25, planNumber);
      prepStatement.setString(26, "null");
      prepStatement.setString(27, "null");
      prepStatement.setString(28, "null");
      prepStatement.setString(29, "null");
      prepStatement.setString(30, "null");
      prepStatement.setString(31, "null");
      prepStatement.setString(32, shortBalance_amount);
      prepStatement.setInt(33, 1);


      // int rowsAffectd = prepStatement.executeUpdate();
      // System.out.println("rowsAffectd=" + rowsAffectd);

      sql = "select username , password from Cobrand";
      // step 6 : cleanup prepStatement.close(); connection.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally { // finally block used to close resources
      try {
        if (prepStatement != null)
          prepStatement.close();
      } catch (SQLException se2) {
      }
      // nothing we can do try
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }



  }
}
