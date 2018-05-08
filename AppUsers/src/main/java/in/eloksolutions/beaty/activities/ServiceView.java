package in.eloksolutions.beaty.activities;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import in.eloksolutions.beaty.MainActivity;
import in.eloksolutions.beaty.R;
import in.eloksolutions.beaty.dtoclasses.ServiceDTO;
import in.eloksolutions.beaty.helpers.ServiceViewHelper;
import in.eloksolutions.beaty.util.Config;


/**
 * Created by welcome on 6/27/2017.
 */

public class ServiceView extends AppCompatActivity {
    TextView name, description,serviceRate;
    ImageView gImage,imgView;
    public Dialog dialog;
    Spinner gCatagery;
    String groupId,tag="GroupUpDate";
    Button createGroup;
 String imagePath;
    private static int PICK_FROM_GALLERY;
    private int STORAGE_PERMISSION_CODE = 23;
    TransferUtility transferUtility;
    File fileToDownload = new File("/storage/sdcard0/Pictures/MY");
    AmazonS3 s3;
    String Tag="Create Offer";
String serviceId;
    String TAG="Create Group";
    String keyName,userId,userName;
    File fileToUpload;
    ImageView serviceimg;
    Glide glide;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_serviceview);
        getSupportActionBar().setTitle("Service View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        serviceId=getIntent().getStringExtra("serviceId");
        Log.i(tag, "serviceId for serviceUpdate is"+serviceId);
        name=(TextView) findViewById(R.id.group_view_title);
        description=(TextView) findViewById(R.id.description);
        serviceRate=(TextView) findViewById(R.id.offer_rate);
        imgView=(ImageView) findViewById(R.id.img_one);
        Button book=(Button) findViewById(R.id.book);
        Button rating=(Button) findViewById(R.id.trate);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(ServiceView.this, Consult.class);
                intent.putExtra("serviceName",name.getText().toString());
                intent.putExtra("servicprice",serviceRate.getText().toString());
                startActivity(intent);

            }
        });
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                serviceDialogRating(ServiceView.this, imagePath);


            }
        });

        final Context ctx = this;
        serviceimg=(ImageView) findViewById(R.id.group_image_view);
        serviceimg.setOnClickListener(new View.OnClickListener() {
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
        ServiceViewHelper getGroupsValue=new ServiceViewHelper(this);
        String surl = Config.SERVER_URL+"services/getService/"+serviceId;
        System.out.println("url for services list"+surl);
        try {
            String output=getGroupsValue.new ServiceUpdateTask(surl).execute().get();
            System.out.println("the output from services"+output);
            setValuesToTextFields(output);
        }catch (Exception e){
            e.printStackTrace();
        }


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
    public  void serviceDialogRating(final ServiceView context, String id) {
        dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.rating);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        EditText textViewOne=(EditText) dialog.findViewById(R.id.name);
        EditText textViewTwo=(EditText) dialog.findViewById(R.id.email);
        ImageView imageView=(ImageView) dialog.findViewById(R.id.service_img);
        RatingBar ratingbar1=(RatingBar)dialog.findViewById(R.id.ratingBar1);
        TextView textViewSix=(TextView) dialog.findViewById(R.id.submit);
        final TextView close=(TextView) dialog.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        glide.with(context).load(Config.IMG_AWS + id).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        dialog.show();
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
                serviceimg.setVisibility(View.VISIBLE);
                serviceimg.setScaleType(ImageView.ScaleType.FIT_XY);
                serviceimg.setImageURI(resultUri);
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
    public void setValuesToTextFields(String result) {
        System.out.println("json xxxx from groupview" + result);
        if (result != null) {
            Gson gson = new Gson();
            ServiceDTO fromJsonn = gson.fromJson(result, ServiceDTO.class);
            name.setText(fromJsonn.getName());
            serviceRate.setText(fromJsonn.getPrice());
            imagePath=fromJsonn.getImgePath();
            if(fromJsonn.getImgePath()!=null) {
                glide.with(ServiceView.this).load(Config.IMG_AWS +fromJsonn.getImgePath()).diskCacheStrategy(DiskCacheStrategy.ALL).into(serviceimg);
            }else{
                glide.with(ServiceView.this).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(serviceimg);
            }


        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
