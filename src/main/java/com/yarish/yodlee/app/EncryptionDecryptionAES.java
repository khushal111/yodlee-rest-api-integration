package com.yarish.yodlee.app;

// http://javapapers.com/java/java-symmetric-aes-encryption-decryption-using-jce/

import static org.apache.commons.codec.binary.Hex.encodeHex;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionDecryptionAES {
  static Cipher cipher;

  public static void main(String[] args) throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    keyGenerator.init(128);
    SecretKey secretKey = keyGenerator.generateKey();
    byte[] encoded = secretKey.getEncoded();
    char[] hex = encodeHex(encoded);
    String secretKeytext = String.valueOf(hex);
//    secretKeytext = "06fdec84346edff41105b4813fae4cca";
    System.out.println("secretKeytext=" + secretKeytext);
    cipher = Cipher.getInstance("AES");


    String plainText = "74bea3d6-a3b1-4681-8cfd-d2a6b3b9a66a";
    System.out.println("Plain Text Before Encryption: " + plainText);

    String encryptedText = encrypt(plainText, secretKey);
    System.out.println("Encrypted Text After Encryption: " + encryptedText);

    String decryptedText = decrypt(encryptedText, secretKey);
    System.out.println("Decrypted Text After Decryption: " + decryptedText);
  }

  public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
    byte[] plainTextByte = plainText.getBytes();
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptedByte = cipher.doFinal(plainTextByte);
    Base64.Encoder encoder = Base64.getEncoder();
    String encryptedText = encoder.encodeToString(encryptedByte);
    return encryptedText;
  }

  public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
    Base64.Decoder decoder = Base64.getDecoder();
    byte[] encryptedTextByte = decoder.decode(encryptedText);
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
    String decryptedText = new String(decryptedByte);
    return decryptedText;
  }



}
