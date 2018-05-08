package in.eloksolutions.divaz.activities;

import android.app.Dialog;
import android.content.Context;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.AndroidDataAdapter;
import in.eloksolutions.divaz.adapter.CheckInternet;
import in.eloksolutions.divaz.checking.Validation;
import in.eloksolutions.divaz.dtoclasses.PackageItem;
import in.eloksolutions.divaz.dtoclasses.PackagesDTO;
import in.eloksolutions.divaz.helpers.GetServicesDailogHelpers;
import in.eloksolutions.divaz.helpers.PackagesHelper;
import in.eloksolutions.divaz.util.Config;


public class Packages extends AppCompatActivity {
    EditText pName, pRate, pDescription;
    TextView service;
    ImageView pickImage;
    String[] service_list;
    TextView noData;
    String serviceOne, serviceTwo, serviceThree, serviceFour;
    private int STORAGE_PERMISSION_CODE = 23;
    ArrayAdapter<String> adapter;
    public AndroidDataAdapter servicesAdapter;
    public Dialog dialog;
    String[] Names={};
RecyclerView rv;
    String tag="Packages";
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Packages");
        service=(TextView) findViewById(R.id.textView2);
        pName=(EditText) findViewById(R.id.package_name);
        pDescription=(EditText) findViewById(R.id.package_description);
        pRate=(EditText) findViewById(R.id.package_rate);
         pickImage= (ImageView) findViewById(R.id.img_one);
           RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.listView);
           mRecyclerView.setHasFixedSize(true);
           RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
           mRecyclerView.setLayoutManager(mLayoutManager);
           mRecyclerView.setNestedScrollingEnabled(false);

           servicesAdapter = new AndroidDataAdapter(getApplicationContext());
           mRecyclerView.setAdapter(servicesAdapter);
           pickImage.setOnClickListener(new View.OnClickListener() {
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
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceDialog(Packages.this);
            }
        });



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

            pickImage.setImageBitmap(bitmap);

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
    public  void showAlertDialog(Context context, String title, String message) {
         dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.packages_new);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        TextView text = (TextView) dialog.findViewById(R.id.package_name);
        text.setText(message);
        TextView text_title = (TextView) dialog.findViewById(R.id.one);
        text_title.setText(title);

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public  void serviceDialog(final Packages context) {
        dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.service_list_recycler);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        final TextView noData = (TextView) dialog.findViewById(R.id.tv_no_data);
        RecyclerView services = (RecyclerView) dialog.findViewById(R.id.service_list);
        services.setHasFixedSize(true);
        LinearLayoutManager lmPadi = new LinearLayoutManager(context);
        services.setLayoutManager(lmPadi);
        String url= Config.SERVER_URL+"services/getAll";
        GetServicesDailogHelpers getGroups=new GetServicesDailogHelpers(context,url,services,noData);
        System.out.println("url for Service list"+url);
        getGroups.execute();

        ImageView dialogButton = (ImageView) dialog.findViewById(R.id.close);

        dialog.show();
    }

    private String savePackageDetails() {

        PackagesDTO packagesDTO = buildDTOObject();

        if (checkValidation()) {
            if (CheckInternet.checkInternetConenction(Packages.this)) {

                String gurl = Config.SERVER_URL + "packages/add";
                try {
                    String json=buildJson(packagesDTO);
                    String gId = new PackagesHelper(json, gurl,this).execute().get();
                    return gId;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                CheckInternet.showAlertDialog(Packages.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(PackagesDTO packagesDTO) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name",packagesDTO.getName());
            jsonObject.accumulate("description", packagesDTO.getDescription());
            jsonObject.accumulate("price", packagesDTO.getPrice());
            jsonObject.accumulate("category", packagesDTO.getCategory());
            jsonObject.accumulate("imgePath", packagesDTO.getImagePath());
            JSONArray jsonArray=new JSONArray();
            ArrayList<PackageItem> services=servicesAdapter.getServiceItems();
            Log.i(tag,"Service Items"+services);
            for (PackageItem service:services){
                JSONObject jsonServices = new JSONObject();
                jsonServices.accumulate("serviceId",service.getServiceId() );
                jsonServices.accumulate("serviceName",service.getServiceName() );
                jsonServices.accumulate("imagePath",service.getImagePath() );
                jsonArray.put(jsonServices);
            }
            jsonObject.accumulate("items", jsonArray);
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
                savePackageDetails();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
