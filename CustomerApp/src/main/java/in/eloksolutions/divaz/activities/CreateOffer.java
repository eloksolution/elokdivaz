package in.eloksolutions.divaz.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.checking.Validation;
import in.eloksolutions.divaz.dtoclasses.OfferDTO;
import in.eloksolutions.divaz.helpers.CreateOfferHelper;
import in.eloksolutions.divaz.util.Config;

public class CreateOffer extends AppCompatActivity implements View.OnClickListener {
    TextView startDate,endDate;
    ImageView createOffer;
    EditText offerName, offerDescription, offerPrice, beforeOfferPrice;
    private int STORAGE_PERMISSION_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_create_dailog);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Create Offer");
        offerName=(EditText) findViewById(R.id.offer_name);
        offerDescription=(EditText) findViewById(R.id.offer_desc);
        offerPrice=(EditText) findViewById(R.id.offer_price);
        beforeOfferPrice=(EditText) findViewById(R.id.normal_rate);

        startDate = (TextView) findViewById(R.id.date);
        // date.setText("" + DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()));
        endDate = (TextView) findViewById(R.id.time);
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
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
        createOffer=(ImageView) findViewById(R.id.msg_img);
        createOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( isReadStorageAllowed()){
                    //If permission is already having then showing the toast
                    //  Toast.makeText(Registartion.this,"You already have the permission",Toast.LENGTH_LONG).show();
                    //Existing the method with return
                    Intent intent = new Intent();
                    if (Build.VERSION.SDK_INT >= 19) {
                        // For Android versions of KitKat or later, we use a
                        // different intent to ensure
                        // we can get the file path from the returned intent URI
                        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                    } else {
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                    }
                    intent.setType("image/*");
                    startActivityForResult(intent, 1);
                    return;
                }

                //If the app has not the permission then asking for the permission
                requestStoragePermission();
            }
        });

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
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        if(!canMakeSmores()) return true;
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }
    private boolean canMakeSmores(){
        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);
    }
    private void requestStoragePermission(){
        if(!canMakeSmores())return ;
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1 && null != data) {
            decodeUri(data.getData());
        }
    }

    public void decodeUri(Uri uri) {
        ParcelFileDescriptor parcelFD = null;
        try {
            parcelFD = getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor imageSource = parcelFD.getFileDescriptor();

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(imageSource, null, o);

            // the new size we want to scale to
            final int REQUIRED_SIZE = 1024;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);

                createOffer.setImageBitmap(bitmap);


        } catch (FileNotFoundException e) {
            // handle errors
        } catch (IOException e) {
            // handle errors
        } finally {
            if (parcelFD != null)
                try {
                    parcelFD.close();
                } catch (IOException e) {
                    // ignored
                }
        }
    }
    private String saveCompanyDetails() {

        OfferDTO offerDTO = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(CreateOffer.this)) {

                String gurl = Config.SERVER_URL + "offers/add";
                try {
                    String json=buildJson(offerDTO);
                    String gId = new CreateOfferHelper(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(CreateOffer.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(OfferDTO offerDTO) {
        try {
            JSONObject jsonObject = new JSONObject();
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
        String offerNames= offerName.getText().toString();
        offerDTO.setName(offerNames);
        String description= offerDescription.getText().toString();
        offerDTO.setDescription(description);
        String normalPrice= beforeOfferPrice.getText().toString();
        offerDTO.setBeforeOfferPrice(normalPrice);
        String offer = offerPrice.getText().toString();
        offerDTO.setOfferPrice(offer);

        /*String are=area.getText().toString();
        registerDto.setArea(are);
        registerDto.setLongi(longi);
        registerDto.setLati(latti);
        registerDto.setImgPath(keyName);*/


        return offerDTO;
    }
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(offerName)) ret = false;

        if (!Validation.hasText(offerDescription)) ret = false;
        return ret;

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
