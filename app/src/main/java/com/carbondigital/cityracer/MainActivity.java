package com.carbondigital.cityracer;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * OFFLINE MOD
 * - Bu sınıf, WebView'de http:// ve https:// isteklerini engeller.
 * - Uygulama tamamen yerel varlıklarla (app/src/main/assets/www) çalışır.
 * - AndroidManifest.xml'de INTERNET izni istenmez.
 * - HTML içindeki CDN / uzak kaynaklar çalışmaz, yerel kopyalarını koyun.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        s.setAllowFileAccessFromFileURLs(true);
        s.setAllowUniversalAccessFromFileURLs(true);
        s.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    // Offline modda dış istekleri boş yanıtla engelle
                    return new WebResourceResponse(
                        "text/plain", "UTF-8",
                        new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
                }
                return null;
            }
        });

        webView.loadUrl("file:///android_asset/www/index.html");
        setContentView(webView);
    }
}
