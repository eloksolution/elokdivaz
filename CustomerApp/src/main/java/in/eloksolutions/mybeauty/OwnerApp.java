package in.eloksolutions.mybeauty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

import in.eloksolutions.mybeauty.adapter.AndroidDataAdapter;
import in.eloksolutions.mybeauty.adapter.AndroidVersion;
import in.eloksolutions.mybeauty.listeners.RecyclerItemClickListener;

public class OwnerApp extends AppCompatActivity {
GridView grid;

    Context context;
    int[] Images = {
            R.drawable.offersmain,
            R.drawable.sendmessage,
            R.drawable.packages,
            R.drawable.servicess,
            R.drawable.contactus,
            R.drawable.gallerybeauty
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);
        context=this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Beauty Parlour");
        /*Button ownerScreen=(Button) findViewById(R.id.search_button);
        ownerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OwnerApp.this,Packages.class);
                startActivity(songsIntent);
            }
        });
*/

        songRecyclerViews();

    }
    private void songRecyclerViews() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<AndroidVersion> av = prepareSongData();
        AndroidDataAdapter movies = new AndroidDataAdapter(getApplicationContext(), av);
        mRecyclerView.setAdapter(movies);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        switch (i) {
                            case 0:

                                Intent intent = new Intent(OwnerApp.this,Packages.class);

                                startActivity(intent);
                                break;
                            case 1:
                                String uri1 = "zV0lDPtAUxw";
                                Intent intent1 = new Intent(OwnerApp.this,Packages.class);
                                intent1.putExtra("uri",uri1);
                                startActivity(intent1);
                                break;
                            case 2:
                                String uri2 = "nquYSlnavuM";
                                Intent intent2 = new Intent(OwnerApp.this,Packages.class);
                                intent2.putExtra("uri",uri2);
                                startActivity(intent2);
                                break;
                            case 3:
                                String uri3 = "pNGdT5obEys";
                                Intent intent3 = new Intent(OwnerApp.this,Services.class);
                                intent3.putExtra("uri",uri3);
                                startActivity(intent3);
                                break;
                            case 4:
                                String uri4 = "tzLX8me67wU";
                                Intent intent4 = new Intent(OwnerApp.this,About.class);
                                intent4.putExtra("uri",uri4);
                                startActivity(intent4);
                                break;
                            case 5:
                                String uri5 = "BOjJGALm2kQ";
                                Intent intent5 = new Intent(OwnerApp.this,About.class);
                                intent5.putExtra("uri",uri5);
                                startActivity(intent5);
                                break;

                        }
                    }
                })
        );

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
