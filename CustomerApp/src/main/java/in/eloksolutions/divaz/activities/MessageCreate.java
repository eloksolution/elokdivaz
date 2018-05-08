package in.eloksolutions.divaz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.checking.Validation;
import in.eloksolutions.divaz.dtoclasses.MessageDTO;
import in.eloksolutions.divaz.helpers.MessagesHelper;
import in.eloksolutions.divaz.util.Config;

/**
 * Created by welcome on 2/23/2018.
 */

public class MessageCreate extends AppCompatActivity implements View.OnClickListener
{
    EditText messageName, messageDescription;
    TextView startDate, endDate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_dailog);
        messageName=(EditText) findViewById(R.id.message_name);
        messageDescription=(EditText) findViewById(R.id.messgae_description);
        startDate = (TextView) findViewById(R.id.date);
        // date.setText("" + DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()));
        endDate = (TextView) findViewById(R.id.time);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
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
    public String saveCompanyDetails() {

        MessageDTO offerDTO = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(MessageCreate.this)) {

                String gurl = Config.SERVER_URL + "messages/add";
                try {
                    String json=buildJson(offerDTO);
                    String gId = new MessagesHelper(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(MessageCreate.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(MessageDTO messageDTO) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("subject",messageDTO.getSubject());
            jsonObject.accumulate("description", messageDTO.getDescription());
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
        String messageNames= messageName.getText().toString();
        messageDTO.setSubject(messageNames);
        String description= messageDescription.getText().toString();
        messageDTO.setDescription(description);
             return messageDTO;
    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(messageName)) ret = false;

        if (!Validation.hasText(messageDescription)) ret = false;
        return ret;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.home_button:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.save:
                saveCompanyDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
