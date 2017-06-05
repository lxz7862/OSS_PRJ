package com.lxz7862gmail.oss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSingUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSingUp = (Button) findViewById(R.id.signUp);
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);
            }
        });
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
