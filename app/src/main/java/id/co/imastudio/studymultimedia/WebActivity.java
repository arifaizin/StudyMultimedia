package id.co.imastudio.studymultimedia;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static id.co.imastudio.studymultimedia.R.id.webView;

public class WebActivity extends AppCompatActivity {


    private static final String USER_AGENT_FAKE = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        web = (WebView) findViewById(webView);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setAllowContentAccess(true);
        web.getSettings().setAllowFileAccessFromFileURLs(true);
        web.getSettings().setAllowUniversalAccessFromFileURLs(true);
        web.getSettings().setDatabaseEnabled(true);
        web.getSettings().setDisplayZoomControls(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setUserAgentString(USER_AGENT_FAKE);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        web.loadUrl("http://www.facebook.com");

        web.setWebViewClient(new WebViewClient() {
                                 @Override
                                 public void onPageFinished(WebView view, String url) {
                                     super.onPageFinished(view, url);
                                     getSupportActionBar().setTitle(web.getTitle());
                                 }

                                 @Override
                                 public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                                     super.onReceivedSslError(view, handler, error);
                                     handler.proceed();
                                 }

                                 @Override
                                 public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                     view.loadUrl(url);
                                     return false;
                                 }

//                                 @Override
//                                 public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                                     return super.shouldOverrideUrlLoading(view, request);
//                                 }
                             }

        );

        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(web.getTitle());
            }


            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                Log.d("TES", "onPermissionRequest");
                WebActivity.this.runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        if (request.getOrigin().toString().equals("http://wondho.xyz")) {
                            request.grant(request.getResources());
                        } else {
                            request.deny();
                        }
                    }
                });
            }
        });


    }

    //generate override mrtho OnBackPress

    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }

    //generate override OnConfigChange

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
