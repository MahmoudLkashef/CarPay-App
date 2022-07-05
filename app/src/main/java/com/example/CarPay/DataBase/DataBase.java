package com.example.CarPay.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.CarPay.model.Car;
import com.example.CarPay.model.Card;
import com.example.CarPay.model.Customer;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public static final String DataBaseName="carPay.db";
    public DataBase(@Nullable Context context) {
        super(context, DataBaseName, null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table cars(image blob,carName text,carModel text, carPrice text,speed text,horsePower text,motorCapacity text ,gas text , seats text , type text , country text)");
        sqLiteDatabase.execSQL("create table customers (customerName text , customerEmail text , customerPassword text,customerCarImage blob,customerCarName text,customerCarModel text, customerCarPrice text,customerCarSpeed text,customerCarHorsePower text,customerCarMotorCapacity text ,customerCarGas text , customerCarSeats text , customerCarType text , customerCarCountry text,customerCardNum text,customerCardCVC text,customerCardDate text,customerCash text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cars");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS customers");
        onCreate(sqLiteDatabase);
    }
    public boolean insertCarData(Car c) {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new  ContentValues();
        //String carName,carModel,carPrice,speed,horsePower,motorCapacity,gas,seats,type;
        byte[]image =getBytes(c.getImg());
        values.put("image",image);
        values.put("carName",c.getName());
        values.put("carModel",c.getModelYear());
        values.put("carPrice",c.getPrice());
        values.put("speed",c.getSpeed());
        values.put("horsePower",c.getHorsePower());
        values.put("motorCapacity",c.getMotorCapacity());
        values.put("gas",c.getGas());
        values.put("seats",c.getSeats());
        values.put("type",c.getType());
        values.put("country",c.getCountry());
        long ins= s.insert( "cars", null, values );
        if(ins==-1)return false;
        else return true;
    }

    public boolean insertCustomerData(Customer c)
    {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new  ContentValues();

        values.put("customerName",c.getCustomerName());
        values.put("customerEmail",c.getCustomerEmail());
        values.put("customerPassword",c.getCustomerPassword());
        long ins= s.insert( "customers", null, values );
        if(ins==-1)return false;
        else return true;
    }
    public void insertCustomerCar(String customerEmail, Car c,String cash)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("customerCarImage",getBytes(c.getImg()));
        values.put("customerCarName",c.getName());
        values.put("customerCarModel",c.getModelYear());
        values.put("customerCarPrice",c.getPrice());
        values.put("customerCarSpeed",c.getSpeed());
        values.put("customerCarHorsePower",c.getHorsePower());
        values.put("customerCarMotorCapacity",c.getMotorCapacity());
        values.put("customerCarGas",c.getGas());
        values.put("customerCarSeats",c.getSeats());
        values.put("customerCarType",c.getType());
        values.put("customerCarCountry",c.getCountry());
        values.put("customerCash",cash);
        sq.update("customers", values, "customerEmail=?", new String[]{customerEmail});
    }
    public void insertCustomerCard(String customerEmail, Card c)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("customerCardNum",c.getCardNum());
        values.put("customerCardCVC",c.getCvc());
        values.put("customerCardDate",c.getDate());
        values.put("customerCash",c.getCash());
        sq.update("customers", values, "customerEmail=?", new String[]{customerEmail});
    }
    // convert from bitmap to byte array
    public  byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    // convert from byte array to bitmap
    public Bitmap getBitmapImage(byte[] image) {
        Bitmap bitmap=null;
        if(image!=null){
            bitmap= BitmapFactory.decodeByteArray(image, 0, image.length);
            return bitmap;
        }
        return bitmap;
    }
    public ArrayList<Car> getAllCars()
    {
        ArrayList<Car>arrayList=new ArrayList<>();
        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor cursor = sq.rawQuery("SELECT * FROM cars", null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            byte[] bitmap=cursor.getBlob(0);
            Bitmap image=getBitmapImage(bitmap);
            String carName=cursor.getString(cursor.getColumnIndexOrThrow("carName"));
            String carModel=cursor.getString(cursor.getColumnIndexOrThrow("carModel"));
            String carPrice=cursor.getString(cursor.getColumnIndexOrThrow("carPrice"));
            String speed=cursor.getString(cursor.getColumnIndexOrThrow("speed"));
            String horsePower=cursor.getString(cursor.getColumnIndexOrThrow("horsePower"));
            String motorCapacity=cursor.getString(cursor.getColumnIndexOrThrow("motorCapacity"));
            String gas=cursor.getString(cursor.getColumnIndexOrThrow("gas"));
            String seats=cursor.getString(cursor.getColumnIndexOrThrow("seats"));
            String type=cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String country=cursor.getString(cursor.getColumnIndexOrThrow("country"));

            arrayList.add(new Car(carName,carModel,carPrice,image,speed,gas,motorCapacity,horsePower,seats,type,country));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<Customer> getAllCustomersPayment()
    {
        ArrayList<Customer>arrayList=new ArrayList<>();
        SQLiteDatabase sq=this.getWritableDatabase();
        Cursor cursor = sq.rawQuery("SELECT * FROM customers where customerCarName is not null",null);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false)
        {
            byte[] bitmap=cursor.getBlob(cursor.getColumnIndexOrThrow("customerCarImage"));
            Bitmap image=getBitmapImage(bitmap);
            String customerName=cursor.getString(cursor.getColumnIndexOrThrow("customerName"));
            String customerEmail=cursor.getString(cursor.getColumnIndexOrThrow("customerEmail"));
            String customerPassword=cursor.getString(cursor.getColumnIndexOrThrow("customerPassword"));
            String customerCarName=cursor.getString(cursor.getColumnIndexOrThrow("customerCarName"));
            String customerCarModel=cursor.getString(cursor.getColumnIndexOrThrow("customerCarModel"));
            String customerCarPrice=cursor.getString(cursor.getColumnIndexOrThrow("customerCarPrice"));
            String customerCarSpeed=cursor.getString(cursor.getColumnIndexOrThrow("customerCarSpeed"));
            String customerCarHorsePower=cursor.getString(cursor.getColumnIndexOrThrow("customerCarHorsePower"));
            String customerCarMotorCapacity=cursor.getString(cursor.getColumnIndexOrThrow("customerCarMotorCapacity"));
            String customerCarGas=cursor.getString(cursor.getColumnIndexOrThrow("customerCarGas"));
            String customerCarSeats=cursor.getString(cursor.getColumnIndexOrThrow("customerCarSeats"));
            String customerCarType=cursor.getString(cursor.getColumnIndexOrThrow("customerCarType"));
            String customerCarCountry=cursor.getString(cursor.getColumnIndexOrThrow("customerCarCountry"));
            String customerCardNum=cursor.getString(cursor.getColumnIndexOrThrow("customerCardNum"));
            String customerCardCVC=cursor.getString(cursor.getColumnIndexOrThrow("customerCardCVC"));
            String customerCardDate=cursor.getString(cursor.getColumnIndexOrThrow("customerCardDate"));
            String customerCash=cursor.getString(cursor.getColumnIndexOrThrow("customerCash"));
            arrayList.add(new Customer(customerName,customerEmail,customerPassword,customerCarName,customerCarModel,customerCarPrice,customerCarSpeed,customerCarHorsePower,customerCarMotorCapacity,customerCarGas,customerCarSeats,customerCarType,customerCarCountry,image,customerCardNum,customerCardCVC,customerCardDate,customerCash));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public boolean isCustomerCardEmpty(String customerEmail)
    {
        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor cursor = sq.rawQuery("SELECT * FROM customers where customerEmail=?",new String[]{customerEmail});
        cursor.moveToFirst();
        if(cursor.getString(cursor.getColumnIndexOrThrow("customerCardNum"))==null)return true;
        else return false;
    }
    public String getCustomerPass(String email)
    {
        if(validEmail(email))
        {
            SQLiteDatabase sq = this.getWritableDatabase();
            Cursor cursor = sq.rawQuery("select * from customers where customerEmail=?", new String[]{email});
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndexOrThrow("customerPassword"));
        }
        return null;
    }
    public ArrayList<Car> getCarsBelongToCountry(String c)
    {
        ArrayList<Car>arrayList=new ArrayList<>();
        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor cursor = sq.rawQuery("SELECT * FROM cars where country=?",new String[]{c});
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            byte[] bitmap=cursor.getBlob(0);
            Bitmap image=getBitmapImage(bitmap);
            String carName=cursor.getString(cursor.getColumnIndexOrThrow("carName"));
            String carModel=cursor.getString(cursor.getColumnIndexOrThrow("carModel"));
            String carPrice=cursor.getString(cursor.getColumnIndexOrThrow("carPrice"));
            String speed=cursor.getString(cursor.getColumnIndexOrThrow("speed"));
            String horsePower=cursor.getString(cursor.getColumnIndexOrThrow("horsePower"));
            String motorCapacity=cursor.getString(cursor.getColumnIndexOrThrow("motorCapacity"));
            String gas=cursor.getString(cursor.getColumnIndexOrThrow("gas"));
            String seats=cursor.getString(cursor.getColumnIndexOrThrow("seats"));
            String type=cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String country=cursor.getString(cursor.getColumnIndexOrThrow("country"));

            arrayList.add(new Car(carName,carModel,carPrice,image,speed,gas,motorCapacity,horsePower,seats,type,country));
            cursor.moveToNext();
        }
        return arrayList;
    }
    public void updateCarInfo(String s,Car c)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("carName",c.getName());
        values.put("carModel",c.getModelYear());
        values.put("carPrice",c.getPrice());
        sq.update("cars",values,"carName=?",new String[]{s});
    }
    public void deleteCar(String s)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cars", "carName=?", new String[]{s});
    }
    public void deleteCustomer(String name,String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("customers", "customerName=? and customerEmail=?", new String[]{name,email});
    }
    public boolean validEmail(String email,String pass)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        Cursor cursor = sq.rawQuery("select * from customers where customerEmail=? and customerPassword=?", new String[]{email,pass});
        if (cursor.getCount() > 0)return true;
        return false;
    }
    public boolean validEmail(String email)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        Cursor cursor = sq.rawQuery("select * from customers where customerEmail=?", new String[]{email});
        if (cursor.getCount() > 0)return true;
        return false;
    }
}
