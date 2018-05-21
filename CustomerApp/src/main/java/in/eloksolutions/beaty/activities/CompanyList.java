package in.eloksolutions.beaty.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.eloksolutions.beaty.R;
import in.eloksolutions.beaty.helpers.GetCompanyHelpers;
import in.eloksolutions.beaty.util.Config;


public class CompanyList extends AppCompatActivity {
    Context context;
    String userId, firstName, lastName;
    TextView noData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list_recycler);
        context=this;
        getSupportActionBar().setTitle("Parlours");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView services = (RecyclerView) findViewById(R.id.service_list);
        services.setHasFixedSize(true);
        LinearLayoutManager lmPadi = new LinearLayoutManager(this);
        services.setLayoutManager(lmPadi);
        String url= Config.SERVER_URL+"company/getAll";
        GetCompanyHelpers getGroups=new GetCompanyHelpers(context,url,services,noData);
        System.out.println("url for Company list"+url);
        getGroups.execute();
        LinearLayout cardView=(LinearLayout) findViewById(R.id.search_linear);
        cardView.setVisibility(View.VISIBLE);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.home_button:
                startActivity(new Intent(this, CompanyList.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}


