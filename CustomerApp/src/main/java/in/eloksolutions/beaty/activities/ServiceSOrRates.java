package in.eloksolutions.beaty.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

import in.eloksolutions.beaty.MainActivity;
import in.eloksolutions.beaty.R;
import in.eloksolutions.beaty.adapter.ServiceDataAdapter;
import in.eloksolutions.beaty.dtoclasses.ServiceDTO;
import in.eloksolutions.beaty.listeners.RecyclerItemClickListener;

public class ServiceSOrRates extends AppCompatActivity
        {
GridView grid;
    Context context;
    final String[] services = new String[] {
                "Face Wash" ,
                "haircutting",
                "Body Massage",
                "Hair tips",
                "Hair styles",
                "Nail",


        };
            final String[] offersRates = new String[] {
                    "1000" ,
                    "900",
                    "200",
                    "500",
                    "600",
                    "700",


            };
            final String[] servicesRates = new String[] {
                    "900" ,
                    "800",
                    "100",
                    "300",
                    "200",
                    "400",

            };
    int[] Images = {
            R.drawable.download,
            R.drawable.beaty,
            R.drawable.beatyp,
            R.drawable.beatys,
            R.drawable.beatys,
            R.drawable.beatys



    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_service);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle(" Services / Rates");
        initRecyclerViews();

    }
    private void initRecyclerViews() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.service_recycler);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
       // mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 56));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<ServiceDTO> av = prepareData();
        ServiceDataAdapter services = new ServiceDataAdapter(getApplicationContext(), av);
        mRecyclerView.setAdapter(services);

        final Context ctx=this;
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        switch (i) {
                            case 0:
                                String uri = "vxpEMuM1eBc";
                                Intent intent = new Intent(ServiceSOrRates.this,ServiceView.class);
                                intent.putExtra("uri",uri);
                                startActivity(intent);
                                break;
                            case 1:

                                String uri1 = "vxpEMuM1eBc";
                                Intent intent1 = new Intent(ServiceSOrRates.this,ServiceView.class);
                                intent1.putExtra("uri",uri1);
                                startActivity(intent1);
                                break;
                            case 2:
                                String uri2 = "4wjuDG7WXY8";
                                Intent intent2 = new Intent(ServiceSOrRates.this,ServiceView.class);
                                intent2.putExtra("uri",uri2);
                                startActivity(intent2);
                                break;
                            case 3:
                                String uri3 = "FTBLd2zz8IU";
                                Intent intent3 = new Intent(ServiceSOrRates.this,ServiceView.class);
                                intent3.putExtra("uri",uri3);
                                startActivity(intent3);
                                break;
                            case 4:

                                String uri4 = "o4vv3PN45Eo";
                                Intent intent4 = new Intent(ServiceSOrRates.this,ServiceView.class);
                                intent4.putExtra("uri",uri4);
                                startActivity(intent4);
                                break;
                            case 5:

                                String uri5 = "o4vv3PN45Eo";
                                Intent intent5 = new Intent(ServiceSOrRates.this,ServiceView.class);
                                intent5.putExtra("uri",uri5);
                                startActivity(intent5);
                                break;

                        }
                    }
                })
        );

    }
    private ArrayList<ServiceDTO> prepareData() {


        ArrayList<ServiceDTO> av = new ArrayList<>();
        for (int i = 0; i < services.length; i++) {
            ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setName(services[i]);
            serviceDTO.setImage(Images[i]);
            serviceDTO.setPrice(servicesRates[i]);
            serviceDTO.setDiscount(offersRates[i]);
            av.add(serviceDTO);
        }
        return av;
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
                        startActivity(new Intent(this, MainActivity.class));
                        return true;
                    default:
                        return super.onOptionsItemSelected(item);
                }
            }
}
