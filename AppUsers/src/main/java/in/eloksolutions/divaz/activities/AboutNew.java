package in.eloksolutions.divaz.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.gson.Gson;

import java.util.ArrayList;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.adapter.AndroidVersion;
import in.eloksolutions.divaz.dtoclasses.CompanyDTO;
import in.eloksolutions.divaz.helpers.CompanyUpdateHelper;
import in.eloksolutions.divaz.helpers.GetGalleryHelpers;
import in.eloksolutions.divaz.util.Config;

/**
 * Created by welcome on 5/12/2018.
 */

public class AboutNew extends AppCompatActivity {
    public static final String[] movies =  {"image one", "image two", "Image Three", "Image Four", "Image Five","Image Six"};
    public static int [] moviesImages ={R.drawable.offersmain,R.drawable.ic_menu_home,R.drawable.gallerybeauty,R.drawable.beaty,R.drawable.booknow,R.drawable.quickbook,R.drawable.gallerybeauty};
 ExpandableLayout expandableLayout;
    TextView hoursOfOperation;
    TextView name,location;
    String companyId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_new_layout);
        TextView myGallery=(TextView) findViewById(R.id.my_gallery);
        hoursOfOperation=(TextView) findViewById(R.id.text);
        name=(TextView) findViewById(R.id.company_name);
        location=(TextView) findViewById(R.id.company_location);
        companyId=getIntent().getStringExtra("companyId");
        CompanyUpdateHelper getGroupsValue=new CompanyUpdateHelper(this);
        String surl = Config.SERVER_URL+"company/"+companyId;
        System.out.println("url for services list"+surl);
        try {
            String output=getGroupsValue.new ServiceUpdateTask(surl).execute().get();
            System.out.println("the output from services"+output);
            setValuesToTextFields(output);
        }catch (Exception e){
            e.printStackTrace();
        }
        myGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(AboutNew.this,companyId);
            }
        });


    }


    public static void showAlertDialog(Context context,String companyId) {
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.about_dailog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        /*TextView text = (TextView) dialog.findViewById(R.id.email_d);
        text.setText(emailD);
        TextView text_title = (TextView) dialog.findViewById(R.id.phone_d);
        text_title.setText(phoneD);
        TextView wha = (TextView) dialog.findViewById(R.id.whats_d);
        wha.setText(whatsapp);
        TextView webs = (TextView) dialog.findViewById(R.id.website_d);
        webs.setText(websitD);*/
        /*RecyclerView mRecyclerView = (RecyclerView) dialog.findViewById(R.id.images_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext(),LinearLayoutManager.HORIZONTAL,true));
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(dialog.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ArrayList<AndroidVersion> av = prepareData();
        GalleryDataAdapter movies = new GalleryDataAdapter(dialog.getContext(), av);

        mRecyclerView.setAdapter(movies);*/
        RecyclerView services = (RecyclerView) dialog.findViewById(R.id.images_recycler);
        services.setLayoutManager(new LinearLayoutManager(dialog.getContext(),LinearLayoutManager.HORIZONTAL,true));
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(dialog.getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager lmPadi = new LinearLayoutManager(context);
        services.setLayoutManager(mLayoutManager);
        services.setLayoutManager(lmPadi);
        String url= Config.SERVER_URL+"gallery/getAll";
        GetGalleryHelpers getGroups=new GetGalleryHelpers(context,url,services,companyId);
        System.out.println("url for Company list"+url);
        getGroups.execute();
        /*mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(dialog.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        switch (i) {
                            case 0:


                                break;
                            case 1:


                                break;
                            case 2:


                            case 3:


                                break;
                            case 4:


                                break;
                            case 5:


                                break;

                        }
                    }
                })
        );*/

        TextView dialogButton = (TextView) dialog.findViewById(R.id.close);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void setValuesToTextFields(String result) {
        System.out.println("json xxxx from groupview" + result);
        if (result != null) {
            Gson gson = new Gson();
            CompanyDTO fromJsonn = gson.fromJson(result, CompanyDTO.class);
            name.setText(fromJsonn.getName());
            location.setText(fromJsonn.getAddress());
/*            imagePath=fromJsonn.getImgPath();
            if(fromJsonn.getImgPath()!=null) {
                glide.with(ProfileView.this).load(Config.IMG_AWS +fromJsonn.getImgPath()).diskCacheStrategy(DiskCacheStrategy.ALL).into(serviceimg);
            }else{
                glide.with(ProfileView.this).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(serviceimg);
            }*/


        }

    }
    private static ArrayList<AndroidVersion> prepareData() {


        ArrayList<AndroidVersion> av = new ArrayList<>();
        for (int i = 0; i < movies.length; i++) {
            AndroidVersion mAndroidVersion = new AndroidVersion();
            mAndroidVersion.setAndroidVersionName(movies[i]);
            mAndroidVersion.setrecyclerViewImage(moviesImages[i]);
            av.add(mAndroidVersion);
        }
        return av;
    }
}
