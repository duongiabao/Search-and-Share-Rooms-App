
package com.example.apprr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import UserAdapter.TienghiAdapter;
import Usermodel.TienNghi;
import Usermodel.phongXT;

public class ChiTietPhong extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    Toolbar toolbarCT;
    ImageView imageViewCT;
    TextView txtTen,txtGia,txtMota,txtPhone,txtPerson;
    FloatingActionButton floatingButtonPhone;

    RecyclerView recyclerView;
    ArrayList<TienNghi> data;
    TienghiAdapter adapter;
    //tesst

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phong);
        Anhxa();
        ActionToolbar();
        getData();
        genMockData();
        adapter= new TienghiAdapter(this,data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        floatingButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });


    }

    private void genMockData() {
        data= new ArrayList<TienNghi>();
        data.add(new TienNghi("May lanh","https://cdn4.iconfinder.com/data/icons/hotel-service-5/300/air_conditioner-512.png"));
        data.add(new TienNghi("WC rieng","https://images.vexels.com/media/users/3/152449/isolated/preview/343d8347c9c13556669231babad91b2d-flush-toilet-stroke-icon-by-vexels.png"));
        data.add(new TienNghi("Cho de xe","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTltQp12m9OWi_X5j0MUu2vskrzQS7Ku_R3fLopB95s_yRad2ux&usqp=CAU"));
        data.add(new TienNghi("Wifi","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS--2zRzbXoCzD0EaXek-JBUMyUgMc5Zyi7t4stTaYK1hbfq7Ac&usqp=CAU"));
        data.add(new TienNghi("Tu do","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQL5DTzpYQ6T0C5i6XwZSuTW1VPdwYhslkHV2FAZeTBsavqjMRK&usqp=CAU"));
        data.add(new TienNghi("gac lung ","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTNioFtn1pjlj-vlj8hsDSUN9OWixSPBq_IsXds-FAk5zaACgrX&usqp=CAU"));
        data.add(new TienNghi("Tu lanh","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRu1NCx0Se1eK_CQKHim3jNuHG3g9yYPy4E7VJJwFcsQYEYWLYO&usqp=CAU"));
        data.add(new TienNghi("May giac","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSS3_-pO5x5FQUB0SF2dcMF8pQLGV4NmtKA6dqw8s34Sjjap4TQ&usqp=CAU"));
        data.add(new TienNghi("An ninh","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ64bZjW7hvFKUcw73-JwcEZguwpj0HVfNAXQUwcIaMnRuvm5MF&usqp=CAU"));
        data.add(new TienNghi("Nha bep","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTPE8kh3D1SzaM0qYE26rQKVSuJcfyazkV1BB7vjwUgUaiOxp_d&usqp=CAU"));
        data.add(new TienNghi("Tivi","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR2PLC5rhwmQNHeBPGAtg6eXoC1Clx-EzIdCnF2ML9J8nw4Ko9S&usqp=CAU"));
        data.add(new TienNghi("Thu cung","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS2L9_ig9tAJR2iTPivflYFzUnJU2EY2uxk0bEk8NAVzCMc1iV2&usqp=CAU"));
        data.add(new TienNghi("Tu do","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTUiy2qgZFqHahG1xUc1uMwh4sfbfkmXwDBLqeGkrOgfVXIzr-7&usqp=CAU"));
        data.add(new TienNghi("Cua so","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTsdke8sRUPuOXzrTvWcbMDR5SZ0Mo5BwoRdffhfDrf9uM13k3h&usqp=CAU"));
        data.add(new TienNghi("May nuoc nong","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR-x8ad5SO40R3m2dW5CY-eBmFnx-CeRX0E_LFWGlKLivmzbqvn&usqp=CAU"));

    }

    private void makeCall() {
        phongXT phongxl =(phongXT) getIntent().getSerializableExtra("thongtinphong");
        String phoneCall=phongxl.getHome_phone();
        if(phoneCall.trim().length()>0){
            if(ContextCompat.checkSelfPermission(ChiTietPhong.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(ChiTietPhong.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            }
            else {
                String dial= "tel: " + phoneCall;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

            }

        }else {

        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CALL){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makeCall();
            }
            else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
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
        floatingButtonPhone= (FloatingActionButton) findViewById(R.id.float_button_chitiet);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerViewTienghi);
    }
}
