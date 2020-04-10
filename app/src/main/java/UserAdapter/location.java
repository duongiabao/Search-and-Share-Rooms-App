package UserAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprr.R;

import java.util.ArrayList;

import HostModel.Location;
import Usermodel.phongXT;


public class location extends RecyclerView.Adapter<location.ItemHolder> {

    Context context;
    ArrayList<Location> arrPQ;

    public location(Context context, ArrayList<Location> arrPQ) {
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
        Location location = arrPQ.get(position);
        holder.txtQuan.setText(location.getLocation());
    }

    @Override
    public int getItemCount() {
        return arrPQ.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView txtQuan;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txtQuan =(TextView) itemView.findViewById(R.id.textViewQ);
        }
    }

}
