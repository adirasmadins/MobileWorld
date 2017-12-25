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
import usmanali.mobileworld.Asynctasks.Register_task;

public class Signup_Activity extends AppCompatActivity {
Button login,signupbtn;
    EditText username,password,name,email,address,phone,confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        login=(Button) findViewById(R.id.loginbtn);
        signupbtn=(Button) findViewById(R.id.signupbtn);
        name=(EditText) findViewById(R.id.name);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        confirmpassword=(EditText) findViewById(R.id.confirmpassword);
        email=(EditText) findViewById(R.id.email);
        address=(EditText)  findViewById(R.id.address);
        phone=(EditText)  findViewById(R.id.phone);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(Signup_Activity.this,"Please Enter Your Name",Toast.LENGTH_LONG).show();
                }else if(username.getText().toString().isEmpty()){
                    Toast.makeText(Signup_Activity.this,"Please Enter Your Username",Toast.LENGTH_LONG).show();
                }else if(password.getText().toString().isEmpty()){
                    Toast.makeText(Signup_Activity.this,"Please Enter Your Password",Toast.LENGTH_LONG).show();
                }else if (confirmpassword.getText().toString().isEmpty()){
                    Toast.makeText(Signup_Activity.this,"Please Enter Your Password again",Toast.LENGTH_LONG).show();
                }else if (email.getText().toString().isEmpty()){
                    Toast.makeText(Signup_Activity.this,"Please Enter Your Email",Toast.LENGTH_LONG).show();
                }else if (address.getText().toString().isEmpty()){
                    Toast.makeText(Signup_Activity.this,"Please Enter Your Address",Toast.LENGTH_LONG).show();
                }else if (phone.getText().toString().isEmpty()){
                    Toast.makeText(Signup_Activity.this,"Please Enter Your Phone Number",Toast.LENGTH_LONG).show();
                }else {
                    new Register_task(Signup_Activity.this).execute(username.getText().toString(), confirmpassword.getText().toString(), name.getText().toString(), email.getText().toString(), address.getText().toString(), phone.getText().toString());
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Signup_Activity.this,Login_Activity.class);
                startActivity(i);
            }
        });
    }

}
