package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import in.eloksolutions.divaz.activities.CompanyList;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.dtoclasses.CompanyDTO;
import in.eloksolutions.divaz.util.RestServices;
import in.eloksolutions.divaz.util.Util;


public class CreateRegistration extends AsyncTask<String, Void, String> {
        // Call after onPreExecute method
        URL url;
    Context mcontext;
        private ProgressDialog progress;
        String gurl;
    CompanyDTO registerDto;
    String json = "";

    public CreateRegistration(String json,CompanyDTO registerDto, String gurl, Context mcontext) {
            this.json = json;
            this.gurl = gurl;
        this.mcontext=mcontext;
        this.registerDto=registerDto;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(mcontext);
            progress.setMessage("Loading...");
            progress.show();
        }

        protected String doInBackground(String... urls) {
            URL url= null;
            String result=null;
            try {
                url = new URL(gurl);
                 result = RestServices.POST(url, json);
                System.out.println("Response  is" + result);
            } catch (MalformedURLException e) {
                e.printStackTrace();

            }

            return result;
        }

        protected void onPostExecute(String result) {
            Log.i("Registration", "After registration Complete"+result);
            if (result!=null&&!result.isEmpty()) {
                Log.i("Registration", "result is registerDto " +registerDto);
                Log.i("Registration", "registerDto.setId(result)"+result);
                registerDto.setId(result);
                Util.setPreferances(mcontext, registerDto,result);
                mcontext.startActivity(new Intent(mcontext,CompanyList.class) );
                Log.i("Registration", "result is registerDto " +registerDto);
            }else{
                Toast.makeText(mcontext, "Not able to Registered", Toast.LENGTH_LONG).show();
                CheckInternet.showAlertDialog(mcontext, "Not able to Registered",
                        "Your EmailID or Mobile Number is Already Registered");

            }


        }
    }