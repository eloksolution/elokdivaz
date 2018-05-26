package in.eloksolutions.divaz.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.checking.Validation;
import in.eloksolutions.divaz.dtoclasses.ServiceDTO;
import in.eloksolutions.divaz.helpers.GalleryUpdateTask;
import in.eloksolutions.divaz.helpers.GalleyUpdateHelper;
import in.eloksolutions.divaz.util.Config;


/**
 * Created by welcome on 6/27/2017.
 */

public class GalleryUpdate extends AppCompatActivity {
    EditText name, description,serviceRate;
    ImageView gImage,imgView;
    Spinner gCatagery;
    String groupId,tag="GroupUpDate";
    Button createGroup;
    private int STORAGE_PERMISSION_CODE = 23;
    File fileToDownload = new File("/storage/sdcard0/Pictures/MY");
String serviceId;
    String TAG="Create Group";
    String keyName,userId,userName;
    File fileToUpload;
    Glide glide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        getSupportActionBar().setTitle("SERVICE Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        serviceId=getIntent().getStringExtra("serviceId");
        Log.i(tag, "serviceId for serviceUpdate is"+serviceId);
        name=(EditText) findViewById(R.id.service_name);
        description=(EditText) findViewById(R.id.service_description);
        serviceRate=(EditText) findViewById(R.id.service_rate);
        imgView=(ImageView) findViewById(R.id.img_one);
        final Context ctx = this;

        GalleyUpdateHelper getGroupsValue=new GalleyUpdateHelper(this);
        String surl = Config.SERVER_URL+"services/getService/"+serviceId;
        System.out.println("url for services list"+surl);
        try {
            String output=getGroupsValue.new ServiceUpdateTask(surl).execute().get();
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
            ServiceDTO fromJsonn = gson.fromJson(result, ServiceDTO.class);
            name.setText(fromJsonn.getName());
            serviceRate.setText(fromJsonn.getPrice());
            if(fromJsonn.getImgePath()!=null) {
                glide.with(GalleryUpdate.this).load(Config.IMG_AWS +fromJsonn.getImgePath()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imgView);
            }else{
                glide.with(GalleryUpdate.this).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(imgView);
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_menu, menu);

        return true;
    }
    private String saveSErviceDetails() {

        ServiceDTO serviceDTO = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(GalleryUpdate.this)) {

                String gurl = Config.SERVER_URL + "services/update";
                try {
                    String json=buildJson(serviceDTO);
                    String gId = new GalleryUpdateTask(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(GalleryUpdate.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(ServiceDTO serviceDTO) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id", serviceId);
            jsonObject.accumulate("name",serviceDTO.getName());
            jsonObject.accumulate("description", serviceDTO.getDescription());
            jsonObject.accumulate("price", serviceDTO.getPrice());
            jsonObject.accumulate("imgePath", serviceDTO.getImgePath());
            jsonObject.accumulate("discount", serviceDTO.getDiscount());
            jsonObject.accumulate("img_icon", serviceDTO.getImg_icon());
            //  jsonObject.accumulate("loc",registerDto.getLati());
            String json = jsonObject.toString();
            System.out.println("Json is" + json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private ServiceDTO buildDTOObject() {
        ServiceDTO serviceDTO=new ServiceDTO();
        String pNames= name.getText().toString();
        serviceDTO.setName(pNames);
        String descriptions= description.getText().toString();
        serviceDTO.setDescription(descriptions);
        String normalPrice= serviceRate.getText().toString();
        serviceDTO.setPrice(normalPrice);
        return serviceDTO;
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(name)) ret = false;

        if (!Validation.hasText(description)) ret = false;
        return ret;

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
            case R.id.save:
                saveSErviceDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
