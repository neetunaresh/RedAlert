package com.example.red;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class Complaint extends Activity {
	EditText sub,complaint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complaint);
		
		WebView mywebview = (WebView)findViewById(R.id.webView1);  
	       
        
	       mywebview.loadUrl("file:///android_asset/yy/Complaint.html"); 
	       
	       
	       
	       sub=(EditText)findViewById(R.id.editText1);
			complaint=(EditText)findViewById(R.id.editText2);
	}
	

	@SuppressLint("NewApi")
	private class Download extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String response = "";
			for (String url : urls) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				try {
					HttpResponse execute = client.execute(httpGet);
					InputStream content = execute.getEntity().getContent();

					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(content));
					String s = "";
					while ((s = buffer.readLine()) != null) {
						response += s;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return response;
		}
		

		@Override
		protected void onPostExecute(String result) {
			
			//msg.setText(result);
			if (result.contains("success")) {
				Toast.makeText(getApplicationContext(), "Complaint Lodged Successfully..", 30).show();
    Intent i = new Intent(getApplicationContext(),List.class);
				
				
				//i.putExtra("id",s);
				
				
				startActivity(i);
			}else{Toast.makeText(getApplicationContext(), "Enter Details", 30).show();}
				
		}
	}
	
	
	
	public void complaint(View v)
	{
		String t1,t2;
		t1 = sub.getText().toString();
		t2 = complaint.getText().toString();
		
if(t1.equals("")){
			
			Toast.makeText(this, "Enter ", Toast.LENGTH_SHORT).show();
		}
		

		else if(t2.equals("")){
			
			Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
		}
		else{
		
		
		
		Download task = new Download();
		
		task.execute(new String[]{"http://yajna.orgfree.com/redalert/complaint.php?sub="+t1+"&complaint="+t2});
		
		}
	}
	
}
