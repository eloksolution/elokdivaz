package in.eloksolutions.beaty.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import in.eloksolutions.beaty.R;
import in.eloksolutions.beaty.adapter.CheckInternet;
import in.eloksolutions.beaty.dtoclasses.CompanyDTO;
import in.eloksolutions.beaty.helpers.CreateRegistration;
import in.eloksolutions.beaty.util.Config;


/**
 * Created by welcome on 2/3/2018.
 */
public class Registartion extends AppCompatActivity {
    EditText companyName, phone, name, email, password,city,loc;
    LocationManager locationManager;
    double latti,longi;
    static final int REQUEST_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Registation");
        companyName = (EditText) findViewById(R.id.first_names);
        phone = (EditText) findViewById(R.id.mobile);
        name = (EditText) findViewById(R.id.et_city);
        email = (EditText) findViewById(R.id.email);
        //password = (EditText) findViewById(R.id.location);
        loc = (EditText) findViewById(R.id.location);
        city = (EditText) findViewById(R.id.et_city);
       Button preview = (Button) findViewById(R.id.submit);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCompanyDetails();
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();
    }

    private String saveCompanyDetails() {

        CompanyDTO registerDto = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(Registartion.this)) {

                String gurl = Config.SERVER_URL + "customer/add";
                try {
                    String json=buildJson(registerDto);
                    String gId = new CreateRegistration(json,registerDto, gurl,this).execute().get();
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
            jsonObject.accumulate("firstName", registerDto.getName());
            //jsonObject.accumulate("lastName", "Desrciption");
            jsonObject.accumulate("email", registerDto.getEmail());
            jsonObject.accumulate("address1", registerDto.getAddress());
            jsonObject.accumulate("address2", registerDto.getAddress_1());
            jsonObject.accumulate("city", registerDto.getCity());
            jsonObject.accumulate("state", registerDto.getState());
            jsonObject.accumulate("phone", registerDto.getOfficePhone());
            //jsonObject.accumulate("imagePath", registerDto.getPassword());
            jsonObject.accumulate("longitude", ""+longi);
            jsonObject.accumulate("latitude", ""+latti);
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
        //String pass=password.getText().toString();
        registerDto.setPassword("1234");
        String citys = city.getText().toString();
        registerDto.setCity(citys);
        String locs=loc.getText().toString();
        registerDto.setAddress(locs);

        /*String are=area.getText().toString();
        registerDto.setArea(are);
        registerDto.setLongi(longi);
        registerDto.setLati(latti);
        registerDto.setImgPath(keyName);*/


        return registerDto;
    }

    void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                latti = location.getLatitude();
                longi = location.getLongitude();

                Geocoder gc= new Geocoder(this, Locale.getDefault());
                // TextView addr = (TextView) main.findViewById(R.id.editText2);
                String result="x03";
                try {
                    List<Address> addressList = gc.getFromLocation(latti,
                            longi, 1);

                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getCountryName());
                        result = sb.toString();
                        loc.setText(result);
                        city.setText(address.getLocality());

                    }else {
                        ((TextView) findViewById(R.id.textView)).
                                setText("Unable to find current location . Try again later");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                // addr.setText("Address is"+result);
            }else{
                //  text.setText("Unabletofind");
                System.out.println("Unable");
            }
        }

    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(name)) ret = false;

        if (!Validation.hasText(companyName)) ret = false;
        return ret;

    }


}
