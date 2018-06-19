package in.eloksolutions.divaz.recyclerviews;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import in.eloksolutions.divaz.MainActivity;
import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.dataobjecs.CompanyOBJ;
import in.eloksolutions.divaz.util.Config;


public class MyRecyclerViewCompany extends RecyclerView
        .Adapter<MyRecyclerViewCompany
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<CompanyOBJ> mDataset;
    private Context context;
    private static MyClickListener myClickListener;
    TextView keyName;
    String groupId, userId, firstName, lastName;
    CardView cardView;
    private List<CompanyOBJ> itemList;
    Glide glide;
    private static final int REQUEST_PHONE_CALL = 1;

    public MyRecyclerViewCompany(ArrayList<CompanyOBJ> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;

    }
    public MyRecyclerViewCompany(Context context, List<CompanyOBJ> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder
             {
        TextView itemName;
        TextView itemDescription,itemPrice;


        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        ImageView imageView;
        TextView edit;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.prlour_name4);
            edit = (TextView) itemView.findViewById(R.id.call4);
            //itemDescription = (TextView) itemView.findViewById(R.id.prlour_rating4);
            itemPrice=(TextView) itemView.findViewById(R.id.prlour_rating4);
            itemDescription = (TextView) itemView.findViewById(R.id.prlour_address4);
            Log.i(LOG_TAG, "Adding Listener");

            imageView = (ImageView) itemView.findViewById(R.id.parlour);
            cardView=(CardView) itemView.findViewById(R.id.cardlist_item4);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    CompanyOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), MainActivity.class);
                    groupView.putExtra("companyId", dataObject.getId());
                    view.getContext().startActivity(groupView);
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CompanyOBJ dataObject = mDataset.get(getAdapterPosition());
                    Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91"+dataObject.getOfficePhone()));
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    }
                    else
                    {
                        context.startActivity(intent);
                    }

                }
            });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                CompanyOBJ dataObject = mDataset.get(getAdapterPosition());
                Log.i(LOG_TAG, "data object is Listener" + dataObject);
                Intent groupView = new Intent(view.getContext(), MainActivity.class);
                groupView.putExtra("companyId", dataObject.getId());
                view.getContext().startActivity(groupView);
            }
        });


        }


    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parlour_list, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        if (mDataset.get(position).getName()!=null) {
            holder.itemName.setText(mDataset.get(position).getName());
            Log.i("gg","mDataset.get(position).getSubject()"+mDataset.get(position).getName());
        }
        if (mDataset.get(position).getAddress()!=null) {
            holder.itemDescription.setText(mDataset.get(position).getAddress());
        }
        if (mDataset.get(position).getCreateDate()!=null) {
            holder.itemPrice.setText("Rs.  "+mDataset.get(position).getCreateDate());
        }
        if(mDataset.get(position).getImgPath1()!=null) {
            glide.with(context).load(Config.IMG_AWS + mDataset.get(position).getImgPath1()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
            System.out.println("past from eventfromJson.gettime()" + mDataset.get(position).getImgPath1());
        }else{
            glide.with(context).load(R.drawable.beaty).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        }
        /*glide.with(context).load(Config.S3_URL+mDataset.get(position).getImgResource()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        if (mDataset.get(position).getMemberSize()!=0) {
            holder.label3.setText(mDataset.get(position).getMemberSize() + "Joined");
        }
        else {
            holder.label3.setText(  "0 Joined");
        }*/
    }


    public void addItem(CompanyOBJ dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }
 
    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
 
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
 
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}