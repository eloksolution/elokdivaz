package in.eloksolutions.beaty.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.io.File;

import in.eloksolutions.beaty.MainActivity;
import in.eloksolutions.beaty.R;
import in.eloksolutions.beaty.dtoclasses.RegisterDTO;
import in.eloksolutions.beaty.helpers.ProfileViewHelper;
import in.eloksolutions.beaty.util.Config;


/**
 * Created by welcome on 6/27/2017.
 */

public class ProfileView extends AppCompatActivity {
    TextView name, email,number;
    ImageView gImage,imgView;
    public Dialog dialog;
    Spinner gCatagery;
    String groupId,tag="PRofileView";
    Button createGroup;
 String imagePath;
    private static int PICK_FROM_GALLERY;
    private int STORAGE_PERMISSION_CODE = 23;
    TransferUtility transferUtility;
    File fileToDownload = new File("/storage/sdcard0/Pictures/MY");
    AmazonS3 s3;
    String Tag="Create Offer";
String serviceId;
    String TAG="Create Group";
    String keyName,userId="11",userName;
    File fileToUpload;
    ImageView serviceimg;
    Glide glide;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  userId=getIntent().getStringExtra("userId");
        Log.i(tag, "serviceId for serviceUpdate is"+serviceId);
        name=(TextView) findViewById(R.id.first_names);
        email=(TextView) findViewById(R.id.email);
        number=(TextView) findViewById(R.id.mobile);
        imgView=(ImageView) findViewById(R.id.profile_image_view);
        ImageView edit=(ImageView) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileView.this,ProfileUpdate.class);
                startActivity(intent);
            }
        });
        ProfileViewHelper getGroupsValue=new ProfileViewHelper(this);
        String surl = Config.SERVER_URL+"customer/"+userId;
        System.out.println("url for services list"+surl);
        try {
            String output=getGroupsValue.new ProfileUpdateTask(surl).execute().get();
            System.out.println("the output from services"+output);
            setValuesToTextFields(output);
        }catch (Exception e){
            e.printStackTrace();
        }



    }



    public void setValuesToTextFields(String result) {
        System.out.println("json xxxx from groupview" + result);
        if (result != null) {
            Gson gson = new Gson();
            RegisterDTO fromJsonn = gson.fromJson(result, RegisterDTO.class);
            name.setText(fromJsonn.getFirstName());
            number.setText(fromJsonn.getPhone());
            email.setText(fromJsonn.getEmail());
            imagePath=fromJsonn.getImgPath();
            if(fromJsonn.getImgPath()!=null) {
                glide.with(ProfileView.this).load(Config.IMG_AWS +fromJsonn.getImgPath()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imgView);
            }else{
                glide.with(ProfileView.this).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(imgView);
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
