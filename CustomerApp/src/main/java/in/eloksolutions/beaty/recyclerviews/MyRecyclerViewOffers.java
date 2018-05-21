package in.eloksolutions.beaty.recyclerviews;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import in.eloksolutions.beaty.R;
import in.eloksolutions.beaty.activities.Consult;
import in.eloksolutions.beaty.activities.OfferView;
import in.eloksolutions.beaty.activities.OffersList;
import in.eloksolutions.beaty.dataobjects.OfferOBJ;
import in.eloksolutions.beaty.dataobjects.ServiceOBJ;
import in.eloksolutions.beaty.util.Config;


public class MyRecyclerViewOffers extends RecyclerView
        .Adapter<MyRecyclerViewOffers
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<OfferOBJ> mDataset;
    private OffersList context;
    public Dialog dialog;
    private static MyClickListener myClickListener;
    TextView keyName;
    String groupId, userId, firstName, lastName;
CardView cardView;
    private List<ServiceOBJ> itemList;
    Glide glide;


    public MyRecyclerViewOffers(ArrayList<OfferOBJ> myDataset, OffersList context) {
        mDataset = myDataset;
        this.context = context;

    }
    public MyRecyclerViewOffers(OffersList context, List<ServiceOBJ> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder
             {
        TextView itemName;
        TextView itemDescription,itemPrice,offerPrice;


        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        ImageView imageView;
        Button book,rating;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.service_name);
            itemDescription = (TextView) itemView.findViewById(R.id.service_of_rate);
            itemPrice=(TextView) itemView.findViewById(R.id.service_rate);
            offerPrice=(TextView) itemView.findViewById(R.id.service_of_rate);
            Log.i(LOG_TAG, "Adding Listener");
            imageView = (ImageView) itemView.findViewById(R.id.service_img);
            book=(Button) itemView.findViewById(R.id.book);
            rating=(Button) itemView.findViewById(R.id.trate);
            /*cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), OfferView.class);
                    groupView.putExtra("offerId", dataObject.getId());
                    view.getContext().startActivity(groupView);
                }
            });*/
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), OfferView.class);
                    groupView.putExtra("offerId", dataObject.getId());
                    context.startActivity(groupView);
                }
            });
            book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent intent=new Intent(context, Consult.class);
                    intent.putExtra("serviceName",dataObject.getName());
                    intent.putExtra("servicprice",dataObject.getBeforeOfferPrice());
                    context.startActivity(intent);


                }
            });
            rating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    serviceDialogRating(context, dataObject.getImgePath());
                }
            });
/*            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    OfferOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), OffersList.class);
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
    public  void serviceDialogRating(final OffersList context, String id) {
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
            holder.itemPrice.setText("Orignal : ₹"+mDataset.get(position).getBeforeOfferPrice());
            holder.itemPrice.setPaintFlags(holder.offerPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (mDataset.get(position).getOfferPrice()!=null) {
            holder.offerPrice.setText("Offer : ₹"+mDataset.get(position).getOfferPrice());

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