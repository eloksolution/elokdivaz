package in.eloksolutions.divaz.adapter;

/**
 * Created by Hi on 12/24/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.dtoclasses.PackageItem;


public class AndroidDataAdapter extends RecyclerView.Adapter<AndroidDataAdapter.ViewHolder> {
    private ArrayList<PackageItem> serviceItems=new ArrayList<>();
    private Context mcontext;

    public ArrayList<PackageItem> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(ArrayList<PackageItem> serviceItems) {
        this.serviceItems = serviceItems;
    }

    public AndroidDataAdapter(Context context) {

        this.mcontext = context;
    }
public void addItem(String id, String name,String imagePath){
    Log.i("araayList","Package Item Values Name"+name+id);
    PackageItem androidVersion=new PackageItem(id,name,imagePath);
    serviceItems.add(androidVersion);
   this.notifyDataSetChanged();

}
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.textView.setText(serviceItems.get(i).getServiceName());
     // holder.imageView.setImageResource(arrayList.get(i).getrecyclerViewImage());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {

        View view = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.row, vGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return serviceItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View v) {
            super(v);
            textView =(TextView) v.findViewById(R.id.text);

            //imageView = (ImageView) v.findViewById(R.id.image);
        }
    }

}