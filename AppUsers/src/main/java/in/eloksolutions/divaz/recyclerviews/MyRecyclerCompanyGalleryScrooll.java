package in.eloksolutions.divaz.recyclerviews;

import android.content.Context;
import android.content.Intent;
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
import in.eloksolutions.divaz.dataobjects.GalleryOBJ;
import in.eloksolutions.divaz.util.Config;


public class MyRecyclerCompanyGalleryScrooll extends RecyclerView
        .Adapter<MyRecyclerCompanyGalleryScrooll
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<GalleryOBJ> mDataset;
    private Context context;
    private static MyClickListener myClickListener;
    TextView keyName;
    String groupId, userId, firstName, lastName;
    CardView cardView;
    private List<CompanyOBJ> itemList;
    Glide glide;
    private static final int REQUEST_PHONE_CALL = 1;

    public MyRecyclerCompanyGalleryScrooll(ArrayList<GalleryOBJ> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    public MyRecyclerCompanyGalleryScrooll(Context context, List<CompanyOBJ> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder
             {
        TextView textView;
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
            textView = (TextView) itemView.findViewById(R.id.services);
            imageView = (ImageView) itemView.findViewById(R.id.Icon);
            Log.i(LOG_TAG, "Adding Listener");

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    GalleryOBJ dataObject = mDataset.get(getAdapterPosition());
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
                .inflate(R.layout.gallery_scrol, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        if(mDataset.get(position).getImagePath()!=null) {
            glide.with(context).load(Config.IMG_AWS + mDataset.get(position).getImagePath()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
            System.out.println("past from eventfromJson.gettime()" + mDataset.get(position).getImagePath());
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


    public void addItem(GalleryOBJ dataObj, int index) {
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