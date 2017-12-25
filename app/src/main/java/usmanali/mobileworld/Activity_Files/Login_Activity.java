package usmanali.mobileworld.Activity_Files;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.login_task;

public class Login_Activity extends AppCompatActivity {
Button button,loginbtn;
    EditText Email,Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       button =(Button)findViewById(R.id.link_signup);
        loginbtn=(Button) findViewById(R.id.btn_login);
        Email=(EditText) findViewById(R.id.emailtxt);
        Password=(EditText) findViewById(R.id.passwordtxt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_Activity.this,Signup_Activity.class);
                startActivity(intent);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(Email.getText().toString().isEmpty()){
                    Toast.makeText(Login_Activity.this,"Please Enter Your Username",Toast.LENGTH_LONG).show();
                }else if (Password.getText().toString().isEmpty()){
                    Toast.makeText(Login_Activity.this,"Please Enter Your Password",Toast.LENGTH_LONG).show();
                }else {
                    new login_task(Login_Activity.this).execute(Email.getText().toString(), Password.getText().toString());
                }
            }
        });
    }

}
