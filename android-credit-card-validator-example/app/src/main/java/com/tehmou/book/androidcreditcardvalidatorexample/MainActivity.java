package com.tehmou.book.androidcreditcardvalidatorexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText creditCardNumber = (EditText) findViewById(R.id.credit_card_number);
        EditText creditCardCvc = (EditText) findViewById(R.id.credit_card_cvc);
        EditText creditCardExpirationDate = (EditText) findViewById(R.id.expiration_date);

        Observable<String> creditCardNumberObservable = RxTextView.textChanges(creditCardNumber).map(CharSequence::toString);
        Observable<String> cvcCodeObservable = RxTextView.textChanges(creditCardCvc).map(CharSequence::toString);
        Observable<String> expirationDateObservable = RxTextView.textChanges(creditCardExpirationDate).map(CharSequence::toString);

        Observable<Boolean> isExpirationDateValid = expirationDateObservable.map(ValidationUtils::checkExpirationDate);
        Observable<CardType> cardTypeObservable = creditCardNumberObservable.map(CardType::fromString);
        Observable<Boolean> isCardTypeValid = cardTypeObservable.map(cardType -> cardType != CardType.UNKNOWN);
        Observable<Boolean> isCheckSumValid = creditCardNumberObservable
           .map(ValidationUtils::convertFromStringToIntArray)
           .map(ValidationUtils::checkCardChecksum);
        Observable<Boolean> isCreditCardNumberValid = ValidationUtils.and(isCardTypeValid, isCheckSumValid);
    }
}
