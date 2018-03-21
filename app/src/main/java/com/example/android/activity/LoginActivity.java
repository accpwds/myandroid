package com.example.android.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.R;

/**
 * A login screen that offers login via email/password.
 *
 *  实现简单登录
 */
public class LoginActivity extends BaseActivity {

    private EditText accountEdit;

    private EditText passwordEdit;

    private CheckBox remmberPass;

    private Button login;

    private SharedPreferences.Editor editor;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        remmberPass = (CheckBox) findViewById(R.id.checkBox);
        //account.setText("admin");
        //password.setText("123456");
        login = (Button) findViewById(R.id.sign_in);
        boolean isRemmber = pref.getBoolean("remember_password",false);
        if (isRemmber){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remmberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = accountEdit.getText().toString();
                String passwd = passwordEdit.getText().toString();
                if (count.equalsIgnoreCase("admin") && passwd.equals("123456")){
                    editor = pref.edit();
                    if (remmberPass.isChecked()){
                        editor.putString("account",count);
                        editor.putString("password",passwd);
                        editor.putBoolean("remember_password",true);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
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

