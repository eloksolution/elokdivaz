package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import in.eloksolutions.divaz.activities.Consult;
import in.eloksolutions.divaz.dataobjects.ServiceOBJ;
import in.eloksolutions.divaz.dtoclasses.ServiceDTO;
import in.eloksolutions.divaz.recyclerviews.MyRecyclerViewServicesDailog;
import in.eloksolutions.divaz.util.RestServices;


/**
 * Created by welcome on 6/30/2017.
 */

public class GetServicesDailogHelpers extends AsyncTask<String, Void, String> {
    private Consult mcontext;
    private ProgressDialog progress;
    String surl;
    RecyclerView rvGroups;
    TextView noData,servicePric,serviName;
    String companyId;

    public GetServicesDailogHelpers(Consult mcontext, String surl, RecyclerView rvGroups, TextView noData,TextView serviName,TextView servicePric,String companyId) {
        this.mcontext = mcontext;
        this.surl=surl;
        this.rvGroups=rvGroups;
        this.noData=noData;
        this.serviName=serviName;
        this.servicePric=servicePric;
        this.companyId=companyId;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = new ProgressDialog(mcontext);
        progress.setMessage("Loading...");
        progress.show();
    }

    protected String doInBackground(String... urls) {
        URL url = null;
        try {
            System.out.println("Connection to url ................." + surl);
            url = new URL(surl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestServices.GET(url,companyId);
    }

    protected void onPostExecute(String result) {
        System.out.println("Get Service Result is "+result);
        progress.dismiss();
        if (result!=null && result.trim().length()>0) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<ServiceDTO>>() { }.getType();
            List<ServiceDTO> fromJson = gson.fromJson(result, type);
            ArrayList<ServiceOBJ> results = new ArrayList<ServiceOBJ>();
             for (ServiceDTO service : fromJson) {

                 ServiceOBJ obj = new ServiceOBJ(service.getId(),service.getName(), service.getDescription(), service.getImgePath(),service.getPrice(),service.getDiscount(),service.getImg_icon());
                results.add(obj);
           }
            if (!results.isEmpty()) {

                MyRecyclerViewServicesDailog mAdapter = new MyRecyclerViewServicesDailog(results, mcontext,serviName,servicePric);
                rvGroups.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }else{
                noData.setVisibility(View.VISIBLE);
            }
        }
    }

}
