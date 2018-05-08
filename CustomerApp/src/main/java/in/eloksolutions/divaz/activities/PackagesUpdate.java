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
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.checking.Validation;
import in.eloksolutions.divaz.dtoclasses.PackagesDTO;
import in.eloksolutions.divaz.dtoclasses.ServiceDTO;
import in.eloksolutions.divaz.helpers.PackageUpdateHelper;
import in.eloksolutions.divaz.helpers.PackageUpdateTask;
import in.eloksolutions.divaz.util.Config;


/**
 * Created by welcome on 6/27/2017.
 */

public class PackagesUpdate extends AppCompatActivity {
    EditText pName, pRate, pDescription;
    TextView service;
    ImageView gImage,imgView;
    Spinner gCatagery;
    String groupId,tag="GroupUpDate";
    Button createGroup;
    private int STORAGE_PERMISSION_CODE = 23;
    File fileToDownload = new File("/storage/sdcard0/Pictures/MY");
String packageId;
    String TAG="Create Group";
    String keyName,userId,userName;
    File fileToUpload;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        getSupportActionBar().setTitle("Package Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        packageId=getIntent().getStringExtra("packageId");
        Log.i(tag, "packageId for serviceUpdate is"+packageId);
        service=(TextView) findViewById(R.id.textView2);
        pName=(EditText) findViewById(R.id.package_name);
        pDescription=(EditText) findViewById(R.id.package_description);
        pRate=(EditText) findViewById(R.id.package_rate);
        imgView=(ImageView) findViewById(R.id.img_one);
        final Context ctx = this;

        PackageUpdateHelper getGroupsValue=new PackageUpdateHelper(this);
        String surl = Config.SERVER_URL+"packages/getoffer/"+packageId;
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
            pName.setText(fromJsonn.getName());
            pDescription.setText(fromJsonn.getDescription());
            pRate.setText(fromJsonn.getPrice());

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_menu, menu);

        return true;
    }
    private String savePackageDetails() {

        PackagesDTO packagesDTO = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(PackagesUpdate.this)) {

                String gurl = Config.SERVER_URL +"packages/update";
                try {
                    String json=buildJson(packagesDTO);
                    String gId = new PackageUpdateTask(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(PackagesUpdate.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(PackagesDTO packagesDTO) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id",packageId);
            jsonObject.accumulate("name",packagesDTO.getName());
            jsonObject.accumulate("description", packagesDTO.getDescription());
            jsonObject.accumulate("price", packagesDTO.getPrice());
            jsonObject.accumulate("category", packagesDTO.getCategory());
            jsonObject.accumulate("imgePath", packagesDTO.getImagePath());

            //  jsonObject.accumulate("loc",registerDto.getLati());
            String json = jsonObject.toString();
            System.out.println("Json is" + json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private PackagesDTO buildDTOObject() {
        PackagesDTO packagesDTO=new PackagesDTO();
        String pNames= pName.getText().toString();
        packagesDTO.setName(pNames);
        String description= pDescription.getText().toString();
        packagesDTO.setDescription(description);
        String normalPrice= pRate.getText().toString();
        packagesDTO.setPrice(normalPrice);
        return packagesDTO;
    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(pName)) ret = false;

        if (!Validation.hasText(pDescription)) ret = false;
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
                savePackageDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
