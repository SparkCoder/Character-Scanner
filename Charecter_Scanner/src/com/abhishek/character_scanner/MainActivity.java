package com.abhishek.character_scanner;

import com.abhishek.character_scanner.R;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ScannerView scannerview;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		scannerview = new ScannerView(this);
		scannerview.setKeepScreenOn(true);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		scannerview.setBackgroundColor(0xff000000);
		scannerview.setBackgroundResource(R.drawable.bg);
		setContentView(scannerview);
	}

	public boolean onCreateOptionsMenu(Menu paramMenu) {
		paramMenu.add(0, 1, 0, "Exit");
		paramMenu.add(0, 2, 0, "About Creator");
		paramMenu.add(0, 3, 0, "Select Gender");
		return true;
	}

	public void onBackPressed() {
		Toast.makeText(this, "Good Bye!!!", Toast.LENGTH_SHORT).show();
		this.finish();
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "Good Bye!!!", Toast.LENGTH_SHORT).show();
			this.finish();
			break;
		case 2:
			final Dialog about = new Dialog(this, R.style.TransparentDialog);
			about.setContentView(R.layout.about_creator);
			Button okButton = (Button) about.findViewById(R.id.ok_b2);
			TextView details = (TextView) about.findViewById(R.id.details);
			details.setText("Name : Abhishek.D\n\nAge : 16\n\nYear : 2014\n\nRemarks : 'This is my first commercial App'");
			okButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					about.dismiss();
				}
			});
			about.show();
			break;
		case 3:
			scannerview.genderShow();
			break;
		}
		return false;
	}
}