package com.slinksoft.weeklypubsub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class OrderUI extends AppCompatActivity {

    WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_u_i);
        // store browser UI reference in respective variable to be able to call its methods
        browser = findViewById(R.id.orderWebView);

        // set necessary options for Publix.com to function in WebView reference
        browser.setWebViewClient(new WebViewClient());
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setDomStorageEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setDisplayZoomControls(false);
        browser.getSettings().setSupportZoom(true);
        browser.getSettings().setDefaultTextEncodingName("utf-8");
        browser.loadUrl("https://www.publix.com/shop-online/in-store-pickup/");
    }

    // close activity, go back to main activity
    public void onBackClick(View v)
    {
        finish();
    }

    public void backBrowser(View v)
    {
        browser.goBack();
    }

    public void forwardBrowser(View v)
    {
        browser.goForward();
    }

    public void takeScreenshot(View v) {
        Date now = new Date(); // create date object for screenshot file name usage
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now); // get current date and time

        try {
            // take screenshot
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            // create bitmap to store screen capture
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            // use MediaStore to save screenshot to default Pictures directory
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap , now + "pubscreenshot", "N/A");

            Toast.makeText(getApplicationContext(), "Screenshot saved. Check your photo gallery!", Toast.LENGTH_SHORT).show();

        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            Toast.makeText(getApplicationContext(), "An error occurred!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}