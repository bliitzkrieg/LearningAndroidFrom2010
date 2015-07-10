package com.example.advancedtipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String BILL_TOTAL = "Bill_TOTAL";
	private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";
	
	private double currentBillTotal; // bill amount entered by the user
	private int currentCustomPercent; // tip % set with the SeekBar
	private EditText tip10EditText; // displays 10% tip
	private EditText total10EditText; // displays total with 10% tip
	private EditText tip15EditText; // displays 15% tip
	private EditText total15EditText; // displays total with 15% tip
	private EditText billEditText; // accepts user input for bill total
	private EditText tip20EditText; // displays 20% tip
	private EditText total20EditText; // displays total with 20% tip
	private TextView customTipTextView; // displays custom tip percentage
	private EditText tipCustomEditText; // displays custom tip amount
	private EditText totalCustomEditText; // displays total with custom tip
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(savedInstanceState == null){
        	currentBillTotal = 0.0;
        	currentCustomPercent = 18;
        }
        else{
        	currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
        	currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
        }
        
        tip10EditText = (EditText) findViewById(R.id.tip10EditText);
        total10EditText = (EditText) findViewById(R.id.total10EditText);
        tip15EditText = (EditText) findViewById(R.id.tip15EditText);
        total15EditText = (EditText) findViewById(R.id.total15EditText);
        tip20EditText = (EditText) findViewById(R.id.tip20EditText);
        total20EditText = (EditText) findViewById(R.id.total20EditText);
        
        customTipTextView = (TextView) findViewById(R.id.customTipTextView);
        
        tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
        totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);
        
        billEditText = (EditText) findViewById(R.id.billEditText);
        
        TextWatcher billEditTextWatcher = new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try{
					currentBillTotal = Double.parseDouble(s.toString());
				}
				catch(NumberFormatException e){
					currentBillTotal = 0.0;
				}
				updateStandard();
				updateCustom();
			}
        };
        
        billEditText.addTextChangedListener(billEditTextWatcher);
        
        
        OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener(){	
        	@Override
        	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        		currentCustomPercent = seekBar.getProgress();
        		updateCustom();
        	}
        	
        	@Override
        	public void onStartTrackingTouch(SeekBar seekBar){	
        	}
        	
        	@Override
        	public void onStopTrackingTouch(SeekBar seekBar){
        	}
        };
        
        SeekBar customSeekBar = (SeekBar) findViewById(R.id.customSlider);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    private void updateStandard(){
    	double tenPercentTip = currentBillTotal * .1;
    	double tenPercentTotal = currentBillTotal + tenPercentTip;
    	tip10EditText.setText(String.format("%.02f", tenPercentTip));
    	total10EditText.setText(String.format("%.02f", tenPercentTotal));
    	
    	double fifteenPercentTip = currentBillTotal * .15;
    	double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;
    	
    	tip15EditText.setText(String.format("%.02f", fifteenPercentTip));
    	
    	total15EditText.setText(String.format("%.02f", fifteenPercentTotal));
    	
    	double twentyPercentTip = currentBillTotal * .20;
    	double twentyPercentTotal = currentBillTotal + twentyPercentTip;
    	
    	tip20EditText.setText(String.format("%.02f", twentyPercentTip));
    	total20EditText.setText(String.format("%.02f", twentyPercentTotal));
    }
    
    private void updateCustom(){
    	customTipTextView.setText(currentCustomPercent + "%");
    	double customTipAmount = currentBillTotal * currentCustomPercent * .01;
    	double customTotalAmount = currentBillTotal + customTipAmount;
    	tipCustomEditText.setText(String.format("%.02f", customTipAmount));
    	totalCustomEditText.setText(
    	String.format("%.02f", customTotalAmount));
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	
    	outState.putDouble(BILL_TOTAL, currentBillTotal);
    	outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
    }
    
}
