package UserAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprr.ChiTietPhong;
import com.example.apprr.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import HostModel.Location;
import Usermodel.LocationModel;
import Usermodel.phongXT;
import util.checkCon;


public class location extends RecyclerView.Adapter<location.ItemHolder> {

    Context context;
    ArrayList<LocationModel> arrPQ;

    public location(Context context, ArrayList<LocationModel> arrPQ) {
        this.context = context;
        this.arrPQ = arrPQ;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_locate,null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        LocationModel location = arrPQ.get(position);
        holder.txtQuan.setText(location.getLocation());
        Picasso.with(context).load(location.getImage())
                .placeholder(R.drawable.aa2)
                .error(R.drawable.aa)
                .into(holder.imageL);
    }

    @Override
    public int getItemCount() {
        return arrPQ.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView txtQuan;
        public ImageView imageL;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txtQuan =(TextView) itemView.findViewById(R.id.textViewQ);
            imageL = itemView.findViewById(R.id.imageView);



        }
    }

}
