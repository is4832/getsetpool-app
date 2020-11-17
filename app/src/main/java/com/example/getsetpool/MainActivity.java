package com.example.getsetpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity {

    public class GeoWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // When user clicks a hyperlink, load in the existing WebView
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * WebChromeClient subclass handles UI-related calls
     * Note: think chrome as in decoration, not the Chrome browser
     */
    public class GeoWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            // Always grant permission since the app itself requires location
            // permission and the user has therefore already granted it
            callback.invoke(origin, true, false);
        }
    }

    WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView=(WebView)findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.setWebViewClient(new GeoWebViewClient());
        // Below required for geolocation
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setGeolocationEnabled(true);
        myWebView.setWebChromeClient(new GeoWebChromeClient());
        myWebView.loadUrl("https://get-set-pool.herokuapp.com/");
    }


    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
