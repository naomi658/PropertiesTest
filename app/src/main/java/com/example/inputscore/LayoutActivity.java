package com.example.inputscore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LayoutActivity extends AppCompatActivity {
    MyItem myItem = new MyItem();
    RadioGroup rg;
    RadioButton java, c;
    EditText et;
    Button okBtn, cancelBtn;
    ArrayList<MyItem> arrayList = new ArrayList<MyItem>();
    ListView listView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        rg = findViewById(R.id.rg);
        java = findViewById(R.id.java_radio);
        c = findViewById(R.id.c_radio);
        et = findViewById(R.id.et_text);
        okBtn = findViewById(R.id.btn_ok);
        cancelBtn = findViewById(R.id.btn_cancel);
        listView = findViewById(R.id.list);

        adapter = new MyAdapter(this, R.layout.row, R.id.tv, arrayList);
        listView.setAdapter(adapter);

        okBtn.setOnClickListener(lisOk);
        cancelBtn.setOnClickListener(lisCancel);
    }

    // OK 버튼 리스너
    View.OnClickListener lisOk = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int checkedId = rg.getCheckedRadioButtonId();

            switch (checkedId){
                case R.id.java_radio:
                    arrayList.add(new MyItem(R.drawable.java, et.getText().toString()));
                    break;
                case R.id.c_radio:
                    arrayList.add(new MyItem(R.drawable.c, et.getText().toString()));
                    break;
            }

            et.setText("");
            adapter.notifyDataSetChanged();
        }
    };

    // CANCEL 버튼 리스너
    View.OnClickListener lisCancel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            et.setText("");
            java.setChecked(false);
            c .setChecked(false);
            arrayList.clear();
            adapter.notifyDataSetChanged();
        }
    };
}

class MyAdapter extends ArrayAdapter<MyItem>{
    LayoutInflater inflater;
    ArrayList<MyItem> mArr;
    ImageView img;
    TextView tv;

    public MyAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ArrayList<MyItem> objects) {
        super(context, resource, textViewResourceId, objects);

        inflater = LayoutInflater.from(context);
        mArr = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = inflater.inflate(R.layout.row, null);
        }

        img = view.findViewById(R.id.img);
        tv = view.findViewById(R.id.tv);

        img.setBackgroundResource(mArr.get(position).getMyImg());
        tv.setText(mArr.get(position).getMyStr());

        return view;
    }
}

class MyItem{
    int myImg;
    String myStr;

    public MyItem(){
        this.myImg = 0;
        this.myStr = "";
    }

    public MyItem(int myImg, String myStr){
        this.myImg = myImg;
        this.myStr = myStr;
    }

    public int getMyImg(){
        return this.myImg;
    }
    public void setMyImg(int myImg){
        this.myImg = myImg;
    }
    public String getMyStr(){
        return this.myStr;
    }
    public void setMyStr(String myStr){
        this.myStr = myStr;
    }
}