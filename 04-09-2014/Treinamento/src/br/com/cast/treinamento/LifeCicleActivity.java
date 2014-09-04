package br.com.cast.treinamento;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public abstract class LifeCicleActivity extends ActionBarActivity {

	private static final String LIFECICLE = "LIFECICLE";

	public abstract String getActivityName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_life_cicle);
		Log.i(LIFECICLE, getActivityName() + ": onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(LIFECICLE, getActivityName() + ": onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(LIFECICLE, getActivityName() + ": onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(LIFECICLE, getActivityName() + ": onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(LIFECICLE, getActivityName() + ": onStop");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(LIFECICLE, getActivityName() + ": onRestart");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(LIFECICLE, getActivityName() + ": onDestroy");
	}
	
}
