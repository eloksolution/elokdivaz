package in.eloksolutions.divaz.activities;

import android.app.TimePickerDialog;
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
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
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


public class Gallery extends AppCompatActivity implements View.OnClickListener {
GridView grid;
    EditText myEditText;
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
        setContentView(R.layout.gallery);
        context=this;
       // fromTimeOne = (TextView) findViewById(R.id.fromTimeOne);
        // date.setText("" + DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()));
       // toTimeOne = (TextView) findViewById(R.id.toTimeOne);
        //toTimeOne.setOnClickListener(this);
       // fromTimeOne.setOnClickListener(this);
         myEditText = (EditText) findViewById(R.id.first_names);
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(100); //Filter to 10 characters
        myEditText .setFilters(filters);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle("Gallery ");
         ownerScreen=(ImageView) findViewById(R.id.img_one);
        ownerScreen.setOnClickListener(new View.OnClickListener() {
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
                     int code = 1;
                    RESULT_LOAD_IMAGE = code;
                    intent.setType("image/*");
                    startActivityForResult(intent, RESULT_LOAD_IMAGE);
                    return;
                }

                //If the app has not the permission then asking for the permission
                requestStoragePermission();

            }
        });

        packages=(ImageView) findViewById(R.id.img_two);
        packages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                int code = 2;
                RESULT_LOAD_IMAGE = code;
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
         servi=(ImageView) findViewById(R.id.img_three);
        servi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                int code = 3;
                RESULT_LOAD_IMAGE = code;
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
         gallery=(ImageView) findViewById(R.id.img_four);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                int code = 4;
                RESULT_LOAD_IMAGE = code;
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
         contact=(ImageView) findViewById(R.id.img_five);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                int code = 5;
                RESULT_LOAD_IMAGE = code;
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
         offer=(ImageView) findViewById(R.id.img_six);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                int code = 6;
                RESULT_LOAD_IMAGE = code;
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });



     //   songRecyclerViews();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE && null != data) {
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
if (RESULT_LOAD_IMAGE==1) {
    ownerScreen.setImageBitmap(bitmap);
}
            if (RESULT_LOAD_IMAGE==2) {
                packages.setImageBitmap(bitmap);
            }
            if (RESULT_LOAD_IMAGE==3) {
                servi.setImageBitmap(bitmap);
            }
            if (RESULT_LOAD_IMAGE==4) {
                gallery.setImageBitmap(bitmap);
            }
            if (RESULT_LOAD_IMAGE==5) {
                contact.setImageBitmap(bitmap);
            }
            if (RESULT_LOAD_IMAGE==6) {
                offer.setImageBitmap(bitmap);
            }
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
    /*private void songRecyclerViews() {

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        ArrayList<AndroidVersion> av = prepareSongData();
        AndroidDataAdapter movies = new AndroidDataAdapter(getApplicationContext(), av);
        mRecyclerView.setAdapter(movies);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        switch (i) {
                            case 0:

                                Intent intent = new Intent(OwnerApp.this,Packages.class);

                                startActivity(intent);
                                break;
                            case 1:

                                Intent intent1 = new Intent(OwnerApp.this,Packages.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(OwnerApp.this,Packages.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3 = new Intent(OwnerApp.this,Services.class);
                                startActivity(intent3);
                                break;
                            case 4:
                                Intent intent4 = new Intent(OwnerApp.this,About.class);
                                startActivity(intent4);
                                break;
                            case 5:
                                Intent intent5 = new Intent(OwnerApp.this,About.class);
                                startActivity(intent5);
                                break;

                        }
                    }
                })
        );*/


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
            if (CheckInternet.checkInternetConenction(Gallery.this)) {

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
                CheckInternet.showAlertDialog(Gallery.this, "No Internet Connection",
                        "You don't have internet connection.");
            }
        }
        return null;
    }

    private String buildJson(CompanyDTO registerDto) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", registerDto.getName());
            jsonObject.accumulate("id", "1");
            jsonObject.accumulate("descriptioin", "DesrciptionDesrciptionDesrciption");
            jsonObject.accumulate("code", "1243");
            jsonObject.accumulate("address", "east anandh bagh");
            jsonObject.accumulate("officePhone", "7788778");
            jsonObject.accumulate("ownerPhone", "88786576");
            jsonObject.accumulate("customerCarePhone", "67766");
            jsonObject.accumulate("email", "updatingemail@gmail.com");
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
      String companyNames= myEditText.getText().toString();
        registerDto.setName(companyNames);
       /* String names= name.getText().toString();
        registerDto.setFirstName(names);
        String rEmail= email.getText().toString();
        registerDto.setEmail(rEmail);
        String phones = phone.getText().toString();
        registerDto.setOfficePhone(phones);
        String pass=password.getText().toString();
        registerDto.setPassword(pass);*/
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
        if (!Validation.hasText(myEditText)) ret = false;
        if (!Validation.hasText(myEditText)) ret = false;
        return ret;

    }
}
