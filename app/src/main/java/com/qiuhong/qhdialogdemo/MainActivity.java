package com.qiuhong.qhdialogdemo;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qiuhong.qhdialog.QHDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void textViewClicker(View v) {
        QHDialog.Builder builder = new QHDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("message");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //Don't forget to add this line
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }
}
