package com.example.assignmentsix;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;


public class ActivityThree extends Activity {

	String currentImage;
	String topCap;
	String botCap;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        
        Intent intent = getIntent();
        currentImage = intent.getStringExtra("image");
		topCap = intent.getStringExtra("top");
		botCap = intent.getStringExtra("bot");		
		
        ImageView img = (ImageView) findViewById(R.id.imageTwo);	
        TextView top = (TextView) findViewById(R.id.TopString);
        TextView bot = (TextView) findViewById(R.id.BotString);
        
        top.setText(topCap);
        bot.setText(botCap);
        Typeface impact = Typeface.createFromAsset(this.getAssets(), "impact.ttf");
        top.setTypeface(impact);
        bot.setTypeface(impact);
        
        if(currentImage.equals("success")){
        	img.setImageResource(R.drawable.success);
        }
        else if(currentImage.equals("firstworld")){
        	img.setImageResource(R.drawable.firstworld);
        	bot.setPadding(0, 310, 0, 0);
        }
        else if(currentImage.equals("onedoesnot")){
        	img.setImageResource(R.drawable.onedoesnot);
        	bot.setPadding(0, 280, 0, 0); 
        }
        else if(currentImage.equals("overattached")){
        	img.setImageResource(R.drawable.overattached);  
        	bot.setPadding(0, 330, 0, 0); 
        	top.setTextColor(Color.parseColor("#000000"));
        	bot.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
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
    	case R.id.next:
    		return true;
    	case R.id.signup:
    		Intent singUpIntent = new Intent(context, signUp.class);
    		startActivity(singUpIntent);	
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    
    
}
