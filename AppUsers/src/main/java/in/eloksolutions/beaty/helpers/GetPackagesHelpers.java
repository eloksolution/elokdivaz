package in.eloksolutions.beaty.helpers;

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

import in.eloksolutions.beaty.activities.PackagesList;
import in.eloksolutions.beaty.dataobjects.PackagesOBJ;
import in.eloksolutions.beaty.dtoclasses.PackagesDTO;
import in.eloksolutions.beaty.recyclerviews.MyRecyclerViewpackages;
import in.eloksolutions.beaty.util.RestServices;


/**
 * Created by welcome on 6/30/2017.
 */

public class GetPackagesHelpers extends AsyncTask<String, Void, String> {
    private PackagesList mcontext;
    private ProgressDialog progress;
    String surl;
    RecyclerView rvGroups;
    TextView noData;
    String companyId;

    public GetPackagesHelpers(PackagesList mcontext, String surl, RecyclerView rvGroups, TextView noData,String companyId) {
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
            Type type = new TypeToken<List<PackagesDTO>>() { }.getType();
            List<PackagesDTO> fromJson = gson.fromJson(result, type);
            ArrayList<PackagesOBJ> results = new ArrayList<PackagesOBJ>();
             for (PackagesDTO packagesDTO : fromJson) {

                 PackagesOBJ obj = new PackagesOBJ(packagesDTO.getId(),packagesDTO.getName(), packagesDTO.getDescription(), packagesDTO.getPrice(),packagesDTO.getCategory(),packagesDTO.getImagePath());
                results.add(obj);
           }
            if (!results.isEmpty()) {

                MyRecyclerViewpackages mAdapter = new MyRecyclerViewpackages(results, mcontext);
                rvGroups.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }else{
                noData.setVisibility(View.VISIBLE);
            }
        }
    }

}
