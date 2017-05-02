package cn.andyleeblog.customutil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.andyleeblog.customutils.BottomDialog;

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
    }
}
