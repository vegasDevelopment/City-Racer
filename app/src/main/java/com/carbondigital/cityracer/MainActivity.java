package com.carbondigital.cityracer;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        WebSettings s = webView.getSettings();
        
        // Temel ayarlar
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        s.setAllowFileAccessFromFileURLs(true);
        s.setAllowUniversalAccessFromFileURLs(true);
        s.setMediaPlaybackRequiresUserGesture(false); // Seslerin otomatik çalması için
        
        // Önbelleği sıfırla (Eski hatalı dosyaların yüklenmesini engeller)
        s.setCacheMode(WebSettings.LOAD_NO_CACHE);
        
        // WebGL ve donanım hızlandırma desteğini zorla
        s.setJavaScriptCanOpenWindowsAutomatically(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true); // Hata ayıklama için
        }

        webView.setWebViewClient(new WebViewClient()); // İstekleri engelleyen kodu sildik

        webView.loadUrl("file:///android_asset/www/index.html");
        setContentView(webView);
    }
}
