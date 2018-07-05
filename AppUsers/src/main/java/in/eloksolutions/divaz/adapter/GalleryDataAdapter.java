package in.eloksolutions.divaz.adapter;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.util.Config;


public class GalleryDataAdapter extends RecyclerView.Adapter<GalleryDataAdapter.ViewHolder> {
    private ArrayList<ImageDetails> arrayList;
    private Context mcontext;
    Glide glide;

    public GalleryDataAdapter(Context context, ArrayList<ImageDetails> android) {
        this.arrayList = android;
        this.mcontext = context;
    }


    @Override
    public void onBindViewHolder(GalleryDataAdapter.ViewHolder holder, int i) {
        holder.textView.setText(arrayList.get(i).getrecyclerViewTitleText());
        if(arrayList.get(i).getRecyclerViewImage()!=null && !arrayList.get(i).getRecyclerViewImage().isEmpty()) {
            glide.with(mcontext).load(Config.IMG_AWS + arrayList.get(i).getRecyclerViewImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        }else{
            glide.with(mcontext).load(R.drawable.beaty).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        }
    }

    @Override
    public GalleryDataAdapter.ViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {

        View view = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.gallery_scrol, vGroup, false);
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