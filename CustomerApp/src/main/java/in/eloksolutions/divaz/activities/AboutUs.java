package in.eloksolutions.divaz.activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.AndroidVersion;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.checking.Validation;
import in.eloksolutions.divaz.dtoclasses.CompanyDTO;
import in.eloksolutions.divaz.helpers.UpdateCompanyHelper;
import in.eloksolutions.divaz.util.Config;


public class AboutUs extends AppCompatActivity implements View.OnClickListener {
GridView grid;
    EditText parlourName, parlourDescription,parlourTeam, CompanyParaOne,CompanyParaTwo;
    TextView fromTimeOne, fromTimeTwo, fromTimeTree, fromTimeFour, fromTimeFive, fromSix, fromTimeSeven,
    toTimeOne,toTimeTwo, toTimeTree, toTimeFour, toTimeFive, toSix, toTimeSeven;
    ImageView ownerScreen,packages,servi,offer,gallery,contact;
    Context context;
    private static int RESULT_LOAD_IMAGE ;
    int[] Images = {
            R.drawable.offersmain,
            R.drawable.sendmessage,
            R.drawable.packages,
            R.drawable.servicess,
            R.drawable.contactus,
            R.drawable.gallerybeauty
    };

    private int STORAGE_PERMISSION_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_form);
        context=this;
       // fromTimeOne = (TextView) findViewById(R.id.fromTimeOne);
        // date.setText("" + DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()));
       // toTimeOne = (TextView) findViewById(R.id.toTimeOne);
        //toTimeOne.setOnClickListener(this);
       // fromTimeOne.setOnClickListener(this);
        parlourName = (EditText) findViewById(R.id.parlour_name);
        parlourDescription = (EditText) findViewById(R.id.parlour_description);
        parlourTeam = (EditText) findViewById(R.id.parlour_team);
        CompanyParaOne = (EditText) findViewById(R.id.company_para_one);
        CompanyParaTwo = (EditText) findViewById(R.id.company_para_two);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("About Us");

     //   songRecyclerViews();
    }


    private ArrayList<AndroidVersion> prepareSongData() {
        ArrayList<AndroidVersion> av = new ArrayList<>();
        for (int i = 0; i < Images.length; i++) {
            AndroidVersion mAndroidVersion = new AndroidVersion();
            //mAndroidVersion.setAndroidVersionName(songNames[i]);
            mAndroidVersion.setrecyclerViewImage(Images[i]);
            av.add(mAndroidVersion);
        }
        return av;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_menu, menu);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String upDateCompanyDetails() {

        CompanyDTO registerDto = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(AboutUs.this)) {

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
                CheckInternet.showAlertDialog(AboutUs.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(CompanyDTO registerDto) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", registerDto.getName());
            jsonObject.accumulate("id", "2");
            jsonObject.accumulate("descriptioin", registerDto.getDescriptioin());
            jsonObject.accumulate("para1", registerDto.getPara1());
            jsonObject.accumulate("para2", registerDto.getPara2());
            jsonObject.accumulate("team", registerDto.getTeam());
            jsonObject.accumulate("code", "1243");
            jsonObject.accumulate("address", "east anandh bagh");
            jsonObject.accumulate("officePhone", "7788778");
            jsonObject.accumulate("ownerPhone", "88786576");
            jsonObject.accumulate("customerCarePhone", "67766");
            jsonObject.accumulate("email", "updatigemail@gmail.com");
            jsonObject.accumulate("password", "433");
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
      String companyNames= parlourName.getText().toString();
        registerDto.setName(companyNames);
       String parlourDescriptions= parlourDescription.getText().toString();
        registerDto.setDescriptioin(parlourDescriptions);
        String parlourTeams= parlourTeam.getText().toString();
        registerDto.setTeam(parlourTeams);
        String companyParas = CompanyParaOne.getText().toString();
        registerDto.setPara1(companyParas);
        String CompanyParaTwos=CompanyParaTwo.getText().toString();
        registerDto.setPara2(CompanyParaTwos);
        /*String are=area.getText().toString();
        registerDto.setArea(are);
        registerDto.setLongi(longi);
        registerDto.setLati(latti);
        registerDto.setImgPath(keyName);*/


        return registerDto;
    }
    @Override
    public void onClick(View v) {
        if (v == fromTimeOne) {
            showToTimePicker();
        }
        if (v == toTimeOne) {
            showToTimePickerTwo();
        }
    }
    private void showToTimePicker() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = DateAndTimePicker.getTimePickerDialog(hour, minute, this, fromTimeOne);
        mTimePicker.show();
    }
    private void showToTimePickerTwo() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = DateAndTimePicker.getTimePickerDialog(hour, minute, this, toTimeOne);
        mTimePicker.show();
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(parlourName)) ret = false;
        if (!Validation.hasText(parlourDescription)) ret = false;
        if (!Validation.hasText(parlourTeam)) ret = false;

        return ret;

    }
}
