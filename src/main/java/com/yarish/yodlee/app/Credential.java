package com.yarish.yodlee.app;

public class Credential {
  String valueIdentifier;
  String valueMask;
  String fieldType;
  String size;
  String maxlength;
  String fieldInfoType;
  String name;
  String displayName;
  String isEditable;
  String isOptional;
  String isEscaped;
  String helpText;
  String isOptionalMFA;
  String isMFA;

  public Credential(String valueIdentifier, String valueMask, String fieldType, String size, String maxlength,
      String fieldInfoType, String name, String displayName, String isEditable, String isOptional, String isEscaped,
      String helpText, String isOptionalMFA, String isMFA) {
    super();
    this.valueIdentifier = valueIdentifier;
    this.valueMask = valueMask;
    this.fieldType = fieldType;
    this.size = size;
    this.maxlength = maxlength;
    this.fieldInfoType = fieldInfoType;
    this.name = name;
    this.displayName = displayName;
    this.isEditable = isEditable;
    this.isOptional = isOptional;
    this.isEscaped = isEscaped;
    this.helpText = helpText;
    this.isOptionalMFA = isOptionalMFA;
    this.isMFA = isMFA;
  }

  public String getValueIdentifier() {
    return valueIdentifier;
  }

  public void setValueIdentifier(String valueIdentifier) {
    this.valueIdentifier = valueIdentifier;
  }

  public String getValueMask() {
    return valueMask;
  }

  public void setValueMask(String valueMask) {
    this.valueMask = valueMask;
  }

  public String getFieldType() {
    return fieldType;
  }

  public void setFieldType(String fieldType) {
    this.fieldType = fieldType;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getMaxlength() {
    return maxlength;
  }

  public void setMaxlength(String maxlength) {
    this.maxlength = maxlength;
  }

  public String getFieldInfoType() {
    return fieldInfoType;
  }

  public void setFieldInfoType(String fieldInfoType) {
    this.fieldInfoType = fieldInfoType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getIsEditable() {
    return isEditable;
  }

  public void setIsEditable(String isEditable) {
    this.isEditable = isEditable;
  }

  public String getIsOptional() {
    return isOptional;
  }

  public void setIsOptional(String isOptional) {
    this.isOptional = isOptional;
  }

  public String getIsEscaped() {
    return isEscaped;
  }

  public void setIsEscaped(String isEscaped) {
    this.isEscaped = isEscaped;
  }

  public String getHelpText() {
    return helpText;
  }

  public void setHelpText(String helpText) {
    this.helpText = helpText;
  }

  public String getIsOptionalMFA() {
    return isOptionalMFA;
  }

  public void setIsOptionalMFA(String isOptionalMFA) {
    this.isOptionalMFA = isOptionalMFA;
  }

  public String getIsMFA() {
    return isMFA;
  }

  public void setIsMFA(String isMFA) {
    this.isMFA = isMFA;
  }


}
