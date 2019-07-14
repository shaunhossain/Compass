package com.shaunhossain.compass;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_TicketView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ticket_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void onBackPressed()
    {
        super.onBackPressed();

        Intent myIntent = new Intent(Activity_TicketView.this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Activity_TicketView.this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();

    }
}
