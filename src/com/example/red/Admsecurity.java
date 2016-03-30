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

@SuppressLint("NewApi")
public class Admsecurity extends Activity {
	EditText id,contact,name,email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admsecurity);
		
		WebView mywebview = (WebView)findViewById(R.id.webView1);  
	       
        
	       mywebview.loadUrl("file:///android_asset/yy/admsecurity.html"); 
	       id=(EditText)findViewById(R.id.editText1);
			contact=(EditText)findViewById(R.id.editText5);
			name=(EditText)findViewById(R.id.editText2);
			email=(EditText)findViewById(R.id.editText3);
	}
	
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
Intent i = new Intent(getApplicationContext(),Admlist.class);
				
				
				//i.putExtra("id",s);
				
				
				startActivity(i);
			}else{Toast.makeText(getApplicationContext(), "Enter Details", 30).show();}
				
		}
	}
	
	public void back(View v){
		Intent i = new Intent(this, Admlist.class);
		startActivity(i);
		
	}
	
	public void submit(View v)
	{
		String t1,t2,t3,t4,t5,t6,t7,t8;
		t1 = id.getText().toString();
		t2 = contact.getText().toString();
		t3 = name.getText().toString(); 
		t4 = email.getText().toString();
if(t1.equals("")){
			
			Toast.makeText(this, "Enter ", Toast.LENGTH_SHORT).show();
		}
		

		else if(t2.equals("")){
			
			Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
		}
		
else if(t3.equals("")){
			
			Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
		}
else if(t4.equals("")){
	
	Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
}
else{
		
		Download task = new Download();
		
		task.execute(new String[]{"http://yajna.orgfree.com/redalert/security.php?id="+t1+"&contact="+t2+"&name="+t3+"&email="+t4});
		
}
	}
	
}
