package com.example.sumitthakur.wission.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sumitthakur.wission.R;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by sumitthakur on 28/07/18.
 */

public class YoutubeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        mContext = parent.getContext();
        View itemView = li.inflate(R.layout.item_youtube, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private YouTubePlayerView youtube_view;

        public ItemViewHolder(View itemView) {
            super(itemView);
            youtube_view = itemView.findViewById(R.id.youtube_view);
        }
    }
}
