
package com.example.apprr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import Usermodel.phongXT;

public class ChiTietPhong extends AppCompatActivity {
    Toolbar toolbarCT;
    ImageView imageViewCT;
    TextView txtTen,txtGia,txtMota,txtPhone,txtPerson;
    //tesst

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phong);
        Anhxa();
        ActionToolbar();
        getData();


    }

    private void getData() {
        int id = 0;
        String Ten ="";
        int Gia =0;
        String imgPhong ="";
        String mota ="";
        int IdPhong =0;
        String phone ="";
        String person ="";
        phongXT phongxl =(phongXT) getIntent().getSerializableExtra("thongtinphong");
        id = phongxl.getId();
        Ten = phongxl.getHome_name();
        Gia = phongxl.getHome_price();
        imgPhong = phongxl.getHome_img();
        mota = phongxl.getHome_describe();
        IdPhong = phongxl.getIdPhong();
        phone = phongxl.getHome_phone();
        person = phongxl.getHome_person();
        txtTen.setText(Ten);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá : " + decimalFormat.format(Gia)+ "Đ");
        txtMota.setText(mota);
        Picasso.with(getApplicationContext()).load(imgPhong)
                .placeholder(R.drawable.aa2)
                .error(R.drawable.aa)
                .into(imageViewCT);
        txtPhone.setText("Số điện thoại: " +phone);
        txtPerson.setText(person);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarCT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarCT = (Toolbar) findViewById(R.id.toolBarCTP);
        imageViewCT =(ImageView) findViewById(R.id.imageViewCTP);
        txtTen =    (TextView) findViewById(R.id.textViewTenCTP);
        txtGia =    (TextView) findViewById(R.id.textViewGiaCTP);
        txtMota =    (TextView) findViewById(R.id.textViewMotaCTP);
        txtPhone =    (TextView) findViewById(R.id.textViewPhone);
        txtPerson =    (TextView) findViewById(R.id.textViewPerson);
    }
}
