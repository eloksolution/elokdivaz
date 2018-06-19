package in.eloksolutions.divaz.helpers;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import in.eloksolutions.divaz.activities.Consult;
import in.eloksolutions.divaz.activities.ServiceLists;
import in.eloksolutions.divaz.dtoclasses.BookingDTO;
import in.eloksolutions.divaz.util.RestServices;


/**
 * Created by welcome on 6/28/2017.
 */

public class BookingHelper extends AsyncTask<String, Void, String> {
    URL url;
    BookingDTO bookingDTO;
    private ProgressDialog progress;
    String gurl,json,companyId;
    Consult booking;
    private static final int PERMISSION_REQUEST_CODE = 1;
    String tag="SendAcceptTask";

    public BookingHelper(String json,BookingDTO bookingDTO,String companyId, String gurl, Consult booking) {
        this.json = json;
        this.gurl = gurl;
        this.booking=booking;
        this.bookingDTO=bookingDTO;
        this.companyId=companyId;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(booking);
        progress.setMessage("Loading...");
        progress.show();
    }

    protected String doInBackground(String... urls) {
        URL url= null;
        String result=null;
        try {
            url = new URL(gurl);
            result = RestServices.POST(url, json);
            System.out.println("Response  is" + json);
            System.out.println("Response  is" + result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return result;
    }
    protected void onPostExecute(String result) {
        Log.i(tag, "result Response send Booking Tag helper is ::  " + result);

        progress.dismiss();
        int newResult=Integer.parseInt(result);
        if (newResult>0) {
            Log.i(tag, "result Response send bookingDTO.getApointMentDate() Tag helper is ::  " + bookingDTO);

            booking.sendCalender(bookingDTO.getApointMentDate(),bookingDTO.getCustomerName(),bookingDTO.getEmail(),bookingDTO.getCustomerPhone());
            if (ContextCompat.checkSelfPermission(booking, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(booking, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);
            } else {
                String phoneNo = "+91"+bookingDTO.getCustomerPhone();
                String sms = ""+bookingDTO.getCustomerName()+"";
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(booking, "succus Fully Appointed",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(booking,
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                Intent intent=new Intent(booking, ServiceLists.class);
                intent.putExtra("companyId",companyId);
                booking.startActivity(intent);
            }

        }else{
            Toast.makeText(booking,
                    "Booking faild, please try again later!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
