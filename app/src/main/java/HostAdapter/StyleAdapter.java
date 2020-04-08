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

import HostModel.Style;

public class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.MyViewHolder> {
    Context context;
    List<Style> datas;


    public StyleAdapter(Context context, List<Style> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View itemView= layoutInflater.inflate(R.layout.list_style, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textStyle.setText(datas.get(position).getStyleHome());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textStyle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textStyle= itemView.findViewById(R.id.textstyle);
        }
    }
}
