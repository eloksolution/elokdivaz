package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import in.eloksolutions.divaz.activities.OffersList;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.dataobjects.OfferOBJ;
import in.eloksolutions.divaz.dtoclasses.OfferDTO;
import in.eloksolutions.divaz.recyclerviews.MyRecyclerViewOffers;
import in.eloksolutions.divaz.util.RestServices;


/**
 * Created by welcome on 6/30/2017.
 */

public class GetOffersHelpers extends AsyncTask<String, Void, String> {
    private OffersList mcontext;
    private ProgressDialog progress;
    String surl;
    RecyclerView rvGroups;
    TextView noData;
    String companyId;

    public GetOffersHelpers(OffersList mcontext, String surl, RecyclerView rvGroups, TextView noData,String companyId) {
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
        System.out.println("Get Service Result is "+result);
        progress.dismiss();
        if (result!=null && result.trim().length()>0) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<OfferDTO>>() { }.getType();
            List<OfferDTO> fromJson = gson.fromJson(result, type);
            ArrayList<OfferOBJ> results = new ArrayList<OfferOBJ>();
             for (OfferDTO offer : fromJson) {

                 OfferOBJ obj = new OfferOBJ(offer.getId(),offer.getName(), offer.getDescription(), offer.getBeforeOfferPrice(),offer.getOfferPrice(),offer.getImgePath(),offer.getStartDate(),offer.getEndDate(),offer.getCreatedDate());
                results.add(obj);
           }
            if (!results.isEmpty()) {

                MyRecyclerViewOffers mAdapter = new MyRecyclerViewOffers(results, mcontext);
                rvGroups.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }else{
               // noData.setVisibility(View.VISIBLE);
                CheckInternet.showAlertDialog(mcontext,"No Offers Found","Choose Another Service");
            }
        }
    }

}
