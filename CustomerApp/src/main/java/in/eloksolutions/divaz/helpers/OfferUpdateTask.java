package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import in.eloksolutions.divaz.activities.OfferUpdate;
import in.eloksolutions.divaz.activities.OffersList;
import in.eloksolutions.divaz.util.RestServices;

/**
 * Created by welcome on 3/20/2018.
 */


    public class OfferUpdateTask extends AsyncTask<String, Void, String> {
        URL url;

        private ProgressDialog progress;
        String gurl,json;
        OfferUpdate offerUpdate;
        String tag="SendAcceptTask";

        public OfferUpdateTask(String json, String gurl, OfferUpdate offerUpdate) {
            this.json = json;
            this.gurl = gurl;
            this.offerUpdate=offerUpdate;
        }

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(offerUpdate);
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
            Intent intent=new Intent(offerUpdate,OffersList.class);
            offerUpdate.startActivity(intent);
            progress.dismiss();

        }
    }


