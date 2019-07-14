package com.shaunhossain.compass.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;





public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, TableAttribute.DATABASE_NAME, null, TableAttribute.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        TableAttribute tableAttribute = new TableAttribute();
        String query = tableAttribute.userTableCreation();
        sqLiteDatabase.execSQL(query);

        query = tableAttribute.busTableCreation();

        try {
            sqLiteDatabase.execSQL(query);
        } catch (Exception e) {
            Toast.makeText(context, "Problem in create User Table", Toast.LENGTH_SHORT).show();
        }

        try {
            sqLiteDatabase.execSQL(query);
        } catch (Exception e) {
            Toast.makeText(context, "Problem in create Bus Table", Toast.LENGTH_SHORT).show();
        }

        query = tableAttribute.seatTableCreation();
        try {
            sqLiteDatabase.execSQL(query);

        } catch (Exception e) {
            Toast.makeText(context, "Problem in create Seat Table", Toast.LENGTH_SHORT).show();

        }

        query = tableAttribute.busScheduleTableCreation();
        try {
            sqLiteDatabase.execSQL(query);

        } catch (Exception e) {
            Toast.makeText(context, "Problem in create Bus schedule Table", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertSignUpDataInDatabase(String userName, String password, String email,String question , String secretAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TableAttribute.COL_USERNAME, userName);
        contentValues.put(TableAttribute.COL_Password, password);
        contentValues.put(TableAttribute.COL_EMAIL, email);
        contentValues.put(TableAttribute.COL_QUESTION, question);
        contentValues.put(TableAttribute.COL_SECRET_ANSWER, secretAnswer);

        long result = db.insert(TableAttribute.USER_TABLE, null, contentValues);

        if (result > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }




    public boolean checkLogin(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;

        String selectString = "SELECT * FROM " + TableAttribute.USER_TABLE + " WHERE " + TableAttribute.COL_USERNAME + " = ?  AND " + TableAttribute.COL_Password + " = ?";

        cursor = db.rawQuery(selectString, new String[]{userName, password});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }

    }







    public Cursor getBusNameId() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TableAttribute.COL_BUS_ID + " , " + TableAttribute.COL_BUS_NAME + " FROM " + TableAttribute.BUS_TABLE, null);

        return cursor;


    }











    public boolean checkIfBusSeatAvailable(int scheduleId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TableAttribute.SEAT_TABLE + " WHERE " + TableAttribute.COL_SCHEDULE_ID + " = ?  ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(scheduleId)});

        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }


    public boolean deleTeBusSchedule(int scheduleId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean res = true;



        String sql1 = "Delete from " + TableAttribute.BUS_SCHEDULE_TABLE  + " WHERE " + TableAttribute.COL_SCHEDULE_ID + " = "+scheduleId;


        try {
            db.execSQL(sql1);
            db.close();

        } catch (Exception e) {

            res = false;
        }

        return res;

    }

    public boolean cancelTicket(String token)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean res = true;


        String query = "SELECT * FROM " + TableAttribute.SEAT_TABLE + " WHERE " + TableAttribute.COL_SEAT_CANCELLABLE_TOKEN + " = ?  ";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(token)});

        if (cursor.getCount()>0)
        {
            db.execSQL("DELETE FROM " + TableAttribute.SEAT_TABLE+ " WHERE "+TableAttribute.COL_SEAT_CANCELLABLE_TOKEN+"='"+token.trim()+"'");
            db.close();
        }
        else
            res=false;


        return res;


    }




    public String getPassword(String userName,String answer)
    {
        SQLiteDatabase db = this.getWritableDatabase();


        String s ="";

        String query = "SELECT " + TableAttribute.COL_Password +  " FROM " + TableAttribute.USER_TABLE+ " WHERE " + TableAttribute.COL_USERNAME + " = ?  AND " + TableAttribute.COL_SECRET_ANSWER + " = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{userName, answer});

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();

            s = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_Password));

        }
        return s;
    }

    public String getQuestion(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String s="";

        String query = "SELECT " + TableAttribute.COL_QUESTION + " FROM " + TableAttribute.USER_TABLE + " WHERE "+ TableAttribute.COL_USERNAME + " = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{userName});



        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            s = cursor.getString(cursor.getColumnIndex(TableAttribute.COL_QUESTION));

        }
        return s;

    }



}
