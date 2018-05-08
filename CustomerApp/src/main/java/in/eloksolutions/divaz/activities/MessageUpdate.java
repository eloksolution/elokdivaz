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
import in.eloksolutions.divaz.dtoclasses.MessageDTO;
import in.eloksolutions.divaz.helpers.MessageUpdateHelper;
import in.eloksolutions.divaz.helpers.MessageUpdateTask;
import in.eloksolutions.divaz.util.Config;


/**
 * Created by welcome on 6/27/2017.
 */

public class MessageUpdate extends AppCompatActivity implements View.OnClickListener{
    EditText messageName, messageDescription;
    TextView startDate, endDate;
    ImageView gImage,imgView;
    Spinner gCatagery;
    String groupId,tag="GroupUpDate";
    Button createGroup;
    private int STORAGE_PERMISSION_CODE = 23;
    File fileToDownload = new File("/storage/sdcard0/Pictures/MY");
String messageId;
    String TAG="Create Group";
    String keyName,userId,userName;
    File fileToUpload;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_dailog);
        getSupportActionBar().setTitle("offer Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        messageId=getIntent().getStringExtra("messageId");
        Log.i(tag, "messageId for serviceUpdate is"+messageId);
        messageName=(EditText) findViewById(R.id.message_name);
        messageDescription=(EditText) findViewById(R.id.messgae_description);
        startDate = (TextView) findViewById(R.id.date);
        // date.setText("" + DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()));
        endDate = (TextView) findViewById(R.id.time);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        imgView=(ImageView) findViewById(R.id.img_one);
        final Context ctx = this;

        MessageUpdateHelper getGroupsValue=new MessageUpdateHelper(this);
        String surl = Config.SERVER_URL+"messages/messages/"+messageId;
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
            MessageDTO fromJsonn = gson.fromJson(result, MessageDTO.class);
            messageName.setText(fromJsonn.getSubject());
            messageDescription.setText(fromJsonn.getDescription());

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_menu, menu);

        return true;
    }
    private String saveSErviceDetails() {

        MessageDTO messageDTO = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(MessageUpdate.this)) {

                String gurl =Config.SERVER_URL+"messages/update";
                try {
                    String json=buildJson(messageDTO);
                    String gId = new MessageUpdateTask(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(MessageUpdate.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(MessageDTO messageDTO) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id", messageId);
            jsonObject.accumulate("name",messageDTO.getSubject());
            jsonObject.accumulate("description", messageDTO.getDescription());
            jsonObject.accumulate("imgPath", messageDTO.getImgPath());
            jsonObject.accumulate("createDate", messageDTO.getCreateDate());
            jsonObject.accumulate("type", messageDTO.getType());

            //  jsonObject.accumulate("loc",registerDto.getLati());
            String json = jsonObject.toString();
            System.out.println("Json is" + json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private MessageDTO buildDTOObject() {
        MessageDTO messageDTO=new MessageDTO();
        String pNames= messageName.getText().toString();
        messageDTO.setSubject(pNames);
        String descriptions= messageDescription.getText().toString();
        messageDTO.setDescription(descriptions);

        return messageDTO;
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(messageName)) ret = false;

        if (!Validation.hasText(messageDescription)) ret = false;
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
