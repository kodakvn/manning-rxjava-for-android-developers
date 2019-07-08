package com.tehmou.book.androidcreditcardvalidatorexample;

import java.util.ArrayList;
import java.util.regex.Pattern;

import io.reactivex.Observable;

public class ValidationUtils {
   static boolean checkExpirationDate(String candidate) {
      return candidate.matches("\\d\\d/\\d\\d");
   }

   public static Observable<Boolean> and(Observable<Boolean> a, Observable<Boolean> b) {
      return Observable.combineLatest(a, b, (valueA, valueB) -> valueA && valueB);
   }

   public static boolean checkCardChecksum(int[] digits) {
      int sum = 0;
      int length = digits.length;
      for (int i = 0; i < length; i++) {
         int digit = digits[length - i - 1];
         if (i % 2 == 1) {
            digit *= 2;
         }
         sum += digit > 9 ? digit - 9 : digit;
      }
      return sum % 10 == 0;
   }

   public static int[] convertFromStringToIntArray(String pattern) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      for (int i = 0; i < pattern.length(); i++) {
         Character c = pattern.charAt(i);
         Integer num = Integer.parseInt(c.toString());
         if (num != null) {
            list.add(num);
         }
      }

      int[] numbers = new int[list.size()];
      for (int i = 0; i < list.size(); i++) {
         numbers[i] = list.get(i).intValue();
      }

      return numbers;
   }
}
