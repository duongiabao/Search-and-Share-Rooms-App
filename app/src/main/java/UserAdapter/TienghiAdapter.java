package UserAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprr.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Usermodel.TienNghi;

public class TienghiAdapter extends RecyclerView.Adapter<TienghiAdapter.MyViewHolder> {
    Context context;
    ArrayList<TienNghi> data;

    public TienghiAdapter(Context context, ArrayList<TienNghi> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View itemView= layoutInflater.inflate(R.layout.list_tiennghi, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTienNghi.setText(data.get(position).getTenTiennghi());
        Picasso.with(context).load(data.get(position).getHinhAnhTienNghi())
                .placeholder(R.drawable.aa2)
                .error(R.drawable.aa)
                .into(holder.imgTienNghi);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgTienNghi;
        public TextView txtTienNghi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTienNghi= itemView.findViewById(R.id.image_tien_nghi);
            txtTienNghi= itemView.findViewById(R.id.text_tien_nghi);

        }
    }
}
