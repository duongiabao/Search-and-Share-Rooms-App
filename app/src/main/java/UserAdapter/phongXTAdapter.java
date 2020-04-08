package UserAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apprr.ChiTietPhong;
import com.example.apprr.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Usermodel.phongXT;
import util.checkCon;

public class phongXTAdapter extends RecyclerView.Adapter<phongXTAdapter.ItemHolder> {

    Context context;
    ArrayList<phongXT> arrphongXT;

    public phongXTAdapter(Context context, ArrayList<phongXT> arrphongXT) {
        this.context = context;
        this.arrphongXT = arrphongXT;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_phongxt,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        phongXT phongxt =arrphongXT.get(position);
        holder.txttenPhong.setText(phongxt.getHome_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiaPhong.setText("Giá : " +decimalFormat.format(phongxt.getHome_price())+ "Đ");
        Picasso.with(context).load(phongxt.getHome_img())
                    .placeholder(R.drawable.aa2)
                    .error(R.drawable.aa)
                    .into(holder.imgPhong);
    }

    @Override
    public int getItemCount() {
        return arrphongXT.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgPhong;
        public TextView txttenPhong,txtgiaPhong;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgPhong = itemView.findViewById(R.id.imageViewPhong);
            txtgiaPhong = itemView.findViewById(R.id.textViewGiaPhong);
            txttenPhong = itemView.findViewById(R.id.textViewTenPhong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ChiTietPhong.class);
                    intent.putExtra("thongtinphong",arrphongXT.get(getPosition()));
                    checkCon.showToast(context,arrphongXT.get(getPosition()).getHome_name());
                    context.startActivity(intent);
                }
            });
        }
    }

}
