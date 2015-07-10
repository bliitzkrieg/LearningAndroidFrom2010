package com.example.assignmentsix;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class signUp extends Activity {

	EditText username,password, email, phone;
	CheckBox contest;
	TextView error;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        
        
        
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        email = (EditText) findViewById(R.id.etEmail);
        phone = (EditText) findViewById(R.id.etPhone);
        error = (TextView) findViewById(R.id.tvError);  
        contest = (CheckBox) findViewById(R.id.cbWin);
        
        Button registerButton = (Button) findViewById(R.id.btnRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	String usernameCheck = username.getText().toString();
            	String passwordCheck = password.getText().toString();
            	String phoneCheck = phone.getText().toString();
            	String emailCheck = email.getText().toString();
            	
            	if(usernameCheck.matches("") || passwordCheck.matches("") || phoneCheck.matches("") || emailCheck.matches("")){	
            		error.setText("Please fill in all of the fields!");
            		}
            	else{
            		addUser();
            		Toast myToast = Toast.makeText(signUp.this, "User added.", Toast.LENGTH_LONG);
            		myToast.show();
                	
            	}
            }
        });
        
        
    }

    
    public void addUser(){
	    DatabaseAdapter databaseConnector = new DatabaseAdapter(this);
	    Boolean isChecked = contest.isChecked();
	    
        databaseConnector.createUsers(
                username.getText().toString(),
                password.getText().toString(), 
                phone.getText().toString(), 
                email.getText().toString(),
                isChecked);
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        menu.getItem(1).setEnabled(false);
        menu.getItem(2).setEnabled(false);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	Context context = getApplicationContext();
    	switch (item.getItemId()){
    	case R.id.help:
    		Intent helpIntent = new Intent(context, Help.class);
    		startActivity(helpIntent);	
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    
    
}
