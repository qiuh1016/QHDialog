package com.qiuhong.qhdialogdemo;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qiuhong.qhdialog.QHDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void textViewClicker(View v) {

        QHDialog qhDialog = new QHDialog(this,"TIP", "This is a beautiful dialog!");
//        qhDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//        qhDialog.setNegativeButton("cancel", null);
        qhDialog.setOnlyOneButtonText("Yes, it is!");
        qhDialog.show();


    }
}
