package in.eloksolutions.divaz.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.dtoclasses.RegisterDTO;
import in.eloksolutions.divaz.helpers.ProfileUpdateHelper;
import in.eloksolutions.divaz.util.Config;

/**
 * Created by welcome on 5/26/2018.
 */

public class ProfileUpdate extends AppCompatActivity {
    EditText name,email,mobile,address, city;
    ImageView imageView;
    Glide glide;
    String imagePath,userId;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_update);
        context=this;
        name=(EditText) findViewById(R.id.first_names);
        email=(EditText) findViewById(R.id.email);
        mobile=(EditText) findViewById(R.id.mobile);
        address=(EditText) findViewById(R.id.et_city);
        imageView=(ImageView) findViewById(R.id.profile_image_view);
        SharedPreferences sharedPreferences=getSharedPreferences(Config.APP_PREFERENCES,context.MODE_PRIVATE);
        userId=sharedPreferences.getString("userId",null);
        ProfileUpdateHelper profileUpdateHelper= new ProfileUpdateHelper(ProfileUpdate.this);
        String purl=Config.SERVER_URL+"customer/"+userId;;
        try {
            profileUpdateHelper. new ProfileUpdateTask(purl).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void setValuesToTextFields(String result) {
        Gson gson=new Gson();
        RegisterDTO registerDTO=gson.fromJson(result,RegisterDTO.class);
        name.setText(registerDTO.getFirstName());
        mobile.setText(registerDTO.getPhone());
        email.setText(registerDTO.getEmail());
        imagePath=registerDTO.getImgPath();



        if(registerDTO.getImgPath()!=null) {
            glide.with(ProfileUpdate.this).load(Config.IMG_AWS +registerDTO.getImgPath()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        }else{
            glide.with(ProfileUpdate.this).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        }

    }
}
