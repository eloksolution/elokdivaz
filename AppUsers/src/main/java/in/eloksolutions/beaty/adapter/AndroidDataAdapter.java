package in.eloksolutions.beaty.adapter;

/**
 * Created by Hi on 12/24/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.eloksolutions.beaty.R;


public class AndroidDataAdapter extends RecyclerView.Adapter<AndroidDataAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> arrayList;
    private Context mcontext;

    public AndroidDataAdapter(Context context, ArrayList<AndroidVersion> android) {
        this.arrayList = android;
        this.mcontext = context;
    }
    public AndroidDataAdapter(Context context) {

        this.mcontext = context;
    }

    @Override
    public void onBindViewHolder(AndroidDataAdapter.ViewHolder holder, int i) {
        holder.textView.setText(arrayList.get(i).getrecyclerViewTitleText());
        holder.imageView.setImageResource(arrayList.get(i).getrecyclerViewImage());
    }

    @Override
    public AndroidDataAdapter.ViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {

        View view = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.gridview_layout, vGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.services);
            imageView = (ImageView) v.findViewById(R.id.Icon);
        }
    }

}