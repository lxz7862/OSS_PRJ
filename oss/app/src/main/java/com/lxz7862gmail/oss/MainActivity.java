package com.lxz7862gmail.oss;

import android.app.Activity;
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

        pref = getSharedPreferences("Game",Activity.MODE_PRIVATE );
        editor = pref.edit();
        edPassword = (EditText) findViewById(R.id.edPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSingUp = (Button) findViewById(R.id.signUp);
        autoLogin = (CheckBox) findViewById(R.id.remember);
        btnSingIn = (Button) findViewById(R.id.signIn);

        final Intent menu = new Intent(MainActivity.this,MenuActivity.class);


        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);


            }
        });



        if(pref.getBoolean("autoLogin",false)) {
            etEmail.setText(pref.getString("email",""));
            edPassword.setText(pref.getString("pw",""));
            autoLogin.setChecked(true);
            startActivity(menu);

            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
            finish();
        }


        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    String id = etEmail.getText().toString();
                    String pw = edPassword.getText().toString();
                    Boolean valdation = loginValdation(id, pw);


                    if (valdation) {
                        startActivity(menu);
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                        if (autoLogin.isChecked()) {
                            // if autoLogin Checked, save values
                            editor.putString("email", id);
                            editor.putString("pw", pw);
                            editor.putBoolean("autoLogin", true);
                            editor.commit();
                        }
                        else
                        {

                            editor.clear();
                            editor.commit();
                        }
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }




            }


        });

     }
     //아이디와 비밀번호가 일치하는지 확인
     private boolean loginValdation(String id,String password){
         if (pref.getString("id","").equals(id)&&pref.getString("pw_1","").equals(password))
         {
             return true;
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
