package in.eloksolutions.divaz.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import in.eloksolutions.divaz.MainActivity;
import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.dtoclasses.CompanyDTO;
import in.eloksolutions.divaz.helpers.CompanyContactHelper;
import in.eloksolutions.divaz.util.Config;

public class ContactUs extends AppCompatActivity {
String companyId;
    TextView emailId,website,mobileNumber,whatsAppNumber;
    ImageView imageView;
    Glide glide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Contact us");
        emailId=(TextView) findViewById(R.id.email_id);
                website=(TextView) findViewById(R.id.web_id);
        mobileNumber=(TextView) findViewById(R.id.phone_id);
        whatsAppNumber=(TextView) findViewById(R.id.whats_id);
        imageView=(ImageView) findViewById(R.id.image);
        companyId=getIntent().getStringExtra("companyId");
        CompanyContactHelper getGroupsValue=new CompanyContactHelper(this);
        String surl = Config.SERVER_URL+"company/"+companyId;
        System.out.println("url for services list"+surl);
        try {
            String output=getGroupsValue.new ServiceUpdateTask(surl).execute().get();
            System.out.println("the output from services"+output);
            setValuesToTextFields(output);
        }catch (Exception e){
            e.printStackTrace();
        }
        whatsAppNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(""+whatsAppNumber.getText().toString()) + "@s.whatsapp.net");
//mobile num without + symbol and with county code(91)
                startActivity(sendIntent);

            }
        });
    }
    public void setValuesToTextFields(String result) {
        System.out.println("json xxxx from groupview" + result);
        if (result != null) {
            Gson gson = new Gson();
            CompanyDTO fromJsonn = gson.fromJson(result, CompanyDTO.class);
            emailId.setText(fromJsonn.getEmail());
            website.setText(fromJsonn.getOwnerPhone());
           whatsAppNumber.setText(fromJsonn.getWhatsapp());
            mobileNumber.setText(fromJsonn.getOwnerPhone());
            if(fromJsonn.getImgPath1()!=null) {
                glide.with(ContactUs.this).load(Config.IMG_AWS +fromJsonn.getImgPath1()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            }else{
                glide.with(ContactUs.this).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            }


        }
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
