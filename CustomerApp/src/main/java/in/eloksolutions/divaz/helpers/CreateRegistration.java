package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import in.eloksolutions.divaz.activities.OwnerApp;
import in.eloksolutions.divaz.activities.Registartion;
import in.eloksolutions.divaz.dtoclasses.CompanyDTO;
import in.eloksolutions.divaz.util.RestServices;
import in.eloksolutions.divaz.util.Util;


public class CreateRegistration extends AsyncTask<String, Void, String> {
        // Call after onPreExecute method
        URL url;
    Registartion mcontext;
        private ProgressDialog progress;
        String gurl;
    CompanyDTO registerDto;
    String json = "";

    public CreateRegistration(String json, String gurl, Registartion mcontext) {
            this.json = json;
            this.gurl = gurl;
        this.mcontext=mcontext;
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
            if (result!=null&&!result.startsWith("ERROR")) {
                registerDto.setId(result);
                Util.setPreferances(mcontext, registerDto);
                Log.i("Registration", "result is registerDto " +registerDto);
            }else{
                Toast.makeText(mcontext, "Not able to Registered", Toast.LENGTH_LONG).show();
            }
            mcontext.startActivity(new Intent(mcontext,OwnerApp.class) );

        }
    }