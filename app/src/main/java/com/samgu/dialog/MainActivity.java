package com.samgu.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button dialog01, dialog02, dialog03, dialog04, dialog05;

    String[] items = {"個人", "多人", "團體"}; //多選訊息用
    int ans = -1; // 選告ans=-1 預設不選擇,單選選項時使用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

    }

    private void findViews() {
        dialog01 = findViewById(R.id.dailog01);
        dialog02 = findViewById(R.id.dailog02);
        dialog03 = findViewById(R.id.dailog03);
        dialog04 = findViewById(R.id.dailog04);
        dialog05 = findViewById(R.id.dailog05);

        dialog01.setOnClickListener(dia01Lis);
        dialog02.setOnClickListener(dia02Lis);
        dialog03.setOnClickListener(dia03Lis);
        dialog04.setOnClickListener(dia04Lis);
        dialog05.setOnClickListener(dia05Lis);

    }

    Button.OnClickListener dia01Lis = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this); //(context)使用他來跳出訊息框
            d.setTitle("開山里緊急通報");//標題
            d.setMessage("注意蚊蟲!登革熱侵襲!");//內容
            d.show(); //一定要show 才會顯示對話框
        }
    };


    private Button.OnClickListener dia02Lis = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
            d.setTitle("萬安演習")
                    .setMessage("9/1 13:00 演習開始")
                    .setCancelable(false);//是否可以取消(不行)(點擊其他地方可取消對話框)

            d.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog02.setText("確認");
                }
            });
            d.show();
        }
    };


    private Button.OnClickListener dia03Lis = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
            d.setTitle("詢問");
            d.setMessage("是否離開");
            d.setCancelable(false);

            d.setPositiveButton("確認", dialogLis);
            d.setNegativeButton("取消", dialogLis); //另外做一個dialogLis DialogInterface

            /* 另一種寫法
            d.setPositiveButton("確認", new DialogInterface.OnClickListener() { //當點擊確認後做DialogInterface.OnClickListener
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog03.setText("離開");
                }
            });

            d.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //當點擊取消後做DialogInterface.OnClickListener
                @Override//(text,listen)
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog03.setText("取消");
                }
            });
            */
            d.show();
        }

    };


    private Button.OnClickListener dia04Lis = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
            d.setTitle("請選擇");
            d.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override    //items陣列選項 , -1 預設不選擇
                public void onClick(DialogInterface dialogInterface, int which) {
                    ans = which;//點擊後會回傳索引值 -1不選擇 0個人  1多人  2團體
                }
            });

            d.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    if (ans != -1) //如果不等於-1不選擇的話  (有選擇的話)
                        dialog04.setText(items[ans]);  //索引值 -1不選擇 0個人  1多人  2團體
                }
            });
            d.setNegativeButton("取消", null); //取消 不做註冊監聽null
            d.show();
        }
    };


    private Button.OnClickListener dia05Lis = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    //也可以另外做一個dialogLis
    DialogInterface.OnClickListener dialogLis = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            if (which == -1) //也可以用數字    -1確認  -2取消  3其他選項
                dialog03.setText("離開");
            if (which == -2)  //也可以寫成(which == AlertDialog.BUTTON_NEGATIVE) 取消
                dialog03.setText("取消");

        }
    };

}
