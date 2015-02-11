package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;


public class ForeignExchange extends Activity implements AsyncGetForeignCurrency.ResultsPassing {

    Currency final_results;
    TextView inr, usd, yen, won, cand, real, aud, yuan, singd, mexpeso, gbp;
    ProgressDialog progressDialog;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_exchange);

        new AsyncGetForeignCurrency(ForeignExchange.this).execute("INR");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("USD");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("JPY");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("KRW");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("CAD");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("BRL");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("AUD");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("CNY");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("SGD");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("MXN");
        new AsyncGetForeignCurrency(ForeignExchange.this).execute("GBP");

        ProgressDialogStart("Loading ForEx values...");
        inr = (TextView) findViewById(R.id.inr);
        usd = (TextView) findViewById(R.id.usd);
        yen = (TextView) findViewById(R.id.yen);
        won = (TextView) findViewById(R.id.won);
        cand = (TextView) findViewById(R.id.cand);
        real = (TextView) findViewById(R.id.real);
        aud = (TextView) findViewById(R.id.aud);
        yuan = (TextView) findViewById(R.id.yuan);
        singd = (TextView) findViewById(R.id.singd);
        mexpeso = (TextView) findViewById(R.id.mexpeso);
        gbp = (TextView) findViewById(R.id.gbp);

    }

    @Override
    public void getResult(Currency result) {
        this.final_results = result;

        if (result.getCurrency_code().equals("INR")) {
            inr.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("USD")) {
            usd.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("JPY")) {
            yen.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("KRW")) {
            won.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("CAD")) {
            cand.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("BRL")) {
            real.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("AUD")) {
            aud.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("CNY")) {
            yuan.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("SGD")) {
            singd.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("MXN")) {
            mexpeso.setText(result.getCurrency_rate());
            count++;
        }
        if (result.getCurrency_code().equals("GBP")) {
            gbp.setText(result.getCurrency_rate());
            count++;
        }
        if(count >= 11)
        {
            progressDialogStop();
        }
    }

    public void ProgressDialogStart(String str) {
        progressDialog = ProgressDialog.show(this, null, str);
        progressDialog.setCancelable(false);
    }

    public void progressDialogStop() {
        progressDialog.dismiss();
    }
}
