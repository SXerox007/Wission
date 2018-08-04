package com.example.sumitthakur.wission.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sumitthakur.wission.R;
import com.example.sumitthakur.wission.model.Item;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

/**
 * Created by sumitthakur on 28/07/18.
 */

public class YoutubeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Item> items;
    private onItemClicked itemClicked;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        mContext = parent.getContext();
        View itemView = li.inflate(R.layout.item_youtube, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Glide.with(mContext).load(items.get(position).getSnippet().getThumbnails().getDefault().getUrl())
                .into(itemViewHolder.ivVideoImg);
        itemViewHolder.tvTitle.setText(items.get(position).getSnippet().getTitle());
        itemViewHolder.tvDescription.setText(items.get(position).getSnippet().getDescription());
        itemViewHolder.llvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClicked.onItemClickedVideo(items.get(position).getId().getVideoId());
            }
        });
    }

    public void setData(List<Item> items, final onItemClicked itemClicked) {
        this.itemClicked = itemClicked;
        this.items = items;
    }


    public interface onItemClicked {

        void onItemClickedVideo(final String videoId);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivVideoImg;
        private AppCompatTextView tvTitle, tvDescription;
        private LinearLayoutCompat llvideo;

        ItemViewHolder(View itemView) {
            super(itemView);
            ivVideoImg = itemView.findViewById(R.id.ivVideoImg);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            llvideo = itemView.findViewById(R.id.llvideo);
        }
    }
}
