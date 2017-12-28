package in.eloksolutions.beaty;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import in.eloksolutions.beaty.activities.AboutUs;
import in.eloksolutions.beaty.activities.BookingAppointMent;
import in.eloksolutions.beaty.activities.Consult;
import in.eloksolutions.beaty.activities.ContactUs;
import in.eloksolutions.beaty.activities.Packages;
import in.eloksolutions.beaty.activities.ServiceList;
import in.eloksolutions.beaty.activities.ServiceSOrRates;
import in.eloksolutions.beaty.adapter.BeatyGridview;
import in.eloksolutions.beaty.adapter.CheckInternet;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridView grid;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                "Quick Book",
                "Consult",
                "Contact Us",
                "About Us",
                "Call Us"
        };
        int[] Images = {
                R.drawable.services,
                R.drawable.packages,
                R.drawable.booknow,
                R.drawable.quickbook,
                R.drawable.consult,
                R.drawable.contact,
                R.drawable.about,
                R.drawable.callus

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
                            Intent intent = new Intent(MainActivity.this, ServiceSOrRates.class);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI.");
                        }
                        break;
                    case 1:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, Packages.class);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI.");
                        }
                        break;

                    case 2:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, ServiceList.class);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI .");
                        }
                        break;
                    case 3:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, BookingAppointMent.class);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI .");
                        }
                        break;
                    case 4:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, Consult.class);
                            startActivity(intent);
                        } else

                        {

                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI .");
                        }
                        break;
                    case 5:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, ContactUs.class);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI.");
                        }
                        break;

                    case 6:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(MainActivity.this, AboutUs.class);
                            startActivity(intent);
                        } else

                        {
                            CheckInternet.showAlertDialog(MainActivity.this, "No Internet Connection",
                                    "Please On your Mobile Data / WIFI .");
                        }
                        break;
                    case 7:
                        if (CheckInternet.checkInternetConenction(context)) {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9963851415"));
                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(intent);
                        }
                        else

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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
