package in.eloksolutions.beaty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import in.eloksolutions.beaty.activities.AboutNew;
import in.eloksolutions.beaty.activities.AppointMents;
import in.eloksolutions.beaty.activities.CompanyList;
import in.eloksolutions.beaty.activities.Consult;
import in.eloksolutions.beaty.activities.ContactUs;
import in.eloksolutions.beaty.activities.OffersList;
import in.eloksolutions.beaty.activities.PackagesList;
import in.eloksolutions.beaty.activities.ProfileView;
import in.eloksolutions.beaty.activities.RecentParlours;
import in.eloksolutions.beaty.activities.ServiceLists;
import in.eloksolutions.beaty.adapter.AndroidVersion;
import in.eloksolutions.beaty.adapter.BeatyGridview;
import in.eloksolutions.beaty.adapter.CheckInternet;
import in.eloksolutions.beaty.adapter.GalleryDataAdapter;
import in.eloksolutions.beaty.dtoclasses.CompanyDTO;
import in.eloksolutions.beaty.helpers.CompanyViewHelper;
import in.eloksolutions.beaty.util.Config;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridView grid;
    Context context;
    String companyId;
    TextView name,location;
    Toolbar toolbar;
    public static final String[] movies =  {"", "", "", "", "",""};
    public static int [] moviesImages ={R.drawable.offersmain,R.drawable.ic_menu_home,R.drawable.gallerybeauty,R.drawable.beaty,R.drawable.booknow,R.drawable.quickbook,R.drawable.gallerybeauty};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        companyId = getIntent().getStringExtra("companyId");
        CompanyViewHelper getGroupsValue=new CompanyViewHelper(this);
        String surl = Config.SERVER_URL+"company/"+companyId;
        System.out.println("url for services list"+surl);
        try {
            String output=getGroupsValue.new ServiceUpdateTask(surl).execute().get();
            System.out.println("the output from services"+output);
            setValuesToTextFields(output);
        }catch (Exception e){
            e.printStackTrace();
        }
        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.service_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,true));
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<AndroidVersion> av = prepareData();
        GalleryDataAdapter movies = new GalleryDataAdapter(getApplicationContext(), av);
        mRecyclerView.setAdapter(movies);
        final int speedScroll = 3000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count < av.size()){
                    mRecyclerView.scrollToPosition(++count);
                   /* Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
                            (count < av.size()) ? R.anim.left_right
                                    : R.anim.left_right);
                    mRecyclerView.startAnimation(animation);*/

                    handler.postDelayed(this,speedScroll);
                    if(count == av.size()){
                        count=-1;
                    }
                }



            }
        };

        handler.postDelayed(runnable,speedScroll);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final String[] services = new String[]{
                "Services/ Rates",
                "Pakages",
                "Book Now",
                "Offers",
                "Contact Us",
                "About Us"
        };
        int[] Images = {
                R.drawable.services,
                R.drawable.packages,
                R.drawable.callus,
                R.drawable.about,
                R.drawable.contact,
                R.drawable.about
        };
        BeatyGridview adapter = new BeatyGridview(MainActivity.this, Images, services);
        grid = (GridView) findViewById(R.id.grid_view_image_text);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(MainActivity.this, "You Clicked at " +array[+ position], Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, ServiceLists.class);
                            intent.putExtra("companyId", companyId);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI.");
                        }
                        break;
                    case 1:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, PackagesList.class);
                            intent.putExtra("companyId", companyId);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI.");
                        }
                        break;


                    case 2:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, Consult.class);
                            intent.putExtra("companyId", companyId);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI.");
                        }
                        break;

                    case 3:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, OffersList.class);
                            intent.putExtra("companyId", companyId);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI .");
                        }
                        break;
                    case 4:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, ContactUs.class);
                            intent.putExtra("companyId", companyId);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI.");
                        }
                        break;
                    case 5:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, AboutNew.class);
                            intent.putExtra("companyId", companyId);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI .");
                        }
                        break;


                    default:
                        break;
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent=new Intent(MainActivity.this, CompanyList.class);
            startActivity(intent);
        } else if (id == R.id.recent) {
            Intent intent=new Intent(MainActivity.this, RecentParlours.class);
            startActivity(intent);
        } else if (id == R.id.profile) {
            Intent intent=new Intent(MainActivity.this, ProfileView.class);
            startActivity(intent);
        } else if (id == R.id.appointment) {
            Intent intent=new Intent(MainActivity.this, AppointMents.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private static ArrayList<AndroidVersion> prepareData() {


         ArrayList<AndroidVersion> av = new ArrayList<>();
        for (int i = 0; i < movies.length; i++) {
            AndroidVersion mAndroidVersion = new AndroidVersion();
            mAndroidVersion.setAndroidVersionName(movies[i]);
            mAndroidVersion.setrecyclerViewImage(moviesImages[i]);
            av.add(mAndroidVersion);
        }
        return av;
    }
    public void setValuesToTextFields(String result) {
        System.out.println("json xxxx from groupview" + result);
        if (result != null) {
            Gson gson = new Gson();
            CompanyDTO fromJsonn = gson.fromJson(result, CompanyDTO.class);
            toolbar.setTitle(fromJsonn.getName());



        }
    }
}
