package in.eloksolutions.divaz.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.MainActivity;
import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.AndroidDataAdapterDynamic;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.dtoclasses.BookingDTO;
import in.eloksolutions.divaz.helpers.ApointMentUpdateHelper;
import in.eloksolutions.divaz.helpers.GetServicesDailogUpdateHelpers;
import in.eloksolutions.divaz.util.Config;

public class ApointMentUpdate extends AppCompatActivity implements View.OnClickListener{
    TextView date,time,serviName,servicePric;
    EditText personName,email,phoneNumber;
    public Dialog dialog;
    Spinner timeHours,timeSecounds;
    String IseviceName,IservicePrice,IserviceDate;
    String companyId,bookingId;
    private static final int Date_id = 0;
    private static final int Time_id = 1;
    String suserName,suserId,suserMail,suserPhone;
    public AndroidDataAdapterDynamic servicesAdapter;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;
    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };
    String TAG="Consult";
    private GoogleApiClient client;
    ContentResolver cr;
    Cursor cur = null;
    String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
            + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
            + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
    String[] selectionArgs = new String[]{"gantasoft145@gmail.com", "com.google",
            "gantasoft145@gmail.com"};
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Appointment");
        SharedPreferences preference=getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE);
        suserId= preference.getString("userId","");
        suserPhone= preference.getString("phone","null");
        suserMail= preference.getString("email","null");
        suserName= preference.getString("firstName","null");
        Log.i(TAG,"The Shared Preferences Values is ::: "+suserId+suserMail+suserName+suserPhone);
        IseviceName=getIntent().getStringExtra("serviceName");
        IservicePrice=getIntent().getStringExtra("servicprice");
        IserviceDate=getIntent().getStringExtra("serviceDate");
        cr = getContentResolver();
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
        Date dates= null;
        try {
            dates = new SimpleDateFormat("E, dd MMM yyyy HH:mm").parse(IserviceDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.format(dates));
        uri = CalendarContract.Calendars.CONTENT_URI;
        date = (TextView) findViewById(R.id.date);

        date.setText("" +formatter.format(dates) );
      String currentTime=  new SimpleDateFormat("HH:mm").format(dates);
          time = (TextView) findViewById(R.id.time);
        time.setText(""+currentTime.toUpperCase());
        date.setOnClickListener(this);
       time.setOnClickListener(this);
        personName=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        phoneNumber=(EditText) findViewById(R.id.phone);
        servicePric=(TextView) findViewById(R.id.services_price);
        serviName = (TextView) findViewById(R.id.services);
        /*String[] timesHours = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        timeHours = (Spinner) findViewById(R.id.time_hours);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, timesHours);
        timeHours.setAdapter(adapter);

        String[] timesecound = new String[]{"00","30"};
        timeSecounds = (Spinner) findViewById(R.id.time_secounds);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, timesecound);
        timeSecounds.setAdapter(adapter1);*/
        companyId = getIntent().getStringExtra("companyId");
        bookingId = getIntent().getStringExtra("bookingId");
        if (IseviceName!=null || IservicePrice!=null) {
            serviName.setText(IseviceName);
            servicePric.setText(IservicePrice);
        }


        TextView appointBooking=(TextView) findViewById(R.id.book_appointment);
        appointBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBookingDetails();
            }

        });

       /* RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.listView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);*/
        personName.setText(suserName);
        email.setText(suserMail);
        phoneNumber.setText(suserPhone);
        servicesAdapter = new AndroidDataAdapterDynamic(getApplicationContext());
        //mRecyclerView.setAdapter(servicesAdapter);

       /* servicesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceDialog(Consult.this);
            }
        });*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALENDAR)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(ApointMentUpdate.this,
                        new String[]{Manifest.permission.WRITE_CALENDAR,Manifest.permission.READ_CALENDAR},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_CALENDAR,Manifest.permission.READ_CALENDAR},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        readCal(cr, uri, selection, selectionArgs);

        TextView pickServices=(TextView) findViewById(R.id.pick_image);
        pickServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceDialog(ApointMentUpdate.this);
            }
        });
    }

    protected Dialog onCreateDialog(int id) {

        // Get the calander
        Calendar c = Calendar.getInstance();
        System.out.println("url for Calender list"+c);
        // From calander get the year, month, day, hour, minute
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        switch (id) {
            case Date_id:
                DatePickerDialog dp=new DatePickerDialog(ApointMentUpdate.this, date_listener, year,
                        month, day);
                dp.getDatePicker().setMinDate(System.currentTimeMillis());
                // Open the datepicker dialog
                return dp;
            case Time_id:

                // Open the timepicker dialog
                return new TimePickerDialog(ApointMentUpdate.this, time_listener, hour,
                        minute, false);

        }
        return null;
    }
    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // store the data in one string and set it to text
            String date1 = String.valueOf(month) + "-" + String.valueOf(day)
                    + "-" + String.valueOf(year);
            Calendar ca1 = Calendar.getInstance();

            // set(year, month, date) month 0-11
            ca1.set(year, month, day);
            Date d = new Date(ca1.getTimeInMillis());
            SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");

            date.setText(formatter.format(d));
        }
    };
    TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            // store the data in one string and set it to text
            String time1 = String.valueOf(hour) + ":" + String.valueOf(minute);
            time.setText(time1);
        }
    };
    public  void serviceDialog(final ApointMentUpdate context) {
        dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.service_list_recycler);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        final TextView noData = (TextView) dialog.findViewById(R.id.tv_no_data);
        RecyclerView services = (RecyclerView) dialog.findViewById(R.id.service_list);
        services.setHasFixedSize(true);
        LinearLayoutManager lmPadi = new LinearLayoutManager(context);
        services.setLayoutManager(lmPadi);
        String url= Config.SERVER_URL+"services/getAll";
        GetServicesDailogUpdateHelpers getGroups=new GetServicesDailogUpdateHelpers(context,url,services,noData,serviName,servicePric);
        System.out.println("url for Service list"+url);
        getGroups.execute();

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);

        dialog.show();
    }
    long calID = 0;
    private void readCal(ContentResolver cr, Uri uri, String selection, String[] selectionArgs) {
        Cursor cur;
        cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        System.out.println("cur " + cur + " selection " + selection);
        while (cur.moveToNext()) {

            String displayName = null;
            String accountName = null;
            String ownerName = null;
            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
            System.out.println(TAG + "calID " + calID + " displayName " + displayName + "accountName " + accountName + " ownerName " + ownerName);

        }
    }
    private String saveBookingDetails() {



        BookingDTO bookingDTO=buildDTOObject();
        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(ApointMentUpdate.this)) {

                String gurl = Config.SERVER_URL + "booking/update";
                  try {
                String json=buildJson(bookingDTO);
                sendCalender(bookingDTO.getApointMentDate(),bookingDTO.getCustomerName(),bookingDTO.getEmail(),bookingDTO.getCustomerPhone());
                  String gId = new ApointMentUpdateHelper(json,bookingDTO,companyId, gurl,this).execute().get();
                  return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(ApointMentUpdate.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS) {
            //  readCal(cr, uri, selection, selectionArgs);
            readCal(cr, uri, selection, selectionArgs);
            Log.i("tag","Request permissions of Calender Allow");
        }else {
            Log.i("tag","Request permissions of Calender Deny");
        }
    }
    public  String sendCalender(Date date, String name,String email,String phoneNumber){


        try {
            long startMillis = 0;
            long endMillis = 0;
            Calendar beginTime = Calendar.getInstance();
            beginTime.setTime(date);
            Log.i(TAG,"Dto values is :: "+date+name+email);
            startMillis = beginTime.getTimeInMillis();
            Calendar endTime = Calendar.getInstance();
            endTime.setTime(new Date(date.getTime()+1800000));
            endMillis = endTime.getTimeInMillis();

            Log.i(TAG,"endMillis :: endTime "+endMillis+"startMillis :: "+startMillis);
            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, startMillis);
            values.put(CalendarContract.Events.DTEND, endMillis);
            values.put(CalendarContract.Events.TITLE, IseviceName);
            values.put(CalendarContract.Events.DESCRIPTION, "name :: "+name+"\n Service Name :: "+IseviceName+"\n Phone Number :: "+phoneNumber);
            values.put(CalendarContract.Events.CALENDAR_ID, calID);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Calcutta");
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
            long eventID = Long.parseLong(uri.getLastPathSegment());
            ContentResolver cr1 = getContentResolver();
            ContentValues values1 = new ContentValues();
            values1.put(CalendarContract.Attendees.ATTENDEE_NAME, companyId);
            values1.put(CalendarContract.Attendees.ATTENDEE_EMAIL, email);
            values1.put(CalendarContract.Attendees.ATTENDEE_RELATIONSHIP, CalendarContract.Attendees.RELATIONSHIP_ATTENDEE);
            values1.put(CalendarContract.Attendees.ATTENDEE_TYPE, CalendarContract.Attendees.TYPE_OPTIONAL);
            values1.put(CalendarContract.Attendees.ATTENDEE_STATUS, CalendarContract.Attendees.ATTENDEE_STATUS_INVITED);
            values1.put(CalendarContract.Attendees.EVENT_ID, eventID);
            Uri uri2 = cr1.insert(CalendarContract.Attendees.CONTENT_URI, values1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("added attendees");
        return  "success";
    }

    private String buildJson(BookingDTO bookingDTO) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id",bookingId);
            jsonObject.accumulate("customerName",bookingDTO.getCustomerName());
            jsonObject.accumulate("customerId", bookingDTO.getCustomerId());
            jsonObject.accumulate("strOrderDate", bookingDTO.getOrderDate());
            jsonObject.accumulate("strOrderItems", bookingDTO.getStrOrderItems());
             jsonObject.accumulate("totalPrice",bookingDTO.getTotalPrice());
            jsonObject.accumulate("status","2");

            String json = jsonObject.toString();
            System.out.println("Json is" + json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private BookingDTO buildDTOObject() {
        BookingDTO bookingDTO=new BookingDTO();
        String Names= personName.getText().toString();
        bookingDTO.setCustomerName(Names);
        bookingDTO.setEmail(email.getText().toString());
        bookingDTO.setStrOrderItems(serviName.getText().toString());
        bookingDTO.setCustomerId(suserId);
        bookingDTO.setTotalPrice(IservicePrice);
        bookingDTO.setCustomerPhone(phoneNumber.getText().toString());
        String apointMentdates= date.getText().toString()+" "+time.getText().toString();
        bookingDTO.setOrderDate(apointMentdates);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("E, dd MMM yyyy hh:mm");
        try {
            apointMentdates=apointMentdates.substring(0,apointMentdates.length()-3);

            Date dates=simpleDateFormat.parse(date.getText().toString()+" "+time.getText().toString());
            Log.i(TAG,"After Simple Date formating ::"+dates);
            bookingDTO.setApointMentDate(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return bookingDTO;
    }


    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(phoneNumber)) ret = false;

        if (!Validation.hasText(personName)) ret = false;
        return ret;

    }

    @Override
    public void onClick(View v) {

        if (v == date) {
            showDialog(Date_id);
        }

        if (v == time) {
            showToTimePicker();      }

    }
    private void showToTimePicker() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = DateAndTimePicker.getTimePickerDialog(hour, minute, this, time);
        mTimePicker.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.home_button:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.save:
                saveBookingDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
