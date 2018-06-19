package in.eloksolutions.divaz.adapter;

/**
 * Created by Hi on 12/24/2016.
 */

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.dtoclasses.ServiceDTO;


public class ServiceDataAdapter extends RecyclerView.Adapter<ServiceDataAdapter.ViewHolder> {
    private ArrayList<ServiceDTO> arrayList;
    private Context mcontext;

    public ServiceDataAdapter(Context context, ArrayList<ServiceDTO> android) {
        this.arrayList = android;
        this.mcontext = context;
    }


    @Override
    public void onBindViewHolder(ServiceDataAdapter.ViewHolder holder, int i) {
        holder.textView.setText(arrayList.get(i).getName());
        holder.imageView.setImageResource(arrayList.get(i).getImage());
        holder.serviceRate.setText(arrayList.get(i).getPrice());
        holder.offerRate.setText(arrayList.get(i).getDiscount());
        holder.offerRate.setPaintFlags(holder.offerRate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public ServiceDataAdapter.ViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {

        View view = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.services_list, vGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private TextView serviceRate;
        private TextView offerRate;

        public ViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.service_name);
            imageView = (ImageView) v.findViewById(R.id.service_img);
            serviceRate = (TextView) v.findViewById(R.id.service_rate);
            offerRate = (TextView) v.findViewById(R.id.service_of_rate);
        }
    }

}