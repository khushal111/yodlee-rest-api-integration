package com.yarish.yodlee.app;

public class LoginFailedException extends Exception {

  private static final long serialVersionUID = -25065833425305070L;

  public LoginFailedException(String message) {
    super(message);
  }

}
