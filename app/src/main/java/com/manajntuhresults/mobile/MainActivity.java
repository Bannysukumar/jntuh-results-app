package com.manajntuhresults.mobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import androidx.activity.OnBackPressedCallback;
import com.getcapacitor.Bridge;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    private static final String TAG = "MainActivity";
    private AlertDialog exitDialog;
    private OnBackPressedCallback backPressedCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Register back button callback for Android 13+ (predictive back gesture)
        // This must be registered BEFORE super.onCreate() or immediately after
        backPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d(TAG, "Back button pressed via OnBackPressedCallback");
                // Prevent default back behavior
                handleBackPress();
            }
        };
        
        // Register the callback - this intercepts the back button
        getOnBackPressedDispatcher().addCallback(this, backPressedCallback);
        Log.d(TAG, "Back button callback registered");
    }

    @Override
    public void onBackPressed() {
        // This is for older Android versions (< API 33)
        Log.d(TAG, "Back button pressed via onBackPressed()");
        handleBackPress();
    }

    private void handleBackPress() {
        try {
            Bridge bridge = getBridge();
            if (bridge != null) {
                WebView webView = bridge.getWebView();
                // Check if WebView can go back (has navigation history)
                if (webView != null && webView.canGoBack()) {
                    Log.d(TAG, "WebView can go back, navigating back");
                    // Let WebView handle the back navigation
                    webView.goBack();
                    return;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error checking WebView history", e);
        }
        
        // No history or error, show exit confirmation
        Log.d(TAG, "No WebView history, showing exit dialog");
        showExitConfirmationDialog();
    }

    private void showExitConfirmationDialog() {
        // Don't show multiple dialogs
        if (exitDialog != null && exitDialog.isShowing()) {
            Log.d(TAG, "Dialog already showing, skipping");
            return;
        }

        try {
            Log.d(TAG, "Creating exit confirmation dialog");
            exitDialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.exit_dialog_title))
                .setMessage(getString(R.string.exit_dialog_message))
                .setPositiveButton(getString(R.string.exit_dialog_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User confirmed exit
                        Log.d(TAG, "User confirmed exit");
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.exit_dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User cancelled, do nothing
                        Log.d(TAG, "User cancelled exit");
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.d(TAG, "Dialog cancelled by user");
                    }
                })
                .create();
            
            exitDialog.show();
            Log.d(TAG, "Exit dialog shown");
        } catch (Exception e) {
            Log.e(TAG, "Error showing exit dialog", e);
            // Fallback: exit directly if dialog fails
            finish();
        }
    }
}

