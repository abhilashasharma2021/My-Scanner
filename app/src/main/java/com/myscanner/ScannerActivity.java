package com.myscanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ScannerActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {
    private BarcodeReader barcodeReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);


    }


    @Override
    public void onScanned(Barcode barcode) {
// play beep sound
        barcodeReader.playBeep();

        Log.e("ScannerActivity", "barcode: " +barcode);
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {


    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {


        Log.e("ScannerActivity", "onBitmapScanned: " +sparseArray);
    }

    @Override
    public void onScanError(String errorMessage) {
        Log.e("ScannerActivity", "errorMessage: " +errorMessage);
    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
    }
}