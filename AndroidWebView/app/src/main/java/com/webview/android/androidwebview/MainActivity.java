package com.webview.android.androidwebview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Ugur on 20.10.2014.
 */
public class MainActivity extends Activity{
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        web=(WebView)findViewById(R.id.webView);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("http://www.yourdomain.com");
        //webview üzerinde tıklanan adresleri webview içerisinde açar.
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.facebook_button:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("facebook adresi"));
                startActivity(browserIntent);
                break;
            case R.id.twitter_button:
                Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter adresi"));
                startActivity(browserIntent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //cihaz üzerindeki geri tuşuna basınca bir önceki sayfayı açması için
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
                case KeyEvent.KEYCODE_BACK:
                    if(web.canGoBack()){
                        web.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
