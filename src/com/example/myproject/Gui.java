package com.example.myproject;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Gui extends Activity implements OnItemSelectedListener {
Spinner s1,s2,s3,s4;
Button b1;
TextView tv,u1 ;
int pos1,pos2,pos3;
String Data="";
String cat1,dep1,place1;
ImageView i1;
String[] cat={"None","real_estate","matrimonial","jobs","vehicle","rent","service"};
String[]  matdepartment={"None","bride","groom"};
String[] allplace={"None","delhi","mumbai","lucknow","indore","jaipur"};
String[] website={"None","backpage","locanto","quikr","olx","aps"};
String[]  jobdepartment={"None","service","it","education","health"};
String[]  vehdep={"None","cars","cars_part","service","trucks"};
String[]  realdep={"None","house","flat","land_plot","commercial"};
String[] rentdep={"None","commercial","houses","roommate","holiday"};
String[] servicedep={"None","home_service","real_state","computer","legal"};
String[] none={""};
String category,department,place,site;
final dbhelper myDbHelper=new dbhelper(this);
Cursor c;
ArrayAdapter<String>adapter,adapter1,adapter2,adapter3,adapter4,adapter11,adapter12,adapter13,adapter14,adapter15,adapplace,adapsite,adapnone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.open);
			 s1=(Spinner)findViewById(R.id.spinner1);
		 s2=(Spinner)findViewById(R.id.spinner2);
		 s3=(Spinner)findViewById(R.id.spinner3);
		 s4=(Spinner)findViewById(R.id.spinner4);
		 b1=(Button)findViewById(R.id.button1);
		 i1=(ImageView)findViewById(R.id.imageView1);
		 i1.setOnClickListener(new OnClickListener() {
			    public void onClick(View v)
			    {
			        //actions
			    	Intent x=new Intent(Gui.this,MainActivity.class);
			    	startActivity(x);
			    } 

			});
	adapter=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,cat);
	adapnone=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,none);
	s1.setAdapter(adapter);
adapter1=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,matdepartment);
adapter2=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,jobdepartment);
adapter13=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,realdep);
adapter14=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,rentdep);
adapter15=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,servicedep);
adapter11=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,vehdep);
adapplace=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,allplace);
adapsite=new ArrayAdapter<String>(Gui.this,android.R.layout.simple_spinner_dropdown_item,website);
s1.setOnItemSelectedListener(this);
s2.setOnItemSelectedListener(this);
s3.setOnItemSelectedListener(this);
s4.setOnItemSelectedListener(this);	
		
	
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				SQLiteDatabase myDB= null;
				  String TableName = "Ads";
				 
				  try {
					  myDbHelper.createDataBase();
		           	     
		           	     myDbHelper.openDataBase();
					  		           	  myDbHelper.TableName = "Ads";
		           	myDbHelper.cat1 = cat1;
		           	myDbHelper.dep1 = dep1;
		           	myDbHelper.place1 = place1;
		           	myDbHelper.site = site;
					Called.categoryfor=cat1; 
					
						   c=myDbHelper.getcur();
                       if(c.moveToNext())
                       {
                           
                           Data = c.getString(c.getColumnIndex("url")); 
     Intent a=new Intent(Gui.this,Called.class);
       				    a.putExtra("key",Data);
       				    startActivity(a);                                
                       }  
					                         else{
                    	   Toast.makeText(getApplicationContext(), "No entry found!!! =)", Toast.LENGTH_LONG).show();
                    	   s1.setAdapter(adapter);
                    	   s2.setAdapter(adapnone);
               			s3.setAdapter(adapnone);
               			s4.setAdapter(adapnone);} 
					c.close();
					  }
				  catch(Exception e) {
					   Log.e("Error", "Error", e);
					  }
				
				
			}
		});
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

		switch(arg0.getId())
		{
		case R.id.spinner1:
		 pos1=s1.getSelectedItemPosition();
		 s1.setSelection(arg2);
			cat1 = (String) s1.getSelectedItem();

		switch(pos1)
		{		
		case 1: 
		s2.setAdapter(adapter13);
		s3.setAdapter(adapplace);
		s4.setAdapter(adapsite);
		break;
		case 2:
		s2.setAdapter(adapter1);
		s3.setAdapter(adapplace);
		s4.setAdapter(adapsite);
		break;
		case 3:
			s2.setAdapter(adapter2);
			s3.setAdapter(adapplace);
			s4.setAdapter(adapsite);
			break;
		case 4:
			s2.setAdapter(adapter11);
			s3.setAdapter(adapplace);
			s4.setAdapter(adapsite);
			break;
		case 5:
			s2.setAdapter(adapter14);
			s3.setAdapter(adapplace);
			s4.setAdapter(adapsite);
			break;
		case 6:
			s2.setAdapter(adapter15);
			s3.setAdapter(adapplace);
			s4.setAdapter(adapsite);
			break;
		}
		break;
		case R.id.spinner2:
			 s2.setSelection(arg2);
				dep1 = (String) s2.getSelectedItem();
		break;
		case R.id.spinner3:
			 s3.setSelection(arg2);
				place1 = (String) s3.getSelectedItem();		
		break;
		case R.id.spinner4:
			 s4.setSelection(arg2);
				site = (String) s4.getSelectedItem();
				
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

		
	}

	

}
