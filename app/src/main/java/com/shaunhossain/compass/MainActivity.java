package com.shaunhossain.compass;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.shaunhossain.compass.Database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private ImageButton mButton;
    private ImageButton mButton1;
    private ImageButton mButton2;
    private ImageButton mButton3;
    private ImageButton mButton4;
    private ImageButton mButton5;

    @BindView(R.id.drawerLayout)DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)NavigationView navigationView;
    @BindView(R.id.toolbarlayoutinmainactivity)Toolbar toolbar;


    DatabaseHelper databaseHelper;


    ActionBarDrawerToggle actionBarDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        databaseHelper=new DatabaseHelper(this);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open
                ,R.string.drawer_close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.HOmeId).setChecked(true);

        addListenerOnButton();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.login:
                        Intent myIntent = new Intent(MainActivity.this, AdminActivity.class);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        MainActivity.this.startActivity(myIntent);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        finish();
                        break;
                    case R.id.cancelTicket:
                        Intent myIntent2 = new Intent(MainActivity.this, TicketCancel.class);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        myIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        MainActivity.this.startActivity(myIntent2);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        finish();
                        break;

                    case R.id.logout:
                        Intent myIntent3 = new Intent(MainActivity.this, SignIn.class);
                        item.setChecked(true);
                        SharedPreferences prefs = getSharedPreferences("loginInfo", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("isLogged?", "no");
                        editor.apply();
                       // editor.clear();
                       // editor.commit();
                        drawerLayout.closeDrawers();
                        myIntent3.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        MainActivity.this.startActivity(myIntent3);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        finish();
                        break;

                }
                return  true;

            }
        });






    }

    private void addListenerOnButton() {


        mButton = (ImageButton) findViewById(R.id.imageButton);
        mButton1 = (ImageButton) findViewById(R.id.imageButton1);
        mButton2 = (ImageButton) findViewById(R.id.imageButton2);
        mButton3 = (ImageButton) findViewById(R.id.imageButton3);
        mButton4 = (ImageButton) findViewById(R.id.imageButton4);
        mButton5 = (ImageButton) findViewById(R.id.imageButton5);

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,
                        Activity_TicketView.class));

            }

        });
        mButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,
                        Activity_TicketView.class));

            }

        });
        mButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,
                        Activity_TicketView.class));

            }

        });
        mButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,
                        Activity_TicketView.class));

            }

        });
        mButton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,
                        Activity_TicketView.class));

            }

        });
        mButton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,
                        Activity_TicketView.class));

            }

        });
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }









}
