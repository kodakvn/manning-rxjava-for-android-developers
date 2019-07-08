package com.tehmou.book.androidcreditcardvalidatorexample;

import java.util.regex.Pattern;

public class ValidationUtils {
   static boolean checkExpirationDate(String candidate) {
      return candidate.matches("\\d\\d/\\d\\d");
   }
}
