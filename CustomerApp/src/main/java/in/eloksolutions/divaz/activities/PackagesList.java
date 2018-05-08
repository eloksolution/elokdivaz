package in.eloksolutions.divaz.activities;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.helpers.GetPackagesHelpers;
import in.eloksolutions.divaz.util.Config;


public class PackagesList extends AppCompatActivity {
    Context context;
    String userId, firstName, lastName;
    TextView noData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list_recycler);
        context=this;
        getSupportActionBar().setTitle("packages List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView services = (RecyclerView) findViewById(R.id.service_list);
        services.setHasFixedSize(true);
        LinearLayoutManager lmPadi = new LinearLayoutManager(this);
        services.setLayoutManager(lmPadi);
        String url= Config.SERVER_URL+"packages/getAll";
        GetPackagesHelpers getGroups=new GetPackagesHelpers(context,url,services,noData);
        System.out.println("url for packages list"+url);
        getGroups.execute();

    }

}


