package in.eloksolutions.mybeauty;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Beauty Parlour");
       CardView preview=(CardView) findViewById(R.id.preview_c);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent songsIntent = new Intent(Home.this,AboutPreview.class);
                startActivity(songsIntent);*/
                showAlertDialog(About.this, "Team beatyglad we have a team of skilled and experenced artists who are ready topamper you.Everyday artist at beautyglad has at least three years of experence and has served 500+ orders",
                        "About Preview");
            }
        });

    }
    public static void showAlertDialog(Context context, String title, String message) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.about_us);

        TextView text = (TextView) dialog.findViewById(R.id.preview);
        text.setText(message);
        TextView text_title = (TextView) dialog.findViewById(R.id.about_us);
        text_title.setText(title);

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.righ);
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
