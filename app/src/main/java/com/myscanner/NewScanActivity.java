package com.myscanner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

public class NewScanActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_scan);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void run() {
                //  Intent i1 = new Intent(SplashActivity.this, HomeActivity .class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]
                                    {Manifest.permission.CAMERA},
                            PERMISSIONS_REQUEST_READ_CONTACTS);
                    //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
                } else {
                    IntentIntegrator integrator = new IntentIntegrator(NewScanActivity.this);// Use a specific camera of the device
                    integrator.setOrientationLocked(true);
                    integrator.setBeepEnabled(true);
                    integrator.setCaptureActivity(CaptureActivityPortrait.class);
                    integrator.initiateScan();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {

            Log.e("NewScanActivity", "resultCode: " +resultCode);
            Log.e("NewScanActivity", "resultCode: " +result.getContents());

            if (result.getContents() == null) {
                Toast.makeText(this, "Data Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
              /*  try {
                    Log.e("fghfghfhfg",result.getContents());
                    JSONObject jsonObject=new JSONObject(result.getContents());
                    String orderid=jsonObject.getString("order_id");
                    String user_id=jsonObject.getString("user_id");
                    String event_id=jsonObject.getString("event_id");
                    if (event_id.equals(EventId)){
                        AppConstant.sharedpreferences = getSharedPreferences(AppConstant.MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = AppConstant.sharedpreferences.edit();
                        editor.putString(AppConstant.OrderId, orderid);
                        editor.putString(AppConstant.UserId, user_id);
                        editor.commit();
                        startActivity(new Intent(NewScanActivity.this,ScannerActivity.class));
                    }else {
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(NewScanActivity.this,R.style.AlertDialog);
                        builder.setMessage("Your QrCode doest not matched")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        startActivity(new Intent(NewScanActivity.this,MainActivity.class));

                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                } catch (Exception e) {
                    Log.e("fghfghfhfg",e.getMessage());
                }*/
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}