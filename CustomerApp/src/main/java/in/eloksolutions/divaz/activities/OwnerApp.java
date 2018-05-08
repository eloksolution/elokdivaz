package in.eloksolutions.divaz.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.AndroidVersion;


public class OwnerApp extends AppCompatActivity {
GridView grid;

    Context context;
    int[] Images = {
            R.drawable.offersmain,
            R.drawable.sendmessage,
            R.drawable.packages,
            R.drawable.servicess,
            R.drawable.contactus,
            R.drawable.about
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_customer);
        context=this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Beauty Parlour");
        ImageView ownerScreen=(ImageView) findViewById(R.id.create_offer);
        ownerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OwnerApp.this,OffersList.class);
                startActivity(songsIntent);
            }
        });
        ImageView packages=(ImageView) findViewById(R.id.packages);
        packages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OwnerApp.this,MessageScreen.class);
                startActivity(songsIntent);
            }
        });
        ImageView servi=(ImageView) findViewById(R.id.send_mes);
        servi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OwnerApp.this,MesagesList.class);
                startActivity(songsIntent);
            }
        });
        ImageView gallery=(ImageView) findViewById(R.id.packages);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OwnerApp.this,PackagesList.class);
                startActivity(songsIntent);
            }
        });
        ImageView contact=(ImageView) findViewById(R.id.services);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OwnerApp.this,ServiceList.class);
                startActivity(songsIntent);
            }
        });
        ImageView offer=(ImageView) findViewById(R.id.gallery);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OwnerApp.this,Packages.class);
                startActivity(songsIntent);
            }
        });
        ImageView send=(ImageView) findViewById(R.id.about_us);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OwnerApp.this,ContactUs.class);
                startActivity(songsIntent);
            }
        });


     //   songRecyclerViews();

    }

    private ArrayList<AndroidVersion> prepareSongData() {
        ArrayList<AndroidVersion> av = new ArrayList<>();
        for (int i = 0; i < Images.length; i++) {
            AndroidVersion mAndroidVersion = new AndroidVersion();
            //mAndroidVersion.setAndroidVersionName(songNames[i]);
            mAndroidVersion.setrecyclerViewImage(Images[i]);
            av.add(mAndroidVersion);
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
