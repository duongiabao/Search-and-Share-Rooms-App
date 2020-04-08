package UserAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apprr.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Usermodel.loaiPhong;

public class loaiPhongAdapter extends BaseAdapter {
    ArrayList<loaiPhong> arrloaiPhong;
    Context context;

    public loaiPhongAdapter(ArrayList<loaiPhong> arrloaiPhong, Context context) {
        this.arrloaiPhong = arrloaiPhong;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrloaiPhong.size();
    }

    @Override
    public Object getItem(int position) {
        return arrloaiPhong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txttenloaiP;
        ImageView imgloaiP;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_listview_loaiphong,null);
            viewHolder.txttenloaiP = (TextView) view.findViewById(R.id.textViewlp);
            viewHolder.imgloaiP = (ImageView) view.findViewById(R.id.imageViewloaiP);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();

        }
        loaiPhong loaiphong = (loaiPhong) getItem(position);
        viewHolder.txttenloaiP.setText(loaiphong.getTenLoaiPhong());
        Picasso.with(context).load(loaiphong.getHinhAnhPhong())
                .placeholder(R.drawable.aa2)
                .error(R.drawable.aa)
                .into(viewHolder.imgloaiP);
        return view;
    }
}
