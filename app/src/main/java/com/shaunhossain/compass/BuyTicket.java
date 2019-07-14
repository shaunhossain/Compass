package com.shaunhossain.compass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.security.SecureRandom;
import java.util.ArrayList;

import com.shaunhossain.compass.Database.DatabaseHelper;


public class BuyTicket extends AppCompatActivity {

    ArrayList<String> seatNumber;
    String randomNumber;
    TextView selectedSeatTV, totalSeatPriceTV, ticketCancellTokenTV;
    EditText customerNameET, customerrEmailET, customerContactNoET;
    int scheduleId;
    String busSeatPrice;
    int totalSeat;

    DatabaseHelper databaseHelper;



    public String getArrayListAsString() {
        String s = "";

        for (int i = 0; i < seatNumber.size(); i++) {
            s += seatNumber.get(i);

            if (seatNumber.size() != 1 && seatNumber.size() != (i + 1))
                s += " , ";
        }
        return s;
    }




    public void startMainActivity()
    {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();
    }
}
