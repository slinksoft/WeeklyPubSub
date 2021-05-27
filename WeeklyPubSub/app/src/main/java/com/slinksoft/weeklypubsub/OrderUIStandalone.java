package com.slinksoft.weeklypubsub;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;

public class OrderUIStandalone extends AppCompatActivity {

    WebView browser;
    boolean notified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_u_i_standalone);
        // store browser UI reference in respective variable to be able to call its methods
        browser = findViewById(R.id.orderWebView);

        AlertDialog note = new AlertDialog.Builder(OrderUIStandalone.this).create();
        note.setCanceledOnTouchOutside(false);
        note.setTitle("Order Sub");
        note.setMessage("NOTE: You are about to access Publix.com through the app. You may need to enter " +
                "your local ZIP code to be able to order the sub on sale and view the promotional savings. You can do this by tapping the main menu icon " +
                "on the website (top right 3 lines icon), then tap \"Choose a Store\" on the bottom. After choosing a store, you will be redirected " +
                "to the order menu to proceed with your order.\n\nNOTE: Use the \"Back\" and \"Forward\" buttons if you need to go back to the previous page or " +
                "return to a current page. You can take a screenshot of your order confirmation if you wish to do so.");
        note.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
        note.setButton(AlertDialog.BUTTON_POSITIVE, "Visit Publix.com to order",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        // creates order service
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

                        // set WebViewClient to perform action to the UI WebView reference when a page finishes loading
                        browser.setWebViewClient(new WebViewClient() {
                            public void onPageFinished(WebView view, String url)
                            {
                                if (browser.getUrl().equals("https://www.publix.com/shop-online/in-store-pickup/checkout"))
                                {
                                    if (!notified)
                                    {
                                        createSendPushNotification();
                                        notified = true;
                                    }
                                }
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });

        note.show();
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

    private void createSendPushNotification()
    {
        // Create an explicit intent for an Activity in your app

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Pub_Sub_Channel")
                .setSmallIcon(R.drawable.p)
                .setContentTitle("Thank You For Your Order.")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("As a Publix I/S associate, I appreciate you ordering from us. We strive and work hard everyday in order to deliver high quality food, products and service to " +
                                "our customers. Our main goal at Publix Super Markets is to provide premier service to all of our customers; we wouldn't be able to accomplish " +
                                "that goal without you. On the behalf of Publix, I thank you. \uD83D\uDC9A\uD83D\uDC9A\uD83D\uDC9A #PubSub \uD83D\uDC9A\uD83D\uDC9A\uD83D\uDC9A"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0))
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        notificationManager.notify(2, builder.build());
    }
}