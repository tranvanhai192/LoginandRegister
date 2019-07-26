package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
    EditText edtname, edtpass;
    Button btnlogin;
    TextView tvregis,tvtext;
    DatabaseHelper db;
    private int count=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        db=new DatabaseHelper(this);
        edtname=findViewById(R.id.edtname);
        edtpass=findViewById(R.id.edtpass);
        btnlogin=findViewById(R.id.btnlogin);
        tvregis=findViewById(R.id.tvregis);
        tvtext=findViewById(R.id.tvtext);
        tvtext.setText("Bạn có 3 lần đăng nhập tài khoản");

        tvregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent= new Intent(Login_Activity.this,MainActivity.class);
                startActivity(registerIntent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username =edtname.getText().toString().trim();
                String password =edtpass.getText().toString().trim();
                Boolean res = db.checkuser(username,password);
                if (res == true){
                    Intent intent = new Intent(Login_Activity.this,Welcome_Activity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Login_Activity.this," Login Error",Toast.LENGTH_LONG).show();

                }if (res == false){
                    count --;
                    tvtext.setText("Bạn có 3 lần đăng nhập tài khoản"  +String.valueOf(count));
                    if (count == 0){
                        btnlogin.setEnabled(false);
                    }

                }
            }
        });
    }
}

