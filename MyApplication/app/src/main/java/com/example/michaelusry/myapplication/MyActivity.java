package com.example.michaelusry.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


/*
Allow the user to enter text.
Allow the user to click on a UI control that will then add the entered text to a data collection.
The data collection will only hold unique entries.
Display the number of entries in the data collection.
Display the average length of entries in the data collection.
Allow the user to select an entry from the data collection by index using UI controls
and display the contents of that entry in an alert that must be acknowledged.
In addition to the above minimum requirements, the following functionality is required
to demonstrate mastery of the week one topics:

Use the proper input type for text entry and reset the view when the contents are added to the data collection.
Use the proper UI controls for accepting user clicks.
Use the proper data collection for storing unique data sets.
The displayed collection size is always accurate.
Use a custom method to calculate the average length of entries instead of duplicating code.
The proper alert method is used to display requested entries.


 */


public class MyActivity extends Activity {
    String TAG = "MyActivity";
    TextView fname, avgLength, nameCount;
    ArrayList<String> nameList = new ArrayList<String>();
    Object names[];

    ListView lv;
    float nameSizeLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        fname = (TextView) findViewById(R.id.nameInput);
        lv = (ListView) findViewById(R.id.nameListView);
        avgLength = (TextView) findViewById(R.id.average_display);
        nameCount = (TextView) findViewById(R.id.count_display);


        Button button = (Button) findViewById(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i(TAG, "onClick");
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(fname
                        .getWindowToken(), 0);



                String firstName = fname.getText().toString();

                Log.i(TAG, "firstName: " + firstName);
                Log.i(TAG, "firstName.length: " + firstName.length());
                Log.i(TAG, "nameSizeLength(before): " + nameSizeLength);

                nameSizeLength += firstName.length();
                Log.i(TAG, "nameSizeLength(after): " + nameSizeLength);

                //add to the arrayList
                nameList.add(firstName);
                Log.i(TAG, "nameList: " + nameList);
                Log.i(TAG, "nameList.size: " + nameList.size());

                //add arrayList to array

                names = nameList.toArray();
                Log.i(TAG, "names.length: " + names.length);
                Log.i(TAG, "names[0]: " + names[0]);



                nameListDisplay();
            fname.setText("");

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TAG, "nameList:position: " + names[position]);
                showToast("The person selected is: " + names[position]
                );

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nameListDisplay() {



        int nameListSize = nameList.size();

        String firstName = fname.getText().toString();

        Log.i(TAG, "firstName: " + firstName);
        Log.i(TAG, "firstName.length: " + firstName.length());
        Log.i(TAG, "nameSizeLength(before): " + nameSizeLength);

        double average = nameSizeLength / nameListSize;

        NumberFormat numberFormat = new DecimalFormat("#,###.00");
        Log.i(TAG, "amount with formatting: " + numberFormat.format(average));


        Log.i(TAG, "nameSizeLength(after): " + nameSizeLength);
        Log.i(TAG, "AVERAGE: " + average);

        avgLength.setText(String.valueOf(numberFormat.format(average)));
        nameCount.setText(String.valueOf(nameListSize));


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_selectable_list_item,
                    nameList);

            lv.setAdapter(arrayAdapter);


    }

public void showToast(String text ){

    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;

    Toast toast = Toast.makeText(context, text, duration);
    toast.show();

    }


}
