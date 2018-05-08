package in.eloksolutions.divaz.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import in.eloksolutions.divaz.dtoclasses.OfferDTO;
import in.eloksolutions.divaz.dtoclasses.ServiceDTO;
import in.eloksolutions.divaz.helpers.OfferUpdateHelper;
import in.eloksolutions.divaz.helpers.OfferUpdateTask;
import in.eloksolutions.divaz.util.Config;


/**
 * Created by welcome on 6/27/2017.
 */

public class OfferUpdate extends AppCompatActivity implements View.OnClickListener{
    ImageView gImage,imgView;
    Spinner gCatagery;
    String groupId,tag="GroupUpDate";
    Button createGroup;
    private int STORAGE_PERMISSION_CODE = 23;
    File fileToDownload = new File("/storage/sdcard0/Pictures/MY");
String offerId;
    String TAG="Create Group";
    String keyName,userId,userName;
    File fileToUpload;
    TextView startDate,endDate;
    ImageView createOffer;
    EditText offerName, offerDescription, offerPrice, beforeOfferPrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_create_dailog);
        getSupportActionBar().setTitle("offer Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        offerId=getIntent().getStringExtra("offerId");
        Log.i(tag, "offerId for serviceUpdate is"+offerId);
        offerName=(EditText) findViewById(R.id.offer_name);
        offerDescription=(EditText) findViewById(R.id.offer_desc);
        offerPrice=(EditText) findViewById(R.id.offer_price);
        beforeOfferPrice=(EditText) findViewById(R.id.normal_rate);

        startDate = (TextView) findViewById(R.id.date);
        // date.setText("" + DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()));
        endDate = (TextView) findViewById(R.id.time);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        final Context ctx = this;

        OfferUpdateHelper getGroupsValue=new OfferUpdateHelper(this);
        String surl = Config.SERVER_URL+"offers/getoffer/"+offerId;
        System.out.println("url for services list"+surl);
        try {
            String output=getGroupsValue.new ServiceUpdateTask(surl).execute().get();
            System.out.println("the output from services"+output);
            setValuesToTextFields(output);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Override
    public void onClick(View v) {

        if (v == startDate) {
            DateAndTimePicker.datePickerDialog(this, startDate);
        }
        if (v == endDate) {
            DateAndTimePicker.datePickerDialog(this, endDate);
        }

    }
    public void setValuesToTextFields(String result) {
        System.out.println("json xxxx from groupview" + result);
        if (result != null) {
            Gson gson = new Gson();
            ServiceDTO fromJsonn = gson.fromJson(result, ServiceDTO.class);
            offerName.setText(fromJsonn.getName());
            offerDescription.setText(fromJsonn.getName());
            offerPrice.setText(fromJsonn.getPrice());

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_menu, menu);

        return true;
    }
    private String saveSErviceDetails() {

        OfferDTO offerDTO = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(OfferUpdate.this)) {

                String gurl =Config.SERVER_URL+"offers/update";
                try {
                    String json=buildJson(offerDTO);
                    String gId = new OfferUpdateTask(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(OfferUpdate.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(OfferDTO offerDTO) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id", offerId);
            jsonObject.accumulate("name",offerDTO.getName());
            jsonObject.accumulate("description", offerDTO.getDescription());
            jsonObject.accumulate("offerPrice", offerDTO.getOfferPrice());
            jsonObject.accumulate("beforeOfferPrice", offerDTO.getBeforeOfferPrice());
            jsonObject.accumulate("imgePath", offerDTO.getImgePath());
            jsonObject.accumulate("startDate", offerDTO.getStartDate());
            jsonObject.accumulate("endDate", offerDTO.getEndDate());
            jsonObject.accumulate("createdDate", offerDTO.getCreatedDate());
            //  jsonObject.accumulate("loc",registerDto.getLati());
            String json = jsonObject.toString();
            System.out.println("Json is" + json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private OfferDTO buildDTOObject() {
        OfferDTO offerDTO=new OfferDTO();
        String pNames= offerName.getText().toString();
        offerDTO.setName(pNames);
        String descriptions= offerDescription.getText().toString();
        offerDTO.setDescription(descriptions);
        String normalPrice= offerPrice.getText().toString();
        offerDTO.setOfferPrice(normalPrice);
        return offerDTO;
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(offerName)) ret = false;
        if (!Validation.hasText(offerPrice)) ret = false;
        if (!Validation.hasText(offerDescription)) ret = false;
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
