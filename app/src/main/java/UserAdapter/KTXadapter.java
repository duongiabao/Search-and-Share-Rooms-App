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

public class KTXadapter extends BaseAdapter {
    Context context;
    ArrayList<phongXT> arrKTX;

    public KTXadapter(Context context, ArrayList<phongXT> arrKTX) {
        this.context = context;
        this.arrKTX = arrKTX;
    }

    @Override
    public int getCount() {
        return arrKTX.size();
    }

    @Override
    public Object getItem(int position) {
        return arrKTX.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txtTenKTX,txtGia,txtMota;
        public ImageView imgKTX;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_ktx,null);
            viewHolder.txtTenKTX = (TextView) view.findViewById(R.id.textViewTenKTX);
            viewHolder.txtGia = (TextView) view.findViewById(R.id.textViewGiaKTX);
            viewHolder.txtMota = (TextView) view.findViewById(R.id.textViewMotaKTX);
            viewHolder.imgKTX = (ImageView) view.findViewById(R.id.imageViewListKTX);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //do data
        phongXT phong = (phongXT) getItem(position);
        viewHolder.txtTenKTX.setText(phong.getHome_name());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGia.setText("Giá : " +decimalFormat.format(phong.getHome_price())+ "Đ");
        viewHolder.txtMota.setMaxLines(2);
        viewHolder.txtMota.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMota.setText(phong.getHome_describe());
        Picasso.with(context).load(phong.getHome_img())
                .placeholder(R.drawable.aa2)
                .error(R.drawable.aa)
                .into(viewHolder.imgKTX);
        return view;
    }
}
