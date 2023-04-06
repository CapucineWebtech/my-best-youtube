package com.example.mybestyoutube.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mybestyoutube.R;
import com.example.mybestyoutube.pojos.YoutubeVideo;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(YoutubeVideo youtubeVideo);
    }
    private List<YoutubeVideo> youtubeVideos;
    private final OnItemClickListener listener;

    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDescription;

        public YoutubeVideoViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }

        public void bind(final YoutubeVideo youtubeVideo, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(youtubeVideo);
                }
            });
        }
    }

    public YoutubeVideoAdapter(List<YoutubeVideo> youtubeVideos, OnItemClickListener listener) {
        this.youtubeVideos = youtubeVideos;
        this.listener = listener;
    }

    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_video_item, parent, false);
        return new YoutubeVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeVideoViewHolder holder, int position) {
        YoutubeVideo youtubeVideo = youtubeVideos.get(position);
        holder.tvTitle.setText(youtubeVideo.getTitle());
        holder.tvDescription.setText(youtubeVideo.getDescription());
        holder.bind(youtubeVideos.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return youtubeVideos.size();
    }
}
