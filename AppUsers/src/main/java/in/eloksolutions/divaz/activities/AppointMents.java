package in.eloksolutions.divaz.activities;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.helpers.GetAppointMentHelpers;
import in.eloksolutions.divaz.util.Config;


public class AppointMents extends AppCompatActivity {
    Context context;
    String userId, companyId, lastName;
    TextView noData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list_recycler);
        context=this;
        getSupportActionBar().setTitle("AppointMents");
        companyId=getIntent().getStringExtra("companyId");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView services = (RecyclerView) findViewById(R.id.service_list);
        services.setHasFixedSize(true);
        LinearLayoutManager lmPadi = new LinearLayoutManager(this);
        services.setLayoutManager(lmPadi);
        SharedPreferences preference=context.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE);
        userId= preference.getString("userId","");
        if(userId!=null && !userId.isEmpty()) {
            String url = Config.SERVER_URL + "booking/myBookings/" + userId;
            GetAppointMentHelpers getGroups = new GetAppointMentHelpers(context, url, services, noData,companyId);
            System.out.println("url for Company list" + url);
            System.out.println("url for Company list" + companyId);
            getGroups.execute();
            CardView cardView = (CardView) findViewById(R.id.csearch_card);
            cardView.setVisibility(View.VISIBLE);
        }else {
            startActivity(new Intent(AppointMents.this,Registartion.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.home_button:

                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder,System.currentTimeMillis());
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setData(builder.build());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}


