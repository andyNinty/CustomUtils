package cn.andyleeblog.customutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.andyleeblog.customutils.BottomDialog;
import cn.andyleeblog.customutils.MyDialog;

public class MainActivity extends AppCompatActivity {

    List<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names.add("item1");
        names.add("item2");
        names.add("item3");
        names.add("item4");
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDialog dialog = new BottomDialog(MainActivity.this, new BottomDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "you clicked: " + position, Toast.LENGTH_SHORT).show();
                    }
                }, names);
                dialog.show();
            }
        });

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialog.Builder(MainActivity.this)
                        .setTitle("提醒您")
                        .setMessage("要退出吗")
                        .setNegativeButton("no", new MyDialog.OnNegativeClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(MainActivity.this, "ys", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("yes", new MyDialog.OnPositiveClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(MainActivity.this, "否", Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();
            }
        });
    }
}
