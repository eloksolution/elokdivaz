package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import in.eloksolutions.divaz.activities.Services;
import in.eloksolutions.divaz.dtoclasses.OfferDTO;
import in.eloksolutions.divaz.util.RestServices;


/**
 * Created by welcome on 6/28/2017.
 */

public class ServiceHelper extends AsyncTask<String, Void, String> {
    URL url;
    OfferDTO offerDTO;
    private ProgressDialog progress;
    String gurl,json;
    Services services;
    String tag="SendAcceptTask";

    public ServiceHelper(String json, String gurl,  Services services) {
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

        progress.dismiss();

    }
}
