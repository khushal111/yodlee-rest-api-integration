package com.yarish.yodlee.app;

import static com.yarish.yodlee.app.KeyStoreUtils.generateKey;
import static com.yarish.yodlee.app.KeyStoreUtils.loadKey;
import static com.yarish.yodlee.app.KeyStoreUtils.saveKey;

import java.io.File;

import javax.crypto.SecretKey;

public class TestCreationStoreAndRetrieval {
  public static void main(String args[]) {
    int failures = 0;
    System.out.println("Starting test...");
    for (int i = 0; i < 1; i++) {
      // System.out.print('.');
      if (!testOnce())
        failures++;
      // if (i % 50 == 0)
      // {
      // System.out.println();
      // }
    }
    System.out.println("Done. " + failures);
  }

  private static boolean testOnce() {
    try {
      SecretKey originalKey = generateKey();
      File file = File.createTempFile("sjks", ".key");
      saveKey(originalKey, file);
      SecretKey persistedKey = loadKey(file);

      String bitsOrig =
          String.format("%8s", Integer.toBinaryString(originalKey.getEncoded()[0] & 0xFF)).replace(' ', '0');
      String bitsPersist =
          String.format("%8s", Integer.toBinaryString(persistedKey.getEncoded()[0] & 0xFF)).replace(' ', '0');
      System.out.println(String.format("%s (%d) -- %s (%d)", bitsOrig, originalKey.getEncoded()[0], bitsPersist,
          persistedKey.getEncoded()[0]));

      if (!originalKey.equals(persistedKey)) {
        System.err.println(String.format("Key %s does not match %s: %s", originalKey, persistedKey, file));
        System.out.println();

        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
