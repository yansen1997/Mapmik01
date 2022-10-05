package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<DataModal> postList;
    Context context;

    public PostAdapter(Context context, List<DataModal> getAllPort){
        this.context = context;
        postList = getAllPort;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

//        DataModal post = postList.get(position);
        holder.name.setText(postList.get(position).getName());
        holder.code.setText(postList.get(position).getCode());
        holder.remarks.setText(postList.get(position).getRemarks());
        holder.longitude.setText(postList.get(position).getLongitude());
        holder.latitude.setText(postList.get(position).getLatitude());


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        TextView name, code, remarks, longitude, latitude;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nametxt);
            code = itemView.findViewById(R.id.codetxt);
            remarks = itemView.findViewById(R.id.remarkstxt);
            longitude = itemView.findViewById(R.id.longitudetxt);
            latitude = itemView.findViewById(R.id.latitudetxt);
        }
    }
}
