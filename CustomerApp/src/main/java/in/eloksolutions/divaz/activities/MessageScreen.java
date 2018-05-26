package in.eloksolutions.divaz.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.AndroidVersion;


public class MessageScreen extends AppCompatActivity {
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
        setContentView(R.layout.message_list);
        context = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Messages List");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent message = new Intent(MessageScreen.this, MessageCreate.class);
                startActivity(message);
                // showAlertDialog(MessageScreen.this, "", "");
            }
        });
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
    public static void showAlertDialog(Context context, String title, String message) {
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.message_dailog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        //TextView text = (TextView) dialog.findViewById(R.id.item_name);
        //text.setText(message);
        //TextView text_title = (TextView) dialog.findViewById(R.id.item_short_desc);
        //text_title.setText(title);

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
