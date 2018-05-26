package in.eloksolutions.divaz.recyclers;

import android.content.Context;
import android.content.Intent;
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
import in.eloksolutions.divaz.activities.OfferView;
import in.eloksolutions.divaz.dataobjects.OfferOBJ;
import in.eloksolutions.divaz.dataobjects.ServiceOBJ;
import in.eloksolutions.divaz.util.Config;

public class MyRecyclerViewOffers extends RecyclerView
        .Adapter<MyRecyclerViewOffers
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<OfferOBJ> mDataset;
    private Context context;
    private static MyClickListener myClickListener;
    TextView keyName;
    String groupId, userId, firstName, lastName;
CardView cardView;
    private List<ServiceOBJ> itemList;
    Glide glide;


    public MyRecyclerViewOffers(ArrayList<OfferOBJ> myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;

    }
    public MyRecyclerViewOffers(Context context, List<ServiceOBJ> itemList) {
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
        Button edit;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemDescription = (TextView) itemView.findViewById(R.id.item_short_desc);
            itemPrice=(TextView) itemView.findViewById(R.id.item_price);
            cardView=(CardView) itemView.findViewById(R.id.cardlist_item);
           // FloatingActionButton createOffer=(FloatingActionButton) itemView.findViewById(R.id.fab);
            Log.i(LOG_TAG, "Adding Listener");
            edit=(Button)  itemView.findViewById(R.id.edit);
            imageView = (ImageView) itemView.findViewById(R.id.product_thumb);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), OfferView.class);
                    groupView.putExtra("offerId", dataObject.getId());
                    view.getContext().startActivity(groupView);
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), OfferView.class);
                    groupView.putExtra("offerId", dataObject.getId());
                    view.getContext().startActivity(groupView);
                }
            });
            /*edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), OfferUpdate.class);
                    groupView.putExtra("offerId", dataObject.getId());
                    view.getContext().startActivity(groupView);
                }
            });*/
           /* createOffer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), CreateOffer.class);
                    groupView.putExtra("offerId", dataObject.getId());
                    view.getContext().startActivity(groupView);
                }
            });*/

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
        if (mDataset.get(position).getBeforeOfferPrice()!=null) {
            holder.itemPrice.setText("Rs.  "+mDataset.get(position).getBeforeOfferPrice());
        }
        String imgPath=mDataset.get(position).getImgePath();
        if(imgPath!=null && !imgPath.trim().isEmpty() ) {
            glide.with(context).load(Config.IMG_AWS + mDataset.get(position).getImgePath()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
            System.out.println("past from eventfromJson.gettime()" + mDataset.get(position).getImgePath());
        }else{
            glide.with(context).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        }

    }


    public void addItem(OfferOBJ dataObj, int index) {
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