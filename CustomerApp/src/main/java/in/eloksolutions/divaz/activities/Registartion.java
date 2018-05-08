package in.eloksolutions.divaz.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.checking.Validation;
import in.eloksolutions.divaz.dtoclasses.CompanyDTO;
import in.eloksolutions.divaz.helpers.CreateRegistration;
import in.eloksolutions.divaz.util.Config;


/**
 * Created by welcome on 2/3/2018.
 */
public class Registartion extends AppCompatActivity {
    EditText companyName, phone, name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Registation");
        companyName = (EditText) findViewById(R.id.first_names);
        phone = (EditText) findViewById(R.id.parlour_name);
        name = (EditText) findViewById(R.id.mobile);
        email = (EditText) findViewById(R.id.email_a);
        password = (EditText) findViewById(R.id.email_a);
       Button preview = (Button) findViewById(R.id.submit);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCompanyDetails();
            }
        });

    }

    private String saveCompanyDetails() {

        CompanyDTO registerDto = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(Registartion.this)) {

                String gurl = Config.SERVER_URL + "company/update";
                try {
                    String json=buildJson(registerDto);
                    String gId = new CreateRegistration(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(Registartion.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(CompanyDTO registerDto) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", registerDto.getName());
            jsonObject.accumulate("descriptioin", "Desrciption");
            jsonObject.accumulate("code", "1243");
            jsonObject.accumulate("address", "east anandh bagh");
            jsonObject.accumulate("officePhone", registerDto.getOfficePhone());
            jsonObject.accumulate("ownerPhone", "88786576");
            jsonObject.accumulate("customerCarePhone", "67766");
            jsonObject.accumulate("email", registerDto.getEmail());
            jsonObject.accumulate("password", registerDto.getPassword());
            jsonObject.accumulate("status", "Y");
            //  jsonObject.accumulate("loc",registerDto.getLati());
            String json = jsonObject.toString();
            System.out.println("Json is" + json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private CompanyDTO buildDTOObject() {
        CompanyDTO registerDto=new CompanyDTO();
        String companyNames= companyName.getText().toString();
        registerDto.setName(companyNames);
        String names= name.getText().toString();
        registerDto.setFirstName(names);
        String rEmail= email.getText().toString();
        registerDto.setEmail(rEmail);
        String phones = phone.getText().toString();
        registerDto.setOfficePhone(phones);
        String pass=password.getText().toString();
        registerDto.setPassword(pass);

        /*String are=area.getText().toString();
        registerDto.setArea(are);
        registerDto.setLongi(longi);
        registerDto.setLati(latti);
        registerDto.setImgPath(keyName);*/


        return registerDto;
    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(name)) ret = false;

        if (!Validation.hasText(companyName)) ret = false;
        return ret;

    }


}
