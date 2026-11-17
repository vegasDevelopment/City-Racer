package com.carbondigital.cityracer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ValueCallback<Uri[]> filePathCb;

    private final ActivityResultLauncher<Intent> picker = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(), r -> {
            Uri[] res = null;
            Intent d = r.getData();
            if (r.getResultCode() == RESULT_OK && d != null) {
                if (d.getClipData() != null) {
                    res = new Uri[d.getClipData().getItemCount()];
                    for (int i = 0; i < res.length; i++) res[i] = d.getClipData().getItemAt(i).getUri();
                } else if (d.getData() != null) res = new Uri[]{ d.getData() };
            }
            if (filePathCb != null) filePathCb.onReceiveValue(res);
            filePathCb = null;
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);

        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override 
            public boolean onShowFileChooser(WebView w, ValueCallback<Uri[]> cb, FileChooserParams p) {
                if (filePathCb != null) filePathCb.onReceiveValue(null);
                filePathCb = cb;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                picker.launch(Intent.createChooser(i, "Dosya Seç"));
                return true;
            }
        });

        webView.loadUrl("file:///android_asset/www/index.html");
        setContentView(webView);
    }
}
