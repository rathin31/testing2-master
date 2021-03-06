package com.example.rathin.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SalesStockNav2 extends AppCompatActivity implements TextWatcher {
    Spinner spinner_item,spinner_models;
    protected EditText et_cp,et_sp,et_sold,et_profit, et_mrp,et_discount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_stock_nav);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ss_toolbar);
        setSupportActionBar(toolbar);

        spinner_item = (Spinner) findViewById(R.id.spinner_item);
        spinner_models= (Spinner) findViewById(R.id.spinner_models);

        et_cp= (EditText) findViewById(R.id.et_cp);
        et_sp= (EditText) findViewById(R.id.et_sp);
        et_sold= (EditText) findViewById(R.id.et_sold);
        et_profit = (EditText) findViewById(R.id.et_profit);
        et_mrp = (EditText) findViewById(R.id.et_mrp);
        et_discount=(EditText) findViewById(R.id.et_discount);
        et_cp.addTextChangedListener(this);
        et_sp.addTextChangedListener(this);
        et_sold.addTextChangedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_item.setAdapter(adapter);
        spinner_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_item = spinner_item.getSelectedItem().toString();

                switch (selected_item){
                    case "Mobile":
                        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(SalesStockNav2.this,R.array.mobile_models,android.R.layout.simple_spinner_dropdown_item);
                        spinner_models.setAdapter(adapter1);
                        break;
                    case "Computer":
                        ArrayAdapter<CharSequence> adapter2= ArrayAdapter.createFromResource(SalesStockNav2.this,R.array.computer_models,android.R.layout.simple_spinner_dropdown_item);
                        spinner_models.setAdapter(adapter2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if(id==R.id.action_logout){
            tabbed_activity ta = new tabbed_activity();
            ta.loggedin=false;
            Intent i = new Intent(getApplication(),tabbed_activity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        float cp,sp,sold,mrp,temp;

        if (TextUtils.isEmpty(et_sp.getText().toString()) || TextUtils.isEmpty(et_sold.getText().toString())) {

        }else{

            cp = Float.valueOf(et_cp.getText().toString());
            sp = Float.valueOf(et_sp.getText().toString());
            sold = Float.valueOf(et_sold.getText().toString());
            mrp = Float.valueOf(et_mrp.getText().toString());
            try {
                temp = (sp - cp) * sold;
                et_profit.setText(Float.toString(temp));
            } catch (Exception e) {
                Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show();
            }
            temp = 0;
            try {
                temp = (mrp - sp)* 100 / mrp;
                et_discount.setText(Float.toString(temp));

            } catch (Exception e) {
                Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
