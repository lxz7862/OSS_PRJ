package com.lxz7862gmail.oss;

import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import android.graphics.Color;
import android.content.SharedPreferences;
public class SignUp extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private EditText etName;
    private EditText etAdress;
    private RadioGroup edSex;
    private Button btnDone;
    private Button btnCancle;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        pref = getSharedPreferences("Game",Activity.MODE_PRIVATE );
        editor = pref.edit();

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.edPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.edPasswordConfirm);
        etName = (EditText) findViewById(R.id.name);
        etAdress = (EditText) findViewById(R.id.adress);
        edSex = (RadioGroup) findViewById(R.id.sex);
        btnDone = (Button)findViewById(R.id.done);
        btnCancle = (Button)findViewById(R.id.cencle);

        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = etPassword.getText().toString();
                String confirm = etPasswordConfirm.getText().toString();

                if(password.equals(confirm)){
                    etPassword.setBackgroundColor(Color.GREEN);
                    etPasswordConfirm.setBackgroundColor(Color.GREEN);
                }
                else {
                    etPassword.setBackgroundColor(Color.RED);
                    etPasswordConfirm.setBackgroundColor(Color.RED);
                }

            }


            @Override
            public void afterTextChanged(Editable s) {

            }


        });


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이메일 입력 확인
                int id = edSex.getCheckedRadioButtonId();

                if ((etEmail.getText().toString().length() == 0)){
                    Toast.makeText(SignUp.this, "Email을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etEmail.requestFocus();
                    return;
                }
                // 비밀 번호 입력 확인
                if (etPassword.getText().toString().length() == 0) {
                    Toast.makeText(SignUp.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }
                // 비밀 번호 확인 입력 확인
                if (etPasswordConfirm.getText().toString().length() ==0){
                    Toast.makeText(SignUp.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }
                if ((etName.getText().toString().length() == 0)) {
                    Toast.makeText(SignUp.this, "Name을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }
                if ((etAdress.getText().toString().length() == 0)) {
                    Toast.makeText(SignUp.this, "Adress를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etAdress.requestFocus();
                    return;
                }
                if (onCheckedChanged(edSex,id) == false){
                    Toast.makeText(SignUp.this, "성별을 체크하세요!", Toast.LENGTH_SHORT).show();
                    edSex.requestFocus();
                    return;
                }


                // 비밀번호 일치 확인
                if (!etPassword.getText().toString().equals(etPasswordConfirm.getText().toString()) ){
                    Toast.makeText(SignUp.this, "비밀번호가 일치 하지 않습니다.", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }



                editor.putString("id",etEmail.getText().toString());
                editor.putString("pw_1",etPassword.getText().toString());
                editor.commit();
                Intent result = new Intent();
                result.putExtra("email", etEmail.getText().toString());

                //자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();
            }


        });



        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });

    }
    public boolean onCheckedChanged(RadioGroup group , int id)
    {
        if(group == edSex)
        {
            if (id == R.id.man)
            {

                return true;
            }
            else if (id == R.id.women)
            {
                return  true;
            }
            else
            {
                return false;
            }
        }
        else return false;
    }
}
