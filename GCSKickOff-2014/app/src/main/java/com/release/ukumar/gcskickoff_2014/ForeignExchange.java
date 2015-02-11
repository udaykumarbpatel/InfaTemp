package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ForeignExchange extends Activity implements AsyncGetForeignCurrency.ResultsPassing {

    Currency final_results;
    TextView inr, usd, yen, won, cand, real, aud, yuan, singd, mexpeso, gbp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_exchange);
        String temp_url = "http://openexchangerates.org/api/latest.json?app_id=a911ba9948cf4084abbc05d1c91791fe";
        new AsyncGetForeignCurrency(ForeignExchange.this).execute(temp_url);

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
        inr.setText(result.getCurr_inr());
        usd.setText(result.getCurr_usd());
        yen.setText(result.getCurr_jpy());
        won.setText(result.getCurr_krw());
        cand.setText(result.getCurr_cad());
        real.setText(result.getCurr_brl());
        aud.setText(result.getCurr_aud());
        yuan.setText(result.getCurr_cny());
        singd.setText(result.getCurr_sgd());
        mexpeso.setText(result.getCurr_mxn());
        gbp.setText(result.getCurr_gbp());
    }
}
