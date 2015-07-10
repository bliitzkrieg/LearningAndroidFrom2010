package com.example.assignmentsix;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Help extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        menu.getItem(0).setEnabled(false);
        menu.getItem(2).setEnabled(false);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	Context context = getApplicationContext();
    	switch (item.getItemId()){
    	case R.id.signup:
    		Intent singUpIntent = new Intent(context, signUp.class);
    		startActivity(singUpIntent);	
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    
    
}
