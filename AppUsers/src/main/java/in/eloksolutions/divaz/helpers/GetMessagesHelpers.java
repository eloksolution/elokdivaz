package in.eloksolutions.divaz.helpers;

import android.app.ProgressDialog;
import android.content.Context;
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

import in.eloksolutions.divaz.dataobjects.MessageOBJ;
import in.eloksolutions.divaz.dtoclasses.MessageDTO;
import in.eloksolutions.divaz.util.RestServices;


/**
 * Created by welcome on 6/30/2017.
 */

public class GetMessagesHelpers extends AsyncTask<String, Void, String> {
    private Context mcontext;
    private ProgressDialog progress;
    String surl;
    RecyclerView rvGroups;
    TextView noData;
    String companyId;

    public GetMessagesHelpers(Context mcontext, String surl, RecyclerView rvGroups, TextView noData) {
        this.mcontext = mcontext;
        this.surl=surl;
        this.rvGroups=rvGroups;
        this.noData=noData;
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
        System.out.println("Get Message Result is "+result);
        progress.dismiss();
        if (result!=null && result.trim().length()>0) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<MessageDTO>>() { }.getType();
            List<MessageDTO> fromJson = gson.fromJson(result, type);
            ArrayList<MessageOBJ> results = new ArrayList<MessageOBJ>();
             for (MessageDTO messageDTO : fromJson) {

                 MessageOBJ obj = new MessageOBJ(messageDTO.getId(),messageDTO.getSubject(), messageDTO.getDescription(), messageDTO.getCreateDate(),messageDTO.getImgPath(),messageDTO.getType());
                results.add(obj);
           }
            if (!results.isEmpty()) {

                /*MyRecyclerViewMessages mAdapter = new MyRecyclerViewMessages(results, mcontext);
                rvGroups.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();*/
            }else{
                noData.setVisibility(View.VISIBLE);
            }
        }
    }

}
