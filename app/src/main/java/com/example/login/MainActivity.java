package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText edtname1, edtpass1,mvpass;
    Button btndk;
    TextView tvlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DatabaseHelper(this);
        edtname1=findViewById(R.id.edtname1);
        edtpass1=findViewById(R.id.edtpass1);
        mvpass=findViewById(R.id.cfpass);
        btndk=findViewById(R.id.btndk);
        tvlogin=findViewById(R.id.tvlogin);
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login= new Intent(MainActivity.this,Login_Activity.class);
                startActivity(login);
            }
        });
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtname1.getText().toString().trim();
                String password = edtpass1.getText().toString().trim();
                String cfpass =mvpass.getText().toString().trim();

                if(username.equals("")||password.equals("")||cfpass.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }else {
                    if (chkpass(password) == false) {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.equals(cfpass)) {
                            Boolean chname = db.chname(username);
                            if (chname == true) {
                                Boolean addUser = db.addUser(username, password);
                                if (addUser == true) {
                                    Toast.makeText(getApplicationContext(), "Registered Successfull", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Username Already exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public Boolean chkpass(String password){
        long a=0;
        if(password.length()>=6) {
            for (int i=0;i<=password.length();i++) {
                if ((password.charAt(i) >= 32 && password.charAt(i) <= 47) || (password.charAt(i) >= 58 && password.charAt(i) <= 64) ||
                        (password.charAt(i) >= 91 && password.charAt(i) <= 96) || password.charAt(i) >= 123 && password.charAt(i) <= 126){
                    a++;
                    break;
                }
            }
            for (int i=0;i<=password.length();i++) {
                if (password.charAt(i) >= 48 && password.charAt(i) <= 57){
                    a++;
                    break;
                }

            }
            for (int i=0;i<=password.length();i++) {
                if (password.charAt(i) >= 65 && password.charAt(i) <= 90){
                    a++;
                    break;
                }
            }
            for (int i=0;i<=password.length();i++)
                if(password.charAt(i)>=97&&password.charAt(i)<=122) {
                    a++;
                    break;
                }
        }
        if(a==4) return true;
        else return false;
    }
}


