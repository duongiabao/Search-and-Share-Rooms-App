package HostAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprr.R;


import java.util.List;

import HostModel.Location;

public class AddHostAdapter extends RecyclerView.Adapter<AddHostAdapter.MyViewHolder> {
    Context context;
    List<Location> data;



    public AddHostAdapter(Context context, List<Location> data) {
        this.context = context;
        this.data = data;
        //this.data1= data1;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View itemView= layoutInflater.inflate(R.layout.list_location,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textLocate.setText(data.get(position).getLocation());
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //data1.add(new Location(data.get(position).getId(),data.get(position).getLocation()));

                Toast.makeText(context, "ID"+ data.get(position).getId(), Toast.LENGTH_SHORT).show();


            }
        });

         */

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textLocate;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            textLocate= itemView.findViewById(R.id.textlocate);


        }
    }
}
