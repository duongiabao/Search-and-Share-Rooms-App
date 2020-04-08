package HostAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprr.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import HostActivity.HostActivity;
import HostActivity.UpdateHostActivity;
import HostModel.Host;

public class HostAdapter extends RecyclerView.Adapter<HostAdapter.MyViewHolder> {
    HostActivity context;
    List<Host> data;
    Host host;

    public HostAdapter(HostActivity context, List<Host> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View itemView= layoutInflater.inflate(R.layout.list_host,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txtname.setText(data.get(position).getName());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        holder.txtprice.setText(data.get(position).getPrice());
        holder.txtperson.setText(data.get(position).getPerson());
        holder.txtphone.setText(data.get(position).getPhone());
        /*
        Picasso.get()
                .load(data.get(position).getImg())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .into(imageView)

         */
        final Host host=data.get(position);
        // DUNG THU VIEN PICASSO DE LOAD URL TREN MANG VE DE TAO HINH ANH
        Picasso.with(context).load(host.getImg())
                .placeholder(R.drawable.aa2)
                .error(R.drawable.aa)
                .into(holder.imgHome);

        // BAT SU KIEN CUA NUT BUTTON DELETE DATA

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(data.get(position).getName(), data.get(position).getId());

            }
        });

        // BAT SU KIEN CUA NUT BUTTON UPDATE DATA

        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateHostActivity.class);
                intent.putExtra("dataHome", host);
                context.startActivity(intent);


                //Toast.makeText(context, "Update "+ data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHome, imgDelete, imgUpdate;
        TextView txtname, txtprice, txtperson, txtphone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHome= itemView.findViewById(R.id.imageView);
            txtname= itemView.findViewById(R.id.textname);
            txtprice= itemView.findViewById(R.id.textprice);
            txtperson= itemView.findViewById(R.id.textperson);
            txtphone= itemView.findViewById(R.id.textphone);
            imgDelete=itemView.findViewById(R.id.imageViewDelete);
            imgUpdate=itemView.findViewById(R.id.imageViewUpdate);

        }


    }
    private void Delete(String name, final int id){
        AlertDialog.Builder dialogDelete= new AlertDialog.Builder(context);
        dialogDelete.setMessage("Are you sure to delete "+name+" ?");
        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteHome(id);

            }
        });
        dialogDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogDelete.show();
    }




}
