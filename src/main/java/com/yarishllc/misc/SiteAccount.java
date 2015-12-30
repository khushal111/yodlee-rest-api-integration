package com.yarishllc.misc;

import org.json.JSONObject;

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

  public SiteAccount() {}

  public SiteAccount(String siteAccountId, String siteRefreshStatus, String code, String suggestedFlowId,
      String suggestedFlow) {
    super();
    this.siteAccountId = siteAccountId;
    this.siteRefreshStatus = siteRefreshStatus;
    this.code = code;
    this.suggestedFlowId = suggestedFlowId;
    this.suggestedFlow = suggestedFlow;
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

  public String getSiteRefreshStatusId() {
    return siteRefreshStatusId;
  }

  public void setSiteRefreshStatusId(String siteRefreshStatusId) {
    this.siteRefreshStatusId = siteRefreshStatusId;
  }

  public String getSiteAccountId() {
    return siteAccountId;
  }

  public void setSiteAccountId(String siteAccountId) {
    this.siteAccountId = siteAccountId;
  }

  public String getSiteRefreshStatus() {
    return siteRefreshStatus;
  }

  public void setSiteRefreshStatus(String siteRefreshStatus) {
    this.siteRefreshStatus = siteRefreshStatus;
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

}
