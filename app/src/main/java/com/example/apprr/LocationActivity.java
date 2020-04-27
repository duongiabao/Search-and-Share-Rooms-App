package com.example.apprr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import UserAdapter.LocationAdapter;
import UserAdapter.phongAdapter;
import Usermodel.phongXT;
import util.checkCon;
import util.server;

public class LocationActivity extends AppCompatActivity {
    Toolbar toolbarL;
    ListView listviewL;
    TextView textViewL;
    LocationAdapter locationAdapter;
    ArrayList<phongXT> arrL;
    int idL = 0;
    int page = 1;
    String nameLocation="";
    View footerView;
    boolean isLoading =false;
    mHandler mhandler;
    boolean limitdata = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Anhxa();


        if(checkCon.haveNetworkConnection(getApplicationContext())){
            getIdLocation();
            ActionToolBar();
            getData(page);
            LoadMoreData();
        }else {
            checkCon.showToast(getApplicationContext(),"Hãy kiểm tra lại kết nối");
            finish();
        }
        textViewL.setText(nameLocation);
    }

    private void LoadMoreData() {
        //chuyen man hinh
        listviewL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChiTietPhong.class);
                //do het data qua trang kia
                intent.putExtra("thongtinphong",arrL.get(position));
                startActivity(intent);
            }
        });
        //su kien keo
        listviewL.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // vuot den vi tri nao do
            }

            @Override
            //firstVisibleItem dong dau tien
            //visibleItemCount cac gia tri items co the nhin thay
            //totalItemCount tong cac items
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount , int totalItemCount) {
                if( firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount !=0 && isLoading == false && limitdata == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();

                }
            }
        });
    }

    private void getData(int Page) {
        //dua yc len cho server
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String link = server.linkGTLocation + String.valueOf(Page);
        //doc tat ca du lieu
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int Id = 0;
                String home_name="";
                Integer home_price = 0;
                String home_person ="";
                String home_phone ="";
                String home_describe ="";
                int locate_id =0;
                String home_img ="";
                int idPhong =0;
                //response hết dữ liệu sẽ trả ra cặp ngoặc vuông
                if(response != null && response.length() != 2){
                    listviewL.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i <jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Id = jsonObject.getInt("id");
                            home_name = jsonObject.getString("home_name");
                            home_price = jsonObject.getInt("home_price");
                            home_person = jsonObject.getString("home_person");
                            home_phone = jsonObject.getString("home_phone");
                            home_describe = jsonObject.getString("home_describe");
                            locate_id = jsonObject.getInt("locate_id");
                            home_img = jsonObject.getString("home_img");
                            idPhong = jsonObject.getInt("style_id");
                            arrL.add(new phongXT(Id,home_name,home_price,home_person,home_phone,home_describe,locate_id,home_img,idPhong));
                            locationAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    limitdata =true;
                    listviewL.removeFooterView(footerView);
                    checkCon.showToast(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String,String>();
                params.put("locate_id", String.valueOf(idL));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarL);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarL.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //get id
    private void getIdLocation() {
        idL = getIntent().getIntExtra("location",-1 );
        nameLocation = getIntent().getStringExtra("nameLocation");
        Log.d("giatriloaiphong",idL+"");

    }

    private void Anhxa() {
        toolbarL = (Toolbar) findViewById(R.id.toolBarLocation);
        listviewL = (ListView) findViewById(R.id.listViewLocation);
        textViewL = findViewById(R.id.textL);
        arrL = new ArrayList<>();
        locationAdapter = new LocationAdapter(getApplicationContext(),arrL);
        listviewL.setAdapter(locationAdapter);
        //gan layout
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.probar, null);
        mhandler = new mHandler();
    }
    public  class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    listviewL.addFooterView(footerView);
                    break;
                case 1:
                    getData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    //thread
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mhandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //obtainMessage lien ket thread voi handler
            Message message = mhandler.obtainMessage(1);
            mhandler.sendMessage(message);

            super.run();
        }
    }


}
