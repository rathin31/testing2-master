package com.example.rathin.testing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private Button btSendMail;
    private FirebaseAuth mauth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etEmail = (EditText) findViewById(R.id.etEmail);
        btSendMail = (Button) findViewById(R.id.btSendMail);
        progressDialog = new ProgressDialog(this);

        btSendMail.setOnClickListener(this);

        mauth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.rp_toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if(id==R.id.action_logout){
            Intent i = new Intent(getApplication(),tabbed_activity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


        public void onClick(View v) {
        if (v == btSendMail) {
            ResetPwd();
        }
       /*if(v==tvBack)
        {
            Intent back=new Intent(getApplicationContext(),fragment_tabbed_login.class);
            startActivity(back);
        }*/
    }

    public void ResetPwd(){

        String email = etEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Sending...");
        progressDialog.show();
        mauth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }

}
