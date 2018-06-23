package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import in.eloksolutions.divaz.activities.Packages;
import in.eloksolutions.divaz.dtoclasses.PackagesDTO;


/**
 * Created by welcome on 6/28/2017.
 */

public class PackagesHelper extends AsyncTask<String, Void, String> {
    URL url;
    PackagesDTO packagesDTO;
    private ProgressDialog progress;
    String gurl,json;
    Packages packages;
    String tag="SendAcceptTask";

    public PackagesHelper(String json, String gurl, Packages packages) {
        this.json = json;
        this.gurl = gurl;
        this.packages=packages;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(packages);
        progress.setMessage("Loading...");
        progress.show();
    }

    protected String doInBackground(String... urls) {
        URL url= null;
        String result=null;
        try {
            url = new URL(gurl);
            //result = RestServices.POST(url, json);
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
