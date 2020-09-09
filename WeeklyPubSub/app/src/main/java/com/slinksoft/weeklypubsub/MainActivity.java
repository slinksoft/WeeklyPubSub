package com.slinksoft.weeklypubsub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String information, salesinfo, sub, date, price, udate;
    TextView s, p, d, u, version;
    int ver, rever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // store UI references in respective variables to be able to call their methods
        s = findViewById(R.id.subDisplay);
        p = findViewById(R.id.priceDisplay);
        d = findViewById(R.id.dateDisplay);
        u = findViewById(R.id.udateDisplay);
        version = findViewById(R.id.vDisplay);
        ver = 1;
        rever = 3;

        // set version in UI
        version.setText("Version: " + ver + "." + rever);
        this.setTitle("Weekly Pub Sub! - Slink Soft");
        // start accessing API to receive sales info
        try {
            Toast.makeText(getApplicationContext(), "Loading sales info...", Toast.LENGTH_SHORT).show();
            acesssWebApp(); // call to start info fetch process
        } catch (Exception e) {
            e.printStackTrace();
            onErrorToast();

        }

    }

    private void acesssWebApp() {
        // We cannot use a BufferedReader in the UI execution thread, therefore, we must execute in a separate thread

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL pubweekly = new URL("https://pubweekly.herokuapp.com/"); // create URL object
                    BufferedReader read = new BufferedReader(new InputStreamReader(pubweekly.openStream())); // created buffered reader
                    information = read.readLine(); // read the contents of the URL
                    System.out.println("run new thread executed with " + information);
                    read.close(); // close buffered reader
                    processSaleInfo(information); // call to access 2nd part of API to process retrieved info
                } catch (Exception e) {

                    System.out.println("exception: " + e);
                    onErrorToast();
                }
            }
        }).start();
    }

    private void processSaleInfo(String info)
    {
        final String[] infoar = info.split(","); // store info into array, splitting the info respectively
        // create url to 2nd part of API; using info to send a request for the API to process and output the final sales info
        final String url = "https://pubweekly.herokuapp.com/process?id=" + infoar[0] + "&price=" + infoar[1]
                + "&date=" + infoar[2] + "&udate=" + infoar[3];
        System.out.println(url);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL pubweekly = new URL(url); // create URL object
                        BufferedReader read = new BufferedReader(new InputStreamReader(pubweekly.openStream())); // created buffered reader
                        salesinfo = read.readLine(); // read the contents of the URL
                        System.out.println("run new thread executed with " + salesinfo);
                        read.close(); // close buffered reader

                        // store final info in array, splitting each piece of info respectively
                        String[] salesinfoar = salesinfo.split(",");
                        sub = salesinfoar[0];
                        price = salesinfoar[1];
                        date = salesinfoar[2];
                        udate = salesinfoar[3];
                        onSuccessToast(); // call Handler thread to display success message
                        updateUI(); // call Handler thread to update the UI accordingly

                    } catch (Exception e) {
                        System.out.println("exception: " + e);
                        onErrorToast();
                    }
                }
            }).start();
    }

    private void updateUI()
    {

        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run()
            {
                s.setText(sub);
                p.setText("$" + price);
                d.setText(date);
                u.setText(udate);
            }
        });
    }

    public void reloadInfo(View v)
    {
        // reload sales info, starting the info fetch process from the API again
        try {
            Toast.makeText(getApplicationContext(), "Loading sales info...", Toast.LENGTH_SHORT).show();
            acesssWebApp();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "An error occurred!", Toast.LENGTH_SHORT).show();
        }
    }

    public void notLoading(View v)
    {
        AlertDialog note = new AlertDialog.Builder(MainActivity.this).create();
        note.setTitle("Info Not Loading?");
        note.setMessage("Either Slink Soft's web API (hosted on Heroku in a AWS data center) is offline," +
                " or your phone is not connected to the Internet! Click the \"Reload Info\" button and try again!");
        note.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        note.show();
    }

    public void updatedDisclaimer(View v)
    {
        AlertDialog note = new AlertDialog.Builder(MainActivity.this).create();
        note.setTitle("Disclaimer Regarding Updated Sales Info");
        note.setMessage("DISCLAIMER: This app is intended for sales info in all and only Florida stores! However, stores in other states will most likely" +
                " have the same sub on sale, but prices and sales date duration may vary. Check with your local store for more information." +
                "\n\nThis app relies on communicating with Slink Soft's web API that hosts the updated " +
                "sales information for the sub of the week. Sales start on Thursday of every week and end on Wednesday " +
                "of every week. Slink Soft updates the API weekly to ensure the latest information is shared with consumers." +
                " However, there may be a slight delay due to many reasons. We appreciate your patience and thank you for using" +
                " my app!\n\n- Slink (Dan, Slink Soft Developer)");
        note.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        note.show();
    }

    public void onCredits(View v)
    {
        AlertDialog credits = new AlertDialog.Builder(MainActivity.this).create();
        credits.setTitle("Credits");
        credits.setMessage("Developed By: Slink (Dan)\nVisit:\nhttps://realslinksoft.wixsite.com/slink-soft-portfolio" +
                "\nand\nhttp://www.YouTube.Com/ReTrOSlink\n\nNOTE: This app is " +
                "not affiliated with Publix Super Markets Inc.! This app is written " +
                "by a college student with intent of practicing, utilizing, and growing his current skills " +
                "to become a prospective software developer at Publix's Information Systems department in Lakeland.\n\n" +
                "Thank you for using this app!\n\n- Slink");
        credits.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        credits.setButton(AlertDialog.BUTTON_POSITIVE, "Visit SlinkSoft",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        // navigates user to my portfolio upon click of the button
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://realslinksoft.wixsite.com/slink-soft-portfolio"));
                        startActivity(browserIntent);
                        dialogInterface.dismiss();
                    }
                });

        credits.show();
    }

    private void onErrorToast()
    {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run()
            {
                Toast.makeText(getApplicationContext(), "An error occurred!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onSuccessToast()
    {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run()
            {
                Toast.makeText(getApplicationContext(), "Fetched sales info successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void orderSub(View v)
    {
        AlertDialog credits = new AlertDialog.Builder(MainActivity.this).create();
        credits.setTitle("Order Sub");
        credits.setMessage("NOTE: You are about to access Publix.com through the app. You may need to enter " +
                "your local ZIP code to be able to order the sub on sale and view the promotional savings. You can do this by tapping the main menu icon " +
                "on the website (top right 3 lines icon), then tap \"Choose a Store\" on the bottom. After choosing a store, you will be redirected " +
                "to the order menu to proceed with your order.\n\nNOTE: Use the \"Back\" and \"Forward\" buttons if you need to go back to the previous page or " +
                "return to a current page. You can take a screenshot of your order confirmation if you wish to do so.");
        credits.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        credits.setButton(AlertDialog.BUTTON_POSITIVE, "Visit Publix.com to order",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        // navigates user to order Activity to order a sub
                        Intent toOrder = new Intent(MainActivity.this, OrderUI.class);
                        startActivity(toOrder);
                        dialogInterface.dismiss();
                    }
                });

        credits.show();
    }
}