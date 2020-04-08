
package UserAdapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apprr.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Usermodel.phongXT;

public class phongAdapter extends BaseAdapter {
    Context context;
    ArrayList<phongXT> arrPhong;

    public phongAdapter(Context context, ArrayList<phongXT> arrPhong) {
        this.context = context;
        this.arrPhong = arrPhong;
    }

    @Override
    public int getCount() {
        return arrPhong.size();
    }

    @Override
    public Object getItem(int position) {
        return arrPhong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txtTenPhong,txtGia,txtMota;
        public ImageView imgPhong;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_phong,null);
            viewHolder.txtTenPhong = (TextView) view.findViewById(R.id.textViewTenPhong);
            viewHolder.txtGia = (TextView) view.findViewById(R.id.textViewGiaPhong);
            viewHolder.txtMota = (TextView) view.findViewById(R.id.textViewMota);
            viewHolder.imgPhong = (ImageView) view.findViewById(R.id.imageViewListPhong);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //do data
        phongXT phong = (phongXT) getItem(position);
        viewHolder.txtTenPhong.setText(phong.getHome_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGia.setText("Giá : " +decimalFormat.format(phong.getHome_price())+ "Đ");
        viewHolder.txtMota.setMaxLines(2);
        viewHolder.txtMota.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMota.setText(phong.getHome_describe());
        Picasso.with(context).load(phong.getHome_img())
                .placeholder(R.drawable.aa2)
                .error(R.drawable.aa)
                .into(viewHolder.imgPhong);
        return view;
    }
}
