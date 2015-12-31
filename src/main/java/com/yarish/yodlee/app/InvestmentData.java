package com.yarish.yodlee.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InvestmentData {

  int yodleeInvestmentDataID;
  int accountType;
  int itemId;
  int userId; 
  String accountNumber;
  String accountName;
  String accountClassification;
  String planName;
  Date asOf;
  Double cash;
  Double marginBalance;
  Double totalBalance;
  Double totalVestedBalance;
  String accountHolder;
  Double moneyMarketBalance;
  Double accruedInterest; // not available
  int yodleeAccountStatusID; // TODO yet to scrape
  Double totalUnVestedBalance;
  Double totalInVestedAmount; // not availble 
  Double t401kLoan;
  String secondaryAccountHolderName; // not availble 
  String linkedBankAccountNumber; // not availble
  String nomineeName;// not availble
  Date accountOpenDate;// not availble
  Date accountCloseDate;// not availble
  String planNumber; 
  String mutualFundFolioNumber; // not availble
  String annuityBalance; // not availble
  Double annuityDeathBenefit;// not availble
  Double cashOptionBuyingPower;// not availble
  Double dayTradingMarginBuyingPower;// not availble
  Double dividendEarnedAsPayOut;// not availble
  Double shortBalance;

  List<Holdings> holdingsList = new ArrayList<Holdings>();


  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public List<Holdings> getHoldingsList() {
    return holdingsList;
  }

  public void setHoldingsList(List<Holdings> holdingsList) {
    this.holdingsList = holdingsList;
  }

  public int getYodleeInvestmentDataID() {
    return yodleeInvestmentDataID;
  }

  public void setYodleeInvestmentDataID(int yodleeInvestmentDataID) {
    this.yodleeInvestmentDataID = yodleeInvestmentDataID;
  }

  public int getAccountType() {
    return accountType;
  }

  public void setAccountType(int accountType) {
    this.accountType = accountType;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getAccountClassification() {
    return accountClassification;
  }

  public void setAccountClassification(String accountClassification) {
    this.accountClassification = accountClassification;
  }

  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }

  public Date getAsOf() {
    return asOf;
  }

  public void setAsOf(Date asOf) {
    this.asOf = asOf;
  }

  public Double getCash() {
    return cash;
  }

  public void setCash(Double cash) {
    this.cash = cash;
  }

  public Double getMarginBalance() {
    return marginBalance;
  }

  public void setMarginBalance(Double marginBalance) {
    this.marginBalance = marginBalance;
  }

  public Double getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(Double totalBalance) {
    this.totalBalance = totalBalance;
  }

  public Double getTotalVestedBalance() {
    return totalVestedBalance;
  }

  public void setTotalVestedBalance(Double totalVestedBalance) {
    this.totalVestedBalance = totalVestedBalance;
  }


  public String getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }

  public Double getMoneyMarketBalance() {
    return moneyMarketBalance;
  }

  public void setMoneyMarketBalance(Double moneyMarketBalance) {
    this.moneyMarketBalance = moneyMarketBalance;
  }

  public Double getAccruedInterest() {
    return accruedInterest;
  }

  public void setAccruedInterest(Double accruedInterest) {
    this.accruedInterest = accruedInterest;
  }

  public int getYodleeAccountStatusID() {
    return yodleeAccountStatusID;
  }

  public void setYodleeAccountStatusID(int yodleeAccountStatusID) {
    this.yodleeAccountStatusID = yodleeAccountStatusID;
  }

  public Double getTotalUnVestedBalance() {
    return totalUnVestedBalance;
  }

  public void setTotalUnVestedBalance(Double totalUnVestedBalance) {
    this.totalUnVestedBalance = totalUnVestedBalance;
  }

  public Double getTotalInVestedAmount() {
    return totalInVestedAmount;
  }

  public void setTotalInVestedAmount(Double totalInVestedAmount) {
    this.totalInVestedAmount = totalInVestedAmount;
  }

  public Double getT401kLoan() {
    return t401kLoan;
  }

  public void setT401kLoan(Double t401kLoan) {
    this.t401kLoan = t401kLoan;
  }

  public String getSecondaryAccountHolderName() {
    return secondaryAccountHolderName;
  }

  public void setSecondaryAccountHolderName(String secondaryAccountHolderName) {
    this.secondaryAccountHolderName = secondaryAccountHolderName;
  }

  public String getLinkedBankAccountNumber() {
    return linkedBankAccountNumber;
  }

  public void setLinkedBankAccountNumber(String linkedBankAccountNumber) {
    this.linkedBankAccountNumber = linkedBankAccountNumber;
  }

  public String getNomineeName() {
    return nomineeName;
  }

  public void setNomineeName(String nomineeName) {
    this.nomineeName = nomineeName;
  }

  public Date getAccountOpenDate() {
    return accountOpenDate;
  }

  public void setAccountOpenDate(Date accountOpenDate) {
    this.accountOpenDate = accountOpenDate;
  }

  public Date getAccountCloseDate() {
    return accountCloseDate;
  }

  public void setAccountCloseDate(Date accountCloseDate) {
    this.accountCloseDate = accountCloseDate;
  }

  public String getPlanNumber() {
    return planNumber;
  }

  public void setPlanNumber(String planNumber) {
    this.planNumber = planNumber;
  }

  public String getMutualFundFolioNumber() {
    return mutualFundFolioNumber;
  }

  public void setMutualFundFolioNumber(String mutualFundFolioNumber) {
    this.mutualFundFolioNumber = mutualFundFolioNumber;
  }

  public String getAnnuityBalance() {
    return annuityBalance;
  }

  public void setAnnuityBalance(String annuityBalance) {
    this.annuityBalance = annuityBalance;
  }

  public Double getAnnuityDeathBenefit() {
    return annuityDeathBenefit;
  }

  public void setAnnuityDeathBenefit(Double annuityDeathBenefit) {
    this.annuityDeathBenefit = annuityDeathBenefit;
  }

  public Double getCashOptionBuyingPower() {
    return cashOptionBuyingPower;
  }

  public void setCashOptionBuyingPower(Double cashOptionBuyingPower) {
    this.cashOptionBuyingPower = cashOptionBuyingPower;
  }

  public Double getDayTradingMarginBuyingPower() {
    return dayTradingMarginBuyingPower;
  }

  public void setDayTradingMarginBuyingPower(Double dayTradingMarginBuyingPower) {
    this.dayTradingMarginBuyingPower = dayTradingMarginBuyingPower;
  }

  public Double getDividendEarnedAsPayOut() {
    return dividendEarnedAsPayOut;
  }

  public void setDividendEarnedAsPayOut(Double dividendEarnedAsPayOut) {
    this.dividendEarnedAsPayOut = dividendEarnedAsPayOut;
  }

  public Double getShortBalance() {
    return shortBalance;
  }

  public void setShortBalance(Double shortBalance) {
    this.shortBalance = shortBalance;
  }


}
