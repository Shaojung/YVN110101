package com.example.yvtc.yvn110101;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int ch = -1;
    int tmp = -1;
    boolean b[] = new boolean[4];
    boolean chks[] = new boolean[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("對話框測試");
        builder.setMessage("這是對話框內容\n第二行");

        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "是被按下", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "否被按下", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "忽略被按下", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    public void click2(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("輸入對話框測試");
        builder.setMessage("請輸入你的暱稱");
        final EditText ed = new EditText(MainActivity.this);
        builder.setView(ed);
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tv2 = (TextView) findViewById(R.id.textView2);
                String str = ed.getText().toString();
                tv2.setText(str);
            }
        });

        builder.show();
    }
    public void click3(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("選項對話框測試");
        builder.setSingleChoiceItems(R.array.drinks, ch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tmp = which;
            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ch = tmp;
                if (ch >= 0)
                {
                    String[] drinks = getResources().getStringArray(R.array.drinks);
                    TextView tv3 = (TextView) findViewById(R.id.textView3);
                    tv3.setText(drinks[ch]);
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tmp = ch;
            }
        });
        builder.show();
    }

    public void click4(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("選項對話框測試");
        builder.setItems(R.array.drinks, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] drinks = getResources().getStringArray(R.array.drinks);
                TextView tv4 = (TextView) findViewById(R.id.textView4);
                tv4.setText(drinks[which]);
            }
        });
        builder.setCancelable(false);

        builder.show();
    }
    public void click5(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("選項對話框測試");
        b = chks.clone();
        builder.setMultiChoiceItems(R.array.drinks, b, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                chks = b.clone();
                for (int i=0;i<=3;i++)
                {
                    if (b[i])
                    {
                        sb.append(String.valueOf(i + ","));
                    }
                }
                TextView tv5 = (TextView) findViewById(R.id.textView5);
                tv5.setText(sb.toString());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                b = chks.clone();
            }
        });
        builder.show();
    }
    public void click6(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("輸入對話框測試");
        builder.setMessage("請輸入你的暱稱");

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View myv = inflater.inflate(R.layout.myview, null);

        final TextView tt = (TextView) myv.findViewById(R.id.textView6);
        Button bb = (Button) myv.findViewById(R.id.button7);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tt.setText("Click!! Click!!");
            }
        });

        builder.setView(myv);

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
    public void click7(View v)
    {
        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setTitle("測試");
        pd.setMessage("載入中，請稍候....");
        pd.setCancelable(false);
        pd.show();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                });
            }
        }.start();


    }
}
