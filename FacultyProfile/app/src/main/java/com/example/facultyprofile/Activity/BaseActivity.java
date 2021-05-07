package com.example.facultyprofile.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.facultyprofile.R;
import com.example.facultyprofile.managers.database;
import com.google.android.material.snackbar.Snackbar;


public class BaseActivity extends AppCompatActivity {

    protected static final String TAG = BaseActivity.class.getSimpleName();
    private ProgressDialog progressDialog;
    private ActionBar actionBar;
    private Dialog progressBarDialog;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar=getSupportActionBar();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void showProgress() {
        showProgress(R.string.loading);
    }

    public void displayhomeasup(boolean dhb){
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(dhb);
    }

    public void showProgress(int message) {
        hideProgress();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(message));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }



    public void showLoadingDialog() {
        if (!(progressBarDialog != null && progressBarDialog.isShowing())) {
            progressBarDialog = new Dialog(this);
            progressBarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressBarDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressBarDialog.setContentView(R.layout.progress_bar_dialog);
            progressBarDialog.setCancelable(true);
            progressBarDialog.setCanceledOnTouchOutside(false);
            progressBarDialog.show();
        }
    }

    public void cancelLoading() {
        if (progressBarDialog != null && progressBarDialog.isShowing())
            progressBarDialog.cancel();
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showWarningDialog(int messageId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(messageId);
        builder.setPositiveButton(R.string.button_ok, null);
        builder.show();
    }

    public void showWarningDialog(String message, final String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                database db;
                db=new database(BaseActivity.this);
                db.delete(name);
                Intent i = new Intent(BaseActivity.this,list.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("CANCEL",null);
        builder.show();
    }

    public void showSnackBar(int messageId) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                messageId, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void showToast(String message) {
        Toast mytoast = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        mytoast.show();
    }

    public boolean hasInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public boolean checkInternetConnection() {
        boolean hasInternetConnection = hasInternetConnection();
        if (!hasInternetConnection) {
            showWarningDialog(R.string.internet_connection_failed);
        }

        return hasInternetConnection;
    }


    public boolean isActivityDestroyed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && this != null && this.isDestroyed()) {
            return true;
        }
        return false;
    }
}
