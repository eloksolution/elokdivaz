package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import in.eloksolutions.divaz.activities.ServiceList;
import in.eloksolutions.divaz.activities.ServiceUpdate;
import in.eloksolutions.divaz.util.RestServices;

/**
 * Created by welcome on 3/20/2018.
 */


    public class ServiceUpdateTask extends AsyncTask<String, Void, String> {
        URL url;

        private ProgressDialog progress;
        String gurl,json;
        ServiceUpdate services;
        String tag="SendAcceptTask";

        public ServiceUpdateTask(String json, String gurl,  ServiceUpdate services) {
            this.json = json;
            this.gurl = gurl;
            this.services=services;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(services);
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
            Log.i(tag, "result Response send createOffer Tag helper is ::  " +result);
            Intent intent=new Intent(services,ServiceList.class);
            services.startActivity(intent);
            progress.dismiss();

        }
    }


