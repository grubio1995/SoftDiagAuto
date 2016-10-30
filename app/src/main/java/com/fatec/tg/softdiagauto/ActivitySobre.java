package com.fatec.tg.softdiagauto;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Created by Gabriel Rubio on 23/10/2016.
 */

public class ActivitySobre extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar act = getActionBar();
        WebView mWebView = null;

        if (act == null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        else
            act.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_sobre);
        formatarTexto(mWebView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public void formatarTexto(WebView mWebView){

        mWebView = (WebView) findViewById(R.id.webview);

        String text = "<html><body>"
                + "<p align=\"justify\">"
                + getString(R.string.txt_sobre)
                + "</p> "
                + "</body></html>";

        mWebView.loadData(text,"text/html;charset=UTF-8",null);
    }


}
