package in.eloksolutions.beaty.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import in.eloksolutions.beaty.MainActivity;
import in.eloksolutions.beaty.R;


public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Contact us");
        TextView about_us=(TextView) findViewById(R.id.about_us);
        about_us.setText("Green Trends offers trendy haircuts and color services, complete skin care solutions and bridal packages, at affordable rates. Equipped with the knowledge on a wide variety of professional hair & skin care products, our well trained professional stylists provide friendly service. Conveniently close, we're located right in your neighbourhood. You can also find your favorite world class hair care brands, like L'Oreal, Matrix, Wella, Schwarzkopf and many more at Green Trends.");
        TextView ceo=(TextView) findViewById(R.id.ceo);
        ceo.setText("Green Trends offers trendy haircuts and color services, complete skin care solutions and bridal packages, at affordable rates. Equipped with the knowledge on a wide variety of professional hair & skin care products, our well trained professional stylists provide friendly service. Conveniently close, we're located right in your neighbourhood. You can also find your favorite world class hair care brands, like L'Oreal, Matrix, Wella, Schwarzkopf and many more at Green Trends.");

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
