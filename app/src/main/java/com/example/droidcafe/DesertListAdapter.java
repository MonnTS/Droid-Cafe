package com.example.droidcafe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DesertListAdapter extends RecyclerView.Adapter<DesertListAdapter.DesertViewHolder> {

    private final Context mContext;
    private final LayoutInflater mInflator;
    private List<Desert> mDesertList;

    public interface DesertSelectedListener{
        void desertSelected(int position);
    }

    private DesertSelectedListener mListener;

    public DesertListAdapter(Context context) {
        mContext = context;
        mInflator = LayoutInflater.from(mContext);
        try {
            mListener = (DesertSelectedListener)context;
        } catch (ClassCastException cce){
            Log.e("DesertListAdapter", "Must implement DesertSelectedListener");
        }
    }

    @NonNull
    @Override
    public DesertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = mInflator.inflate(R.layout.desert_list_row, parent, false);
        return new DesertViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull DesertViewHolder holder, int position) {
        Desert desert = mDesertList.get(position);
        holder.desertName.setText(desert.name);
        holder.desertPrice.setText(desert.price.toString());
        holder.desertDescription.setText(desert.description);
        holder.position = position;

        switch (desert.id.intValue()){
            case 1: holder.desertImage.setImageResource(R.drawable.donut_circle);
                break;
            case 2: holder.desertImage.setImageResource(R.drawable.icecream_circle);
                break;
            case 3: holder.desertImage.setImageResource(R.drawable.froyo_circle);
                break;
            default: holder.desertImage.setImageResource(R.drawable.ic_question_mark);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(mDesertList == null)
            return 0;
        return mDesertList.size();
    }

    public void setDesertList(List<Desert> mDesertList) {
        this.mDesertList = mDesertList;
        notifyDataSetChanged();
    }


    public class DesertViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView desertImage;
        TextView desertName;
        TextView desertDescription;
        TextView desertPrice;
        int position;

        public DesertViewHolder(@NonNull View itemView) {
            super(itemView);
            desertImage = itemView.findViewById(R.id.desertImageView);
            desertName = itemView.findViewById(R.id.desertNameTextView);
            desertDescription = itemView.findViewById(R.id.desertDescriptionTextView);
            desertPrice = itemView.findViewById(R.id.desertPriceTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null)
                mListener.desertSelected(position);
        }
    }
}
