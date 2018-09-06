package com.color.download.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.color.bean.Hash;
import com.color.them.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by wanggaolei on 2018/9/5.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private List<Hash> data = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private ViewHolder mViewHolder;
    private OnItemClickListener listener;
    public RecyclerviewAdapter(Context context, List<Hash> data,OnItemClickListener listener) {
        this.data=data;
        mContext=context;
        inflater = LayoutInflater.from(mContext);
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null!=inflater){
           View view=inflater.inflate(R.layout.musiclist_layout,null);
           mViewHolder = new ViewHolder(view);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.musicName.setText(data.get(position).getFileName());
            holder.album.setText(data.get(position).getFileName()+"-"+data.get(position).getAlbumName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
         TextView musicName;
         TextView album;
         ViewHolder(View itemView) {
            super(itemView);
            musicName=itemView.findViewById(R.id.music_name);
            album=itemView.findViewById(R.id.album);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v);
                }
            });
        }


    }
    public  interface OnItemClickListener {
        void onItemClick(View view);

    }
}
