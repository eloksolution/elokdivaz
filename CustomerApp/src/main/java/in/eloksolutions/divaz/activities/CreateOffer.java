package in.eloksolutions.divaz.activities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONObject;

import java.io.File;
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
import in.eloksolutions.divaz.util.Util;

public class CreateOffer extends AppCompatActivity implements View.OnClickListener {
    TextView startDate,endDate;
    ImageView createOffer;
    EditText offerName, offerDescription, offerPrice, beforeOfferPrice;
    String keyName,userId,userName;
    File fileToUpload;
    private static int PICK_FROM_GALLERY;
    private int STORAGE_PERMISSION_CODE = 23;
    TransferUtility transferUtility;
    File fileToDownload = new File("/storage/sdcard0/Pictures/MY");
    AmazonS3 s3;
    String Tag="Create Offer";
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
                if(isReadStorageAllowed()) {
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
                }
                requestStoragePermission();
            }
        });
        credentialsProvider();
        // callback method to call the setTransferUtility method
        setTransferUtility();
    }
    public void credentialsProvider(){

        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "ap-northeast-1:46c87d1a-07be-4ee3-86a2-359a5683ce61", // Identity Pool ID
                Regions.AP_NORTHEAST_1  // Region
        );

        setAmazonS3Client(credentialsProvider);
    }
    public void setAmazonS3Client(CognitoCachingCredentialsProvider credentialsProvider){

        // Create an S3 client
        s3 = new AmazonS3Client(credentialsProvider);

        // Set the region of your S3 bucket
        s3.setRegion(Region.getRegion(Regions.US_EAST_1));

    }

    public void setTransferUtility(){

        transferUtility = new TransferUtility(s3, getApplicationContext());
    }


    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            try {
                CropImageView.CropShape cropShape = CropImageView.CropShape.RECTANGLE;
                Uri uri = data.getData();
                CropImage.activity(uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        // .setFixAspectRatio(true)
                        .setCropShape(cropShape)
                        // .setAspectRatio(4,2)
                        .setMinCropResultSize(480,720)
                        .setMaxCropResultSize(800,1200)
                        .start(this);

                System.out.println("the uri is" + uri);

            } catch (Exception e) {

                Log.e(Tag, "Unable to upload file from the given uri", e);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                createOffer.setVisibility(View.VISIBLE);
                createOffer.setScaleType(ImageView.ScaleType.FIT_XY);
                createOffer.setImageURI(resultUri);
                String path = getPath(getApplicationContext(), resultUri);
                fileToUpload = new File(path);
                Log.e(Tag, "File path is " + path);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    public void transferObserverListener(TransferObserver transferObserver){

        transferObserver.setTransferListener(new TransferListener(){

            @Override
            public void onStateChanged(int id, TransferState state) {
                Log.i("statechange", state+"");

                Log.e("statechange Vluesss", state+"");
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                int percentage = (int) (bytesCurrent/bytesTotal * 100);
                Log.e("percentage",percentage +"");
            }

            @Override
            public void onError(int id, Exception ex) {
                Log.e("error","error");
            }

        });
    }
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());

    }
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
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

    private boolean canMakeSmores(){
        return(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP_MR1);
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
        keyName="siri/offers/M__"+ Util.getRandomNumbers()+"_"+System.currentTimeMillis();
        OfferDTO offerDTO = buildDTOObject();
        if(fileToDownload!=null) {
            Log.i(Tag,"the file upload values is"+fileToDownload);
            TransferObserver transferObserver = transferUtility.upload(
                    "divaz",     /* The bucket to upload to */
                    keyName,    /* The key for the uploaded object */

                    fileToUpload       /* The file where the data to upload exists */

            );

            transferObserverListener(transferObserver);
        }
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
        offerDTO.setImgePath(keyName);
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
