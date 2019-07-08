package com.tehmou.book.androidcreditcardvalidatorexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "kkk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get UI elements
        EditText creditCardNumber = (EditText) findViewById(R.id.credit_card_number);
        EditText creditCardCvc = (EditText) findViewById(R.id.credit_card_cvc);
        EditText creditCardExpirationDate = (EditText) findViewById(R.id.expiration_date);
        Button submitFormButton = (Button) findViewById(R.id.submit_button);

        // create observable from text changes event
        Observable<String> creditCardNumberObservable = RxTextView.textChanges(creditCardNumber).map(CharSequence::toString);
        Observable<String> cvcCodeObservable = RxTextView.textChanges(creditCardCvc).map(CharSequence::toString);
        Observable<String> expirationDateObservable = RxTextView.textChanges(creditCardExpirationDate).map(CharSequence::toString);

        // expiration date
        Pattern expirationDatePattern = Pattern.compile("^\\d\\d/\\d\\d$");
        final Observable<Boolean> isExpirationDateValid = expirationDateObservable.map(text -> expirationDatePattern.matcher(text).find());

        // card number
        Observable<CardType> cardTypeObservable = creditCardNumberObservable.map(CardType::fromString);
        Observable<Boolean> isCardTypeValid = cardTypeObservable.map(cardType -> cardType != CardType.UNKNOWN);
        Observable<Boolean> isCheckSumValid = creditCardNumberObservable
           .map(ValidationUtils::convertFromStringToIntArray)
           .map(ValidationUtils::checkCardChecksum);
        Observable<Boolean> isCreditCardNumberValid = ValidationUtils.and(isCardTypeValid, isCheckSumValid);

        // cvc
        Observable<Integer> requiredCvcLength = cardTypeObservable.map(CardType::getCvcLength);
        Observable<Integer> cvcInputLength = cvcCodeObservable.map(String::length);
        Observable<Boolean> isCvcCodeValid = ValidationUtils.equals(requiredCvcLength, cvcInputLength);

        // put them all
        Observable<Boolean> isFormValidObservable = ValidationUtils.and(
           isCreditCardNumberValid.doOnNext(value -> Log.d(TAG, "isCreditCardNumberValid: " + value)),
           isCheckSumValid,
           isCvcCodeValid);
        isFormValidObservable
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(submitFormButton::setEnabled);


    }
}
