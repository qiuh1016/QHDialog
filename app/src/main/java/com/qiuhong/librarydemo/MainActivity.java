package com.qiuhong.librarydemo;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.qiuhong.qhlibrary.Dialog.QHDialog;
import com.qiuhong.qhlibrary.Dialog.QHDialogClass;

public class MainActivity extends AppCompatActivity {

    private RoundCornerProgressBar progressBar;
    private TextView progressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void textViewClicker(View v) {

//        QHDialog qhDialog = new QHDialog(this,"TIP", "This is a beautiful dialog!");
////        qhDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT).show();
////                dialog.dismiss();
////            }
////        });
////        qhDialog.setNegativeButton("cancel", null);
//        qhDialog.setOnlyOneButtonText("Yes, it is!");
//        qhDialog.setCancelable(false);
//        qhDialog.show();

        QHDialog d = new QHDialog(this);

        View view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null);
        progressBar = (RoundCornerProgressBar) view.findViewById(R.id.progressBar);
        progressTextView = (TextView) view.findViewById(R.id.progressTextView);
        d.setContextView(view);
        d.setPositiveButton("ok",null);
        d.setCancelable(false);
        d.show();

        new Thread(new CountDown()).start();

//        View view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null);
////        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
////        progressBar = (RoundCornerProgressBar) view.findViewById(R.id.progressBar);
////        progressTextView = (TextView) view.findViewById(R.id.progressTextView);
//
//        QHDialogClass.Builder builder = new QHDialogClass.Builder(this);
//        builder.setTitle("新版本下载中");
//        builder.setContentView(view);
//        builder.setCancelable(false);
//       builder.setPositiveButton("ok",null);
//
//
//        QHDialogClass dialog = builder.create();
//        dialog.show();

    }

    public class CountDown implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                final float finalI = (float) (i / 10.0);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(finalI);
                        progressTextView.setText( (int) finalI + "/100");
                    }
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
