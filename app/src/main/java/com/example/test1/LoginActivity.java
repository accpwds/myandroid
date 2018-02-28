package com.example.test1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 *
 *  实现简单登录
 */
public class LoginActivity extends BaseActivity {

    private EditText account;

    private EditText password;

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.sign_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = account.getText().toString();
                String passwd = password.getText().toString();
                if (count.equalsIgnoreCase("admin") && passwd.equals("123456")){
                    Intent intent = new Intent(LoginActivity.this,FirstActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"login success!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this,"account or password is invaild",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

