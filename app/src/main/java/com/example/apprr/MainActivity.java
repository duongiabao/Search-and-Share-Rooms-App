package com.example.apprr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import HostActivity.AddHostActivity;
import HostActivity.HostActivity;
import HostModel.Location;
import LoginActivity.HomeActivity;
import UserAdapter.loaiPhongAdapter;
import UserAdapter.location;
import UserAdapter.phongXTAdapter;
import Usermodel.LocationModel;
import Usermodel.loaiPhong;
import Usermodel.phongXT;
import util.checkCon;
import util.server;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewflipper;
    RecyclerView recyclerview;
    NavigationView navigationview;
    ListView listview;
    DrawerLayout drawerlayout;
    ArrayList<loaiPhong> arrloaip;

    loaiPhongAdapter loaiphongadapter;
    int id = 0;
    String tenLoaiP= "";
    String hinhAnhLoaiP ="";
    String User_ID;
    ArrayList<phongXT> arrPXT;
    phongXTAdapter PXTAdapter;

    FloatingActionButton floatingActionButton;

    ArrayList<LocationModel> arrL;
    location locationAdapter;
    RecyclerView recyclerviewL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        if(getIntent().getStringExtra("user_id")!= null) {
            User_ID = getIntent().getStringExtra("user_id");
            //Toast.makeText(this, "ccc", Toast.LENGTH_SHORT).show();
            Log.d("cminh",User_ID);
        }
        if(checkCon.haveNetworkConnection(getApplicationContext())) {
            actionBar();
            ActionViewFlipper();
            getDataLoaiP();
            getDataPhongXT();
            GetOnItemListView();
            addRoom();
            getLocation();
            getOnItemRecyclerView();
        }else{
            checkCon.showToast(getApplicationContext(),"Hay kiem tra lai ket noi");
            finish();
        }

    }

    private void getOnItemRecyclerView() {
        recyclerviewL.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, recyclerviewL ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this,LocationActivity.class);
                        intent.putExtra("location",arrL.get(position).getId());
                        intent.putExtra("nameLocation",arrL.get(position).getLocation());

                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    private void getLocation() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.linkLocation, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int Id = 0;
                    String Location = "";
                    String Image ="";
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Id = jsonObject.getInt("Id");
                            Location =jsonObject.getString("Location");
                            Image = jsonObject.getString("Image");
                            arrL.add(new LocationModel(Id,Location, Image));
                            locationAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void addRoom() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, HostActivity.class);
                intent.putExtra("user_id",User_ID);
                startActivityForResult(intent, 123);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            arrPXT.clear();
            getDataPhongXT();

        }
    }

    private void GetOnItemListView() {

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(checkCon.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            checkCon.showToast(getApplicationContext(),"Hãy kiểm tra lại kết nối");

                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(checkCon.haveNetworkConnection(getApplicationContext())){

                                Intent intent = new Intent(MainActivity.this,PhongActivity.class);
                                //do id loaip trung voi id case nen de position
                                intent.putExtra("idloaiPhong",arrloaip.get(position).getId());
                                startActivity(intent);


                        }else{
                            checkCon.showToast(getApplicationContext(),"Hãy kiểm tra lại kết nối");

                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(checkCon.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,KTXActivity.class);
                            //do id loaip trung voi id case nen de position
                            intent.putExtra("idloaiPhong",arrloaip.get(position).getId());
                            startActivity(intent);
                        }else{
                            checkCon.showToast(getApplicationContext(),"Hãy kiểm tra lại kết nối");

                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(checkCon.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,LienHeActivity.class);
                            startActivity(intent);
                        }else{
                            checkCon.showToast(getApplicationContext(),"Hãy kiểm tra lại kết nối");

                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(checkCon.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else{
                            checkCon.showToast(getApplicationContext(),"Hãy kiểm tra lại kết nối");

                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void getDataPhongXT() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.urlget, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int Id = 0;
                    String home_name="";
                    Integer home_price = 0;
                    String home_person ="";
                    String home_phone ="";
                    String home_describe ="";
                    int locate_id =0;
                    String home_img ="";
                    int idPhong =0;

                    for( int i=0; i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Id = jsonObject.getInt("Id");
                            home_name = jsonObject.getString("Home_name");
                            home_price = jsonObject.getInt("Home_price");
                            home_person = jsonObject.getString("Home_person");
                            home_phone = jsonObject.getString("Home_phone");
                            home_describe = jsonObject.getString("Home_describe");
                            locate_id = jsonObject.getInt("Locate_id");
                            home_img = jsonObject.getString("Home_img");
                            idPhong = jsonObject.getInt("Style_id");
                            arrPXT.add(new phongXT(Id,home_name,home_price,home_person,home_phone,home_describe,locate_id,home_img,idPhong));

                            PXTAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataLoaiP() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.urlstyle, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i =0; i <response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("Id");
                            tenLoaiP = jsonObject.getString("Stylehome");
                            hinhAnhLoaiP =jsonObject.getString("Styleimg");
                            arrloaip.add(new loaiPhong(id,tenLoaiP,hinhAnhLoaiP));
                            loaiphongadapter.notifyDataSetInvalidated();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    arrloaip.add(3, new loaiPhong(0,"Liên hệ","https://cdn0.iconfinder.com/data/icons/man-user-human-profile-business-avatar-person/100/07-1User_10-512.png"));
                    arrloaip.add(4,new loaiPhong(0,"Thông tin","https://cdn3.iconfinder.com/data/icons/user-group-black/100/user-information-512.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                checkCon.showToast(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> quangcao = new ArrayList<>();
        quangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTQE9mq4yb2LPxedU9TBDsueP4eimR3GwuVwlPP9kA8v2qlIloa");
        quangcao.add("https://photosrp.dotproperty.com.vn/2.0-VN-877661-PP-3835945-8929817015d15c1de13bde-1-490-325-ct/cho-thu%C3%AA-c%C4%83n-h%E1%BB%99-chung-c%C6%B0-1-ph%C3%B2ng-ng%E1%BB%A7-t%E1%BA%A1i-qu%E1%BA%ADn-7-h%E1%BB%93-ch%C3%AD-minh.jpg");
        quangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRN7Xk0y9PvuU1ey710BWiIOHOWKCxZDMaxqEUVJiYB1zZ9ykEa");
        for (int i =0; i<quangcao.size();i++) {
            ImageView imageview = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(quangcao.get(i)).into(imageview);
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            viewflipper.addView(imageview);
        }
        viewflipper.setFlipInterval(5000);
        viewflipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.slide_in_left);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.slide_out_right);
        viewflipper.setInAnimation(animation_slide_in);
        viewflipper.setOutAnimation(animation_slide_out);

    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                //luu y
                //floatingActionButton.setVisibility(View.INVISIBLE);
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        viewflipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        navigationview = (NavigationView) findViewById(R.id.navigationView);
        listview = (ListView) findViewById(R.id.listView);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        arrloaip = new ArrayList<>();
        arrloaip.add(0,new loaiPhong(0,"Trang chủ","https://i.pinimg.com/originals/b6/36/a8/b636a8185502da53da279ab02abdbece.png"));

        loaiphongadapter = new loaiPhongAdapter(arrloaip,getApplicationContext());
        listview.setAdapter(loaiphongadapter);

        arrPXT = new ArrayList<>();
        PXTAdapter = new phongXTAdapter(getApplicationContext(),arrPXT);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerview.setAdapter(PXTAdapter);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        recyclerviewL = (RecyclerView) findViewById(R.id.recyclerView1);
        arrL = new ArrayList<>();
        locationAdapter = new location(getApplicationContext(),arrL);
        recyclerviewL.setHasFixedSize(true);
        recyclerviewL.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        recyclerviewL.setAdapter(locationAdapter);
    }
    // on click recyclerView
    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }

}
