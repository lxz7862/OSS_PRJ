package com.lxz7862gmail.oss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, edPassword;
    private Button btnSingUp;
    private CheckBox autoLogin;
    private Button btnSingIn;
    private Boolean loginChecked;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPassword = (EditText) findViewById(R.id.edPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSingUp = (Button) findViewById(R.id.signUp);
        autoLogin = (CheckBox) findViewById(R.id.remember);
        btnSingIn = (Button) findViewById(R.id.signIn);

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);


            }
        });
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pref.getBoolean("autoLgin",false)){
                    etEmail.setText(pref.getString("id",""));
                    edPassword.setText(pref.getString("pw",""));
                    autoLogin.setChecked(true);
                }
                else {
                    String email = etEmail.getText().toString();
                    String password = edPassword.getText().toString();
                    Boolean validation = loginValdation(email,password);

                    if(validation) {
                        Toast.makeText(MainActivity.this, "Lgoin Success", Toast.LENGTH_LONG).show();

                        if (loginChecked) {
                            editor.putString("email", email);
                            editor.putString("pw", password);
                            editor.putBoolean("autoLogin", true);
                            editor.commit();
                        }
                    }

                    else {
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                    }
                }
                autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            loginChecked = true;
                        }
                        else {
                            loginChecked = false;
                            editor.clear();
                            editor.commit();
                        }
                    }
                });
            }
        });
     }
     //아이디와 비밀번호가 일치하는지 확인
     private boolean loginValdation(String id,String password){
         if (pref.getString("email","").equals(id)&&pref.getString("pw","").equals(password))
         {
             return true;
         }
         else if (pref.getString("email","").equals(null)){
             Toast.makeText(MainActivity.this,"Please Sign in first",Toast.LENGTH_LONG).show();
             return false;
         }
         else{
             return false;
         }

     }


     protected void onActivityResult(int requestCode, int resultCode, Intent data){
         Log.d("RESULT",requestCode + "");
         Log.d("RESULT",resultCode + "");
         Log.d("RESULT",data + "");

         if (requestCode == 1000 && resultCode == RESULT_OK){
             Toast.makeText(this, "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();
             etEmail.setText(data.getStringExtra("email"));
         }
     }
}
