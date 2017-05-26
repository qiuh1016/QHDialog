package com.qiuhong.librarydemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.qiuhong.qhlibrary.Dialog.QHDialog;

public class MainActivity extends AppCompatActivity {

    private RoundCornerProgressBar progressBar;
    private TextView progressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void textViewClicker(View v) {

        final QHDialog qhDialog = new QHDialog(this,"TIP", "This is a beautiful dialog!");
        qhDialog.setPositiveButton("删除", com.qiuhong.qhlibrary.R.drawable.single_select_alert, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, qhDialog.getEditTextText(), Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });
        qhDialog.setNegativeButton("取消", null);
//        qhDialog.setNavigationBackgroundResource(R.drawable.top_select);
//        qhDialog.setOnlyOneButtonText("Yes, it is!");
        qhDialog.setEditText("输入0-99");
        qhDialog.show();

//        QHDialog d = new QHDialog(this);
//
//        View view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null);
//        progressBar = (RoundCornerProgressBar) view.findViewById(R.id.progressBar);
//        progressTextView = (TextView) view.findViewById(R.id.progressTextView);
//        d.setContextView(view);
//        d.setPositiveButton("ok",null);
//        d.setCancelable(false);
//        d.show();
//
//        new Thread(new CountDown()).start();

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
