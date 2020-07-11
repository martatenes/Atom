package com.fractalmedia.codechallenge.atom.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fractalmedia.codechallenge.atom.R;
import com.fractalmedia.codechallenge.atom.models.Credit;
import com.fractalmedia.codechallenge.atom.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CreditAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public List<Credit> mCreditList;
    private Context mContext;
    private String baseUrl;

    public CreditAdapter(Context context){
        this.mContext = context;
        this.baseUrl =  Utils.getBaseUrlImages(context) + "w780";
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_item, parent, false);
        return new CreditViewHolder(view);
    }
    @Override
    public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder,int position){
        CreditViewHolder creditViewHolder = (CreditViewHolder) holder;
        creditViewHolder.tvCredit.setText(mCreditList.get(position).getName());
        String imagePath = mCreditList.get(position).getProfilePath();
        if (baseUrl != null && imagePath != null) {
            Picasso.get().load(this.baseUrl + imagePath).placeholder(R.drawable.progress_animation).error(R.drawable.ic_movie_placeholder).into(creditViewHolder.ivCredit);
        }
    }

    @Override
    public int getItemCount () {
        return mCreditList == null ? 0 : mCreditList.size();

    }

    public void setCreditList(List<Credit> creditList) {
        this.mCreditList = creditList;
        this.notifyDataSetChanged();
    }

    private static class CreditViewHolder  extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView ivCredit;
        public TextView tvCredit;

        public CreditViewHolder(final View itemView){
            super(itemView);
            this.cardView = itemView.findViewById(R.id.cardView);
            this.ivCredit = itemView.findViewById(R.id.ivCredit);
            this.tvCredit = itemView.findViewById(R.id.tvCredit);
        }
    }
}


