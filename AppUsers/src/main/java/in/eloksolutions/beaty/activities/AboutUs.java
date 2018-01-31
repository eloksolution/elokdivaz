package in.eloksolutions.beaty.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.eloksolutions.beaty.MainActivity;
import in.eloksolutions.beaty.R;
import in.eloksolutions.beaty.adapter.AndroidDataAdapter;
import in.eloksolutions.beaty.adapter.AndroidVersion;
import in.eloksolutions.beaty.listeners.RecyclerItemClickListener;


public class AboutUs extends AppCompatActivity {
    public static final String[] movies =  {"image one", "image two", "Image Three", "Image Four", "Image Five","Image Six"};
    public static int [] moviesImages ={R.drawable.offersmain,R.drawable.ic_menu_home,R.drawable.gallerybeauty,R.drawable.beaty,R.drawable.booknow,R.drawable.quickbook,R.drawable.gallerybeauty};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Contact us");
     //   TextView about_us=(TextView) findViewById(R.id.about_us);
       // about_us.setText("Green Trends offers trendy haircuts and color services, complete skin care solutions and bridal packages, at affordable rates. Equipped with the knowledge on a wide variety of professional hair & skin care products, our well trained professional stylists provide friendly service. Conveniently close, we're located right in your neighbourhood. You can also find your favorite world class hair care brands, like L'Oreal, Matrix, Wella, Schwarzkopf and many more at Green Trends.");
        TextView ceo=(TextView) findViewById(R.id.ceo);
        TextView our=(TextView) findViewById(R.id.gallery);
        ceo.setText("Green Trends offers trendy haircuts and color services, complete skin care solutions and bridal packages, at affordable rates. Equipped with the knowledge on a wide variety of professional hair & skin care products, our well trained professional stylists provide friendly service. Conveniently close, we're located right in your neighbourhood. You can also find your favorite world class hair care brands, like L'Oreal, Matrix, Wella, Schwarzkopf and many more at Green Trends.");

        our.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(AboutUs.this);
            }
        });
    }

    public static void showAlertDialog(Context context) {
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.about_dailog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        /*TextView text = (TextView) dialog.findViewById(R.id.email_d);
        text.setText(emailD);
        TextView text_title = (TextView) dialog.findViewById(R.id.phone_d);
        text_title.setText(phoneD);
        TextView wha = (TextView) dialog.findViewById(R.id.whats_d);
        wha.setText(whatsapp);
        TextView webs = (TextView) dialog.findViewById(R.id.website_d);
        webs.setText(websitD);*/
        RecyclerView mRecyclerView = (RecyclerView) dialog.findViewById(R.id.images_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext(),LinearLayoutManager.HORIZONTAL,true));
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(dialog.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<AndroidVersion> av = prepareData();
        AndroidDataAdapter movies = new AndroidDataAdapter(dialog.getContext(), av);
        mRecyclerView.setAdapter(movies);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(dialog.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        switch (i) {
                            case 0:


                                break;
                            case 1:


                                break;
                            case 2:


                            case 3:


                                break;
                            case 4:


                                break;
                            case 5:


                                break;

                        }
                    }
                })
        );

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void initRecyclerViews() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.images_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,true));
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<AndroidVersion> av = prepareData();
        AndroidDataAdapter movies = new AndroidDataAdapter(getApplicationContext(), av);
        mRecyclerView.setAdapter(movies);
        final Context ctx=this;
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        switch (i) {
                            case 0:


                                break;
                            case 1:


                                break;
                            case 2:


                            case 3:


                                break;
                            case 4:


                                break;
                            case 5:


                                break;

                        }
                    }
                })
        );

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
