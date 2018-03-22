package com.cmpe277.mortgagecalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Stack;
import java.lang.String;

import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.support.v4.view.GravityCompat;
import android.app.Dialog;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Spinner & Radio
    Spinner house_type_spinner;
    ArrayAdapter<CharSequence> house_type_adapter;
    Spinner state_type_spinner;
    ArrayAdapter<CharSequence> state_type_adapter;
    RadioGroup year_group;
    RadioButton year_radio;

    //EditText
    private EditText showtext;
    private String OperateSum="";
    private EditText show_address;
    private String new_address="1279 38th Ave";
    private EditText show_city;
    private String new_city="San Francisco";
    private EditText show_zip;
    private String new_zip="94122";
    private EditText show_price;
    private String new_price="1500000";
    private EditText show_downpayment;
    private String new_downpayment="1000000";
    private EditText show_apr;
    private String new_apr="4.00";



    //Menu
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        house_type_spinner = (Spinner)findViewById(R.id.house_type_spinner);
        house_type_adapter = ArrayAdapter.createFromResource(this, R.array.house_type, android.R.layout.simple_spinner_item);
        house_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        house_type_spinner.setAdapter(house_type_adapter);
        house_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        state_type_spinner = (Spinner)findViewById(R.id.state_type_spinner);
        state_type_adapter = ArrayAdapter.createFromResource(this, R.array.state_type, android.R.layout.simple_spinner_item);
        state_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_type_spinner.setAdapter(state_type_adapter);
        state_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        year_group = findViewById(R.id.radio_group);

        initView();
        initEvent();
    }

    private void initView()
    {
        //Text View
        showtext=(EditText) findViewById(R.id.result_text);
        showtext.setCursorVisible(false);


        show_address=(EditText) findViewById(R.id.show_address);
        show_address.setText(new_address);
        show_city=(EditText) findViewById(R.id.show_city);
        show_city.setText(new_city);
        show_zip=(EditText) findViewById(R.id.show_zip);
        show_zip.setText(new_zip);

        show_price=(EditText) findViewById(R.id.show_price);
        show_price.setText(new_price);
        show_downpayment=(EditText) findViewById(R.id.show_downpayment);
        show_downpayment.setText(new_downpayment);
        show_apr=(EditText) findViewById(R.id.show_apr);
        show_apr.setText(new_apr);

        //Drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.show_me:
                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();

                                int a  = 0;
                                if (a == 0) {
                                    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("There is a solution. Please continue!")
                                            .setPositiveButton("Cotinue", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                } else {
                                    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                                    builder.setMessage("Sorry, there are actually no solutions!")
                                            .setPositiveButton("Next Puzzle", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    OperateSum="";
                                                    showtext.setText(OperateSum);
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }
                                return true;

                            case R.id.num_picker:
                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                return true;
                        }
                        return true;
                    }
                });

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        } else {
            toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        }
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
    }

    private void initEvent() {

        //Done button
//        equal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //evaluate result
//                equal.setEnabled(false);
//                int a = 0;
//                if(a == 0){
//                    //Snackbar
//                    android.support.design.widget.Snackbar.make(view, "Incorrect. Please try again!", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null)
//                            .show();
//                } else {
//                    //Dialog
//                    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
//                    builder.setMessage("Binggo! "+OperateSum+"=24")
//                            .setPositiveButton("Next Puzzle", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    //reset input
//                                    OperateSum="";
//                                    showtext.setText(OperateSum);
//
//                                    //reset numpad
//                                    resetNumber();
//                                }
//                            });
//                    AlertDialog alert = builder.create();
//                    alert.show();
//                }
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.game_reset:

                return true;

            case R.id.game_skipper:

                return true;

            case R.id.num_picker:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.delete:
//                if(OperateSum.length()>=1)
//                {
//                    char c = OperateSum.charAt(OperateSum.length()-1);
//                    if (Character.isDigit(c)) {
//                        if (one.getText().charAt(0) == c && (!one.isEnabled())){
//                            one.setEnabled(true);
//                        } else if (two.getText().charAt(0) == c && (!two.isEnabled())) {
//                            two.setEnabled(true);
//                        } else if (three.getText().charAt(0) == c && (!three.isEnabled())) {
//                            three.setEnabled(true);
//                        } else if (four.getText().charAt(0) == c && (!four.isEnabled())) {
//                            four.setEnabled(true);
//                        }
//                    }
//                    OperateSum=OperateSum.substring(0,OperateSum.length()-1);
//                }
//                showtext.setText(OperateSum);
            default:
                break;
        }
    }


    public void checkYears(View v) {
        int radioId = year_group.getCheckedRadioButtonId();
        year_radio = findViewById(radioId);

        Toast.makeText(this, "Selected: " + year_radio.getText(), Toast.LENGTH_SHORT).show();
    }
}