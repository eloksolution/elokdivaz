package in.eloksolutions.beaty.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import in.eloksolutions.beaty.activities.Home;
import in.eloksolutions.beaty.util.RestServices;

public class UpdateCompanyHelper extends AsyncTask<String, Void, String> {
        // Call after onPreExecute method
        URL url;
    Context mcontext;
        private ProgressDialog progress;
        String gurl;
    String json = "";

    public UpdateCompanyHelper(String json, String gurl, Context mcontext) {
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

            mcontext.startActivity(new Intent(mcontext,Home.class) );

        }
    }