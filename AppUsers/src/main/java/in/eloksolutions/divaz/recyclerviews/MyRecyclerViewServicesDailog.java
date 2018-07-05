package in.eloksolutions.divaz.recyclerviews;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.activities.Consult;
import in.eloksolutions.divaz.dataobjects.ServiceOBJ;
import in.eloksolutions.divaz.util.Config;


public class MyRecyclerViewServicesDailog extends RecyclerView
        .Adapter<MyRecyclerViewServicesDailog
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<ServiceOBJ> mDataset;
    private Consult mcontext;
    private static MyClickListener myClickListener;
    TextView keyName;
    String groupId, userId, firstName, lastName;
    CardView cardView;
    private List<ServiceOBJ> itemList;
    Glide glide;
    TextView servicePric,serviName;


    public MyRecyclerViewServicesDailog(ArrayList<ServiceOBJ> myDataset, Consult mcontext,TextView serviName,TextView servicePric) {
        mDataset = myDataset;
        this.mcontext = mcontext;
        this.serviName=serviName;
        this.servicePric=servicePric;
    }



    public class DataObjectHolder extends RecyclerView.ViewHolder
             {
        TextView itemName;
        TextView itemDescription,itemPrice;
        Button book;

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        ImageView imageView;
        Button edit;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.service_name);
            itemDescription = (TextView) itemView.findViewById(R.id.service_of_rate);
            itemPrice=(TextView) itemView.findViewById(R.id.service_rate);
            book=(Button) itemView.findViewById(R.id.book);
            book.setVisibility(View.GONE);
            Log.i(LOG_TAG, "Adding Listener");
            imageView = (ImageView) itemView.findViewById(R.id.service_img);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    ServiceOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    mcontext.dialog.dismiss();
                    serviName.setText(dataObject.getName());
                    servicePric.setText(dataObject.getPrice());
                   // mcontext.servicesAdapter.addItem(dataObject.getId(), dataObject.getName(),dataObject.getImgePath());
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
                .inflate(R.layout.services_list, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        if (mDataset.get(position).getName()!=null) {
            holder.itemName.setText(mDataset.get(position).getName());
        }
        if (mDataset.get(position).getDescription()!=null) {
            holder.itemDescription.setText(mDataset.get(position).getDescription());
        }
        if (mDataset.get(position).getPrice()!=null) {
            holder.itemPrice.setText("Rs.  "+mDataset.get(position).getPrice());
        }
        if(mDataset.get(position).getImgePath()!=null) {
            glide.with(mcontext).load(Config.IMG_AWS + mDataset.get(position).getImgePath()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
            System.out.println("past from eventfromJson.gettime()" + mDataset.get(position).getImgePath());
        }else{
            glide.with(mcontext).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        }
        /*glide.with(context).load(Config.S3_URL+mDataset.get(position).getImgResource()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        if (mDataset.get(position).getMemberSize()!=0) {
            holder.label3.setText(mDataset.get(position).getMemberSize() + "Joined");
        }
        else {
            holder.label3.setText(  "0 Joined");
        }*/
    }


    public void addItem(ServiceOBJ dataObj, int index) {
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