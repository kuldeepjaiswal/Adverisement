package com.example.myproject;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;




import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnItemSelectedListener {
	// Progress Dialog
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static String url_create_product = "http://www.divesh12kumar.site40.net/Site/main/php_file/create_product.php";
	private static final String TAG_SUCCESS = "success";
	Spinner s1,s2,s3;
Button b1;
EditText e1;
TextView tv,u1 ;
int pos1,pos2,pos3;
String Data="";
String cat1,dep1,place1;
String[] cat={"None","real_estate","matrimonial","jobs","vehicle","rent","service"};
String[]  matdepartment={"None","bride","groom"};
String[] allplace={"None","delhi","mumbai","lucknow","indore"};
String[] website={"None","backpage","locanto"};
String[]  jobdepartment={"None","service","it","education","health"};
String[]  vehdep={"None","cars","cars_part","service","trucks"};
String[]  realdep={"None","house","flat","land_plot","commercial"};
String[] rentdep={"None","commercial","houses","roommate","holiday"};
String[] servicedep={"None","home_service","real_state","computer","legal"};
//String[]  matplace={"None","gwalior","Indore","Ujjain","delhi"};
//String[]  jobplace={"None","Ranchi","Giridih","Bokaro","Dhanbad","gwalior"};
//String[]  vehplace={"None","bhopal","indore","gwalior","delhi"};
String category,department,place,site;
//private static String DB_PATH = "/data/data/com.example.myproject/databases/";
final dbpost myDbHelper=new dbpost(this);
Cursor c;
//private static String DB_NAME = "g-quikr.db";
//String myPath = DB_PATH + DB_NAME;
ArrayAdapter<String>adapter,adapter1,adapter2,adapter3,adapter4,adapter11,adapter12,adapter13,adapter14,adapter15,adapplace,adapsite;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//tv=(TextView)findViewById(R.id.textView4);
		//u1=(TextView)findViewById(R.id.textView5);
	 s1=(Spinner)findViewById(R.id.spinner1);
		 s2=(Spinner)findViewById(R.id.spinner2);
		 s3=(Spinner)findViewById(R.id.spinner3);
		 //s4=(Spinner)findViewById(R.id.spinner4);
		 b1=(Button)findViewById(R.id.button1);
		 e1=(EditText)findViewById(R.id.editText1);

	adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,cat);
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	s1.setAdapter(adapter);
adapter1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,matdepartment);
adapter2=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,jobdepartment);
adapter13=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,realdep);
adapter14=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,rentdep);
adapter15=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,servicedep);
//adapter3=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_item,matplace);
//adapter4=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_item,jobplace);
adapter11=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,vehdep);
//adapter12=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_item,vehplace);
adapplace=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,allplace);
//adapsite=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,website);
adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
//adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);		
//adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
adapplace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//adapsite.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
s1.setOnItemSelectedListener(this);
s2.setOnItemSelectedListener(this);
s3.setOnItemSelectedListener(this);
//s4.setOnItemSelectedListener(this);	
		
	
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				new CreateNewProduct().execute();
				
			}
		});
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.spinner1:
		 pos1=s1.getSelectedItemPosition();
		 s1.setSelection(arg2);
			cat1 = (String) s1.getSelectedItem();
		// Toast.makeText(null, pos1+pos2+pos3, 5000).show();
		switch(pos1)
		{
		case 1: 
		s2.setAdapter(adapter13);
		s3.setAdapter(adapplace);
		//s4.setAdapter(adapsite);
		break;
		case 2:
		s2.setAdapter(adapter1);
		s3.setAdapter(adapplace);
		//s4.setAdapter(adapsite);
		break;
		case 3:
			s2.setAdapter(adapter2);
			s3.setAdapter(adapplace);
			//s4.setAdapter(adapsite);
			break;
		case 4:
			s2.setAdapter(adapter11);
			s3.setAdapter(adapplace);
			//s4.setAdapter(adapsite);
			break;
		case 5:
			s2.setAdapter(adapter14);
			s3.setAdapter(adapplace);
			//s4.setAdapter(adapsite);
			break;
		case 6:
			s2.setAdapter(adapter15);
			s3.setAdapter(adapplace);
			//s4.setAdapter(adapsite);
			break;
		}
		break;
		// pos2=s2.getSelectedItemPosition();
		// pos3=s3.getSelectedItemPosition();
		case R.id.spinner2:
			 s2.setSelection(arg2);
				dep1 = (String) s2.getSelectedItem();
		break;
		case R.id.spinner3:
			 s3.setSelection(arg2);
				place1 = (String) s3.getSelectedItem();		
		break;
		
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Creating Product..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			
			String description =e1.getText().toString();
			Success.cat=cat1;
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("category", cat1));
			params.add(new BasicNameValuePair("department", dep1));
			params.add(new BasicNameValuePair("place", place1));
			params.add(new BasicNameValuePair("description", description));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					  Intent a=new Intent(MainActivity.this,Success.class);
					  Data="product successfully created";
     				    a.putExtra("key",Data);
     				    startActivity(a);
						// closing this screen
					finish();
				} else {
					 Intent a=new Intent(MainActivity.this,Success.class);
					  Data="Failed to create product";
   				    a.putExtra("key",Data);
   				    startActivity(a);
   				    // Toast.makeText(getApplicationContext(), "Connection failed!!! =)", Toast.LENGTH_LONG).show();	// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}

}
