package com.example.json;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	//primer comentario de la aplicacion de android para 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button actualizar = (Button)findViewById(R.id.actualizar);
		final EditText latitude = (EditText)findViewById(R.id.latitude);
		final EditText longitude = (EditText)findViewById(R.id.longitude);
		//System.out.println("Empezando con el test");
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		actualizar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HttpClient httpClient = new DefaultHttpClient();
				String url="http://api.open-notify.org/iss-now/v1/";
				//System.out.println("url: " + url);
				HttpGet get = new HttpGet(URI.create(url));
				//comment to add svn
				get.setHeader("content-type", "application/json");
				//System.out.println("get: "+get.toString());
				try
		        {			
		        	HttpResponse resp = httpClient.execute(get);
		        	
		        	String respStr = EntityUtils.toString(resp.getEntity());
		        	//System.out.println("url: " + url +" " +respStr);
		        	JSONObject obj = new JSONObject(respStr);
		        	JSONObject issloc = obj.getJSONObject("iss_position");
		        		
		        		
			        	String lat = issloc.getString("latitude");
			        	String longi = issloc.getString("longitude");
			        	latitude.setText(lat);
			        	longitude.setText(longi);
			        	
			        	
		        	//Rellenamos la lista con los resultados
			        		
		        	
		        }
		        catch(Exception ex)
		        {
		        	Log.e("ServicioRest","Error!", ex);
		        }
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
