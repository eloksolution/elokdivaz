package in.eloksolutions.divaz.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.checking.Validation;
import in.eloksolutions.divaz.dtoclasses.CompanyDTO;
import in.eloksolutions.divaz.helpers.UpdateCompanyHelper;
import in.eloksolutions.divaz.util.Config;

public class ContactUs extends AppCompatActivity {
    EditText webslik, phone, whats, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Contact Us");
        webslik=(EditText) findViewById(R.id.website_text);
        phone=(EditText) findViewById(R.id.phone);
        whats=(EditText) findViewById(R.id.whats_app_number);
        email=(EditText) findViewById(R.id.email);

        /* Button preview=(Button) findViewById(R.id.preview_c);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent songsIntent = new Intent(Home.this,AboutPreview.class);
                startActivity(songsIntent);*//*
                showAlertDialog(About.this, "Team beatyglad we have a team of skilled and experenced artists who are ready topamper you.Everyday artist at beautyglad has at least three years of experence and has served 500+ orders",
                        "About Preview");
            }
        });*/

    }

    public static void showAlertDialog(Context context, String emailD, String phoneD, String whatsapp, String websitD) {
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.contact_us_dailog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        TextView text = (TextView) dialog.findViewById(R.id.email_d);
        text.setText(emailD);
        TextView text_title = (TextView) dialog.findViewById(R.id.phone_d);
        text_title.setText(phoneD);
        TextView wha = (TextView) dialog.findViewById(R.id.whats_d);
        wha.setText(whatsapp);
        TextView webs = (TextView) dialog.findViewById(R.id.website_d);
        webs.setText(websitD);

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preview, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.save:
                upDateCompanyDetails();
                return true;
            case R.id.preview:
                if (checkValidation()) {
                    showAlertDialog(ContactUs.this, "EmailId:\n" + email.getText().toString(), "WebSite:  \n " + webslik.getText().toString(), "Mobile No: \n" + phone.getText().toString(), "WhatsApp : \n " + whats.getText().toString());
                }

                    return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private String upDateCompanyDetails() {

        CompanyDTO registerDto = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(ContactUs.this)) {

                String gurl = Config.SERVER_URL + "company/update";
                try {
                    String json=buildJson(registerDto);
                    String gId = new UpdateCompanyHelper(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(ContactUs.this, "No Internet Connection",
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
            jsonObject.accumulate("id", "1");
            jsonObject.accumulate("address", "east anandh bagh");
            jsonObject.accumulate("officePhone", registerDto.getOwnerPhone());
            jsonObject.accumulate("ownerPhone", registerDto.getOwnerPhone());
            jsonObject.accumulate("customerCarePhone", "67766");
            jsonObject.accumulate("email", registerDto.getEmail());
            jsonObject.accumulate("password", "12345");
            jsonObject.accumulate("status", "Y");
            jsonObject.accumulate("whatsapp",registerDto.getWhatsapp());
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
      String webLinks= webslik.getText().toString();
        registerDto.setName(webLinks);
        String pones= phone.getText().toString();
        registerDto.setOwnerPhone(pones);
        String rEmail= email.getText().toString();
        registerDto.setEmail(rEmail);
        String whatss = whats.getText().toString();
        registerDto.setOfficePhone(whatss);
       /*  String pass=password.getText().toString();
        registerDto.setPassword(pass);*/
        /*String are=area.getText().toString();
        registerDto.setArea(are);
        registerDto.setLongi(longi);
        registerDto.setLati(latti);
        registerDto.setImgPath(keyName);*/


        return registerDto;
    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(webslik)) ret = false;
        if (!Validation.hasText(phone)) ret = false;
        if (!Validation.hasText(whats)) ret = false;
        if (!Validation.hasText(email)) ret = false;
        return ret;

    }

}
