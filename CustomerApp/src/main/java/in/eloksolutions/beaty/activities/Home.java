package in.eloksolutions.beaty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import in.eloksolutions.beaty.MainActivity;
import in.eloksolutions.beaty.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beauty_home);
        CardView main=(CardView) findViewById(R.id.cardlist_item);
        CardView ofers=(CardView) findViewById(R.id.cardlist_item2);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(Home.this,MainActivity.class);
                startActivity(songsIntent);
            }
        });
        ofers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent songsIntent = new Intent(Home.this,AboutPreview.class);
                startActivity(songsIntent);*/
                AboutPreview.showAlertDialog(Home.this, "No Internet Connection",
                        "Please On your Mobile Data / WIFI.");
            }
        });

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
