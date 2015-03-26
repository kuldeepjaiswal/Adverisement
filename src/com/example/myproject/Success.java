package com.example.myproject;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Success extends Activity{
	Button b1;
	public static String cat;
	ImageView i2;
	String link="";
	//TextView t1;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.success);
		b1=(Button)findViewById(R.id.backbutton);
		i2=(ImageView)findViewById(R.id.imagemm);
		//t1=(TextView)findViewById(R.id.textdisp);
		   Bundle xyz=getIntent().getExtras();
	        String display=xyz.getString("key");
		Toast.makeText(getApplicationContext(), display, Toast.LENGTH_LONG).show();
		
		link="http://www.divesh12kumar.site40.net/Site/main/php_file/cat.php?category="+cat;
		//t1.setText(link);
		b1.setOnClickListener(new View.OnClickListener()
		{
		public void onClick(View arg0) {
			Intent a=new Intent(Success.this,MainActivity.class);
			startActivity(a);
		}
		});
		 i2.setOnClickListener(new OnClickListener() {
			    public void onClick(View v)
			    {
			        //actions
			    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
			    	startActivity(browserIntent);
			    } 

			});
	
}
}
