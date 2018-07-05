package in.eloksolutions.divaz.recyclerviews;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.activities.ApointMentUpdate;
import in.eloksolutions.divaz.activities.LoginActivity;
import in.eloksolutions.divaz.activities.ServiceLists;
import in.eloksolutions.divaz.dataobjects.BookingOBJ;
import in.eloksolutions.divaz.dataobjects.ServiceOBJ;
import in.eloksolutions.divaz.util.Config;


public class MyRecyclerViewApointMents extends RecyclerView
        .Adapter<MyRecyclerViewApointMents
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<BookingOBJ> mDataset;
    private Context context;
    public Dialog dialog;
    private static MyClickListener myClickListener;
    TextView keyName;
    String groupId, userId, firstName, lastName,companyId;
    RecyclerView rvGroups;
    private List<ServiceOBJ> itemList;
    Glide glide;


    public MyRecyclerViewApointMents(ArrayList<BookingOBJ> myDataset, String companyId, Context context) {
        mDataset = myDataset;
        this.context = context;
        this.companyId=companyId;
    }



    public class DataObjectHolder extends RecyclerView.ViewHolder
             {
        TextView personName;
        TextView itemName,itemPrice,date, time;


        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        ImageView imageView;
                 ImageView book,rating;

        public DataObjectHolder(final View itemView) {
            super(itemView);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            itemName = (TextView) itemView.findViewById(R.id.service_name);
            itemPrice=(TextView) itemView.findViewById(R.id.total);
            date = (TextView) itemView.findViewById(R.id.date);
            book=(ImageView) itemView.findViewById(R.id.book);
            rating=(ImageView) itemView.findViewById(R.id.trate);
            Log.i(LOG_TAG, "Adding Listener");
            imageView = (ImageView) itemView.findViewById(R.id.serive_image);

            /*imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + itemName.getText());
                    BookingOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    Intent groupView = new Intent(view.getContext(), ServiceView.class);
                    groupView.putExtra("serviceId", dataObject.getId());
                    context.startActivity(groupView);
                }
            });*/
           book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + personName.getText());
                    BookingOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    SharedPreferences preference=context.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE);
                    String user_id= preference.getString("userId",null);
                    if(user_id!=null && user_id.length()>0) {
                        Intent intent = new Intent(context, ApointMentUpdate.class);
                        intent.putExtra("companyId", companyId);
                        intent.putExtra("bookingId",dataObject.getId());
                        intent.putExtra("serviceName", dataObject.getOrderItems());
                        intent.putExtra("serviceDate", dataObject.getStrOrderDate());
                        intent.putExtra("servicprice", dataObject.getTotalPrice());
                        context.startActivity(intent);
                    }else {
                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.putExtra("companyId", companyId);
                        context.startActivity(intent);
                    }

                }
            });
            rating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(LOG_TAG, "Adding Listener " + personName.getText());
                    BookingOBJ dataObject = mDataset.get(getAdapterPosition());
                    Log.i(LOG_TAG, "data object is Listener" + dataObject);
                    //serviceDialogRating(context, dataObject.getCustomerId());
                }
            });

        }


    }
    public  void serviceDialog(final ServiceLists context, String id) {
        dialog = new Dialog(context,android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.contact_us);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        EditText textViewOne=(EditText) dialog.findViewById(R.id.name);
        EditText textViewTwo=(EditText) dialog.findViewById(R.id.email);
        EditText textViewThree=(EditText) dialog.findViewById(R.id.phone);
        TextView textViewFour=(TextView) dialog.findViewById(R.id.date);
        TextView textViewSix=(TextView) dialog.findViewById(R.id.time);

        dialog.show();
    }
    public  void serviceDialogRating(final ServiceLists context, String id) {
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
                .inflate(R.layout.appointment_list, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        if (mDataset.get(position).getOrderItems()!=null) {
            holder.personName.setText(mDataset.get(position).getCustomerName());
            Log.i("hh","The out put value of customer name"+mDataset.get(position).getCustomerName());
        }
        if (mDataset.get(position).getOrderItems()!=null) {
            holder.itemName.setText(mDataset.get(position).getOrderItems());
            holder.itemName.setSelected(true);
        }
        if (mDataset.get(position).getTotalPrice()!=null) {
            holder.itemPrice.setText("₹ "+mDataset.get(position).getTotalPrice());
        }
        if (mDataset.get(position).getStrOrderDate()!=null) {
            holder.date.setText(""+mDataset.get(position).getStrOrderDate());

        }
        if(mDataset.get(position).getCustomerId()!=null) {
            glide.with(context).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
           // glide.with(context).load(Config.IMG_AWS + mDataset.get(position).getTotalPrice()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
            System.out.println("past from eventfromJson.Services()" + mDataset.get(position).getCustomerId());
        }else{
            glide.with(context).load(R.drawable.sallon_two).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        }
        /*glide.with(context).load(Config.S3_URL+mDataset.get(position).getImgResource()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        if (mDataset.get(position).getMemberSize()!=0) {
            holder.label3.setText(mDataset.get(position).getMemberSize() + "Joined");
        }
        else {
            holder.label3.setText(  "0 Joined");
        }*/
    }


    public void addItem(BookingOBJ dataObj, int index) {
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