package in.eloksolutions.beaty.helpers;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import in.eloksolutions.beaty.activities.ServiceSOrRates;
import in.eloksolutions.beaty.dataobjecs.ServiceDataObject;
import in.eloksolutions.beaty.util.RestServices;

/**
 * Created by welcome on 3/3/2018.
 */

public class GetServicesHelper extends AsyncTask<String, Void, String > {
    String urls;
    ProgressDialog progress;
    ServiceSOrRates serviceSOrRates;
    ServiceDataObject serviceDataObject;
    String companyId;
    public GetServicesHelper(String urls, ServiceSOrRates serviceSOrRates, ServiceDataObject serviceDataObject) {
        this.urls = urls;
        this.serviceSOrRates = serviceSOrRates;
        this.serviceDataObject = serviceDataObject;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(serviceSOrRates);
        progress.setMessage("Loading...");
        progress.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            System.out.println("Connection to url ................." + urls);
            url = new URL(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestServices.GET(url,companyId);

    }

    protected void onPostExecute(String result) {
        System.out.println("Get Groups Result is " + result);
        progress.dismiss();
        if (result != null && result.trim().length() > 0) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<ServiceDataObject>>() {
            }.getType();
            List<ServiceDataObject> fromJson = gson.fromJson(result, type);
            ArrayList<ServiceDataObject> results = new ArrayList<ServiceDataObject>();

        }

    }
}
