package in.eloksolutions.divaz.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.AndroidVersion;


public class OffersScreen extends AppCompatActivity {
GridView grid;
    EditText serviceName, serviceDescription, ServiceRate, serviceOfferRate;
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
        setContentView(R.layout.offers_screen);
        context=this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Offers");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent=new Intent(OffersScreen.this, CreateOffer.class);
                startActivity(intent);
                //offerCreate(OffersScreen.this, "", "");
            }
        });


        /*ImageView ownerScreen=(ImageView) findViewById(R.id.packages);
        ownerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OffersScreen.this,Packages.class);
                startActivity(songsIntent);
            }
        });
        ImageView packages=(ImageView) findViewById(R.id.packages);
        packages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OffersScreen.this,Packages.class);
                startActivity(songsIntent);
            }
        });
        ImageView servi=(ImageView) findViewById(R.id.services);
        servi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OffersScreen.this,Packages.class);
                startActivity(songsIntent);
            }
        });
        ImageView gallery=(ImageView) findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OffersScreen.this,Services.class);
                startActivity(songsIntent);
            }
        });
        ImageView contact=(ImageView) findViewById(R.id.about_us);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OffersScreen.this,Packages.class);
                startActivity(songsIntent);
            }
        });
        ImageView offer=(ImageView) findViewById(R.id.send_mes);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OffersScreen.this,Packages.class);
                startActivity(songsIntent);
            }
        });
        ImageView send=(ImageView) findViewById(R.id.create_offer);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(OffersScreen.this,About.class);
                startActivity(songsIntent);
            }
        });*/


     //   songRecyclerViews();

    }
    public static void offerCreate(Context context, String title, String message) {
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.offer_create_dailog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
       TextView date = (TextView) dialog.findViewById(R.id.date);
        // date.setText("" + DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()));
        TextView time = (TextView) dialog.findViewById(R.id.time);

        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        //TextView text = (TextView) dialog.findViewById(R.id.item_name);
        //text.setText(message);
        //TextView text_title = (TextView) dialog.findViewById(R.id.item_short_desc);
        //text_title.setText(title);
        ImageView impage = (ImageView) dialog.findViewById(R.id.close);
        impage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (Build.VERSION.SDK_INT >= 19) {
                    // For Android versions of KitKat or later, we use a
                    // different intent to ensure
                    // we can get the file path from the returned intent URI
                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                } else {
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                }
                int code = 2;

                intent.setType("image/*");
                //dialog.startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    /*private void songRecyclerViews() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
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

                                Intent intent1 = new Intent(OwnerApp.this,Packages.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(OwnerApp.this,Packages.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3 = new Intent(OwnerApp.this,Services.class);
                                startActivity(intent3);
                                break;
                            case 4:
                                Intent intent4 = new Intent(OwnerApp.this,About.class);
                                startActivity(intent4);
                                break;
                            case 5:
                                Intent intent5 = new Intent(OwnerApp.this,About.class);
                                startActivity(intent5);
                                break;

                        }
                    }
                })
        );*/


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
    public static void showAlertDialog(Context context, String title, String message) {
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.offers_screen_new);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        TextView text = (TextView) dialog.findViewById(R.id.item_name);
        text.setText(message);
        TextView text_title = (TextView) dialog.findViewById(R.id.item_short_desc);
        text_title.setText(title);

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preview, menu);

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
            case R.id.preview:
                showAlertDialog(OffersScreen.this, "",
                        "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
