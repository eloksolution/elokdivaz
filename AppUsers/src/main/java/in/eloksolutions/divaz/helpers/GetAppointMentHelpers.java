package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import in.eloksolutions.divaz.dataobjects.BookingOBJ;
import in.eloksolutions.divaz.dtoclasses.BookingDTO;
import in.eloksolutions.divaz.recyclerviews.MyRecyclerViewApointMents;
import in.eloksolutions.divaz.util.RestServices;


/**
 * Created by welcome on 6/30/2017.
 */

public class GetAppointMentHelpers extends AsyncTask<String, Void, String> {
    private Context mcontext;
    private ProgressDialog progress;
    String surl;
    RecyclerView rvGroups;
    TextView noData;
    String companyId;

    public GetAppointMentHelpers(Context mcontext, String surl, RecyclerView rvGroups, TextView noData,String companyId) {
        this.mcontext = mcontext;
        this.surl=surl;
        this.rvGroups=rvGroups;
        this.noData=noData;
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
        System.out.println("Get Appoint Result is "+result);
        progress.dismiss();
        if (result!=null && result.trim().length()>0) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<BookingDTO>>() { }.getType();
            List<BookingDTO> fromJson = gson.fromJson(result, type);
            ArrayList<BookingOBJ> results = new ArrayList<BookingOBJ>();
             for (BookingDTO company : fromJson) {

                 BookingOBJ obj = new BookingOBJ(company.getId(),company.getCustomerName(),company.getStrOrderDate(),company.getStrOrderItems(),company.getTotalPrice());
                results.add(obj);
           }
            if (!results.isEmpty()) {

                MyRecyclerViewApointMents mAdapter = new MyRecyclerViewApointMents(results,companyId, mcontext);
                rvGroups.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }else{
               // noData.setVisibility(View.VISIBLE);
            }
        }
    }

}
